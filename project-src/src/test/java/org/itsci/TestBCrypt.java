package org.itsci;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestBCrypt {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encrypted = bCryptPasswordEncoder.encode("1234");
        System.out.println("Encrypt: " + encrypted);
//        CreateWordDocument.test();
    }
}
