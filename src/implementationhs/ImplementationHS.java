/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementationhs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import sun.rmi.runtime.Log;

/**
 *
 * @author user
 */
public class ImplementationHS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        File imgFile = new File("D:\\end.JPG");
        BufferedImage img = null;
        
        try{
            img = ImageIO.read(imgFile);
        }catch(Exception e){
            System.out.println(e);
//            Logger.getLogger(main(args).)
            System.exit(0);
        }
        int [][] dimensifoto = new int[img.getHeight()][img.getWidth()];
        for (int y = 0; y < dimensifoto.length; y++) {
            for (int x = 0; x < dimensifoto[y].length; x++) {
                dimensifoto[y][x] = img.getRGB(x, y);
            }
        }
        
        
        
        //merubah pesan menjadi binary
        String message = "hesoyam";
        char[] messageChar = message.toCharArray();
        String pwd = "";
        for (int i = 0; i < messageChar.length; i++) {
            int tmp01 = (int)messageChar[i];
            String tmp02 = Integer.toBinaryString(tmp01);
            pwd +=String.format("%1$7s", tmp02).replace(' ', '1');
        }
        
//        mendapatkan histogram
        int [] histogram = new int [256];
        for (int y = 0; y < dimensifoto.length; y++) {
            for (int x = 0; x < dimensifoto[y].length; x++) {
                int tmp = (dimensifoto [y] [x] >> 16)& 0xFF;
                histogram[tmp]++;
            }
        }
        System.out.print("Histogram : ");
        for (int i = 0; i <histogram.length; i++) {
            System.out.print(histogram[i]+" ");
        }
        System.out.println("");
        
//        menentukan peak dan zero point
        int peak = 0, peakValue = histogram[0];
        int zero = 0, zeroValue = histogram[0];
        for (int i = 0; i <histogram.length; i++) {
            if (histogram[i]> peakValue) {
                peak =i;
                peakValue=histogram[i];
            }if (histogram[i]<zeroValue) {
                zero=i;
                zeroValue=histogram[i];
            }
        }
        System.out.println("Peak point : "+peak +"Value :"+peakValue);
        System.out.println("Zero point : "+ zero + "Value :"+zeroValue);
    }
    
}
