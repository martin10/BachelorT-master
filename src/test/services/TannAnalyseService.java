package test.services;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;

import test.readExcel;
import test.models.TannAnalyse;

public abstract class TannAnalyseService 
{

	public static void ImportFromExcelFile(String filePath) throws Exception 
	{

		ArrayList sheetData = new ArrayList();
		ArrayList<TannAnalyse> tannAnalyser = new ArrayList();
		
		try {
			// Read file and return ArrayList
			sheetData = readExcel.getSheetDataAsArrayList(filePath);
			tannAnalyser = convertSheetDataToTannAnalyse(sheetData);
			
			//System.out.println("|\tFelt_ID\t|\tValdnr. HR\t|\tValdnavn\t\t|\tJaktfeltnr. HR\t|\tJaktfeltnavn\t\t|\tJaktleder\t|\tDato\t|\tAlder\t|\tVeid Vekt\t|\tKjønn\t|\tFelt dyr\t|\tAntall kalv\t|\tAntall tagger\t|\tMerknad\tAntatt\t|\tVekt\t|\tTvilling\t|");
			
			for(int i=0; i < tannAnalyser.size(); i++) {
				//System.out.println(tannAnalyser.get(i).toString());
				tannAnalyser.get(i).save();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static ArrayList convertSheetDataToTannAnalyse(ArrayList sheetData) 
	{
		ArrayList tannAnalyser = new ArrayList();
		
		for (int i = 1; i < sheetData.size(); i++) {
			ArrayList list = (ArrayList) sheetData.get(i);
			TannAnalyse tannAnalyse = new TannAnalyse();
			tannAnalyse.mapSheetDataRow(list);
			tannAnalyser.add(tannAnalyse);
			
		}
		
		return tannAnalyser;
	}
	
	// Other methods like getAll, getBlipByBlop and so on.
	
}