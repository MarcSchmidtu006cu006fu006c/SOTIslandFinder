import java.util.Scanner;
import check.CheckImage;
import dataAccess.Database;
import process.Compare;


public class Main {
    public static void main(String[] args) throws Exception{

        String sourceImage = "";
        boolean correctFormat = false;
        Compare compare = new Compare();
        Database data = new Database();
        int islandId = 0;
        String location = "";

        //Scanner to receive user submitted image
        Scanner scan = new Scanner(System.in);

        //image messages for user on how they must use the application
        System.out.println("Hello please follow the instructions listed bellow to use the island locator :-)");
        System.out.println("Please note that all images submitted must be in a .png format");
        System.out.println("The image dimensions must be 300 pixels in height and 300 pixels in width");
        System.out.println("If your image meets these standards please enter the file path of the image to compare" +
               " also note that the comparison may take some time as every pixel will be analyzed to ensure " +
               "an accurate comparison.");

        //Contain the image file path input from user in source image varible
        sourceImage = scan.nextLine();
        try{
           // correctFormat = CheckImage.checkFormat(sourceImage = scan.nextLine());
            correctFormat = CheckImage.checkFormat(sourceImage);
        }catch(Exception e){
            System.out.println("There was an exception caught on line 27 of the main class. This was caused when trying " +
                    "to check the user's input for correct format.");
        }

        //Check if the users format was found to be within the specifications
       if (correctFormat == true) {
           //Call imageCompare method in the Compare class to compare user image with those stored in database
            islandId = compare.imageCompare(sourceImage);
            //Print the found island image to the user along with its map location
           System.out.println("The island you are looking for is called :" + data.getIslandLocation(islandId));
       }else{
           //Inform the user that the image they provided is not within the specified format
            System.out.println("The image you have submitted does not meet the required standards. Please try again");
       }





    }
}