package test.services;

import java.io.IOException;
import java.util.ArrayList;


import test.readExcel;
import test.models.BeiteAnalyse;

public abstract class BeiteAnalyseService 
{

	public static void ImportFromExcelFile(String filePath) throws Exception 
	{

		ArrayList sheetData = new ArrayList();
		ArrayList<BeiteAnalyse> beiteAnalyser = new ArrayList();

		try {
			// Read file and return ArrayList
			sheetData = readExcel.getSheetDataAsArrayList(filePath);
			beiteAnalyser = convertSheetDataToBeiteAnalyse(sheetData);

			//System.out.println("|\tFelt_ID\t|\tValdnr. HR\t|\tValdnavn\t\t|\tJaktfeltnr. HR\t|\tJaktfeltnavn\t\t|\tJaktleder\t|\tDato\t|\tAlder\t|\tVeid Vekt\t|\tKj�nn\t|\tFelt dyr\t|\tAntall kalv\t|\tAntall tagger\t|\tMerknad\tAntatt\t|\tVekt\t|\tTvilling\t|");
			
			for(int i=0; i < beiteAnalyser.size(); i++) {
				//System.out.println(tannAnalyser.get(i).toString());
				beiteAnalyser.get(i).save();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static ArrayList convertSheetDataToBeiteAnalyse(ArrayList sheetData) 
	{
		ArrayList beiteAnalyser = new ArrayList();
		
		for (int i = 1; i < sheetData.size(); i++) {
			ArrayList list = (ArrayList) sheetData.get(i);
			BeiteAnalyse beiteAnalyse = new BeiteAnalyse();
			beiteAnalyse.mapSheetDataRow(list);
			beiteAnalyser.add(beiteAnalyse);
			
		}
		
		return beiteAnalyser;
	}
	
	// Other methods like getAll, getBlipByBlop and so on.
	
}