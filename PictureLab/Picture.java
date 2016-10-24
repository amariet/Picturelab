import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for(Pixel[] rowArray : pixels)
        {
            for(Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for(Pixel[] rowArray : pixels)
        {
            for(Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(255 - pixelObj.getRed());
                pixelObj.setGreen(255 - pixelObj.getGreen());
                pixelObj.setBlue(255 - pixelObj.getBlue());
            }
        }
    }

    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for(Pixel[] rowArray : pixels)
        {
            for(Pixel pixelObj : rowArray)
            {
                int average = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
                pixelObj.setRed(average);
                pixelObj.setGreen(average);
                pixelObj.setBlue(average);
            }
        }
    }

    public void fixUnderWater()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel pixel = null;
        for ( int row = 0; row < pixels.length; row++ )
        {
            for ( int col = 0; col < pixels[0].length; col++ )
            {
                pixel = pixels[row][col];
                pixel.setRed(pixel.getRed() * 3 );
            }
        }
    }

    public void zeroRed()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
            }
        }
    }

    public void zeroGreen()
    {
        Pixel[][] pixels = this.getPixels2D();
        //     for (Pixel[] rowArray : pixels)
        //     {
        //       for (Pixel pixelObj : rowArray)
        //       {
        //         pixelObj.setGreen(0);
        //       }
        //     }
        for (int i = 0; i < pixels.length; i++)
        {
            for (int j = 0; j < pixels[i].length; j++)
            {
                pixels[i][j].setGreen(255);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    /** Method that mirrors a picture around a mirror
     * placed vertically from right to left
     */
    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    /** Method that mirrors a picture around a mirror
     * placed horizontally at the middle of the height 
     * of the picture
     */
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel =  null;
        int height = pixels.length;
        for(int row = 0; row < pixels.length; row++)
        {
            for(int col = 0; col < pixels[0].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /** Method that mirrors a picture around a mirror
     * placed horizontally from bottom to top
     */
    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for(int row = 0; row < pixels.length; row++)
        {
            for(int col = 0; col < pixels[0].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                topPixel.setColor(topPixel.getColor());
            }
        }
    }

    /** Method that mirrors just a square part of the picture 
     * from botton left to top right around a mirror placed
     * on the diagonal line
     */
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topRightPixel = null;
        Pixel bottomLeftPixel = null;
        int length = 0;
        if(pixels.length > pixels[0].length)
        {
            length = pixels[0].length;
        }
        else
        {
            length = pixels.length;
        }
        for(int row = 0; row < pixels.length; row++)
        {
            for(int col = 0; col < pixels.length; col++)
            {
                topRightPixel = pixels[row][col];
                bottomLeftPixel = pixels[col][row];
                bottomLeftPixel.setColor(topRightPixel.getColor());
            }
        }
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {
                count++;
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        System.out.println(count);
    }

    /** Mirrors the arms on the snowman in "snowman.jpg"
     * to make a snowman with 4 arms
     */
    public void mirrorArms()
    {
        int mirrorPoint = 193;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        for(int row = 158; row < mirrorPoint; row++)
        {
            for(int col = 103; col < 170; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
        int otherMirrorPoint = 198;
        Pixel otherTopPixel = null;
        Pixel otherBottomPixel = null;
        for(int row = 171; row < otherMirrorPoint; row++)
        {
            for(int col = 239; col < 294; col++)
            {
                otherTopPixel = pixels[row][col];
                otherBottomPixel = pixels[otherMirrorPoint - row + otherMirrorPoint][col];
                otherBottomPixel.setColor(otherTopPixel.getColor());
            }
        }
    }

    /** Mirrors the seagull in "seagull.jpg"
     * to the right so that there are two seagulls on 
     * the beach near each other
     */
    public void mirrorGull()
    {
        int mirrorPoint = 375;
        Pixel rightPixel = null;
        Pixel leftPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        for(int row = 230; row < 320; row++)
        {
            for(int col = 235; col < 348 ; col++)
            {
                rightPixel = pixels[row][col];
                leftPixel = pixels[row][col - 235 + mirrorPoint];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic,int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length && toRow < toPixels.length; fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length && toCol < toPixels[0].length; fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        } 
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /** Method to create a collage that copies just part of fromPic*/ 
    public void copy2(Picture fromPic, int startRow, int startCol, int fromStartRow, int fromEndRow, int fromStartCol, int fromEndCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        int a = fromPixels.length;
        int b = fromPixels[0].length;
        for(int fromRow = fromStartRow, toRow = startRow; fromRow < fromEndRow && toRow < toPixels.length; fromRow++, toRow++)
        {
            for(int fromCol = fromStartCol, toCol = startCol; fromRow < fromEndCol && toCol < toPixels[0].length; fromCol++, toCol++)
            {
                if(fromRow < a && fromCol < b){
                    fromPixel = fromPixels[fromRow][fromCol];
                    toPixel = toPixels[toRow][toCol];
                    toPixel.setColor(fromPixel.getColor());
                }
            }
        }
    }

    /** My own collage that has at least three pictures copied 
     * three times with three different picture manipulations 
     * and at least one mirroring
     */
    public void myCollage()
    {
        
    /** My own collage that has at least three pictures copied 
     * three times with three different picture manipulations 
     * and at least one mirroring
     */
    public void myCollage()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (int row = 0; row < pixels.length; row++)
        {
            for(int col = 0; col < pixels[row].length; col++)
            {
                Pixel a = pixels[row][col];
                a.setGreen((int)(Math.random()*256));
                a.setBlue((int)(Math.random()*256));
                a.setRed((int)(Math.random()*256));
            }       
        }
        Picture krabs = new Picture();
        Picture mouse = new Picture();
        Picture frog = new Picture();
        Picture sponge = new Picture();
        Picture mouse2 = new Picture();
        mouse.grayscale();
        mouse2.grayscale();
        frog.negate();
        krabs.keepOnlyBlue();
        this.copy(krabs.scale(.,.), 0, 0);
        this.mirrorVertical();
        this.mirrorHorizontal();
        this.copyPartial (mouse, , , , , );
        this.copyPartial (frog.scale(.,.), , , , , ,);
        this.copyPartial (sponge, , , , , ,);
        this.copyPartial (mouse2, , , , , ,);
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        {
            Pixel leftPixel = null;
            Pixel rightPixel = null;
            Pixel[][] pixels = this.getPixels2D();
            Color rightColor = null;
            for (int row = 0; row < pixels.length; row++)
            {
                for (int col = 0; col < pixels[0].length-1; col++)
                {
                    leftPixel = pixels[row][col];
                    rightPixel = pixels[row][col+1];
                    rightColor = rightPixel.getColor();
                    if (leftPixel.colorDistance(rightColor) > edgeDist)
                    {
                        leftPixel.setColor(Color.BLACK);
                    }
                    else 
                    {
                        leftPixel.setColor(Color.WHITE);
                    }
                }
            }
        }
    }

    /** Method  that compares the current pixel with 
     * the one below and sets the current pixel color 
     * to black as well when the color distance is greater
     * than the specified edge distance
     * */
    public void edgeDetection2(int edgeDist)
    {        
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel currentPixel = null;
        //Pixel otherPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        Color currentColor = null;
        //Color otherColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                if (row < pixels.length - 1)
                {
                    currentPixel = pixels[row+1][col];
                    //otherPixel = pixels[row+2][col];
                    rightColor = rightPixel.getColor();
                    currentColor = currentPixel.getColor();
                    //otherColor = otherPixel.getColor();
                }
                if((leftPixel.colorDistance(rightColor) > edgeDist)) //| leftPixel.colorDistance(otherColor) > edgeDist)
                {
                    leftPixel.setColor(Color.BLUE);
                }
                if((leftPixel.colorDistance(currentColor) > edgeDist))
                {
                    leftPixel.setColor(Color.RED);
                }
                //if(leftPixel.colorDistance(otherColor) > edgeDist)
                //{
                  //  leftPixel.setColor(Color.GREEN);
                //}
                else 
                {
                    leftPixel.setColor(Color.BLACK);
                }
            }
        }
    }

    /** Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }
} // this } is the end of class Picture, put all new methods before this

