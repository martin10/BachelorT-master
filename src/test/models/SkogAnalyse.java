package test.models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.sql.*;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class SkogAnalyse extends Persistable {

	private int aar, avvirketm3, daaRyddet;
	private String kommune;
	
	public SkogAnalyse() {
		tableName = "skoganalyse";
	}
	//Getters
	public int getAar() { return aar; }
	public int getAvvirketm3() { return avvirketm3; }
	public int getDaaRyddet() { return daaRyddet; }
	public String getKommune() { return kommune; }
	
	//Setters
	public void setAar(int aar) { this.aar = aar; }
	public void setAvvirketm3(int avvirketm3) { this.avvirketm3 = avvirketm3; }
	public void setDaaRyddet(int daaRyddet) { this.daaRyddet = daaRyddet; }
	public void setKommune(String kommune) { this.kommune = kommune; }
	
	public void mapGetResponse(ResultSet rs) throws SQLException {
		aar = rs.getInt("aar");
		avvirketm3 = rs.getInt("avvirketm3");
		daaRyddet = rs.getInt("daaRyddet");
		kommune = rs.getString("kommune");
	
		
	}
	
	// Create
	public String getCreateStatement() {
		return "INSERT INTO skoganalyse (aar, avvirketm3, daaRyddet, kommune ) VALUES (?,?,?,?)";
				
	}
	
	public void mapCreateRequest(PreparedStatement ps) throws SQLException {
		ps.setInt(1, aar);
		ps.setInt(2, avvirketm3);
		ps.setInt(3, daaRyddet);
		ps.setString(4, kommune);
		
	}
	
	public void mapCreateResponse(ResultSet rs) throws SQLException {
		aar = rs.getInt(1);
	}
	
	// Update
	
	
	public void mapUpdateRequest(PreparedStatement ps) throws SQLException {
		ps.setInt(1, aar);
		ps.setInt(2, avvirketm3);
		ps.setInt(3, daaRyddet);
		ps.setString(4, kommune);
	}
	
	// To String
	public String toString() {
		//return FeltID + " - " + ValdNr + " - \"" + ValdNavn + "\" - " + JaktfeltNr + " - " + "\"" + JaktfeltNavn + "\" - " + JaktLeder;
		//String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(Dato);
		return "|\t" + aar + "\t|\t" + avvirketm3 + "\t|\t" + daaRyddet + "\t|\t" + 
				kommune + "\t|";
	}

	// Populate object with ArrayList based on Excel sheet data row.
	public void mapSheetDataRow(ArrayList row) 
	{
		ArrayList values = new ArrayList();
		
		for (int j = 0; j < row.size(); j++) {
			Cell cell = (Cell) row.get(j);
			
			if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {//cell.getColumnIndex() == 6 //DateUtil.isCellDateFormatted(cell.getDateCellValue())
				java.util.Date utilDate = cell.getDateCellValue();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				values.add(cell.getColumnIndex(), sqlDate);
			} else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				values.add(cell.getColumnIndex(), cell.getStringCellValue());
			}
		}
		
		try {
			aar = (int) Integer.parseInt((String) values.get(0));
			avvirketm3 = (int) Integer.parseInt((String) values.get(1));
			daaRyddet = (int) Integer.parseInt((String) values.get(2));
			kommune = (String) values.get(3);
		
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public String getUpdateStatement() {
		return "UPDATE `" + tableName + "` SET `FeltId`=?, `ValdNr`=?, `ValdNavn`=?, "
				+ "`JaktFeltNr`=?, `JaktFeltNavn`=?, `JaktLeder`=?, `Dato`=?, `Alder`=?, `VeidVekt`=?, "
				+ "`Kjonn`=?, `FeltDyr`=?, `AntallKalv`=?, `AntallTagger`=?, `Merknad`=?, `AntattVekt`=? "
				+ "WHERE id=?";
	}
	
}
