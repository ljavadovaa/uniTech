package com.project.unitech.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class PasswordUtil {

    // The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int ITERATIONS = 20 * 1000;
    private static final int DESIRED_KEY_LENGTH = 256;

    // using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
    // cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
    public String hash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (Objects.isNull(password) || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        if (Objects.isNull(salt))
            salt = new byte[16];
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, ITERATIONS, DESIRED_KEY_LENGTH));
        return Base64.encodeBase64String(key.getEncoded());
    }

}
