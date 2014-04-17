package Control;

import java.util.Date;

public class assignment {
	String name = "";
	Date dueDate = new Date();
	public assignment(String title, Date due)
	{
		name = title;
		dueDate = due;
	}
	
	public Date checkDueDate()
	{
		return dueDate;
	}
}
