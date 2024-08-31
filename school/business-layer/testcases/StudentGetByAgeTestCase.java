import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
import java.util.*;
import java.text.*;
class StudentGetByAgeTestCase
{
	public static void main(String args[])
	{
		StudentManagerInterface studentManagerInterface;
		StudentBeanInterface studentBeanInterafce;
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		List<StudentBeanInterface> blStudents;
		int age;
		age=Integer.parseInt(args[0]);
		try
		{
			studentManagerInterface=new StudentManager();
			blStudents=studentManagerInterface.getByAge(age,ORDER_BY.NAME);
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
