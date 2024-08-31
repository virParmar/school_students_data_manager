import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import java.util.*;
import java.text.*;
import com.thinking.machines.school.bl.enums.*;
class StudentGetByGenderTestCase
{
	public static void main(String args[])
	{
		String genderString;
		GENDER gender;
		java.util.Date dateOfBirth;
		SimpleDateFormat simpleDateFormat;
		String dateOfBirthString;
		genderString=args[0];
		if(genderString.equals("MALE")) gender=GENDER.MALE;
		else if(genderString.equals("FEMALE")) gender=GENDER.FEMALE;
		else if(genderString.equals("TRANSGENDER")) gender=GENDER.TRANSGENDER;
		else{
			System.out.println("Invalid gender");
			return;
		}
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			List<StudentBeanInterface> blStudents;
			blStudents=studentManagerInterface.getByGender(gender,ORDER_BY.NAME);
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
