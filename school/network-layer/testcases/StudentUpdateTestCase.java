import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
class StudentUpdateTestCase
{
	public static void main(String args[])
	{
		try
		{
		int rollNumber;
		String name;
		String genderString;
		GENDER gender;
		boolean isIndian;
		java.util.Date dateOfBirth;
		int dd,mm,yyyy;
		rollNumber=Integer.parseInt(args[0]);
		name=args[1];
		genderString=args[2];
		//gender=GENDER.valueOf(args[2]);
	        if(genderString.equals("MALE"))
		{
			gender=GENDER.MALE;
		}else if(genderString.equals("FEMALE"))
		{
			gender=GENDER.FEMALE;
		}else if(genderString.equals("TRANSGENDER"))
		{
			gender=GENDER.TRANSGENDER;
		}else 
		{
			throw new BLException("Invalid gender");
		}
		isIndian=Boolean.parseBoolean(args[3]);
		dd=Integer.parseInt(args[4]);
		mm=Integer.parseInt(args[5]);
		yyyy=Integer.parseInt(args[6]);
		dateOfBirth=new java.util.Date(yyyy-1900,mm-1,dd);
		StudentBeanInterface studentBeanInterface;
		studentBeanInterface=new StudentBean();
		studentBeanInterface.setRollNumber(rollNumber);
		studentBeanInterface.setName(name);
		studentBeanInterface.setGender(gender);
		studentBeanInterface.setIsIndian(isIndian);
		studentBeanInterface.setDateOfBirth(dateOfBirth);
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			studentManagerInterface.update(studentBeanInterface);
		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
}
