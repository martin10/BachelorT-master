package test.dal;

import java.sql.*;

public class DBManager {
	
    private String db_server;
    private String db_user;
    private String db_password;
    private String db_driver;

    private static DBManager instance;
    private Connection connection;
    

    public DBManager() throws Exception {
        init();
    }

   
    private void init() throws Exception{
        // Editer dette hvis du er kobla mot en annen database.
        db_server   = "jdbc:mysql://sql4.freemysqlhosting.net/sql435045";
        db_user     = "sql435045";
        db_password = "mG2%nR5!";
        db_driver   = "com.mysql.jdbc.Driver";
        Class.forName(db_driver);
    }   
   
    public Connection initConnection() throws Exception{
        if( this.connection == null ){
            this.connection = DriverManager.getConnection(db_server, db_user, db_password);
            this.connection.setAutoCommit(false);
        }else if( this.connection.isClosed() ){
            this.connection = null;
            this.connection = DriverManager.getConnection(db_server, db_user, db_password);
            this.connection.setAutoCommit(false);
        }
        return this.connection;
    }
    
    public static DBManager instance() throws Exception {
    	try {
	    	if(instance == null) 
	    		instance = new DBManager();
	    	if(instance.connection == null) 
	    		instance.connection = DriverManager.getConnection(
    				instance.db_server, 
    				instance.db_user, 
    				instance.db_password
				);
	    		//instance.connection.setAutoCommit(false);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }

    
    public void closeConnection(){
        try {
            if(this.connection != null ){
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitConnection(){
        try {
            if( this.connection != null && !this.connection.isClosed() ){
                this.connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackConnection(){
        try {
            if( this.connection != null && !this.connection.isClosed() ){
                this.connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}