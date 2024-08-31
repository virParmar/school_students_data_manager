import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
import java.util.*;
import java.text.*;
class StudentGetByDateOfBirthTestCase
{
	public static void main(String args[])
	{
		StudentManagerInterface studentManagerInterface;
		StudentBeanInterface studentBeanInterafce;
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		List<StudentBeanInterface> blStudents;
		java.util.Date dateOfBirth;
		int dd,mm,yyyy;
		dd=Integer.parseInt(args[0]);
		mm=Integer.parseInt(args[1]);
		yyyy=Integer.parseInt(args[2]);
		dateOfBirth=new java.util.Date(yyyy-1900,mm-1,dd);
		try
		{
			studentManagerInterface=new StudentManager();
			blStudents=studentManagerInterface.getByDateOfBirth(dateOfBirth,ORDER_BY.NAME);
			for(StudentBeanInterface studentBeanInterface : blStudents)
			{
				System.out.println(studentBeanInterface.getRollNumber());
				System.out.println(studentBeanInterface.getName());
				System.out.println(studentBeanInterface.getGender());
				System.out.println(studentBeanInterface.getIsIndian());
				System.out.println(simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
			}
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
		}
	}
}
