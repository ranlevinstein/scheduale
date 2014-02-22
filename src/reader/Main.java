package reader;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;






import java.awt.FlowLayout;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main implements Runnable {
    @Override
    
    public void run() {
    	


    }
 
    public static void main(String[] args) throws IOException {
        // Create the window
    	JFrame frame = new JFrame("Good Morning");
    	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  JPanel panel = new JPanel();
    	  panel.setLayout(null);
    	  frame.add(panel);
    	  

        
        //
        /////////////////////////////////////////////////////////setting to current date
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String month = Integer.toString(Integer.parseInt(dateFormat.format(date)));
        dateFormat = new SimpleDateFormat("DD");
        date = new Date();
        String day = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        String address = "http://handasaim.co.il/_Uploads/dbsAttachedFiles/";
        address+=day+"-"+month;
        address+=".xls";
        String localFileName = day+"-"+month;
        localFileName +=".xls";
        ////////////////////////////////////////////////////////
        //until apple script use write manually the day.
        System.out.println(address);
        address = "http://handasaim.co.il/_Uploads/dbsAttachedFiles/23-2.xls";
        
        for (int i = 0; i < address.length(); i++) {
            download(address, localFileName);
        }
        read(localFileName, panel);
        
        
        
        frame.setSize(1600,900);
  	  frame.setVisible(true);
    }
    
    
    
    
    public static void download(String address, String localFileName) {
        OutputStream out = null;
        URLConnection conn = null;
        InputStream in = null;
        try {
            URL url = new URL(address);
            out = new BufferedOutputStream(new FileOutputStream(localFileName));
            conn = url.openConnection();
            in = conn.getInputStream();
            byte[] buffer = new byte[1024];

            int numRead;
            long numWritten = 0;

            while ((numRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, numRead);
                numWritten += numRead;
            }

            System.out.println("Downloading");
        } 
        
        catch (Exception exception) { 
            exception.printStackTrace();
        } 
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } 
            catch (IOException ioe) {
            }
        }
        
    }


    
    
    
    
     static void read(String localFileName, JPanel panel) throws IOException  {
        File inputWorkbook = new File(localFileName);
        Workbook w;
        try {
          w = Workbook.getWorkbook(inputWorkbook);
          // Get the first sheet
          Sheet sheet = w.getSheet(0);
          // Loop over first 10 column and lines

          JLabel[] labels = new JLabel[sheet.getRows()];
          JLabel label;
          boolean flag = true;
            for (int i = 1; i < sheet.getRows(); i++) {
              Cell cell = sheet.getCell(2, i);
              CellType type = cell.getType();
              if (type == CellType.LABEL) {
            	  labels[i-1] = new JLabel(i-1 + ": " + cell.getContents());
            	  labels[i-1].setBounds(700, i*50, 400, 50);
            	  panel.add(labels[i-1]);
            	  System.out.println(i-1 + ": " + cell.getContents());
              }

            }
          
        } catch (BiffException e) {
          e.printStackTrace();
        }
      }

 
}