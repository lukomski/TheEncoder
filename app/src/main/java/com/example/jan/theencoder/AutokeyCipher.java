package com.example.jan.theencoder;

public class AutokeyCipher implements Cipher {

    private boolean isEnglishLetter( char letter ){
        return (letter >= 'A' && letter <='Z') || (letter>='a' && letter <='z');
    }
    private char encryptChar( char messChar, char keyChar)
    {
        char gap = 'z' - 'Z';
        boolean small = Character.isLowerCase(messChar);

        if (Character.isLowerCase(messChar)) messChar -= gap;
        if (Character.isLowerCase(keyChar)) keyChar -= gap;
        char result = (char) (messChar + keyChar);

        result = (char) ((result % 26) + 'A');
        if (small) result += gap;

        return result;
    }

    private char decryptChar( char messChar, char keyChar)
    {
        char gap = 'z' - 'Z';
        boolean small = Character.isLowerCase(messChar);

        if (Character.isLowerCase(messChar)) messChar -= gap;
        if (Character.isLowerCase(keyChar)) keyChar -= gap;
        int result = (messChar - keyChar);
        if (result < 0) result += 26;
        result += 'A';
        if (small) result += gap;

        return (char)(result);
    }


	public String encrypt(String message, String key)
	{
	    // checking correctness of input
		if(badKey(key))
			return "Please enter the correct key";

        // data structure
		StringBuilder encryptedMessage = new StringBuilder(); // encrypted message
		StringBuilder generatedKey = new StringBuilder(); // encrypted message

        generatedKey.append(key);
        for (int i = 0; i < message.length(); i++)
            if (isEnglishLetter(message.charAt(i)))
                generatedKey.append(message.charAt(i));

	    int keyIterator = 0;
        char letter;

	    // encrypt
		for(int i = 0; i < message.length(); i++) {

		    // handle non-letter characters. It does not encrypt it.
	        if(!isEnglishLetter(message.charAt(i))) {
	            encryptedMessage.append(message.charAt(i));
	            continue; }

            letter = encryptChar(message.charAt(i), generatedKey.charAt(keyIterator));
	        keyIterator++;

			encryptedMessage.append(letter);
		}
		return encryptedMessage.toString();
	}


	public String decrypt(String message, String key)
	{
		if(badKey(key))
			return "Please enter the correct key";

		StringBuilder decryptedMessage = new StringBuilder(); // decrypted message
		StringBuilder generatedKey = new StringBuilder(); // autokey
        generatedKey.append(key);

        int keyIterator = 0;
        char letter;

		for(int i = 0; i < message.length(); i++) {

            if(!isEnglishLetter(message.charAt(i))) {
                decryptedMessage.append(message.charAt(i));
                continue; }

            letter = decryptChar(message.charAt(i), generatedKey.charAt(keyIterator));
            keyIterator++;

            decryptedMessage.append(letter);
            generatedKey.append(letter);
        }

		return decryptedMessage.toString();
	}

}

