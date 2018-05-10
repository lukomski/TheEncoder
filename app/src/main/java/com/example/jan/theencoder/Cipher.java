public interface Cipher {
	 String encrypt(String message, String key);
	 String decrypt(String cipherText, String key);

	 default boolean badKey(String key)
     {
         key = key.toUpperCase();
         for(int i = 0; i < key.length(); i++)
             if(key.charAt(i)<'A' || key.charAt(i)>'Z')
                 return true;

         return false;
     }

}
