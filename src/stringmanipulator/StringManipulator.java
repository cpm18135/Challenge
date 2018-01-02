/*
 * Interview by Caleb Perez
 * StringManipulator Class 
 * contains constructors, cleanString, hasUniqueChars, sortStrings and Main methods
 */
package stringmanipulator;
import java.io.*;
import java.util.*;
import java.lang.*;
/**
 *
 * @author calperez
 */

public class StringManipulator {
    
    /*
    I decided to create the object with the fields weight/word/unique because those were the ones required for the output file. 
    */
    
        double weight;
        String  word;
        boolean unique;    
        
        
        
        public StringManipulator(){
            /*
            Initializing values in empty constructor
            */
            this.word = "";
            this.weight = 0.0;
            this.unique = false;
        }    
        public StringManipulator(String str){
            /*
            Since in this excercise all the values had to be upper and cleaned I am calling the cleanString function and using its results to get the other values; 
            */
            this.word = cleanString(str);
            this.weight = getWeight(this.word);
            this.unique = hasUniqueChars(this.word);
        
        }
        private String cleanString(String str) {
            /*
            This function only leaves letters of the alphabet from the original string as stated in the email as well as making it all to upper casee..
            */
            str = str.replaceAll("[^a-zA-Z]", "").toUpperCase();
        return str;
    }        
        private boolean hasUniqueChars(String word) {
        /*
            this functiong returns a True if there are no repeated characters and a Flase if there are.  
            In order to not care about Upper case or letters outside of the alphabet as it was requested I am making a cvall to the cleanString function
            */    
        word = cleanString(word);
        
        for (int i=0; i<word.length(); i++){
            for (int j=i+1; j<word.length(); j++){
                if (word.charAt(j) == word.charAt(i)){
                    return false;
                }    
            }    
        }    
        return true;
    }       
        private double getWeight(String word) {
            /*
            This function returns the average of the ascii values of the alphabetical characters contained in the String.
            In order to not care about Upper case or letters outside of the alphabet as it was requested I am making a cvall to the cleanString function
            */
            word = cleanString(word);
        
            double sum = 0;
            for (int i=0; i<word.length(); i++){
                sum += (int) word.charAt(i);
            }     
        return sum/word.length();
    }
        public void sortStrings(String[] words){
            /*
            This function does not return anything but it is capable of utilizing java's Arrays.sort to provide an Array of StringManipulator items
            in ascending order by weight rather than by alphabetical order from a sting array it receives as input.  Since no instructions were said 
            about when to make the call to write the .csv file and this function did not return an element, I have included the call to CsvFileWriter
            function from within this function itself.  In case it wasnt wanted that way I apologize.
            */
            ArrayList<StringManipulator> list=new ArrayList<StringManipulator>();
        
        
            for (int i=0; i<words.length; i++){
            
                list.add(new StringManipulator(words[i]));  
        
            }
        
            StringManipulator[] resultArray = new StringManipulator[list.size()];
            resultArray = list.toArray(resultArray);
        
            Arrays.sort(resultArray, new SortByWeight());
            CsvFileWriter file = new CsvFileWriter();
            file.writeCsvFile("src/atdd/challenge_sorted.csv", resultArray);
        
        return;
    }

    
    public static void main(String[] args) {
        /*
        Main class will read the input .csv file named challenge_tests.csv on the path relative to the project src/atdd/* as stated.
        */              
        BufferedReader br = null;
        try
        {
            //Reading the csv file
            br = new BufferedReader(new FileReader("src/atdd/challenge_tests.csv"));
            
            //Create List for holding words in the csv file
            List<String> words = new ArrayList<String>();
            
            String line = "";
            //Read to skip the header
            br.readLine();
            //Reading from the second line
            while ((line = br.readLine()) != null) 
            {
                String[] wordDetails = line.split(",");
                 
                if(wordDetails.length > 0 )
                {
                    //Save the word in our list
                    String word = wordDetails[0];
                    words.add(word);
                } 
          
            }
            
            
            //Create a String array to hold the list that will later be used to call the sort and create the output .csv file
            String[] wordArray = new String[words.size()];
            wordArray = words.toArray(wordArray);
            
            //Create a StringManipulator object that will be used to call the sortStrings function with the String array resulting from
            //reading the input .csv file
            StringManipulator obj = new StringManipulator();
            obj.sortStrings(wordArray);
        }
        //Exceptions and closing read connections opened to read the file
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch(IOException ie)
            {
                System.out.println("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }   
        
    }
}    
/*
Overwriting the compare so Arrays.sort will function forcibly by weight.
*/
class SortByWeight implements Comparator<StringManipulator>
{
    // Used for sorting in ascending order of
    // weight number
    public int compare(StringManipulator a, StringManipulator b)
    {
        return (int) a.weight - (int) b.weight;
    }
}