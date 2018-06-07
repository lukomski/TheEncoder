package com.example.jan.theencoder;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PlayfairCipherTest {
    @Test
    public void encrypt() {
        PlayfairCipher tester = new PlayfairCipher();
        assertEquals("elffa", tester.encrypt("hello", "HELOABCDFGIKMNPQRSTUVWXYZ"));
        assertEquals("eutPBTbt;123", tester.encrypt("kraSFAfa;123", "anetka"));
        assertEquals("GQRFDFG", tester.encrypt("KOTECEK", "ALUSIA"));
        assertEquals("frbamltyb", tester.encrypt("alamakota", "mama"));
        assertEquals("OfOfakqvfHfHsC", tester.encrypt("TaTachowaLaLkE", "przedMama"));
        assertEquals("Please enter the correct key", tester.encrypt("ąęć", "ĄŚĆ"));
        assertEquals("( ͡° ͜ʖ ͡° )  ozpqr://ydd.znzpmic.kia/zxqhc?z=1sz3xBV8MzH", tester.encrypt("( ͡° ͜ʖ ͡° )  https://www.youtube.com/watch?v=1ty3mFU8EtA", "MEMEXD"));
    }

    @Test
    public void decrypt() {
        PlayfairCipher tester = new PlayfairCipher();
        assertEquals("hello", tester.decrypt("elffa", "HELOABCDFGIKMNPQRSTUVWXYZ"));
        assertEquals("kraSFAfa;123", tester.decrypt("eutPBTbt;123", "anetka"));
        assertEquals("KOTECEB", tester.decrypt("GQRFDFG", "ALUSIA"));
        assertEquals("alamakota", tester.decrypt("frbamltyb", "mama"));
        assertEquals("TaTachowaLaLkE", tester.decrypt("OfOfakqvfHfHsC", "przedMama"));
        assertEquals("Please enter the correct key", tester.decrypt("Please enter the correct key", "ĄŚĆ"));
        assertEquals("( ͡° ͜ʖ ͡° )  https://www.youtube.com/watch?v=1ty3mFU8EtA", tester.decrypt("( ͡° ͜ʖ ͡° )  ozpqr://ydd.znzpmic.kia/zxqhc?z=1sz3xBV8MzH", "MEMEXD"));
    }
}
