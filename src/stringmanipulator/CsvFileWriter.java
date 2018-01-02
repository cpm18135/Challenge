/*
 * Interview by Caleb Perez
 * CsvFileWriter Class 
 * class to write our resulting .csv file
 * contains function writeCsvFile that receives a filename and a StringManipulator array.  In this case the one already sorted 
 */
package stringmanipulator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvFileWriter {

     

   //Delimiters used in CSV file

    private static final String COMMA_DELIMITER = ",";

    private static final String NEW_LINE_SEPARATOR = "\n";

     

    //CSV file header

    private static final String FILE_HEADER = "Words,Unique,Weight";

 

    public static void writeCsvFile(String fileName, StringManipulator[] resultArray) {

 
        FileWriter fileWriter = null;

                 

        try {

            fileWriter = new FileWriter(fileName);

 
            //Header portion of .csv file
            //Write the .csv file header
            fileWriter.append(FILE_HEADER.toString());
            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Start writing the information in the order that was stated in the header
            for (int i=0; i<resultArray.length; i++){ 
                fileWriter.append(resultArray[i].word);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(resultArray[i].unique));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(resultArray[i].weight));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }   

             
            //Success message if everything went alright
            System.out.println("CSV file was created successfully");

             
            //formal catch and error messages in case we have issues writing the file
        } catch (Exception e) {

            System.out.println("Error in CsvFileWriter");

            e.printStackTrace();
            //Closing the file writer
        } finally {

             

            try {

                fileWriter.flush();

                fileWriter.close();

            } catch (IOException e) {

                System.out.println("Error while flushing/closing fileWriter");

                e.printStackTrace();

            }

             

        }

    }

}
