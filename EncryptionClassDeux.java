package p10_package;

/**
 * Class encrypts, stores, and decrypts messages encoded and stored in an array 
 * of long integers
 * 
 * @author MichaelL
 *
 */
public class EncryptionClassDeux
   {
    /**
     * Default capacity for square array
     */
    private final String DEFAULT_STRING = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Constant for decoding first three digits of long
     */
    private final int THREE_DIGIT_OFFSET = 1000;
    
    /**
     * Constant for decoding second three digits of long
     */
    private final int SIX_DIGIT_OFFSET = 1000000;

    /**
     * Constant for decoding third three digits of long
     */
    private final int NINE_DIGIT_OFFSET = 1000000000;

    /**
     * Constant for random index lower limit
     */
    private final int RAND_LOWER_LIMIT = 10;

    /**
     * Constant for random index upper limit
     */
    private final int RAND_UPPER_LIMIT = 99;

    /**
     * Constant for minimum string size
     */
    private final int MIN_STRING_LENGTH = 15;

    /**
     * Constant indicating used value
     */
    private final int USED_CODE = 1;

    /**
     * Constant indicating unused value
     */
    private final int UNUSED_CODE_LOW = 2;
    
    /**
     * Constant indicating unused value
     */
    private final int UNUSED_CODE_HIGH = 9;
    
    /**
     * Constant indicating control code for number only
     */
    public static final int ENUM_NUMBER_ONLY = 1001;
    
    /**
     * Constant indicating control code for character only
     */
    public static final int ENUM_CHARACTER_ONLY = 2002;
    
    /**
     * Constant indicating control code for both number and character
     */
    public static final int ENUM_BOTH = 3003;
    
    /**
     * Constant indicating tilde character
     */
    private final char TILDE_CHAR = '~';
    
    /**
     * Constant indicating caret character
     */
    private final char CARET_CHAR = '^';
    
    /**
     * Constant indicating space character
     */
    private final char SPACE_CHAR = ' ';
    
    /**
     * Constant indicating caret character
     */
    private final char RIGHT_CURLY_BRACE_CHAR = '}';
    
    /**
     * data array
     */
    private long array[][];
    
    /**
     * side capacity for square array
     */
    private int sideCapacity;
    
    /**
     * Default constructor
     * <p>
     * Creates an array with DEFAULT_STRING "abcdefghijklmnopqrstuvwxyz" 
     * and sets all the elements in it to random characters
     * <p>
     * Dependencies: createArray
     */
    EncryptionClassDeux()
       {
        array = createArray( DEFAULT_STRING );
       }
    
    /**
     * Initialization constructor 
     * <p>
     * Captures and encrypts string
     * <p>
     * Dependencies: encryptString
     * 
     * @param toEncrypt String object holding data to be encrypted
     * 
     */
    EncryptionClassDeux( String toEncrypt )
       {
        encryptString( toEncrypt );
       }
    
    /**
     * Copy constructor 
     * <p>
     * Dependencies: None
     * 
     * @param copied EncryptionClassDeux object to be copied into this object
     * 
     */
    EncryptionClassDeux( EncryptionClassDeux copied )
       {
        // 
          this.sideCapacity = copied.sideCapacity;
       }
    
    /**
     * Create array
     * <p>
     * Finds length of string to be encrypted; 
     * if string is less than MIN_STRING_LENGTH, 
     * length is set to MIN_STRING_LENGTH;
     * either way string length is then doubled
     * <p>
     * Finds the square root of the updated string length and sets side capacity; 
     * creates local array, then calls fillArray which sets all the elements 
     * in the array to random characters other than tilde ('~')
     * <p>
     * Returns local array to calling method
     * <p>
     * Dependencies: {String}.length, Math.sqrt, fillArray
     * 
     * @param setUpString string used to find and process string length
     * 
     * @return array [][] of long values
     */
    private long [][] createArray( String setUpString )
       {
        // initialize method/variables
        int strLen = setUpString.length();        
        long [][] tempArr;
        
        // check for string length less than MIN_STRING_LENGTH
        if( strLen < MIN_STRING_LENGTH )
           {
            // set string length to MIN_STRING_LENGTH
            strLen = MIN_STRING_LENGTH; 
           }
        
        // double length of string
        strLen *= 2;
        
        // calculate side capacity
           // method: .sqrt
        sideCapacity = (int)( Math.sqrt( strLen ) );
        
        // crate new array
        tempArr = new long[ sideCapacity ][ sideCapacity ];
        
        // fill array with random
           // method: fillArray
        tempArr = fillArray( tempArr );
                
        // return array reference
        return tempArr;
       }
    
    /**
     * displayArray
     * <p>
     * Depending on control code, array is displayed one of three ways: 
     * NUMBER_ONLY: displays array of long values; CHARACTER_ONLY: displays
     * encoded characters; BOTH: displays both numbers and characters
     * <p>
     * Uses System.out.format
     * <p>
     * Dependencies: System.out.println, System.out.format, getCharValue
     * 
     * @param ctrlCode integer control code to indicate kind of display
     */
    public void displayArray( int ctrlCode )
       {
        //
          //create variables
          int outIndex, inIndex;
          char dataChar;
          
          //check which ctrlcode will be used
          //dependant on the code, create number only, char only, or both
          if (ctrlCode == ENUM_NUMBER_ONLY) 
             {
                //loop through every element in the array, printing each one
                for (outIndex = 0; outIndex < sideCapacity; outIndex++) 
                   {
                      for (inIndex = 0; inIndex < sideCapacity; inIndex++) 
                         {
                            //print out only the number
                            System.out.format(" %d", array[outIndex][inIndex]);
                         }
                      //print a new line at the end of each row
                      System.out.println();
                   }
             }
          //print out only the character
          else if (ctrlCode == ENUM_CHARACTER_ONLY) 
             {
                for (outIndex = 0; outIndex < sideCapacity; outIndex++) 
                   {
                      for (inIndex = 0; inIndex < sideCapacity; inIndex++) 
                         {
                            //find the character at the element in the array
                            dataChar = getCharValue(array[outIndex][inIndex]);
                            
                            //print out the formatted character
                            System.out.format(" ('%c')", dataChar);
                         }
                      //print out a new line
                      System.out.println();
                   }
             }
          //print out both number and char
          else 
             {
                for (outIndex = 0; outIndex < sideCapacity; outIndex++) 
                   {
                      for (inIndex = 0; inIndex < sideCapacity; inIndex++) 
                         {
                            //find the character at the element in the array
                            dataChar = getCharValue(array[outIndex][inIndex]);
                            
                            //print out the number and the character together
                            System.out.format(" %d('%c')", array[outIndex][inIndex], dataChar);
                         }
                      //print out a new line
                      System.out.println();
                   }
             }
       }
    
    /**
     * decryptArray
     * <p>
     * Finds the tilde in the array, and tracks the elements of the array
     * to set the individual character values back into a String; 
     * list of characters ends with a caret ('^')
     * <p>
     * Dependencies: findTilde, getNextXPos, getNextYPos, getCharValue
     * 
     * @return String value containing decrypted text
     */
    public String decryptString()
       {
        // 
          //create variables
          long tildeLocation, currentLocation, nextLocation;
          int nextXPos, nextYPos;
          char curChar;
          String decryptedString = "";
          
          //find the tilde location and set the current location
          tildeLocation = findTilde();
          currentLocation = tildeLocation;
          curChar = TILDE_CHAR;
          
          //start at the tilde location then loop through the trail of next positions
          //add each character to the final string
          //end when you find a caret
          while (curChar != CARET_CHAR)
             {
                //find the character at the location
                curChar = getCharValue(currentLocation);
                
                //Add the character to the final string
                decryptedString += curChar;
                
                //find next position
                nextXPos = getNextXPos(currentLocation);
                nextYPos = getNextYPos(currentLocation);
                
                //find the value at the next location
                nextLocation = array[nextYPos][nextXPos];
                
                //set the next location as the current location
                currentLocation = nextLocation;
             }
             
          //return the decrypted string
          return decryptedString;
       }
    
    /**
     * elementIsUsed
     * <p>
     * Returns true if element is used in the array, or if the current y position
     * is equal to the next y position and the current x position is equal to
     * the next x position
     * <p>
     * Dependencies: None
     * 
     * @param curYPos integer current y position to be tested
     * 
     * @param curXPos integer current x position to be tested
     * 
     * @param nextYPos integer next y position to be tested
     * 
     * @param nextXPos integer next x position to be tested
     * 
     * @return Boolean value indicating state of specified testing
     */
    private boolean elementIsUsed( int curYPos, 
                                       int curXPos, int nextYPos, int nextXPos )
       {
        // 
          //initialize variables
          //boolean response to if it is used
          boolean isUsed = false;
          long encodedValue, used;
          
          //get the value from the next location in the array
          encodedValue = array[nextYPos][nextXPos];
          
          //get the used value from the encoded value
          used = encodedValue / NINE_DIGIT_OFFSET;
          
          //check if the element at x,y is equal to the next x,y
          //otherwise it is false
          if (curXPos == nextXPos && curYPos == nextYPos) 
             {
                isUsed = true;
             }
          //otherwise check if the next element has been used already
          else if (used == 1) 
             {
                isUsed = true;
             }
          
          //otherwise the location has not been used
          //return the result
          return isUsed;
       }

    /**
     * encodeData
     * <p>
     * Encodes four parts of data into long value as ucccyyyxxx
     * where u is the used flag
     * ccc is the character Unicode/ASCII value
     * yyy is the y position of an element
     * xxx is the x position of an element
     * <p>
     * Dependencies: None
     * 
     * @param used integer value indicating that the value is part 
     * of the encrypted string, which is encoded into the long value
     * 
     * @param dataChar character value encoded into the long value
     * 
     * @param yPos integer value encoded into the long value
     * 
     * @param xPos integer value encoded into the long value
     * 
     * @return long value containing encoded data
     */
    public long encodeData( int used, char dataChar, int yPos, int xPos )
       {
        //
          //create the long encoded value
          long encodedValue;
          
          //set it equal to the used value, then offset it
          encodedValue = used * THREE_DIGIT_OFFSET;
          
          //Next add the char value to the encoded long, thn offset it again
          encodedValue += (int) dataChar;
          encodedValue *= THREE_DIGIT_OFFSET;
          
          //Then add the y position and offset again
          encodedValue += yPos;
          encodedValue *= THREE_DIGIT_OFFSET;
          
          //finally add the x position
          encodedValue += xPos;
          
          //return the final encoded value
          return encodedValue;
       }
    
    /**
     * encryptString
     * <p>
     * Creates array using the input string which also fills array with random 
     * characters, then places characters randomly around the array starting 
     * with a tilde ('~') and ending with a caret ('^')
     * <p>
     * Each long value in the array holds a used marker, the character,
     * and the y and x position of the next character
     * <p>
     * Process generates a new random y, x pair for each character data element
     * but verifies that the y, x pair has not already been used; once these
     * are found, the long value is encoded with the used marker, the character,
     * and the next y, x positions, and placed into the array
     * <p>
     * Dependencies: {String}.length, {String}.charAt, createArray, 
     * getRandBetween, encodeData, elementIsUsed
     * 
     * @param toEncrypt String value to be encrypted
     */
    public void encryptString( String toEncrypt )
       {
        // initialize method/variables
        long encodeVal;
        int currentYPos, currentXPos;
        int nextYPos, nextXPos;        
        int strIndex, strLen;
        char postChar = TILDE_CHAR;

        // place caret character at end of string
        toEncrypt += CARET_CHAR;
        
        // get string length
           // method: .length
        strLen = toEncrypt.length();
        
        // create array
           // method: createArray
        array = createArray( toEncrypt );

        // get random values for first (tilde) character
           // method: getRandBetween
        currentYPos = getRandBetween( 0, sideCapacity - 1 );
        currentXPos = getRandBetween( 0, sideCapacity - 1 );

        // loop across string
        for( strIndex = 0; strIndex < strLen; strIndex++ )
           {
            // loop until unique y and x positions
            do
               {
                // set potential next y pos
                   // method: getRandBetween
                nextYPos = getRandBetween( 0, sideCapacity - 1 );
                
                // set potential next x pos
                   // method: getRandBetween
                nextXPos = getRandBetween( 0, sideCapacity - 1 );
                
                // encode value into long
                   // method: encodeData
                encodeVal = encodeData( USED_CODE, 
                                               postChar, nextYPos, nextXPos );
               }
            // end loop if element or previous indices are not already used
               // method: elementIsUsed
            while( elementIsUsed( currentYPos, 
                                            currentXPos, nextYPos, nextXPos ) );

            // place encoded value in array
            array[ currentYPos ][ currentXPos ] = encodeVal;
            
            // set current y and x positions to processed next ones
            currentYPos = nextYPos;            
            currentXPos = nextXPos;
            
            // set new character to be encrypted
               // metod: .charAt
            postChar = toEncrypt.charAt( strIndex );
           }
        
        // set last value to last character (tilde)
           // method: encodeData
        encodeVal = encodeData( USED_CODE, postChar, currentYPos, currentXPos );
        
        // set last element
        array[ currentYPos ][ currentXPos ] = encodeVal;
       }
    
    /**
     * fillArray
     * <p>
     * Fills temporary array with a random value between UNUSED_CODE_LOW and 
     * UNUSED_CODE_HIGH, a random character other than tilde ('~'), and a 
     * randomly generated y and x location between RAND_LOWER_LIMIT and
     * RAND_UPPER_LIMIT 
     * <p>
     * Dependencies: getRandBetween, getRandomChar, encodeData
     * 
     * @param arrayToFill long [][] array to be filled with random data
     * 
     * @return long [][] array with specified random values
     */
    public long [][] fillArray( long [][] arrayToFill )
       {
        // 
          //create variables
          int used, xPos, yPos, inIndex, outIndex;
          char dataChar;
          long encodedValue;
          
          //run through every element in the array with nested loops
          for (outIndex = 0; outIndex < sideCapacity; outIndex++) 
             {
                for (inIndex = 0; inIndex < sideCapacity; inIndex++) 
                   {
                      //get the random values for the encoded value
                      used = getRandBetween(UNUSED_CODE_LOW, UNUSED_CODE_HIGH);
                      dataChar = getRandomChar();
                      xPos = getRandBetween(RAND_LOWER_LIMIT, RAND_UPPER_LIMIT);
                      yPos = getRandBetween(RAND_LOWER_LIMIT, RAND_UPPER_LIMIT);
                      
                      //encode all the data into the one value
                      encodedValue = encodeData(used, dataChar, xPos, yPos);
                      
                      //put the value into the location in the array
                      arrayToFill[outIndex][inIndex] = encodedValue;
                   }
             }

          //return the filled array
          return arrayToFill;
       }
    
    /**
     * findTilde
     * <p>
     * Finds tilde encoded in two dimensional array of long values
     * <p>
     * Dependencies: getCharValue, encodeData
     * 
     * @return long encoded value containing UNUSED_CODE_LOW, TILDE_CHAR,
     * and the y and x locations of the TILDE_CHAR
     */
    private long findTilde()
       {
        // 
          //create variables
          int inIndex, outIndex;
          long tildeLocation = 0;
          char dataChar;
          
          //run through every element in the array to check for the tilde
          for (outIndex = 0; outIndex < sideCapacity; outIndex++) 
             {
                for (inIndex = 0; inIndex < sideCapacity; inIndex++) 
                   {
                      //get the character from the encoded value
                      dataChar = getCharValue(array[outIndex][inIndex]);
                      
                      //check if it is the tilde
                      if (dataChar == TILDE_CHAR) 
                         {
                            tildeLocation = array[outIndex][inIndex];
                         }
                   }
             }

          //return the tilde location
          return tildeLocation;
       }
    
    /**
     * getCharValue
     * <p>
     * decodes long integer to return character
     * <p>
     * Dependencies: None
     * 
     * @param encoded long value with encoded character to retrieve
     * 
     * @return character retrieved
     */
    private char getCharValue( long encoded )
       {
        // 
          //create variables
          long dataCharValue;
          char dataChar;
          
          //create an offset so that we can find the data characters value and used value
          dataCharValue = encoded / SIX_DIGIT_OFFSET;
          
          //use a remainder to find only the data character value
          dataCharValue = encoded % THREE_DIGIT_OFFSET;
          
          //cast the value to the corresponding character
          dataChar = (char) (dataCharValue + '0');
          
          //return the character
          return dataChar;
       }
    
    /**
     * getRandBetween
     * <p>
     * Returns random value between low and high parameters, inclusive
     * <p>
     * Dependencies: Math.random
     * 
     * @param low integer value indicating low end of random range to generate
     * 
     * @param high integer value indicating high end of random range to generate
     * 
     * @return random value between low and high parameters, inclusive
     */
    public int getRandBetween( int low, int high )
       {
        // initialize method/variables
        // set range
        int range = high - low + 1;
        
        // return randomly generated value within range
           // method: .random
        return (int)( Math.random() * range + low );
       }

    /**
     * getRandomChar
     * <p>
     * Returns a Unicode/ASCII character from space (lowest printable character)
     * to right curly brace (highest printable character before tilde); 
     * tilde ('~') is never returned
     * <p>
     * Dependencies: getRandBetween
     * 
     * @return value containing random character
     */
    private char getRandomChar()
       {
        // initialize methd/variables
        int randomVal;
        
        // set random value
           // method: getRandBetween
        randomVal = getRandBetween( (int)SPACE_CHAR, 
                                                  (int)RIGHT_CURLY_BRACE_CHAR );
        
        // return random value as character
        return (char)randomVal;
       }
    
    /**
     * getNextXPos
     * <p>
     * Returns the next x position from the encoded value
     * <p>
     * Dependencies: None
     * 
     * @param encoded long value with encoded x location to retrieve
     * 
     * @return int x location retrieved
     */
    private int getNextXPos( long encoded )
       {
        // 
          //create variables
          long encodedXPos;
          int nextXPos;
          
          //access the x location from the encoded value
          //use remainder to access only the x position
          encodedXPos = encoded % THREE_DIGIT_OFFSET;
          
          //cast it to an integer to find the next
          nextXPos = (int) (encodedXPos + 0);
          
          //return the next x position
          return nextXPos;
       }

   /**
     * getNextYPos
     * <p>
     * Returns the y position from the encoded value
     * <p>
     * Dependencies: None
     * 
     * @param encoded long value with encoded y location to retrieve
     * 
     * @return int y location retrieved
     */
    private int getNextYPos( long encoded )
       {
        //
          //create variables
          long encodedYPos;
          int nextYPos;
          
          //access the x location from the encoded value
          //use offset and remainder to access only the y position
          encodedYPos = encoded / THREE_DIGIT_OFFSET;
          encodedYPos = encodedYPos % THREE_DIGIT_OFFSET;

          //cast it to an integer to find the next
          nextYPos = (int) (encodedYPos + 0);
          
          //return the next x position
          return nextYPos;
       }

   }
