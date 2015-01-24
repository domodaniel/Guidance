/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guidance2015;

import guidance2015.fileUtilities.USB_FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Uyematsu
 */
public class Guidance2015 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        USB_FileReader.setDefaultMediaDevice("D:\\");
        USB_FileReader.setDefaultDir("sdb1\\");
        String[][] data;
        RoboCMDTable obj;
        try {
            USB_FileReader fr = new USB_FileReader("CSV0.csv");
            data = fr.read();
            obj = new RoboCMDTable(data.length - 1);
            for(int i = 0; i < data.length - 2; i++){
                int di = i + 1;
                System.out.println(Integer.parseInt(data[di][4]));
                System.out.println((data[di][5]));
                System.out.print("adf");
                obj.setRow(i, Double.parseDouble(data[di][1]), Double.parseDouble(data[di][2]),
                    Double.parseDouble(data[di][3]), Integer.parseInt(data[di][4]), Integer.parseInt(data[di][5]));
            }
            obj.printStuff();
        } catch (IOException ex) {
            Logger.getLogger(Guidance2015.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    
}
class RoboCMDTable{
    private double[] x;
    private double[] y;
    private double[] angle;
    private int[] articCMD;
    private int[] checkFlag;
    public RoboCMDTable(int numRows){
        x = new double[numRows];
        y = new double[numRows];
        angle = new double[numRows];
        checkFlag = new int[numRows];
        articCMD = new int[numRows];
    }
    public void setRow(int i, double x, double y, double ang,  int artic, int flag){
        this.x[i] = x;
        this.y[i] = y;
        angle[i] = ang;
        articCMD[i] = artic;
        checkFlag[i] = flag;
    }
    public void printStuff() {
        System.out.printf("SeqNum\tx\ty\tangle\tarticCMD\tcheckFlag\t");
        for(int i =  0; i < x.length - 1; i++) {
            System.out.printf("%d\t%f\t%f\t%f\t%d\t%d\n", i, x[i], y[i], angle[i], articCMD[i], checkFlag[i]);
        }
    }
}