package com.step.forum.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

    private static String output;

    public static String inputToHash(String input) {
        input = inputToSha(input);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] outputBytes = md.digest(output.getBytes());
            output = DatatypeConverter.printBase64Binary(outputBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return output;
    }

    private static String inputToSha(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] outputBytes = md.digest(input.getBytes());
            output = DatatypeConverter.printBase64Binary(outputBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return output;

    }
}
