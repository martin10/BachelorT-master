package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javax.swing.*;

import java.awt.event.*;

public class ExportDataTann extends main implements ActionListener {
	
	
    public static void main(String[] args) {
    	
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://sql4.freemysqlhosting.net/sql435045","sql435045","mG2%nR5!");
            Statement statement = con.createStatement();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("Tannanalyse.xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Sheet 0");
            Row row1 = worksheet.createRow((short)0);
            row1.createCell(0).setCellValue("id");
            row1.createCell(1).setCellValue("Feltid");
            row1.createCell(3).setCellValue("ValdNr");
            row1.createCell(4).setCellValue("JaktFeltNr");
            row1.createCell(5).setCellValue("JaktFeltNavn");
            row1.createCell(6).setCellValue("JaktLeder");
            row1.createCell(7).setCellValue("Dato");
            row1.createCell(8).setCellValue("Alder");
            row1.createCell(9).setCellValue("VeidVekt");
            row1.createCell(10).setCellValue("Kjønn");
            row1.createCell(11).setCellValue("FeltDyr");
            row1.createCell(12).setCellValue("AntallKalv");
            row1.createCell(13).setCellValue("AntallTagger");
            row1.createCell(14).setCellValue("Merknad");
            row1.createCell(14).setCellValue("AntattVekt");

            Row row2 ;
            ResultSet rs = statement.executeQuery("SELECT * FROM tannanalyse");
            while(rs.next()){
                int a = rs.getRow();
                row2 = worksheet.createRow((short)a);
                row2.createCell(0).setCellValue(rs.getString(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getString(6));
                row2.createCell(6).setCellValue(rs.getString(7));
                row2.createCell(7).setCellValue(rs.getString(8));
                row2.createCell(8).setCellValue(rs.getString(9));
                row2.createCell(9).setCellValue(rs.getString(10));
                row2.createCell(10).setCellValue(rs.getString(11));
                row2.createCell(11).setCellValue(rs.getString(12));
                row2.createCell(12).setCellValue(rs.getString(13));
                row2.createCell(13).setCellValue(rs.getString(14));
                row2.createCell(14).setCellValue(rs.getString(15));
                
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();
            statement.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Din data er nå exportert til tannanalyse.xls");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(SQLException ex){
            System.out.println(ex);
        }catch(IOException ioe){
            System.out.println(ioe);
        }

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
