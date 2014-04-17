package Control;

import java.sql.SQLException;
import java.util.ArrayList;

public class course {
	String courseName = "";
	String professor = "";
	ArrayList<assignment> assignments = new ArrayList<assignment>();
	public course(String title, String instructor)
	{
		courseName = title;
		professor = instructor;
	}
	
	@Override
	public String toString()
	{
		return "Class: " + courseName + ", Instructor: " + professor;
	}
	
	public static void main(String args[])
	{
		try {
			databaseController db = new databaseController();
			db.addUser("test", "test");
			for(course e : db.getAllCourses())
			{
				System.out.println(e.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
