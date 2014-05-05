package test.services;

import java.io.IOException;
import java.util.ArrayList;


import test.readExcel;
import test.models.SkogAnalyse;

public abstract class SkogAnalyseService 
{

	public static void ImportFromExcelFile(String filePath) throws Exception 
	{

		ArrayList sheetData = new ArrayList();
		ArrayList<SkogAnalyse> skogAnalyser = new ArrayList();

		try {
			// Read file and return ArrayList
			sheetData = readExcel.getSheetDataAsArrayList(filePath);
			skogAnalyser = convertSheetDataToSkogAnalyse(sheetData);

			//System.out.println("|\tFelt_ID\t|\tValdnr. HR\t|\tValdnavn\t\t|\tJaktfeltnr. HR\t|\tJaktfeltnavn\t\t|\tJaktleder\t|\tDato\t|\tAlder\t|\tVeid Vekt\t|\tKj�nn\t|\tFelt dyr\t|\tAntall kalv\t|\tAntall tagger\t|\tMerknad\tAntatt\t|\tVekt\t|\tTvilling\t|");
			
			for(int i=0; i < skogAnalyser.size(); i++) {
				//System.out.println(tannAnalyser.get(i).toString());
				skogAnalyser.get(i).save();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static ArrayList convertSheetDataToSkogAnalyse(ArrayList sheetData) 
	{
		ArrayList skogAnalyser = new ArrayList();
		
		for (int i = 1; i < sheetData.size(); i++) {
			ArrayList list = (ArrayList) sheetData.get(i);
			SkogAnalyse skogAnalyse = new SkogAnalyse();
			skogAnalyse.mapSheetDataRow(list);
			skogAnalyser.add(skogAnalyse);
			
		}
		
		return skogAnalyser;
	}
	
	// Other methods like getAll, getBlipByBlop and so on.
	
}