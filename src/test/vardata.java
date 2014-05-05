package test;


import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class vardata extends Mats {
	
	public vardata(String VarDataKald, String VarDataVarm, String VarDataSno) {
    	
    	  this.VarDataKald = VarDataKald;
    	  this.VarDataVarm = VarDataVarm;
    	  this.VarDataSno = VarDataSno;

    	    
	}
    private static String db_table = "vardata";
    // JDBC driver og db url 
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://sql4.freemysqlhosting.net/sql435045";


    //  Database logg inn
    static final String USER = "sql435045";
    static final String PASS = "mG2%nR5!";

	private String VarDataKald;
	private String VarDataVarm;
	private String VarDataSno;

	

		public static void main(String[] args) {
		@SuppressWarnings("unused")
		main frameTabel = new main();
		}
	
		vardata(){
		
		// vet ikke hvorfor dette er her, men f??r ikke kj??rt programmet uten. 
		setSize(800,600);
		setLocation(500,280);

	    
	    
		//Henter content fra panel og setter visible til true slik at det vises
		BeiteContent.setVisible(true);
    	mainContent.setVisible(false);
    	VarDataContent.setVisible(true);
        registrerTann.setVisible(false);
		}
		
	    
		public boolean doUpdate(boolean commit, boolean close){     
	        try {           
	            DBM dbm = new DBM();
	            vardata.clientUpdate(dbm, this, close, commit);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public static vardata getByVarData(DBM dbm, String VarDataKald, String VarDataVarm, String VarDataSno,  boolean close) throws Exception{
	        vardata b                 = null;
	        PreparedStatement preState = null;
	        ResultSet resultSet        = null;
	        try {
	        	Class.forName("com.mysql.jdbc.driver");
	            String sql = "SELECT * FROM "+db_table;
	            preState   = dbm.initConnection().prepareStatement(sql); 
	            preState.setString(1, VarDataKald);
	            preState.setString(2, VarDataVarm);
	            preState.setString(3, VarDataSno);

	            
	           
	         
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
	    

	    public static boolean clientUpdate(DBM dbm, vardata b, boolean close, boolean commit) throws Exception{        
	        boolean returnValue = true;
	        PreparedStatement preState = null;
	        try {
	            vardata dbo = getByVarData(dbm,b.getVarDataKald(),b.getVarDataVarm(),b.getVarDataSno(), false);           
	            if(dbo==null){
	                String sql = null;
	               

	                //SQL insert sp??rringen og rekkef??lgen p?? hvordanden skal tolke stringene
	                sql = "INSERT INTO vardata (kald, varm, sno) VALUES (?,?,?)";
	                preState = dbm.initConnection().prepareStatement(sql); 
	               
	                preState.setString(1, b.getVarDataKald());
	                preState.setString(2, b.getVarDataVarm());
	                preState.setString(3, b.getVarDataSno());

	               	                
	                preState.executeUpdate();
	            }else{              
	                String sql = null;
	                sql = "UPDATE  "+db_table+" set _vardata=?";
	                preState = dbm.initConnection().prepareStatement(sql); 
	                      //Ikke k??dd med rekkef??lgen, dette funker. 
	                preState.setString(1, b.getVarDataKald());
	                preState.setString(2, b.getVarDataVarm());
	                preState.setString(3, b.getVarDataSno());	             
	               
	                
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
		public String getVarDataKald() {
	        return VarDataKald;
	    }
	    public void setVarDataKald(String VarDataKald) {
	        this.VarDataKald = VarDataKald;
	    }
		public String getVarDataVarm() {
	        return VarDataVarm;
	    }
	    public void setVarDataVarm(String VarDataVarm) {
	        this.VarDataVarm = VarDataVarm;
	        
	    }	public String getVarDataSno() {
	        return VarDataSno;
	    }
	    public void setVarDataSno(String VarDataSno) {
	        this.VarDataSno = VarDataSno;
	    }
	
}
