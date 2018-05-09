package com.example.jan.theencoder;

public class AutokeyCipher implements Cipher {

	private boolean isBigLetter( char c ){
		return c  >= 'A' && c <= 'Z';
	}
    private boolean isSmallLetter( char c ){
        return c  >= 'a' && c <= 'z';
    }
    private char encryptChar( char messChar, char keyChar)
    {
        char gap = 'z' - 'Z';
        boolean small = isSmallLetter(messChar);

        if (isSmallLetter(messChar)) messChar -= gap;
        if (isSmallLetter(keyChar)) keyChar -= gap;
        char result = (char) (messChar + keyChar);

        result = (char) ((result % 26) + 'A');
        if (small) result += gap;

        return result;
    }
    private boolean isKeyCorrect( String key ){
        for(int i = 0; i < key.length(); i++)
            if (!isBigLetter(key.charAt(i)) && !isSmallLetter(key.charAt(i))) {
                return false;
            }
        return true;
    }


    public String encrypt(String message, String key)
    {
        // checking correctness of input
        if( !isKeyCorrect( key ) ){
            return "The key is not correct. Note it can not contain any character other than letters of English alphabet.";
        }

        // data structure
        StringBuilder em = new StringBuilder(); // encrypted message
        StringBuilder gk = new StringBuilder(); // generated key
        int keyIterator = 0;

        // encrypte
        for(int i = 0; i < message.length(); i++) {

            // handle non-letter characters. It do not encrypte it.
            if( !isSmallLetter(message.charAt(i)) && !isBigLetter(message.charAt(i))){
                em.append(message.charAt(i));
                continue;
            }

            char a;
            if (keyIterator < key.length()) {
                a = encryptChar( message.charAt(i), key.charAt(keyIterator));
            } else {
                a = encryptChar (message.charAt(i),gk.toString().charAt(keyIterator - key.length()));
            }
            em.append(a);
            gk.append(a);
            keyIterator++;
        }
        return em.toString();
    }
	private char getLetterFromString(int i, String s){
	    int c = 0;
	    for( int j=0; j< s.length(); j++ ){
	        if(isSmallLetter(s.charAt(j)) || isBigLetter(s.charAt(j)) ) {
	            c++;
            }
            if(c == i){
	            return s.charAt(j);
            }
        }
        return '*';
    }
    private int letterToNumber( char c ){
        return isBigLetter( c )? c - 'A' : c - 'a';
    }
	private char decryptChar( char cipherChar, char keyChar ){
        char a = (char) (cipherChar - letterToNumber(keyChar) );

        if( ( isSmallLetter( cipherChar ) && !isSmallLetter( a ) )
                || ( isBigLetter( cipherChar ) && !isBigLetter( a ) ) ){
            a += 'z' - 'a' + 1;
        }
        return a;
    }

    private String removeWhiteCharacters( String s ){
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < s.length(); i++) {
	        if( isSmallLetter(s.charAt(i)) || isBigLetter(s.charAt(i))){
	            sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

	public String decrypt(String cipherText, String key)
	{

        String sCipherText = removeWhiteCharacters(cipherText); // squeezed cipherText

        StringBuilder mes = new StringBuilder();
        int it;
        int itKey = 0;

        // decrypte using basic key
        String sKey = removeWhiteCharacters(key); // squeezed Key
        for( it = 0; itKey < key.length() && it < cipherText.length(); it++){

            // handle white characters
            if( !isBigLetter(cipherText.charAt(it)) && !isSmallLetter(cipherText.charAt(it)) ) {
                mes.append(cipherText.charAt(it));
                continue;
            }
            char mesChar = decryptChar( cipherText.charAt(it), sKey.charAt(itKey++));
            mes.append(mesChar);
        }


        // decrypte using autoKey
        itKey = 0;
        sKey = removeWhiteCharacters(cipherText); // squeezed cipherText as a Key
        for( ; it < cipherText.length(); it++){

            // handle white characters
            if( !isBigLetter(cipherText.charAt(it)) && !isSmallLetter(cipherText.charAt(it)) ) {
                mes.append(cipherText.charAt(it));
                continue;
            }

            char mesChar = decryptChar( cipherText.charAt(it), sKey.charAt(itKey++));
            mes.append(mesChar);
        }
        return mes.toString();
	}

}

