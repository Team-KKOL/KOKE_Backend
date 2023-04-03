package com.koke.koke_backend.common.security;

import com.koke.koke_backend.common.properties.EncryptProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Getter
@Component
public class EncryptUtil {

    private final String BLOCK_ALGORITHM = "AES";
    private final String CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";
    private final SecretKeySpec secretKeySpec;
    private final IvParameterSpec ivParameterSpec;
    private final EncryptProperty properties;

    public EncryptUtil(EncryptProperty property) {
        this.properties = property;
        this.secretKeySpec = new SecretKeySpec(property.getKey().getBytes(StandardCharsets.UTF_8), BLOCK_ALGORITHM);
        this.ivParameterSpec = new IvParameterSpec(property.getIv().getBytes(StandardCharsets.UTF_8));
    }

    public String encrypt(@NotBlank final String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptData = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(encryptData);
        } catch (NoSuchPaddingException e) {
            log.error("algorithm padding 값이 잘못 됨", e);
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            log.error("algorithm 을 찾을 수 없을 때 발생", e);
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            log.error("algorithm init 도중 Invalid 한 Parameter 입력시 발생", e);
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            log.error("암호화 key 가 Invalid 한 값일 경우 발생", e);
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            log.error("복호화 data 가 비정상 으로 만들어 진 값일 경우 발생", e);
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            log.error("블록 암호에 제공된 data 의 size 가 비 Invalid 한 경우 발생", e);
            throw new RuntimeException(e);

        }
    }

    public String decrypt(@NotBlank final String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decryptData = cipher.doFinal(cipherText.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(decryptData);
        } catch (NoSuchAlgorithmException e) {
            log.error("algorithm padding 값이 잘못 됨", e);
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            log.error("algorithm 을 찾을 수 없을 때 발생", e);
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            log.error("암호화 key 가 Invalid 한 값일 경우 발생", e);
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            log.error("algorithm init 도중 Invalid 한 Parameter 입력시 발생", e);
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            log.error("블록 암호에 제공된 data 의 size 가 비 Invalid 한 경우 발생", e);
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            log.error("복호화 data 가 비정상 으로 만들어 진 값일 경우 발생", e);
            throw new RuntimeException(e);
        }
    }

}
