package com.example.jan.theencoder;

public class PlayfairCipher implements Cipher {
	
	private void createPlayfairMatrix (String key, char matrix [][]) {
        int if_repeat[] = new int[26];
        key = key.toUpperCase();
        key=key.replaceAll("J", "I");
        key=key.replaceAll(" ", "");
        int i = 0, j = 0;

        for (int k = 0; k < key.length(); k++) {
            if (if_repeat[(int) key.charAt(k) - 65] == 0) {
                matrix[i][j] = key.charAt(k);
                if_repeat[(int) key.charAt(k) - 65]++;
                j++;
                if (j > 4) {
                    j = 0;
                    i++;
                }
            }
        }
        char rest='A';

        while(i < 5) {
            while (j < 5) {
                if (if_repeat[(int) rest - 65] == 0 && rest != 'J') {
                    matrix[i][j] = rest;
                    j++;
                }
                rest++;
            }
            if (j>4) {
                j = 0;
                i++;
            }
        }
    }
    private String createOnlyLettersMessage(String message, String onlyLettersMessage, char notLetters [], int x, char encode_matrix[][] )
    {

        for (int i=0;i<message.length();i++) {
            if ((message.charAt(i) >= 'A' && message.charAt(i) <= 'Z') || (message.charAt(i) >= 'a' && message.charAt(i) <= 'z')) {
                onlyLettersMessage += message.charAt(i);
                notLetters[i]='a';
            }
            else
                notLetters[i]=message.charAt(i);
        }
        if (onlyLettersMessage.length()%2!=0) {
            onlyLettersMessage += encode_matrix[0][0];
            x++;
        }

        onlyLettersMessage=onlyLettersMessage.replaceAll("J", "I");
        onlyLettersMessage=onlyLettersMessage.replaceAll("j", "i");
                return onlyLettersMessage;

    }
    private String doubleLetter(int r1, int c1, String onlyLettersMessage, char encode_matrix[][], String result, int l, int p1, int p2, int p3)
    {
        r1=r1+p1;
        c1=c1+p1;

        if (r1*p1 > p2)
            r1 = p3;

        if (c1*p1 > p2)
            c1 = p3;

        if (Character.isUpperCase ( onlyLettersMessage.charAt(l)))
            result += encode_matrix[r1][c1];
        else
            result += Character.toLowerCase(encode_matrix[r1][c1]);

        if (Character.isUpperCase ( onlyLettersMessage.charAt(l+1)))
            result += encode_matrix[r1][c1];
        else
            result += Character.toLowerCase(encode_matrix[r1][c1]);
        return result;
    }
    private String moveLeftRight(int r1, int r2, int c1,int c2, String onlyLettersMessage, char encode_matrix[][], String result, int l, int p1, int p2, int p3)
    {
        r1=r1+p1;
        r2=r2+p1;
        if (r1*p1 > p2)
            r1 = p3;

        if (r2*p1> p2)
            r2 = p3;

        if (Character.isUpperCase ( onlyLettersMessage.charAt(l)))
            result += encode_matrix[r1][c2];
        else
            result += Character.toLowerCase(encode_matrix[r1][c2]);

        if (Character.isUpperCase ( onlyLettersMessage.charAt(l+1)))
            result += encode_matrix[r2][c1];
        else
            result += Character.toLowerCase(encode_matrix[r2][c1]);
        return result;
    }
    private String moveUpDown(int r1, int r2, int c1,int c2, String onlyLettersMessage, char encode_matrix[][], String result, int l, int p1, int p2, int p3)
    {
        c1=c1+p1;
        c2=c2+p1;
        if (c1*p1 > p2)
            c1 = p3;

        if (c2*p1 > p2)
            c2 = p3;
        if (Character.isUpperCase ( onlyLettersMessage.charAt(l)))
            result += encode_matrix[r1][c1];
        else
            result += Character.toLowerCase(encode_matrix[r1][c1]);

        if (Character.isUpperCase ( onlyLettersMessage.charAt(l+1)))
            result += encode_matrix[r2][c2];
        else
            result += Character.toLowerCase(encode_matrix[r2][c2]);
        return result;
    }

