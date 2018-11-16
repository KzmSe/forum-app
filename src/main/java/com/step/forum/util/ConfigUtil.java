package com.step.forum.util;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

    private static Properties properties = new Properties();
    private static final String IMAGE_UPLOAD_PATH = "image.upload.path";

    static {
        try {
            properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream("conf.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConfigUtil() {

    }

    public static String getImageUploadPath() {
        return properties.getProperty(IMAGE_UPLOAD_PATH);
    }

}
