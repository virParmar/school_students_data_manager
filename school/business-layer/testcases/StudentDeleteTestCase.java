import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
class StudentDeleteTestCase
{
	public static void main(String args[])
	{
		try
		{
			int rollNumber;
			rollNumber=Integer.parseInt(args[0]);
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			studentManagerInterface.delete(rollNumber);
			System.out.println("Student deleted");

		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
}