    private String crossLetters(int r1, int r2, int c1,int c2, String onlyLettersMessage, char encode_matrix[][], String result, int l) {
        if (Character.isUpperCase(onlyLettersMessage.charAt(l)))
            result += encode_matrix[r1][c2];
        else
            result += Character.toLowerCase(encode_matrix[r1][c2]);

        if (Character.isUpperCase(onlyLettersMessage.charAt(l + 1)))
            result += encode_matrix[r2][c1];
        else
            result += Character.toLowerCase(encode_matrix[r2][c1]);
        return result;
    }
    private String createFinalResult(int x, String result, char[] notLetters, String finalResult,int j, String text)
    {
        if (x==1)
            result = result.substring(0, result.length() - 1);

        for (int i=0; i<text.length();i++) {
            if (notLetters[i] != 'a')
                finalResult += notLetters[i];
            else {
                finalResult += result.charAt(j);
                j++;
            }
        }
        return finalResult;
    }
    public String encrypt(String message, String key)
	{
		if (badKey(key))
            return "Please enter the correct key";

        String result = "";

        char encode_matrix[][] = new char[5][5];
        createPlayfairMatrix(key, encode_matrix);
        String onlyLettersMessage= new String("");
        char notLetters [] = new char [message.length()];
        int x=0;
        int p1=1;
        int p2=4;
        int p3=0;
        onlyLettersMessage= createOnlyLettersMessage(message,onlyLettersMessage,notLetters,x,encode_matrix);
                for (int l = 0; l < onlyLettersMessage.length(); l += 2) {

            int r1=0;
            int r2=0;
            int c1=0;
            int c2=0;

            for (int i = 0; i < encode_matrix.length; i++) {
                for (int j = 0; j < encode_matrix[i].length; j++) {
                    if (encode_matrix[i][j] == onlyLettersMessage.toUpperCase().charAt(l)) {
                        r1 = i;
                        c1 = j;
                    }
                    if (encode_matrix[i][j] == onlyLettersMessage.toUpperCase().charAt(l + 1)) {
                        r2 = i;
                        c2 = j;
                    }

                }
            }

            if ( r1==r2 && c1==c2)
                result = doubleLetter(r1,c1,onlyLettersMessage,encode_matrix,result,l,p1,p2,p3);
            else if (c1 == c2)
                result = moveLeftRight(r1,r2,c1,c2,onlyLettersMessage,encode_matrix,result,l,p1,p2,p3);
            else if (r1 == r2)
                result = moveUpDown(r1,r2,c1,c2,onlyLettersMessage,encode_matrix,result,l,p1,p2,p3);
            else
                result = crossLetters(r1,r2,c1,c2,onlyLettersMessage,encode_matrix,result,l);
        }
        String finalResult = "";
        int j=0;
        finalResult=createFinalResult(x,result,notLetters,finalResult,j,message);
        return finalResult;
	}

	public String decrypt(String cipherText, String key)
	{
        if (badKey(key))
            return "Please enter the correct key";

        String result = "";

        char encode_matrix[][] = new char[5][5];
        createPlayfairMatrix(key, encode_matrix);

        String onlyLettersMessage="";
        char notLetters [] = new char [cipherText.length()];
        int x=0;
        int p1=-1;
        int p2=0;
        int p3=4;
        onlyLettersMessage= createOnlyLettersMessage(cipherText,onlyLettersMessage,notLetters,x,encode_matrix);

        for (int l = 0; l < onlyLettersMessage.length(); l += 2)
        {

            int r1=0;
            int r2=0;
            int c1=0;
            int c2=0;

            for (int i = 0; i < encode_matrix.length; i++) {
                for (int j = 0; j < encode_matrix[i].length; j++) {
                    if (encode_matrix[i][j] == onlyLettersMessage.toUpperCase().charAt(l)) {
                        r1 = i;
                        c1 = j;
                    }
                    if (encode_matrix[i][j] == onlyLettersMessage.toUpperCase().charAt(l + 1)) {
                        r2 = i;
                        c2 = j;
                    }
                }
            }
                if ( r1==r2 && c1==c2)
                    result = doubleLetter(r1,c1,onlyLettersMessage,encode_matrix,result,l,p1,p2,p3);
                else if (c1 == c2)
                    result = moveLeftRight(r1,r2,c1,c2,onlyLettersMessage,encode_matrix,result,l,p1,p2,p3);
                else if (r1 == r2)
                    result = moveUpDown(r1,r2,c1,c2,onlyLettersMessage,encode_matrix,result,l,p1,p2,p3);
                else
                    result = crossLetters(r1,r2,c1,c2,onlyLettersMessage,encode_matrix,result,l);
        }
        String finalResult = "";
        int j=0;
        finalResult=createFinalResult(x,result,notLetters,finalResult,j,cipherText);
        return finalResult;
	}
}
