package com.technumen.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    Map<String, String> DB = new HashMap<String, String>();
    public static final String SALT = "technumen";

    public void signup(String username, String password) throws Exception {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        DB.put(username, hashedPassword);
    }

    public Boolean login(String username, String password) throws Exception {
        Boolean isAuthenticated = false;
        // remember to use the same SALT value use used while storing password
        // for the first time.
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);

        String storedPasswordHash = DB.get(username);
        if (hashedPassword.equals(storedPasswordHash)) {
            isAuthenticated = true;
        } else {
            isAuthenticated = false;
        }

        return isAuthenticated;
    }

    public static String generateHash(String input) throws Exception {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }

        return hash.toString();
    }

}