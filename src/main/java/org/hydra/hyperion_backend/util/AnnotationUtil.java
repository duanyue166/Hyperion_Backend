package org.hydra.hyperion_backend.util;

/**
 * Created with IntelliJ IDEA.
 * author： Shuowei Hou
 * date： 2024/10/16
 * description：
 */


import org.hydra.hyperion_backend.annotation.ValidState;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

public class AnnotationUtil {
    public static void updateAnnotationValue(Annotation annotation, String key, Object newValue) {
        try {
            InvocationHandler handler = Proxy.getInvocationHandler(annotation);
            Field memberValuesField = handler.getClass().getDeclaredField("memberValues");
            memberValuesField.setAccessible(true);
            Map<String, Object> memberValues = (Map<String, Object>) memberValuesField.get(handler);
            memberValues.put(key, newValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
