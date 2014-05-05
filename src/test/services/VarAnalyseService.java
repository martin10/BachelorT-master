package test.services;

import java.io.IOException;
import java.util.ArrayList;


import test.readExcel;
import test.models.VarAnalyse;

public abstract class VarAnalyseService 
{

	public static void ImportFromExcelFile(String filePath) throws Exception 
	{

		ArrayList sheetData = new ArrayList();
		ArrayList<VarAnalyse> varAnalyser = new ArrayList();

		try {
			// Read file and return ArrayList
			sheetData = readExcel.getSheetDataAsArrayList(filePath);
			varAnalyser = convertSheetDataToVarAnalyse(sheetData);

			//System.out.println("|\tFelt_ID\t|\tValdnr. HR\t|\tValdnavn\t\t|\tJaktfeltnr. HR\t|\tJaktfeltnavn\t\t|\tJaktleder\t|\tDato\t|\tAlder\t|\tVeid Vekt\t|\tKj�nn\t|\tFelt dyr\t|\tAntall kalv\t|\tAntall tagger\t|\tMerknad\tAntatt\t|\tVekt\t|\tTvilling\t|");
			
			for(int i=0; i < varAnalyser.size(); i++) {
				//System.out.println(tannAnalyser.get(i).toString());
				varAnalyser.get(i).save();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static ArrayList convertSheetDataToVarAnalyse(ArrayList sheetData) 
	{
		ArrayList varAnalyser = new ArrayList();
		
		for (int i = 1; i < sheetData.size(); i++) {
			ArrayList list = (ArrayList) sheetData.get(i);
			VarAnalyse varAnalyse = new VarAnalyse();
			varAnalyse.mapSheetDataRow(list);
			varAnalyser.add(varAnalyse);
			
		}
		
		return varAnalyser;
	}
	
	// Other methods like getAll, getBlipByBlop and so on.
	
}