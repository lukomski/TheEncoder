package com.example.jan.theencoder;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VigenereCipherTest {
    @Test
    public void encrypt() {
        VigenereCipher tester = new VigenereCipher();

        assertEquals("oiwwc", tester.encrypt("hello", "hello"));
        assertEquals("momcmqtaicx", tester.encrypt("thisisatest", "THEKEY"));
        assertEquals("OiWWc33", tester.encrypt("HeLLo33", "hello"));
        assertEquals("dirbeebekweryR", tester.encrypt("tetragrammatoN", "KEY"));
        assertEquals("zlhptxlnlovztjl", tester.encrypt("gedelescherbach", "THELITTLEKEY"));
        assertEquals("AiqiiMcaaaMqqiJSNSDHHJAE", tester.encrypt("NeverGonnaGiveLETYOUDOWN", "NEVERGONNAGIVEYOUUP"));
        assertEquals("Uhywinymbqabsiavpw?", tester.encrypt("Ihavenoideadreally?", "MAYBEAKEY"));
    }

    @Test
    public void decrypt() {
        VigenereCipher tester = new VigenereCipher();

        assertEquals("hello", tester.decrypt("oiwwc", "hello"));
        assertEquals("thisisatest", tester.decrypt("momcmqtaicx", "THEKEY"));
        assertEquals("HeLLo33", tester.decrypt("OiWWc33", "hello"));
        assertEquals("tetragrammatoN", tester.decrypt("dirbeebekweryR", "KEY"));
        assertEquals("gedelescherbach", tester.decrypt("zlhptxlnlovztjl", "THELITTLEKEY"));
        assertEquals("NeverGonnaGiveLETYOUDOWN", tester.decrypt("AiqiiMcaaaMqqiJSNSDHHJAE", "NEVERGONNAGIVEYOUUP"));
        assertEquals("Ihavenoideadreally?", tester.decrypt("Uhywinymbqabsiavpw?", "MAYBEAKEY"));
    }
}
