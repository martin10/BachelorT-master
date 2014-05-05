package test.models;

import java.sql.*;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class VarAnalyse extends Persistable {

	private String kald, varm, sno;
	
	public VarAnalyse() {
		tableName = "vardata";
	}

	//Getters
	public String getKald() { return kald; }
	public String getVarm() { return varm; }
	public String getSno() { return sno; }


	//Setters
	public void setKald(String kald) { this.kald = kald; }
	public void setVarm(String varm) { this.varm = varm; }
	public void setSno(String sno) { this.sno = sno; }



	
	public void mapGetResponse(ResultSet rs) throws SQLException {
		varm = rs.getString("varm");
		sno = rs.getString("sno");
		kald = rs.getString("kald");

	}
	
	// Create
	public String getCreateStatement() {
		return "INSERT INTO vardata (kald, varm, sno) VALUES (?, ?, ?)";
	}
	
	public void mapCreateRequest(PreparedStatement ps) throws SQLException {
	
	
		ps.setString(1, kald);
		ps.setString(2, varm);
		ps.setString(3, sno);
	}
	
	public void mapCreateResponse(ResultSet rs) throws SQLException {
		kald = rs.getString(1);
		

	}
	
	// Update
	public String getUpdateStatement() {
		return "UPDATE `" + tableName + "` SET `eik`=?, `ros`=?, `einer`=?, "
				+ "`bjørk`=?, `kald`=?"
				+ "WHERE id=?";
	
}
	
	public void mapUpdateRequest(PreparedStatement ps) throws SQLException {
	;
	ps.setString(1, kald);
	ps.setString(2, varm);
	ps.setString(3, sno);
	}
	
	// To String
	public String toString() {
		//return FeltID + " - " + ValdNrR+ " - \"" + kald + "\" - " + varm + " - " + "\"" + sno + "\" - " + Einer;
		return "|\t" + kald + "\t|\t" + varm + "\t|\t" + sno + "\t|";
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
			
			kald = (String) values.get(0);
			varm = (String) values.get(1);
			sno = (String) values.get(2);

			
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
