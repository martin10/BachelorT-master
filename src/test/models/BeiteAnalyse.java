package test.models;

import java.sql.*;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class BeiteAnalyse extends Persistable {

	private String furu, Eik, Ros, Einer, Bjork;
	
	public BeiteAnalyse() {
		tableName = "beiteregistrering";
	}

	//Getters
	public String getfuru() { return furu; }
	public String getEik() { return Eik; }
	public String getRos() { return Ros; }
	public String getEiner() { return Einer; }
	public String getBjork() { return Bjork; }

	//Setters
	public void setfuru(String furu) { this.furu = furu; }
	public void setEik(String Eik) { this.Eik = Eik; }
	public void setEiner(String Einer) { this.Einer = Einer; }
	public void setRos(String Ros) { this.Ros = Ros; }
	public void setBjork(String Bjork) { this.Bjork = Bjork; }


	
	public void mapGetResponse(ResultSet rs) throws SQLException {
		Eik = rs.getString("Eik");
		Ros = rs.getString("Ros");
		Einer = rs.getString("Einer");
		Bjork = rs.getString("Bjork");
		furu = rs.getString("furu");

	}
	
	// Create
	public String getCreateStatement() {
		return "INSERT INTO beiteregistrering (eik, ros, einer, bjørk, furu) VALUES (?, ?, ?, ?, ?)";
	}
	
	public void mapCreateRequest(PreparedStatement ps) throws SQLException {
	
	
		ps.setString(1, Eik);
		ps.setString(2, Ros);
		ps.setString(3, Einer);
		ps.setString(4, Bjork);
		ps.setString(5, furu);
	
	}
	
	public void mapCreateResponse(ResultSet rs) throws SQLException {
		Eik = rs.getString(1);
		

	}
	
	// Update
	public String getUpdateStatement() {
		return "UPDATE `" + tableName + "` SET `eik`=?, `ros`=?, `einer`=?, "
				+ "`bjørk`=?, `furu`=?"
				+ "WHERE id=?";
	
}
	
	public void mapUpdateRequest(PreparedStatement ps) throws SQLException {
	;
	ps.setString(1, Eik);
	ps.setString(2, Ros);
	ps.setString(3, Einer);
	ps.setString(4, Bjork);
	ps.setString(5, furu);
		
	}
	
	// To String
	public String toString() {
		//return FeltID + " - " + ValdNrR+ " - \"" + furu + "\" - " + Eik + " - " + "\"" + Ros + "\" - " + Einer;
		return "|\t" + Eik + "\t|\t" + Ros + "\t|\t" + Einer + "\t|\t" + 
		Bjork + "\t|\t" + furu + "\t|";
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
			
			Eik = (String) values.get(0);
			Ros = (String) values.get(1);
			Einer = (String) values.get(2);
			Bjork = (String) values.get(3);
			furu = (String) values.get(4);
			
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
