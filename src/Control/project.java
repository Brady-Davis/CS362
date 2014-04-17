package Control;

import java.util.ArrayList;
import java.util.Date;

public class project extends assignment {
	ArrayList<String> teamMembers = new ArrayList<String>();
	public project(String title, Date due, ArrayList<String> team) {
		super(title, due);
		teamMembers = team;
	}

	public ArrayList<String> getMembers()
	{
		return teamMembers;
	}
}
