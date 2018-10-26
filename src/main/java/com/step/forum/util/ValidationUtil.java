package com.step.forum.util;

public class ValidationUtil {

    public static boolean validate(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }

        return true;
    }

}
