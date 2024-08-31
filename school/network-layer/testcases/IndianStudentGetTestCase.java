import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import java.util.*;
import java.text.*;
import com.thinking.machines.school.bl.enums.*;
class IndianStudentGetTestCase
{
	public static void main(String args[])
	{
		java.util.Date dateOfBirth;
		String dateOfBirthString;
		SimpleDateFormat simpleDateFormat;
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			List<StudentBeanInterface> blStudents;
			blStudents=studentManagerInterface.getIndians(ORDER_BY.NAME);
			for(StudentBeanInterface studentBeanInterface : blStudents)
			{
			System.out.println(studentBeanInterface.getRollNumber());
			System.out.println(studentBeanInterface.getName());
			System.out.println(studentBeanInterface.getGender());
			System.out.println(studentBeanInterface.getIsIndian());
			dateOfBirth=studentBeanInterface.getDateOfBirth();
			simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
			dateOfBirthString=simpleDateFormat.format(dateOfBirth);
			System.out.println(dateOfBirthString);
			}
		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
}
