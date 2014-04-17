package Control;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import Utilities.JavaHasher;
import Utilities.SQLWrapper;

public class controller {
	public static void main(String args[])
	{
		
	}
	
	public static void login(String usr, String pw) throws SQLException
	{
		databaseController db = new databaseController();
		int id = db.GetUsersid(usr);
		if(JavaHasher.HashMyPass(pw, usr).equals(db.GetUsersHashedPass(usr)))
			System.out.println("Successfully Logged IN");
			
		else
			System.out.println("Username or Password incorrect");
	}
}
