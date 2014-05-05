package test;

import test.models.TannAnalyse;

public class test {
	
	public static void main(String[] args) {
		try {
			
			// Test load from DB
			/*TannAnalyse ta = new TannAnalyse();
			ta.load(1);
			System.out.println("TannAnalyse: " + ta.toString());*/
			
			// Test create and store to DB
			/*TannAnalyse ta = new TannAnalyse();
			ta.setFeltID(6);
			ta.setValdNr("6");
			ta.setValdNavn("Hjortvaldet");
			ta.save();
			System.out.println("TannAnalyse stored to DB. (ID: " + ta.getID() + ")");
			System.out.println("TannAnalyse - JaktFeltNr = " + ta.getJaktfeltNr());
			ta.setJaktfeltNr("1337");
			ta.save();
			System.out.println("TannAnalyse - JaktFeltNr endret.");
			System.out.println("TannAnalyse - JaktFeltNr = " + ta.getJaktfeltNr());
			System.out.println("Deleting TannAnalyse with ID: "+ta.getID());
			ta.delete();
			System.out.println("TannAnalyse deleted.");*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public test() {}
	
}
