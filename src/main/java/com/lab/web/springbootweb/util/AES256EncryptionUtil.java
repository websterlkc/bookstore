package com.lab.web.springbootweb.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

public class AES256EncryptionUtil {

    // AES-256 requires a 32-byte key (256 bits)
    private static final int KEY_SIZE = 256;
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String PROVIDER = "BC"; // BouncyCastle provider

    static {
        // Register BouncyCastle as the security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    // Generate a new AES-256 key
//    public static SecretKey generateKey() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM, PROVIDER);
//        keyGenerator.init(KEY_SIZE);
//        return keyGenerator.generateKey();
//    }

    // Encrypt a plain text using AES-256
    public static String encrypt(String plainText, SecretKey secretKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM, PROVIDER);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    // Decrypt a cipher text using AES-256
    public static String decrypt(String cipherText, SecretKey secretKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM, PROVIDER);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

//    public static void main(String[] args) throws Exception {
//        // Example usage
//
//        // Generate AES-256 key
//        SecretKey secretKey = generateKey();
//
//        // Initialization vector (IV) for CBC mode (16 bytes for AES)
//        byte[] iv = new byte[16]; // You can generate a random IV for security or use a constant one
//        IvParameterSpec ivSpec = new IvParameterSpec(iv);
//
//        String originalText = "This is a secret message";
//        System.out.println("Original Text: " + originalText);
//
//        // Encrypt the text
//        String encryptedText = encrypt(originalText, secretKey, iv);
//        System.out.println("Encrypted Text: " + encryptedText);
//
//        // Decrypt the text
//        String decryptedText = decrypt(encryptedText, secretKey, iv);
//        System.out.println("Decrypted Text: " + decryptedText);
//    }
}
