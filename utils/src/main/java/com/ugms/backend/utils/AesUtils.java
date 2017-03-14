package com.ugms.backend.utils;

import com.ugms.backend.bean.exception.UgmsStatus;
import com.ugms.backend.bean.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by roy on 2017/3/8.
 */
@Component
public class AesUtils {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${ugms.aes.algorithm}")
    private String algorithm;

    private static final byte[] ivSchema = {
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0
    };
	private static final byte[] ivSoftwareUuid = {
			1, 0, 0, 1,
			0, 1, 1, 0,
			0, 1, 1, 0,
			1, 0, 0, 1
	};

	public byte [] encrypt(byte[] data, byte[] key, byte[] iv) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, "AES");
			Cipher aesCipher = Cipher.getInstance(algorithm);
			aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
			return aesCipher.doFinal(data);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {

			throw new UserException(UgmsStatus.INTERNAL_ERROR, e.getMessage());
		}
	}

    public byte[] encryptSchema(byte[] data, String keyStr) throws UserException {
	    return encrypt(data, keyStr.getBytes(), ivSchema);
    }
	public byte[] encryptToken(byte[] data, String keyStr) throws UserException {
		return encrypt(data, keyStr.getBytes(), ivSchema);
	}
	public byte[] decryptToken(byte[] data, String keyStr) throws UserException {
		return decrypt(data, keyStr.getBytes(), ivSchema);
	}
	public byte[] decryptSoftwareUuid(byte[] data, byte[] key) {
		return decrypt(data, key, ivSoftwareUuid);
	}
    public byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws UserException {
        try {
            SecretKey secretKey = new SecretKeySpec(key, "AES");
            Cipher aesCipher = Cipher.getInstance(algorithm);
            aesCipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            return aesCipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            throw new UserException(UgmsStatus.INTERNAL_ERROR, e.getMessage());
        }
    }


}
