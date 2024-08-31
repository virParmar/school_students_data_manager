package com.thinking.machines.school.ui;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.utils.*;
import java.text.*;
import java.util.*;

public class StudentUI
{
	private void drawLine(int size)
	{
		for(int i=0;i<size;i++)
		{
			System.out.print("-");
		}
		System.out.println();
	}
	public java.util.Date toDate(String dateString)
	{
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		try
		{
		java.util.Date dateOfBirth;
		dateOfBirth=simpleDateFormat.parse(dateString);
		String pcs1[],pcs2[];
		pcs1=dateString.split("/");
		pcs2=simpleDateFormat.format(dateOfBirth).split("/");
		int dd1,mm1,yyyy1;
		int dd2,mm2,yyyy2;
		dd1=Integer.parseInt(pcs1[0]);
		mm1=Integer.parseInt(pcs1[1]);
		yyyy1=Integer.parseInt(pcs1[2]);
		dd2=Integer.parseInt(pcs2[0]);
		mm2=Integer.parseInt(pcs2[1]);
		yyyy2=Integer.parseInt(pcs2[2]);
		if(dd1!=dd2 || mm1!=mm2 || yyyy1!=yyyy2) return null;
		return dateOfBirth;
		}catch(ParseException parseException)
		{
			return null;
		}
	}
	public void addStudent()
	{
		int rollNumber;
		String name;
		char gender;
		char isIndian;
		String dateOfBirthString;
		java.util.Date dateOfBirth;
		char confirm;
		drawLine(40);
		System.out.println("Student add module");
		drawLine(40);
		rollNumber=Keyboard.getInt("Roll number : ");
		if(rollNumber<=0)
		{
			System.out.println("Invalid input");
			return;
		}
		name=Keyboard.getString("Name : ");
		// The ==null should be on left side
		if(name==null || name.length()==0)
		{
			System.out.println("Name required");
			return;
		}
		gender=Keyboard.getCharacter("Gender (M/F/T) : ");
		if(gender!='M' && gender!='F' && gender!='T')
		{
			System.out.println("Invalid gender");
			return;
		}
		isIndian=Keyboard.getCharacter("Is Indian (Y/N) : ");
		if(isIndian!='Y' && isIndian!='N')
		{
			System.out.println("Invalid input");
			return;
		}
		dateOfBirthString=Keyboard.getString("Date of Birth (dd/mm/yyyy): ");
		if(dateOfBirthString==null || dateOfBirthString.length()==0)
		{
			System.out.println("Invalid Date Of Birth");
			return;
		}
		dateOfBirth=toDate(dateOfBirthString);
		if(dateOfBirth==null)
		{
			System.out.println("Invalid Date Of Birth");
			return;
		}
		confirm=Keyboard.getCharacter("Save (Y/N) : ");
		if(confirm!='Y' && confirm!='N')
		{
			System.out.println("Invalid input");
			return;
		}
		if(confirm=='N')
		{
			System.out.println("Student not saved");
			return;
		}
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			StudentBeanInterface studentBeanInterface;
			studentBeanInterface=new StudentBean();
			studentBeanInterface.setRollNumber(rollNumber);
			studentBeanInterface.setName(name);
			if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
			else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
			else studentBeanInterface.setGender(GENDER.TRANSGENDER);
			if(isIndian=='Y') studentBeanInterface.setIsIndian(true);
			else studentBeanInterface.setIsIndian(false);	
			studentBeanInterface.setDateOfBirth(dateOfBirth);
			studentManagerInterface.add(studentBeanInterface);
			System.out.println("Student Saved");
		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
	public void updateStudent()
	{
		drawLine(40);
		System.out.println("Student update module");
		drawLine(40);
		int rollNumber;
		String name;
		char gender;
		char isIndian;
		String dateOfBirthString;
		java.util.Date dateOfBirth;
		char confirm;
		rollNumber=Keyboard.getInt("Roll number : ");
		if(rollNumber<=0)
		{
			System.out.println("Roll number should be greater than zero (0)");
			return;
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		StudentManagerInterface studentManagerInterface;
		StudentBeanInterface studentBeanInterface;
		studentManagerInterface=new StudentManager();
		try
		{
			studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
			if(studentBeanInterface==null) 
			{
				System.out.println("Invalid roll number");
				return;
			}
			System.out.println("Name : "+studentBeanInterface.getName());
			if(studentBeanInterface.getGender()==GENDER.MALE)
			{
				System.out.println("Gender : Male");
			}
			else if(studentBeanInterface.getGender()==GENDER.FEMALE)
			{
				System.out.println("Gender : Female");
			}
			else 
			{
				System.out.println("Gender : Transgender");
			}
			if(studentBeanInterface.getIsIndian())
			{
				System.out.println("Is Indian : True");
			}
			else
			{
				System.out.println("Is Indian : False");
			}
			System.out.println("Date of Birth : "+simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
			confirm=Keyboard.getCharacter("Edit (Y/N) : ");
			if(confirm=='y') confirm='Y';
			if(confirm=='n') confirm='N';
			if(confirm!='Y' && confirm!='N')
			{
				System.out.println("Invalid choice, Student not updated");
				return;
			}
			if(confirm=='N')
			{
				System.out.println("Student not updated");
				return;
			}
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
		}
		name=Keyboard.getString("Name : ");
		if(name==null || name.length()==0)
		{
			System.out.println("Name required");
			return;
		}
		gender=Keyboard.getCharacter("Gender (M/F/T) : ");
		if(gender=='m') gender='M';
		if(gender=='f') gender='F';
		if(gender=='t') gender='T';
		if(gender!='M' && gender!='F' && gender!='T')
		{
			System.out.println("Invalid input");
			return;
		}
		isIndian=Keyboard.getCharacter("Is Indian (Y/N) : ");
		if(isIndian=='y') isIndian='Y';
		if(isIndian=='n') isIndian='N';
		if(isIndian!='Y' && isIndian!='N')
		{
			System.out.println("Invalid input");
			return;
		}
		dateOfBirthString=Keyboard.getString("Date of birth (dd/mm/yyyy) : ");
		if(dateOfBirthString==null || dateOfBirthString.length()==0)
		{
			System.out.println("Date of birth required");
			return;
		}
		dateOfBirth=toDate(dateOfBirthString);
		confirm=Keyboard.getCharacter("Update (Y/N) : ");
		if(confirm=='y') confirm='Y';
		if(confirm=='n') confirm='N';
		if(confirm!='Y' && confirm!='N')
		{
			System.out.println("Invalid choice, Student not updated");
			return;
		}
		if(confirm=='N')
		{
			System.out.println("Student not updated");
			return;
		}
		try
		{
			studentBeanInterface=new StudentBean();
			studentBeanInterface.setRollNumber(rollNumber);
			studentBeanInterface.setName(name);
			if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
			else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
			else studentBeanInterface.setGender(GENDER.TRANSGENDER);
			if(isIndian=='Y') studentBeanInterface.setIsIndian(true);
			else studentBeanInterface.setIsIndian(false);
			studentBeanInterface.setDateOfBirth(dateOfBirth);
			studentManagerInterface.update(studentBeanInterface);
			System.out.println("Student updated");
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
			System.out.println("Studet not updated");
		}
	}
	public void deleteStudent()
	{
		drawLine(40);
		System.out.println("Student update module");
		drawLine(40);
		int rollNumber;
		String name;
		char gender;
		char isIndian;
		String dateOfBirthString;
		java.util.Date dateOfBirth;
		char confirm;
		rollNumber=Keyboard.getInt("Roll number : ");
		if(rollNumber<=0)
		{
			System.out.println("Roll number should be greater than zero (0)");
			return;
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		StudentManagerInterface studentManagerInterface;
		StudentBeanInterface studentBeanInterface;
		studentManagerInterface=new StudentManager();
		try
		{
			studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
			if(studentBeanInterface==null) 
			{
				System.out.println("Invalid roll number");
				return;
			}
			System.out.println("Name : "+studentBeanInterface.getName());
			if(studentBeanInterface.getGender()==GENDER.MALE)
			{
				System.out.println("Gender : Male");
			}
			else if(studentBeanInterface.getGender()==GENDER.FEMALE)
			{
				System.out.println("Gender : Female");
			}
			else 
			{
				System.out.println("Gender : Transgender");
			}
			if(studentBeanInterface.getIsIndian())
			{
				System.out.println("Is Indian : True");
			}
			else
			{
				System.out.println("Is Indian : False");
			}
			System.out.println("Date of Birth : "+simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
			confirm=Keyboard.getCharacter("Delete (Y/N) : ");
			if(confirm=='y') confirm='Y';
			if(confirm=='n') confirm='N';
			if(confirm!='Y' && confirm!='N')
			{
				System.out.println("Invalid choice, Student not deleted");
				return;
			}
			if(confirm=='N')
			{
				System.out.println("Student not deleted");
				return;
			}
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
		}
		try
		{
			studentBeanInterface=new StudentBean();
			studentManagerInterface.delete(rollNumber);
			System.out.println("Student deleted");
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
			System.out.println("Studet not deleted");
		}
	}
	public void getStudent()
	{
		drawLine(40);
		System.out.println("Student update module");
		drawLine(40);
		int rollNumber;
		rollNumber=Keyboard.getInt("Roll number : ");
		if(rollNumber<=0)
		{
			System.out.println("Roll number should be greater than zero (0)");
			return;
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		StudentManagerInterface studentManagerInterface;
		StudentBeanInterface studentBeanInterface;
		studentManagerInterface=new StudentManager();
		try
		{
			studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
			if(studentBeanInterface==null) 
			{
				System.out.println("Invalid roll number");
				return;
			}
			System.out.println("Name : "+studentBeanInterface.getName());
			if(studentBeanInterface.getGender()==GENDER.MALE)
			{
				System.out.println("Gender : Male");
			}
			else if(studentBeanInterface.getGender()==GENDER.FEMALE)
			{
				System.out.println("Gender : Female");
			}
			else 
			{
				System.out.println("Gender : Transgender");
			}
			if(studentBeanInterface.getIsIndian() == true)
			{
				System.out.println("Is Indian : True");
			}
			else
			{
                                System.out.println("Is Indian : "+studentBeanInterface.getIsIndian());
				System.out.println("Is Indian : False");
			}
			System.out.println("Date of Birth : "+simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
		}
	}
	public void getStudents()
	{
		drawLine(40);
		System.out.println("Student List get module");
		drawLine(40);
		int choice;
		List<StudentBeanInterface> blStudents;
		System.out.println("1. List");
		System.out.println("2. List ordered by name");
		System.out.println("3. List ordered by date of birth");
		System.out.println("4. List ordered by age");
		System.out.println("5. List ordered by gender");
		choice=Keyboard.getInt("Enter your choice : ");
		try
		{
			StudentManagerInterface studentManagerInterface;
			studentManagerInterface=new StudentManager();
			if(choice==1)
			{
				blStudents=studentManagerInterface.getAll(ORDER_BY.ROLL_NUMBER);
			}
			else if(choice==2)
			{
				blStudents=studentManagerInterface.getAll(ORDER_BY.NAME);
			}
			else if(choice==3)
			{
				blStudents=studentManagerInterface.getAll(ORDER_BY.DATE_OF_BIRTH);
			}
			else if(choice==4)
			{
				blStudents=studentManagerInterface.getAll(ORDER_BY.AGE);
			}
			else if(choice==5)
			{
				blStudents=studentManagerInterface.getAll(ORDER_BY.GENDER);
			}
			else
			{
				System.out.println("Invalid choice");
				return;
			}
			GENDER gender;
			SimpleDateFormat simpleDateFormat;
			simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
			for(StudentBeanInterface studentBeanInterface : blStudents)
			{
				System.out.println("Roll number : "+studentBeanInterface.getRollNumber());
				System.out.println("Name : "+studentBeanInterface.getName());
				gender=studentBeanInterface.getGender();		
				if(gender==GENDER.MALE) System.out.println("Gender : Male");
				else if(gender==GENDER.FEMALE) System.out.println("Gender : Female");
				else System.out.println("Gender : Transgender");
				System.out.println("Is Indian : "+studentBeanInterface.getIsIndian());
				System.out.println(simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
			}
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
		}
	}
	public void getFilteredStudents()
	{
		drawLine(40);
		System.out.println("List filtered student module");
		drawLine(40);
		System.out.println("1. Gender");
		System.out.println("2. Age");
		System.out.println("3. Date of Birth");
		System.out.println("4. Indian");
		System.out.println("5. Foreigners");
		int key=Keyboard.getInt("Select any filter key (1-5) : ");
		if(key<1 || key>5)
		{
			System.out.println("Invalid key");
		}
		List<StudentBeanInterface> blStudents=null;
		StudentManagerInterface studentManagerInterface;
		studentManagerInterface=new StudentManager();
		try
		{
		if(key==1)
		{
			char gender=Keyboard.getCharacter("Enter gender (M/F/T) : ");
			if(gender=='m') gender='M';
			if(gender=='f') gender='F';
			if(gender=='t') gender='T';
			if(gender!='M' && gender!='F' && gender!='T')
			{
				System.out.println("Invalid gender");
				return;
			}
			if(gender=='M') blStudents=studentManagerInterface.getByGender(GENDER.MALE,ORDER_BY.ROLL_NUMBER);
			else if(gender=='F') blStudents=studentManagerInterface.getByGender(GENDER.FEMALE,ORDER_BY.ROLL_NUMBER);
			else blStudents=studentManagerInterface.getByGender(GENDER.TRANSGENDER,ORDER_BY.ROLL_NUMBER);
		}
		else if(key==2)
		{
			int age=Keyboard.getInt("Enter age : ");
			blStudents=studentManagerInterface.getByAge(age,ORDER_BY.ROLL_NUMBER);
		}
		else if(key==3)
		{
			String dateOfBirthString=Keyboard.getString("Enter date of Birth (dd/mm/yyyy) : ");
			if(dateOfBirthString==null || dateOfBirthString.length()==0)
			{
				System.out.println("Invalid date of birth");
				return;
			}
			java.util.Date dateOfBirth;
			dateOfBirth=toDate(dateOfBirthString);
			if(dateOfBirth==null)
			{
				System.out.println("Invalid date of birth");
				return;
			}
			blStudents=studentManagerInterface.getByDateOfBirth(dateOfBirth,ORDER_BY.ROLL_NUMBER);
		}
		else if(key==4)
		{
			blStudents=studentManagerInterface.getIndians(ORDER_BY.ROLL_NUMBER);
		}
		else if(key==5)
		{
			blStudents=studentManagerInterface.getForeigners(ORDER_BY.ROLL_NUMBER);
                }
		GENDER gender;
 		SimpleDateFormat simpleDateFormat;
		simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		for(StudentBeanInterface studentBeanInterface : blStudents)
		{
			System.out.println("Roll number : "+studentBeanInterface.getRollNumber());
			System.out.println("Name : "+studentBeanInterface.getName());
			gender=studentBeanInterface.getGender();		
			if(gender==GENDER.MALE) System.out.println("Gender : Male");
			else if(gender==GENDER.FEMALE) System.out.println("Gender : Female");
			else System.out.println("Gender : Transgender");
			System.out.println("Is Indian : "+studentBeanInterface.getIsIndian());
			System.out.println(simpleDateFormat.format(studentBeanInterface.getDateOfBirth()));
		}
		}catch(BLException blException)
		{
			System.out.println(blException.getMessage());
		}
	}
}
