package Database;
import java.sql.*;
public class databaseInit
{
	public static Connection conn;
	public databaseInit(String name) throws SQLException, ClassNotFoundException
	{
		String connInfo = "jdbc:sqlite:" + name + ".db";

		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(connInfo);
		Statement stmt = conn.createStatement();
    String sql = "CREATE TABLE STUDENTS " +
                 "(ID INT PRIMARY KEY     NOT NULL," +
                 " UNIQNAME           TEXT    NOT NULL," + 
                 " NAME            TEXT     NOT NULL," + 
                 "CORRECT       INT      NOT NULL," + 
                 "OUT_OF       INT      NOT NULL"+ ")"; 
    stmt.executeUpdate(sql);
    stmt.close();
	}
	
	public static void addNewUser(String name, String pwd, int new_id, int is_student)
	{
		try
		{
		Statement mystatement = conn.createStatement();
		mystatement.setQueryTimeout(30);
		String sql = "INSERT INTO Students " +
				"(UMID, username, password) " +
				"VALUES (" + new_id + ", '" + name + "', '" +
				pwd +"'"+ ")";
				mystatement.executeUpdate(sql);
		}
		catch(SQLException sqlEx)
		{
			System.out.println("Got a SQLException");
			System.out.println(sqlEx.getMessage());
		}
	}
};
