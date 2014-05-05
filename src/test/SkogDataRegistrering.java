package test;


import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SkogDataRegistrering extends Mats {
	
	public SkogDataRegistrering(String aar, String avvirketm3, String daaRyddet, String Kommune) {
    	
    	  this.aar = aar;
    	  this.avvirketm3 = avvirketm3;
    	  this.daaRyddet = daaRyddet;
    	  this.Kommune = Kommune;

    	    
	}
    private static String db_table = "skoganalyse";
    // JDBC driver og db url 
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://sql4.freemysqlhosting.net/sql435045";


    //  Database logg inn
    static final String USER = "sql435045";
    static final String PASS = "mG2%nR5!";

	private String aar;
	private String avvirketm3;
	private String daaRyddet;
	private String Kommune;

	
	

		public static void main(String[] args) {
		@SuppressWarnings("unused")
		main frameTabel = new main();
		}
	
		SkogDataRegistrering(){
		
		// vet ikke hvorfor dette er her, men f??r ikke kj??rt programmet uten. 
		setSize(800,600);
		setLocation(500,280);

	    
	    
		//Henter content fra panel og setter visible til true slik at det vises
		BeiteContent.setVisible(false);
    	mainContent.setVisible(false);
    	VarDataContent.setVisible(false);
        registrerTann.setVisible(false);
        SkogDataContent.setVisible(true);
		}
		
	    
		public boolean doUpdate(boolean commit, boolean close){     
	        try {           
	            DBM dbm = new DBM();
	            SkogDataRegistrering.clientUpdate(dbm, this, close, commit);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public static SkogDataRegistrering getBySkogDataRegistrering(DBM dbm, String aar, String avvirketm3, String daaRyddet, String Kommune, boolean close) throws Exception{
	    	SkogDataRegistrering b                 = null;
	        PreparedStatement preState = null;
	        ResultSet resultSet        = null;
	        try {
	        	Class.forName("com.mysql.jdbc.driver");
	            String sql = "SELECT * FROM "+db_table;
	            preState   = dbm.initConnection().prepareStatement(sql); 
	            preState.setString(1, aar);
	            preState.setString(2, avvirketm3);
	            preState.setString(3, daaRyddet);
	            preState.setString(4, Kommune);
	         
	            resultSet  = preState.executeQuery();
	            while (resultSet.next()) {
	            
	            	
	            	 int id = resultSet.getInt("Feltid");
	            	
	                 String msg = resultSet.getString("Feltid");
	                 System.out.println(id + "\t" + msg);
	                
	            }
	         
	               
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            if( preState != null )
	                preState.close();
	            if( close && dbm.connection != null )
	                dbm.connection.close();         
	        }
	        return b;
	    }
	    

	    public static boolean clientUpdate(DBM dbm, SkogDataRegistrering b, boolean close, boolean commit) throws Exception{        
	        boolean returnValue = true;
	        PreparedStatement preState = null;
	        try {
	        	SkogDataRegistrering dbo = getBySkogDataRegistrering(dbm,b.getaar(),b.getavvirketm3(), b.getdaaRyddet(), b.getKommune(),  false);           
	            if(dbo==null){
	                String sql = null;
	               

	                //SQL insert sp??rringen og rekkef??lgen p?? hvordanden skal tolke stringene
	                sql = "INSERT INTO skoganalyse (aar, avvirketm3, daaRyddet, kommune ) VALUES (?,?,?,?)";
	                preState = dbm.initConnection().prepareStatement(sql); 
	               
	                preState.setString(1, b.getaar());
	                preState.setString(2, b.getavvirketm3());
	                preState.setString(3, b.getdaaRyddet());
	                preState.setString(4, b.getKommune());
	               	                
	                preState.executeUpdate();
	            }else{              
	                String sql = null;
	                sql = "UPDATE  "+db_table+" set _vardata=?";
	                preState = dbm.initConnection().prepareStatement(sql); 
	                      //Ikke k??dd med rekkef??lgen, dette funker. 
	                preState.setString(1, b.getaar());
	                preState.setString(2, b.getavvirketm3());
	                preState.setString(3, b.getdaaRyddet());
	                preState.setString(4, b.getKommune());

	             
	               
	                
	            }
	            
	        }catch (Exception e) {
	            returnValue = false; 
	            e.printStackTrace();
	        }finally{
	            if( preState != null )
	                preState.close();
	            if( dbm.connection != null ){
	                if( commit ){
	                    if( returnValue )
	                        dbm.connection.commit();
	                    else
	                        dbm.connection.rollback();
	                }
	                if( close )
	                    dbm.connection.close();
	            }
	        }
	        return returnValue;
	    }
	    

	//Get og set for alle stringene. 
		public String getaar() {
	        return aar;
	    }
	    public void setaar(String aar) {
	        this.aar = aar;
	    }
		public String getavvirketm3() {
	        return avvirketm3;
	    }
	    public void setavvirketm3(String avvirketm3) {
	        this.avvirketm3 = avvirketm3;
	    }
		public String getdaaRyddet() {
	        return aar;
	    }
	    public void setdaaRyddet(String daaRyddet) {
	        this.daaRyddet = daaRyddet;
	    }
		public String getKommune() {
	        return Kommune;
	    }
	    public void setKommune(String Kommune) {
	        this.Kommune = Kommune;
	    }
	
}