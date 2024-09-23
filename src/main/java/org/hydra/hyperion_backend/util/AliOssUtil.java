package org.hydra.hyperion_backend.util;

import com.aliyun.oss.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.hydra.hyperion_backend.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;

@Component
public class AliOssUtil {
    @FunctionalInterface
    private interface ThrowableRunnable {
        void run() throws Exception;
    }

    @Autowired
    private AppConfig appConfig;

    private static String endpoint;
    private static OSS ossClient;
    private static String bucketName;

    @PostConstruct
    public void init() {
        endpoint = appConfig.getAliyun().getEndpoint().replaceFirst("^https://", "");
        String accessKeyId = appConfig.getAliyun().getAccessKeyId();
        String accessKeySecret = appConfig.getAliyun().getAccessKeySecret();
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        bucketName = appConfig.getAliyun().getBucketName();
    }

    @PreDestroy
    public void destroy() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }

    private static void doWithCatch(ThrowableRunnable function) {
        try {
            function.run();
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, " + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered " + "a serious internal problem while trying to communicate with OSS, " + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String uploadFile(String filename, InputStream is) {
        doWithCatch(() -> ossClient.putObject(bucketName, filename, is));
        return "https://" + bucketName + "." + endpoint + "/" + filename;
    }
}
