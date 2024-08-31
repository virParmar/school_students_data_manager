import com.thinking.machines.school.dl.dao.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.exceptions.*;
import java.text.*;
class StudentGetTestCase
{
	public static void main(String args[])
	{
		try
		{
		int rollNumber;
		rollNumber=Integer.parseInt(args[0]);
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface=new StudentDAO();
		StudentDTOInterface studentDTOInterface;
		studentDTOInterface=studentDAOInterface.get(rollNumber);
		String name=studentDTOInterface.getName();
		char gender=studentDTOInterface.getGender();
		boolean isIndian=studentDTOInterface.getIsIndian();
		java.util.Date dateOfBirth;
		dateOfBirth=studentDTOInterface.getDateOfBirth();
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		String dateOfBirthString;
		dateOfBirthString=simpleDateFormat.format(dateOfBirth);
		System.out.println("Rollnumber : "+rollNumber);
		System.out.println("Name : "+name);
		System.out.println("Gender : "+gender);
		System.out.println("Is Indian : "+isIndian);
		System.out.println("Date of birth : "+dateOfBirthString);
		}catch(DAOException daoException)
		{
			System.out.println(daoException.getMessage());
		}
	}
}
