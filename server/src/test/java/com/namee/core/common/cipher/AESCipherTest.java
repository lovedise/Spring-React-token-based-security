package com.namee.core.common.cipher;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by namee on 2016. 6. 21..
 */
public class AESCipherTest {
    AESCipher aesCipher;

    @Before
    public void setUp(){
        aesCipher = new AESCipher();
    }

    @Test
    public void test_encode() {
        String ip = aesCipher.encode("127.0.0.1");
        String username = aesCipher.encode("root");
        String passowrd = aesCipher.encode("lovedise79");
        String dbname = aesCipher.encode("util");

        System.out.println("encode ip : " + ip);
        System.out.println("encode username : " + username);
        System.out.println("encode password : " + passowrd);
        System.out.println("encode dbname : " + dbname);
    }

    @Test
    public void test_decode() {

    }
}