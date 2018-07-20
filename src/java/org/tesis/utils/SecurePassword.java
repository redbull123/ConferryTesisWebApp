package org.tesis.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author rjsan
 */
public final class SecurePassword {

    /**
     * Para evitar la instanciacion de esta clase
     */
    public SecurePassword(){}
    
    /**
     * Clase usada para encriptar el password del usuario
     * @param passwordToHash
     * @return 
     */
    public static String getPasswordHash(String passwordToHash) {
        String generatedPassword = null;
        String algorithm = "SHA-256";
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
}
