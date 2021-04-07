package p10_package;

public class EncryptDeuxMain
   {

   public static void main(String[] args)
      {
       EncryptionClassDeux test_1 = new EncryptionClassDeux();
       String resultStr;
       
//       System.out.println( "Randomly Generated Array" );
       
//       test_1.displayArray( EncryptionClassDeux.ENUM_BOTH );

       System.out.println( "Encrypted Array" );
       
       test_1.encryptString( "abc" );
       //test_1.encryptString( "abcdef" );
       //test_1.encryptString( "Every good girl does better" );
       //test_1.encryptString( "It was the best of times, it was the worst of times" );
       
       //test_1.displayArray( EncryptionClassDeux.ENUM_NUMBER_ONLY );
       //test_1.displayArray( EncryptionClassDeux.ENUM_CHARACTER_ONLY );
       test_1.displayArray( EncryptionClassDeux.ENUM_BOTH );
       
       resultStr = test_1.decryptString();
       
       System.out.println( "Decrypted String: " + resultStr );
      }

   }