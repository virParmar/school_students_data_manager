import com.thinking.machines.school.dl.dao.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.exceptions.*;
import java.text.*;
import java.util.*;
class StudentGetAllTestCase
{
	public static void main(String args[])
	{
		try
		{
		int rollNumber;
		String name;
		char gender;
		boolean isIndian;
		java.util.Date dateOfBirth;
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		String dateOfBirthString;
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface=new StudentDAO();
		StudentDTOInterface studentDTOInterface;
		List<StudentDTOInterface> students;
		students=studentDAOInterface.getAll();
		int x;
		for(x=0;x<students.size();x++)
		{
		studentDTOInterface=students.get(x);
		rollNumber=studentDTOInterface.getRollNumber();
		name=studentDTOInterface.getName();
		gender=studentDTOInterface.getGender();
		isIndian=studentDTOInterface.getIsIndian();
		dateOfBirth=studentDTOInterface.getDateOfBirth();
		dateOfBirthString=simpleDateFormat.format(dateOfBirth);
		System.out.println("Rollnumber : "+rollNumber);
		System.out.println("Name : "+name);
		System.out.println("Gender : "+gender);
		System.out.println("Is Indian : "+isIndian);
		System.out.println("Date of birth : "+dateOfBirthString);
		}
		}catch(DAOException daoException)
		{
			System.out.println(daoException.getMessage());
		}
	}
}
