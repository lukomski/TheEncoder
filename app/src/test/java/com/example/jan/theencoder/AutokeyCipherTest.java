package com.example.jan.theencoder;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AutokeyCipherTest {
    @Test
    public void encrypt() {
        Cipher tester =  new AutokeyCipher();
        assertEquals("oiwwc", tester.encrypt("hello", "hello"));
        assertEquals("jtwqppqofoe", tester.encrypt("elemelekuku", "fis"));
        assertEquals("___", tester.encrypt("___", "freee"));
        assertEquals("gggggggggghhhtttckkkkkkkkhhhszi", tester.encrypt("wwwwwwwwwwwwwiiiiiiiiiiiiiiitam", "kkkkkkkkkkllllllucccccccczzzzz"));
        assertEquals("łębfłyrę", tester.encrypt("łękołodę", "rr"));
    }

    @Test
    public void decrypt() {
        Cipher tester =  new AutokeyCipher();
        assertEquals("hello", tester.decrypt("oiwwc", "hello"));
        assertEquals("elemelekuku", tester.decrypt("jtwqppqofoe", "fis"));
        assertEquals("___", tester.decrypt("___", "freee"));
        assertEquals("wwwwwwwwwwwwwiiiiiiiiiiiiiiitam", tester.decrypt("gggggggggghhhtttckkkkkkkkhhhszi", "kkkkkkkkkkllllllucccccccczzzzz"));
        assertEquals("łękołodę", tester.decrypt("łębfłyrę", "rr"));

    }
}
