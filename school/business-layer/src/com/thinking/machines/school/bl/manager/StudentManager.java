package com.thinking.machines.school.bl.manager;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.school.bl.comparators.*;
import java.util.*;

import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.dao.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.exceptions.*;
public class StudentManager implements  StudentManagerInterface
{
	private int getAge(java.util.Date dateOfBirth)
	{
		java.util.Date today=new java.util.Date();
		int todayDD=today.getDate();
		int todayMM=today.getMonth()+1;
		int todayYYYY=today.getYear()+1900;

		int dobDD=dateOfBirth.getDate();
		int dobMM=dateOfBirth.getMonth()+1;
		int dobYYYY=dateOfBirth.getYear()+1900;

		int age=todayYYYY-dobYYYY;
		if(todayMM < dobMM)
		{
			age--;
		}
		else if(todayMM == dobMM)
		{
			if(todayDD < dobDD)
			{
				age--;
			}
		}
		return age;
	}
	public void add(StudentBeanInterface studentBeanInterface) throws BLException
	{
		int rollNumber;
		String name;
		GENDER gender;
		boolean isIndian;
		java.util.Date dateOfBirth;
			rollNumber=studentBeanInterface.getRollNumber();
			name=studentBeanInterface.getName();
			gender=studentBeanInterface.getGender();
			isIndian=studentBeanInterface.getIsIndian();
			dateOfBirth=studentBeanInterface.getDateOfBirth();
			if(rollNumber<=0)
			{
				throw new BLException("Invalid roll number : "+rollNumber+", should be greater than Zero (0)");
			}
			if(name==null)
			{
				throw new BLException("Name required");
			}
			name=name.trim();
			if(name.length()==0)
			{
				throw new BLException("Name required");
			}
			if(name.length()>50)
			{
				throw new BLException("Name cannot exceed 50 characters");
			}
			if(dateOfBirth==null)
			{
				throw new BLException("Date of Birth required");
			}
			if(getAge(dateOfBirth)<5)
			{
				throw new BLException("Age cannot be less than 5");
			}
			StudentDTOInterface studentDTOInterface;
			studentDTOInterface=new StudentDTO();
			studentDTOInterface.setRollNumber(rollNumber);
			studentDTOInterface.setName(name);
			if(gender==GENDER.MALE) studentDTOInterface.setGender('M');
			else if(gender==GENDER.FEMALE) studentDTOInterface.setGender('F');
			else studentDTOInterface.setGender('T');
			studentDTOInterface.setIsIndian(isIndian);
			studentDTOInterface.setDateOfBirth(dateOfBirth);
			StudentDAOInterface studentDAOInterface;
			studentDAOInterface=new StudentDAO();
		try
		{
			studentDAOInterface.add(studentDTOInterface);	
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public void update(StudentBeanInterface studentBeanInterface) throws BLException
	{	
		int rollNumber;
		String name;
		GENDER gender;
		boolean isIndian;
		java.util.Date dateOfBirth;
			rollNumber=studentBeanInterface.getRollNumber();
			name=studentBeanInterface.getName();
			gender=studentBeanInterface.getGender();
			isIndian=studentBeanInterface.getIsIndian();
			dateOfBirth=studentBeanInterface.getDateOfBirth();
			if(rollNumber<=0)
			{
				throw new BLException("Invalid roll number : "+rollNumber+", should be greater than Zero (0)");
			}
			if(name==null)
			{
				throw new BLException("Name required");
			}
			name=name.trim();
			if(name.length()==0)
			{
				throw new BLException("Name required");
			}
			if(name.length()>50)
			{
				throw new BLException("Name cannot exceed 50 characters");
			}
			if(dateOfBirth==null)
			{
				throw new BLException("Date of Birth required");
			}
			if(getAge(dateOfBirth)<5)
			{
				throw new BLException("Age cannot be less than 5");
			}
			StudentDTOInterface studentDTOInterface;
			studentDTOInterface=new StudentDTO();
			studentDTOInterface.setRollNumber(rollNumber);
			studentDTOInterface.setName(name);
			if(gender==GENDER.MALE) studentDTOInterface.setGender('M');
			else if(gender==GENDER.FEMALE) studentDTOInterface.setGender('F');
			else studentDTOInterface.setGender('T');
			studentDTOInterface.setIsIndian(isIndian);
			studentDTOInterface.setDateOfBirth(dateOfBirth);
			StudentDAOInterface studentDAOInterface;
			studentDAOInterface=new StudentDAO();
		try
		{
			studentDAOInterface.update(studentDTOInterface);	
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}

	}
	public void delete(int rollNumber) throws BLException
	{
		if(rollNumber<=0)
		{
			throw new BLException("Invalid roll number : "+rollNumber+", should be greater than zero");
		}
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface=new StudentDAO();
		try
		{
			studentDAOInterface.delete(rollNumber);
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public StudentBeanInterface getByRollNumber(int rollNumber) throws BLException
	{
		if(rollNumber<=0)
		{
			throw new BLException("Invalid roll number : "+rollNumber);
		}
		String name;
		char vGender;
		GENDER gender;
		boolean isIndian;
		java.util.Date dateOfBirth;
		try
		{
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface=new StudentDAO();
		StudentDTOInterface studentDTOInterface;
		studentDTOInterface=new StudentDTO();
		studentDTOInterface=studentDAOInterface.get(rollNumber);
		name=studentDTOInterface.getName();
		vGender=studentDTOInterface.getGender();
		if(vGender=='M') gender=GENDER.MALE;
		else if(vGender=='F') gender=GENDER.FEMALE;
		else gender=GENDER.TRANSGENDER;
		isIndian=studentDTOInterface.getIsIndian();
		dateOfBirth=studentDTOInterface.getDateOfBirth();
		StudentBeanInterface studentBeanInterface;
		studentBeanInterface=new StudentBean();
		studentBeanInterface.setRollNumber(rollNumber);
		studentBeanInterface.setName(name);
		studentBeanInterface.setGender(gender);
                studentBeanInterface.setIsIndian(isIndian);
		studentBeanInterface.setDateOfBirth(dateOfBirth);
		return studentBeanInterface;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public List<StudentBeanInterface> getAll(ORDER_BY orderBy) throws BLException
	{
		List<StudentDTOInterface> dlStudents;
		List<StudentBeanInterface> blStudents;
		try
		{
			StudentDAOInterface studentDAOInterface;
			studentDAOInterface=new StudentDAO();
			StudentBeanInterface studentBeanInterface;
			dlStudents=studentDAOInterface.getAll();
			blStudents=new ArrayList<StudentBeanInterface>();
			char gender;
			for(StudentDTOInterface studentDTOInterface : dlStudents)
			{
				studentBeanInterface=new StudentBean();
				studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
				studentBeanInterface.setName(studentDTOInterface.getName());
				gender=studentDTOInterface.getGender();
				if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
				else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
				else studentBeanInterface.setGender(GENDER.TRANSGENDER);
				studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
				studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
				blStudents.add(studentBeanInterface);
			}
		         	if(orderBy == ORDER_BY.ROLL_NUMBER)
				{
					Collections.sort(blStudents,new StudentRollNumberComparator());
				}
				else if(orderBy == ORDER_BY.NAME)
				{
					Collections.sort(blStudents,new StudentNameComparator());
				}
				else if(orderBy == ORDER_BY.GENDER)
				{
					Collections.sort(blStudents,new StudentGenderComparator());
				}
				else if(orderBy == ORDER_BY.AGE)
				{
					Collections.sort(blStudents,new StudentAgeComparator());
				}
				else if(orderBy == ORDER_BY.DATE_OF_BIRTH)
				{
					Collections.sort(blStudents,new StudentDateOfBirthComparator());
				}
				return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public List<StudentBeanInterface> getByGender(GENDER gender,ORDER_BY orderBy) throws BLException
	{
		List<StudentDTOInterface> dlStudents;
		List<StudentBeanInterface> blStudents;
		StudentDAOInterface studentDAOInterface;
		StudentBeanInterface studentBeanInterface;
		try
		{
			studentDAOInterface=new StudentDAO();
			dlStudents=studentDAOInterface.getAll();
			blStudents=new ArrayList<StudentBeanInterface>();
			char gen=' ';
			GENDER g;
			for(StudentDTOInterface studentDTOInterface : dlStudents)
			{
				gen=studentDTOInterface.getGender();
				if(gen=='F') g=GENDER.FEMALE;
				else if(gen=='M') g=GENDER.MALE;
				else g=GENDER.TRANSGENDER;
				if(gender == g)
				{
				studentBeanInterface=new StudentBean();
				studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
				studentBeanInterface.setName(studentDTOInterface.getName());
				studentBeanInterface.setGender(gender);
				studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
				blStudents.add(studentBeanInterface);
				}
			}
				if(orderBy == ORDER_BY.ROLL_NUMBER)
				{
					Collections.sort(blStudents,new StudentRollNumberComparator());
				}
				else if(orderBy == ORDER_BY.NAME)
				{
					Collections.sort(blStudents,new StudentNameComparator());
				}
				else if(orderBy == ORDER_BY.GENDER)
				{
					Collections.sort(blStudents,new StudentGenderComparator());
				}
				else if(orderBy == ORDER_BY.AGE)
				{
					Collections.sort(blStudents,new StudentAgeComparator());
				}
				else if(orderBy == ORDER_BY.DATE_OF_BIRTH)
				{
					Collections.sort(blStudents,new StudentDateOfBirthComparator());
				}

			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}

	}
	public List<StudentBeanInterface> getByAge(int age,ORDER_BY orderBy) throws BLException
	{
		List<StudentDTOInterface> dlStudents;
		List<StudentBeanInterface> blStudents;
		StudentDAOInterface studentDAOInterface;
		StudentBeanInterface studentBeanInterface;
		char gender;
		try
		{
			studentDAOInterface=new StudentDAO();
			dlStudents=studentDAOInterface.getAll();
			blStudents=new ArrayList<StudentBeanInterface>();
			for(StudentDTOInterface studentDTOInterface : dlStudents)
			{
				if(getAge(studentDTOInterface.getDateOfBirth())==age)
				{
					studentBeanInterface=new StudentBean();
					studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
					studentBeanInterface.setName(studentDTOInterface.getName());
					gender=studentDTOInterface.getGender();
					if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
					else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
					else studentBeanInterface.setGender(GENDER.TRANSGENDER);
					studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
					studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
					blStudents.add(studentBeanInterface);
				}
			}
				if(orderBy == ORDER_BY.ROLL_NUMBER)
				{
					Collections.sort(blStudents,new StudentRollNumberComparator());
				}
				else if(orderBy == ORDER_BY.NAME)
				{
					Collections.sort(blStudents,new StudentNameComparator());
				}
				else if(orderBy == ORDER_BY.GENDER)
				{
					Collections.sort(blStudents,new StudentGenderComparator());
				}
				else if(orderBy == ORDER_BY.AGE)
				{
					Collections.sort(blStudents,new StudentAgeComparator());
				}
				else if(orderBy == ORDER_BY.DATE_OF_BIRTH)
				{
					Collections.sort(blStudents,new StudentDateOfBirthComparator());
				}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public List<StudentBeanInterface> getByDateOfBirth(java.util.Date dateOfBirth,ORDER_BY orderBy) throws BLException
	{
		List<StudentDTOInterface> dlStudents;
		List<StudentBeanInterface> blStudents;
		StudentDAOInterface studentDAOInterface;
		StudentBeanInterface studentBeanInterface;
		char gender;
		int dd,mm,yyyy;
		java.util.Date dob;
		try
		{
			studentDAOInterface=new StudentDAO();
			dlStudents=studentDAOInterface.getAll();
			blStudents=new ArrayList<StudentBeanInterface>();
			for(StudentDTOInterface studentDTOInterface : dlStudents)
			{
				dob=studentDTOInterface.getDateOfBirth();
				dd=dob.getDate();
				mm=dob.getMonth();
				yyyy=dob.getYear();
				if(dateOfBirth.getDate()==dd && dateOfBirth.getMonth()==mm && dateOfBirth.getYear()==yyyy)
				{
					studentBeanInterface=new StudentBean();
					studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
					studentBeanInterface.setName(studentDTOInterface.getName());
					gender=studentDTOInterface.getGender();
					if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
					else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
					else studentBeanInterface.setGender(GENDER.TRANSGENDER);
					studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
					studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
					blStudents.add(studentBeanInterface);
				}
			}
				if(orderBy == ORDER_BY.ROLL_NUMBER)
				{
					Collections.sort(blStudents,new StudentRollNumberComparator());
				}
				else if(orderBy == ORDER_BY.NAME)
				{
					Collections.sort(blStudents,new StudentNameComparator());
				}
				else if(orderBy == ORDER_BY.GENDER)
				{
					Collections.sort(blStudents,new StudentGenderComparator());
				}
				else if(orderBy == ORDER_BY.AGE)
				{
					Collections.sort(blStudents,new StudentAgeComparator());
				}
				else if(orderBy == ORDER_BY.DATE_OF_BIRTH)
				{
					Collections.sort(blStudents,new StudentDateOfBirthComparator());
				}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public List<StudentBeanInterface> getIndians(ORDER_BY orderBy) throws BLException
	{	
		List<StudentDTOInterface> dlStudents;
		List<StudentBeanInterface> blStudents;
		StudentDAOInterface studentDAOInterface;
		StudentBeanInterface studentBeanInterface;
		char gender;
		try
		{
			studentDAOInterface=new StudentDAO();
			dlStudents=studentDAOInterface.getAll();
			blStudents=new ArrayList<StudentBeanInterface>();
			for(StudentDTOInterface studentDTOInterface : dlStudents)
			{
				if(studentDTOInterface.getIsIndian()==true)
				{
					studentBeanInterface=new StudentBean();
					studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
					studentBeanInterface.setName(studentDTOInterface.getName());
					gender=studentDTOInterface.getGender();
					if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
					else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
					else studentBeanInterface.setGender(GENDER.TRANSGENDER);
					studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
					studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
					blStudents.add(studentBeanInterface);
				}
			}
				if(orderBy == ORDER_BY.ROLL_NUMBER)
				{
					Collections.sort(blStudents,new StudentRollNumberComparator());
				}
				else if(orderBy == ORDER_BY.NAME)
				{
					Collections.sort(blStudents,new StudentNameComparator());
				}
				else if(orderBy == ORDER_BY.GENDER)
				{
					Collections.sort(blStudents,new StudentGenderComparator());
				}
				else if(orderBy == ORDER_BY.AGE)
				{
					Collections.sort(blStudents,new StudentAgeComparator());
				}
				else if(orderBy == ORDER_BY.DATE_OF_BIRTH)
				{
					Collections.sort(blStudents,new StudentDateOfBirthComparator());
				}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}

	}
	public List<StudentBeanInterface> getForeigners(ORDER_BY orderBy) throws BLException
	{
		List<StudentDTOInterface> dlStudents;
		List<StudentBeanInterface> blStudents;
		StudentDAOInterface studentDAOInterface;
		StudentBeanInterface studentBeanInterface;
		char gender;
		try
		{
			studentDAOInterface=new StudentDAO();
			dlStudents=studentDAOInterface.getAll();
			blStudents=new ArrayList<StudentBeanInterface>();
			for(StudentDTOInterface studentDTOInterface : dlStudents)
			{
				if(studentDTOInterface.getIsIndian()==false)
				{
					studentBeanInterface=new StudentBean();
					studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
					studentBeanInterface.setName(studentDTOInterface.getName());
					gender=studentDTOInterface.getGender();
					if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
					else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
					else studentBeanInterface.setGender(GENDER.TRANSGENDER);
					studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
					studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
					blStudents.add(studentBeanInterface);
				}
			}
				if(orderBy == ORDER_BY.ROLL_NUMBER)
				{
					Collections.sort(blStudents,new StudentRollNumberComparator());
				}
				else if(orderBy == ORDER_BY.NAME)
				{
					Collections.sort(blStudents,new StudentNameComparator());
				}
				else if(orderBy == ORDER_BY.GENDER)
				{
					Collections.sort(blStudents,new StudentGenderComparator());
				}
				else if(orderBy == ORDER_BY.AGE)
				{
					Collections.sort(blStudents,new StudentAgeComparator());
				}
				else if(orderBy == ORDER_BY.DATE_OF_BIRTH)
				{
					Collections.sort(blStudents,new StudentDateOfBirthComparator());
				}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}

	}
}

