package test.models;

import java.sql.*;
import java.util.*;
import java.lang.reflect.*;

import test.dal.DBManager;

public abstract class Persistable {
	
	protected int id;
	protected String tableName;
	protected Boolean stored = false;
	
	public int getID() { return id; }
	public String getTableName(){ return tableName; }
	
    
	// CRUD
	
	// Get
    public String getLoadStatement() { return "SELECT * FROM " + tableName + " WHERE id = ?"; }
    public void mapGetRequest(PreparedStatement stmt) throws SQLException { stmt.setInt(1, id); }
    public abstract void mapGetResponse(ResultSet rs) throws SQLException;
    
    public void load(int id) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.instance().getConnection();
			stmt = con.prepareStatement(getLoadStatement());
			mapGetRequest(stmt);

			rs = stmt.executeQuery();
			
			try {
				if(rs.next()) { 
					mapGetResponse(rs);
					stored = true;
				}

			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try{
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
            } catch(SQLException e){
            	e.printStackTrace();
            }
		}
	}
    
    // Save >> Create or Update
 	public void save() throws Exception {
 		try {
 			if(stored.equals(false)) {
 				create();
 			}
 			else { 
 				update();
 			}
 		} 
 		catch(Exception e) {
 			
 		}
 	}
    
    // Create
    public abstract String getCreateStatement();
    public void mapCreateRequest(PreparedStatement ps) throws SQLException{}
    public void mapCreateResponse(ResultSet rs) throws SQLException {
        id = (int)rs.getInt(1);
    }
    
    protected void create() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.instance().getConnection();
			stmt = con.prepareStatement(getCreateStatement());
			mapCreateRequest(stmt);
			stmt.executeUpdate();
			stmt.executeQuery("SELECT LAST_INSERT_ID()");
			rs = stmt.getResultSet();
			rs.next();
			mapCreateResponse(rs);
			//con.commit();
			stored = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try{
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
            } catch(SQLException e){
            	e.printStackTrace();
            }
		}
	}
    
    
    // Update
    public abstract String getUpdateStatement();
    public abstract void mapUpdateRequest(PreparedStatement ps) throws SQLException;
	
	
	protected void update() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBManager.instance().getConnection();
			stmt = con.prepareStatement(getUpdateStatement());
			mapUpdateRequest(stmt);
			stmt.executeUpdate();

			//con.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try{
	            if (stmt != null) stmt.close();
            } catch(SQLException e){
            	e.printStackTrace();
            }
		}
	}

    // Delete
	public String getDeleteStatement() { return "DELETE FROM " + tableName + " WHERE id=?"; }
    public void mapDeleteRequest(PreparedStatement stmt) throws SQLException { stmt.setInt(1, id); }
	
	public void delete() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBManager.instance().getConnection();
			stmt = con.prepareStatement(getDeleteStatement());
			mapDeleteRequest(stmt);
			stmt.executeUpdate();

			//con.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try{
	            if (stmt != null) stmt.close();
            } catch(SQLException e){
            	e.printStackTrace();
            }
		}
	}

}
