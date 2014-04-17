package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class databaseController {
	private Connection conn;
	
	public databaseController() throws SQLException {
		// throw new SQLException("This Class is no longer supported");
		String url = "jdbc:mysql://database.student.iastate.edu:3306/";
		String dbName = "planner";
		String driver = ("sun.jdbc.odbc.JdbcOdbcDriver");
		String userName = "java";
		String password = "12SQLdftba94";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("Something is wrong with the database.");
		}

	}
	public ArrayList<course> getAllCourses()
	{
		ArrayList<course> courses = new ArrayList<course>();
		try {
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("select * from classes");
			while(res.next())
			{
				courses.add(new course(res.getString("Class"), res.getString("Instructor")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	/**
	 * Gets the info from the users table and returns it in a list
	 * 
	 * @return the list of data
	 */
	public ArrayList<String> ViewUsersTable() {
		ArrayList<String> userInfo = new ArrayList<String>();
		try {
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  users");
			while (res.next()) {
				int id = res.getInt("id");
				String title = res.getString("username");

				userInfo.add((id + "\t" + title));
			}

			System.out.println("Successfully Ran");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userInfo;
	}
	
	public boolean addUser(String userName, String password) {
		int id = 0;
		for (String s : ViewUsersTable()) {
			String[] temp = s.split("\t");
			id = Integer.parseInt(temp[0]);
		} // gets the last id in the table
		id++;

		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO users SET username=\"" + userName
					+ "\"" + ",hash=\""
					+ JavaHasher.HashMyPass(password, userName) + "\""
					+ ",salt=\"" + userName + "\",userType=\"standard\";");

			System.out.println("User has been added");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String checkPermissions(String userName)
	{
		
		return "";
	}
}
