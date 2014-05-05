package test.models;

//import java.text.Format;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;



//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class TannAnalyse extends Persistable {
	private int FeltID, FeltDyr, AntallKalv, AntallTagger;
	private double Alder, VeidVekt, AntattVekt;
	private String ValdNr, ValdNavn, JaktfeltNr, JaktfeltNavn, JaktLeder, Kjonn, Merknad;
	private Date Dato;
	
	public TannAnalyse() {
		tableName = "tannanalyse";
	}
	
	// Getters
	public int getFeltID() { return FeltID; }
	public String getValdNr() { return ValdNr;}
	public String getValdNavn() { return ValdNavn; }
	public String getJaktfeltNr() { return JaktfeltNr; }
	public String getJaktfeltNavn() { return JaktfeltNavn; }
	public String getJaktLeder() { return JaktLeder; }
	public Date getDato() { return Dato; }
	public double getAlder() { return Alder; }
	public double getVeidVekt() { return VeidVekt; }
	public String getKjonn() { return Kjonn; }
	public int getFeltDyr() { return FeltDyr; }
	public int getAntallKalv() { return AntallKalv; }
	public int getAntallTagger() { return AntallTagger; }
	public String getMerknad() { return Merknad; }
	public double getAntattVekt() { return AntattVekt; }
	
	// Setters
	public void setFeltID(int FeltID) { this.FeltID = FeltID; }
	public void setValdNr(String ValdNr) { this.ValdNr = ValdNr; }
	public void setValdNavn(String ValdNavn) { this.ValdNavn = ValdNavn; }
	public void setJaktfeltNr(String JaktfeltNr) { this.JaktfeltNr = JaktfeltNr; }
	public void setJaktfeltNavn(String JaktfeltNavn) { this.JaktfeltNavn = JaktfeltNavn; }
	public void setJaktLeder(String JaktLeder) { this.JaktLeder = JaktLeder; }
	public void setDato(Date Dato) { this.Dato = Dato; }
	public void setAlder(double Alder) { this.Alder = Alder; }
	public void setVeidVekt(double VeidVekt) { this.VeidVekt = VeidVekt; }
	public void setKjonn(String Kjonn) { this.Kjonn = Kjonn; }
	public void setFeltDyr(int FeltDyr) { this.FeltDyr = FeltDyr; }
	public void setAntallKalv(int AntallKalv) { this.AntallKalv = AntallKalv; }
	public void setAntallTagger(int AntallTagger) { this.AntallTagger = AntallTagger; }
	public void setMerknad(String Merknad) { this.Merknad = Merknad; }
	public void setAntattVekt(double AntattVekt) { this.AntattVekt = AntattVekt; }
	
	// CRUD Requests
	//
	// Get
	public void mapGetResponse(ResultSet rs) throws SQLException {
		id = rs.getInt("id");
		FeltID = rs.getInt("FeltId");
		ValdNr = rs.getString("ValdNr");
		ValdNavn = rs.getString("ValdNavn");
		JaktfeltNr = rs.getString("JaktfeltNr");
		JaktfeltNavn = rs.getString("JaktfeltNavn");
		JaktLeder = rs.getString("JaktLeder");
		Dato = rs.getDate("Dato");
		Alder = rs.getDouble("Alder");
		VeidVekt = rs.getDouble("VeidVekt");
		Kjonn = rs.getString("Kjonn");
		FeltDyr = rs.getInt("FeltDyr");
		AntallKalv = rs.getInt("AntallKalv");
		AntallTagger = rs.getInt("AntallTagger");
		Merknad = rs.getString("Merknad");
		AntattVekt = rs.getDouble("AntattVekt");
	}
	
	// Create
	public String getCreateStatement() {
		return "INSERT INTO `" + tableName + "` (`FeltId`, `ValdNr`, `ValdNavn`, "
				+ "`JaktFeltNr`, `JaktFeltNavn`, `JaktLeder`, `Dato`, `Alder`, `VeidVekt`, "
				+ "`Kjonn`, `FeltDyr`, `AntallKalv`, `AntallTagger`, `Merknad`, `AntattVekt`) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	}
	
	public void mapCreateRequest(PreparedStatement ps) throws SQLException {
		ps.setInt(1, FeltID);
		ps.setString(2, ValdNr);
		ps.setString(3, ValdNavn);
		ps.setString(4, JaktfeltNr);
		ps.setString(5, JaktfeltNavn);
		ps.setString(6, JaktLeder);
		ps.setDate(7, Dato);
		ps.setDouble(8, Alder);
		ps.setDouble(9, VeidVekt);
		ps.setString(10, Kjonn);
		ps.setInt(11, FeltDyr);
		ps.setInt(12, AntallKalv);
		ps.setInt(13, AntallTagger);
		ps.setString(14, Merknad);
		ps.setDouble(15, AntattVekt);
	}
	
	public void mapCreateResponse(ResultSet rs) throws SQLException {
		id = rs.getInt(1);
	}
	
	// Update
	public String getUpdateStatement() {
		return "UPDATE `" + tableName + "` SET `FeltId`=?, `ValdNr`=?, `ValdNavn`=?, "
				+ "`JaktFeltNr`=?, `JaktFeltNavn`=?, `JaktLeder`=?, `Dato`=?, `Alder`=?, `VeidVekt`=?, "
				+ "`Kjonn`=?, `FeltDyr`=?, `AntallKalv`=?, `AntallTagger`=?, `Merknad`=?, `AntattVekt`=? "
				+ "WHERE id=?";
	}
	
	public void mapUpdateRequest(PreparedStatement ps) throws SQLException {
		ps.setInt(1, FeltID);
		ps.setString(2, ValdNr);
		ps.setString(3, ValdNavn);
		ps.setString(4, JaktfeltNr);
		ps.setString(5, JaktfeltNavn);
		ps.setString(6, JaktLeder);
		ps.setDate(7, Dato);
		ps.setDouble(8, Alder);
		ps.setDouble(9, VeidVekt);
		ps.setString(10, Kjonn);
		ps.setInt(11, FeltDyr);
		ps.setInt(12, AntallKalv);
		ps.setInt(13, AntallTagger);
		ps.setString(14, Merknad);
		ps.setDouble(15, AntattVekt);
		ps.setInt(16, id);
	}
	
	// To String
	public String toString() {
		//return FeltID + " - " + ValdNr + " - \"" + ValdNavn + "\" - " + JaktfeltNr + " - " + "\"" + JaktfeltNavn + "\" - " + JaktLeder;
		//String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(Dato);
		return "|\t" + FeltID + "\t|\t" + ValdNr + "\t|\t" + ValdNavn + "\t|\t" + 
				JaktfeltNr + "\t|\t" + JaktfeltNavn + "\t|\t" + JaktLeder + "\t|\t" + 
				Dato + "\t|\t" + Alder + "\t|\t" + VeidVekt + "\t|\t" + 
				Kjonn + "\t|\t" + FeltDyr + "\t|\t" + AntallKalv + "\t|\t" +
				AntallTagger + "\t|\t" + Merknad + "\t|\t" + AntattVekt + "\t|";
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
			FeltID = (int) Integer.parseInt((String) values.get(0));
			ValdNr = (String) values.get(1);
			ValdNavn = (String) values.get(2);
			JaktfeltNr = (String) values.get(3);
			JaktfeltNavn = (String) values.get(4);
			JaktLeder = (String) values.get(5);
			//Dato = (Date) new SimpleDateFormat("dd.MM.yyyy").parse((String) values.get(6));
			Dato = (Date) values.get(6);
			Alder = (double) Double.parseDouble((String) values.get(7));
			VeidVekt = (double) Double.parseDouble((String) values.get(8));
			Kjonn = (String) values.get(9);
			FeltDyr = (int) Integer.parseInt((String) values.get(10));
			AntallKalv = (int) Integer.parseInt((String) values.get(11));
			AntallTagger = (int) Integer.parseInt((String) values.get(12));
			Merknad = (String) values.get(13);
			AntattVekt = (double) Double.parseDouble((String) values.get(14));
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
