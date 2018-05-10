package com.example.jan.theencoder;

public class VigenereCipher implements Cipher {

    public String encrypt(String message, String key)
    {
    	 if(badKey(key))
             return "Please enter the correct key";
    	 
        String result = "";
        //message = message.toLowerCase();
        
        for (int i = 0, j = 0; i < message.length(); i++)
        {
            char letter = message.charAt(i);
            if (Character.isLetter(letter)){
                if(Character.isUpperCase(letter)) {
                    result += (char) ((letter + key.toUpperCase().charAt(j) - 2 * 'A') % 26 + 'A');

                } else {
                    result += (char) ((letter + key.toLowerCase().charAt(j) - 2 * 'a') % 26 + 'a');

                }
            } else {
                result+=letter;
            }
            j = ++j % key.length();
        }
        return result;
    }

    public String decrypt(String cipherText, String key)
    {
        if(badKey(key))
            return "Please enter the correct key";
        
        StringBuilder result = new StringBuilder("");
        
        char letter;
        
        for(int i = 0, j = 0; i < cipherText.length(); i++){
            
            letter = cipherText.charAt(i);
            if (Character.isLetter(letter)){
                if(Character.isUpperCase(letter)) {
                    result.append((char)('Z'-(25-(letter-key.toUpperCase().charAt(j)))%26));

                } else {
                    result.append((char)('z'-(25-(letter-key.toLowerCase().charAt(j)))%26));

                }
            } else {
                result.append(letter);
            }
            j = ++j % key.length();
        }
        return result.toString();
    }


}