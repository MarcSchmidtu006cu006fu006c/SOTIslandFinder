package check;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;



/*
The check.CheckImage class is intended to check the format and size of
a submitted image to confirm that the image meets the required
format.
The images used in the database are png format with a size of 300X300
all image submitted for comparison must match this in order for the image
to be compared accurately
*/

public class CheckImage {

    /**
     *
     * @param imgLocation imgLocation is the local file path of the image whose format is to be checked
     * @return The method return true if the image format and dismentions are within specification and false if they
     * are not
     * @throws Exception An exception is thrown if the image is not able to be accessed
     */

    public static boolean checkFormat(String imgLocation) throws Exception {
        String format = "";

        //get image format in a file
        File file = new File(imgLocation);

        //Create an image input stream from the specified file
        ImageInputStream iis = ImageIO.createImageInputStream(file);

        //obtain all currently registered readers that recognize the image format
        Iterator iter = ImageIO.getImageReaders(iis);

        //throw Run Time Exception if the image contains no possible readers
        if (!iter.hasNext()) {
            throw new RuntimeException("No readers found!");
        }

        //Get the first reader
        ImageReader reader = (ImageReader) iter.next();

        //assign the value of the image's format to the string variable format
        format = reader.getFormatName();
        System.out.println("this is the format: " + format);

        //Close the image input stream that was opened in line 38
        iis.close();

        //Check if the string variable format is equal to "png"
        if (format.equals("png")) {
            //check that the image has the correct dimensions
            BufferedImage img = ImageIO.read(file);
            //Assign the image's width and height to the int variables width and height
            int width = img.getWidth();
            int height = img.getHeight();
            //Return true if the dimensions of the image are 300 by 300 pixels
            return width == 300 && height == 300;
        } else {
            //Return false if the image is not 300 by 300 pixels
            return false;
        }
    }

}
