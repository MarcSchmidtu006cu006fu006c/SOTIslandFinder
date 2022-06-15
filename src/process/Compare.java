package process;
/*
The process.Compare class is used to check the level of similarity of two islands.
The class does this by comparing each pixel of the two images and use this information
to determine the percentage of probability that the images are the same.
 */

import dataAccess.Database;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Compare {
    final int numOfIslands = 66;
    Database db = new Database();

    public int imageCompare(String image) throws Exception {

        BufferedImage userImage = ImageIO.read(new File(image));
        long difference = 0;
        int uw = userImage.getWidth();
        int uh = userImage.getHeight();
        long dataTotal = 0;
        long previousTotal = Long.MAX_VALUE;
        int idLocated = 0;

        for (int i = 1; i <= numOfIslands; i++) {
            // System.out.println("checking island with id: " + i);
            BufferedImage stockImage = ImageIO.read(new File(db.getIslandFile(i)));

            int sw = stockImage.getWidth();
            int sh = stockImage.getHeight();


            for (int x = 0; x < sh; x++) {
                for (int y = 0; y < sw; y++) {
                    //getting the RGB values of all pixels... this might take a while will need to multi thread in future
                    int pixel1 = stockImage.getRGB(y, x);
                    Color c1 = new Color(pixel1, true);
                    int r1 = c1.getRed();
                    int g1 = c1.getGreen();
                    int b1 = c1.getBlue();

                    int pixel2 = userImage.getRGB(y, x);
                    Color c2 = new Color(pixel2, true);
                    int r2 = c2.getRed();
                    int g2 = c2.getGreen();
                    int b2 = c2.getBlue();

                    //this is to calculate the difference in RGB values between the two images
                    long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
                    dataTotal = dataTotal + data;
                }
            }
            System.out.println("this is dataTotal: " + dataTotal);
            System.out.println("this is test count: " + i);
            //The difference between the compared images is checked and if it is found to be less than the previous value
            //it is then saved as the new previous value
            if (dataTotal < previousTotal) {
                previousTotal = dataTotal;
                idLocated = i;
                System.out.println("this iD: " + idLocated);
            }
            dataTotal = 0;

        }



        return idLocated;
    }

}
