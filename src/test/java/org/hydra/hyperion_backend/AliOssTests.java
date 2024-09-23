package org.hydra.hyperion_backend;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.hydra.hyperion_backend.config.AppConfig;
import org.hydra.hyperion_backend.util.AliOssUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class AliOssTests {
    @FunctionalInterface
    public interface ThrowableRunnable {
        void run() throws Exception;
    }

    @Autowired
    private AppConfig appConfig;

    private static OSS ossClient;
    private static String bucketName;

    @BeforeEach
    public void prepare() {
        String endpoint = appConfig.getAliyun().getEndpoint();
        String accessKeyId = appConfig.getAliyun().getAccessKeyId();
        String accessKeySecret = appConfig.getAliyun().getAccessKeySecret();
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        bucketName = appConfig.getAliyun().getBucketName();
    }

    @AfterEach
    public void tearDown() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }

    private void doWithCatch(ThrowableRunnable function) {
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

    @Test
    public void testUpload() {
        doWithCatch(() -> {
            String objectName = "test.txt";
            String content = "Hello OSS\n1st\n2nd\n3rd";
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
        });
    }

    @Test
    public void testDownload() {
        doWithCatch(() -> {
            String objectName = "test.txt";
            InputStream is = ossClient.getObject(bucketName, objectName).getObjectContent();
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(is));
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        });
    }

    @Test
    public void testList() {
        doWithCatch(() -> {
            ossClient.listObjects(bucketName).getObjectSummaries().forEach(summary -> {
                System.out.println(summary.getKey());
            });
        });
    }

    @Test
    public void testDelete() {
        doWithCatch(() -> {
            String objectName = "test.text";
            ossClient.deleteObject(bucketName, objectName);
        });
    }

    @Test
    public void testUploadFile() {
        doWithCatch(() -> {
            String objectName = "pics/pic.png";
            String filePath = "/home/dy/Pictures/wallhaven-01qxv3.png";
            ossClient.putObject(bucketName, objectName, new java.io.File(filePath));
        });
    }

    @Test
    public void testDownloadFile() {
        doWithCatch(() -> {
            //delete if exists
            Path path = Paths.get("src/test/resources/downloads/pic.png");
            java.nio.file.Files.deleteIfExists(path);

            String objectName = "pics/pic.png";
            InputStream is = ossClient.getObject(bucketName, objectName).getObjectContent();
            java.nio.file.Files.copy(is, path);
        });
    }

    @Test
    public void testUtil() {
        String url = AliOssUtil.uploadFile("testUtil.txt", new ByteArrayInputStream("Hello OSS".getBytes()));
        System.out.println(url);
    }
}