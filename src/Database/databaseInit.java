package Database;
import java.sql.*;
public class databaseInit
{	
	public static void addNewUser(String name, String pwd, int new_id, int is_student)
	{
			String connInfo = "jdbc:sqlite:/Users/Lauren/Documents/workspace/StudentDatabase.db";
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(connInfo);
			Statement mystatement = conn.createStatement();
			mystatement.setQueryTimeout(30);
			String sql = "INSERT INTO Students " +
					"(UMID, username, password) " +
					"VALUES (" + new_id + ", '" + name + "', '" +
					pwd +"'"+ ")";
					mystatement.executeUpdate(sql);
		}
		catch (SQLException sqlEx)
		{
			System.out.println("Got a SQLException");
			System.out.println(sqlEx.getMessage());
		}
		catch (ClassNotFoundException cnfEx)
		{
			System.out.println("Got a ClassNotFoundException!");
		}
	}
};
