package com.thinking.machines.school.dl.dao;
import java.util.*;
import java.io.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.exceptions.*;
public class StudentDAO implements StudentDAOInterface
{
	private final static String dataFile="student.data";
	public void add(StudentDTOInterface studentDTOInterface) throws DAOException
	{
		try
		{
		String dateOfBirthString;
		int vRollNumber;
		int rollNumber;
		String name;
		char gender;
		boolean isIndian;
		java.util.Date dateOfBirth;
		rollNumber=studentDTOInterface.getRollNumber();
		name=studentDTOInterface.getName();
		gender=studentDTOInterface.getGender();
		isIndian=studentDTOInterface.getIsIndian();
		dateOfBirth=studentDTOInterface.getDateOfBirth();
		File file=new File(dataFile);
		RandomAccessFile randomAccessFile;
		randomAccessFile=new RandomAccessFile(file,"rw");
		while(randomAccessFile.getFilePointer()<randomAccessFile.length())
		{
			vRollNumber=Integer.parseInt(randomAccessFile.readLine());
			if(vRollNumber==rollNumber)
			{
				randomAccessFile.close();
				throw new DAOException(rollNumber+" exists");
			}
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			randomAccessFile.readLine();
			randomAccessFile.readLine();
		}
		// The internal pointer will be 100% at the byte next to the last byte
		randomAccessFile.writeBytes(rollNumber+"\n");
		randomAccessFile.writeBytes(name+"\n");
		randomAccessFile.writeBytes(gender+"\n");
		randomAccessFile.writeBytes(isIndian+"\n");
		dateOfBirthString=(dateOfBirth.getDate())+"/"+(dateOfBirth.getMonth()+1)+"/"+(dateOfBirth.getYear()+1900);
		randomAccessFile.writeBytes(dateOfBirthString+"\n");
		randomAccessFile.close();
		}catch(IOException ioException)
		{
			throw new DAOException("IOException : "+ioException.getMessage());
		}catch(Exception exception)
		{
			throw new DAOException("Exception : "+exception.getMessage());
		}
	}
	public void update(StudentDTOInterface studentDTOInterface) throws DAOException
	{
		int vRollNumber;
		String rollNumberString;
		String name;
		String genderString;
		String isIndianString;
		String dateOfBirthString;
		java.text.SimpleDateFormat simpleDateFormat;
		simpleDateFormat=new java.text.SimpleDateFormat("dd/MM/yyyy");
		try
		{
			File file;
			file=new File(dataFile);
			if(file.exists()==false)
			{
				throw new DAOException("Invalid rollnumber");
			}
			RandomAccessFile randomAccessFile;
			randomAccessFile=new RandomAccessFile(file,"rw");
			if(randomAccessFile.length()==0)
			{
				randomAccessFile.close();
				throw new DAOException("Invaid rollnumber");
			}
			boolean found=false;
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				rollNumberString=randomAccessFile.readLine();
				vRollNumber=Integer.parseInt(rollNumberString);
				if(vRollNumber==studentDTOInterface.getRollNumber())
				{
					found=true;
					break;
				}
				randomAccessFile.readLine();
				randomAccessFile.readLine();
				randomAccessFile.readLine();
				randomAccessFile.readLine();
			}
			if(found==false)
			{
				randomAccessFile.close();
				throw new DAOException("Invalid rollnumber");
			}
			randomAccessFile.seek(0);
			File tmpFile=new File("fhaltu.data");
			if(tmpFile.exists())
			{
				tmpFile.delete();
			}
			RandomAccessFile tmpRandomAccessFile;
			tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				vRollNumber=Integer.parseInt(randomAccessFile.readLine());
				name=randomAccessFile.readLine();
				genderString=randomAccessFile.readLine();
				isIndianString=randomAccessFile.readLine();
				dateOfBirthString=randomAccessFile.readLine();
				if(studentDTOInterface.getRollNumber()!=vRollNumber)
				{
					tmpRandomAccessFile.writeBytes(vRollNumber+"\n");
					tmpRandomAccessFile.writeBytes(name+"\n");
					tmpRandomAccessFile.writeBytes(genderString+"\n");
					tmpRandomAccessFile.writeBytes(isIndianString+"\n");
					tmpRandomAccessFile.writeBytes(dateOfBirthString+"\n");
				}
				else
				{
					tmpRandomAccessFile.writeBytes(studentDTOInterface.getRollNumber()+"\n");
					tmpRandomAccessFile.writeBytes(studentDTOInterface.getName()+"\n");
					tmpRandomAccessFile.writeBytes(studentDTOInterface.getGender()+"\n");
					tmpRandomAccessFile.writeBytes(studentDTOInterface.getIsIndian()+"\n");
					dateOfBirthString=simpleDateFormat.format(studentDTOInterface.getDateOfBirth());
					tmpRandomAccessFile.writeBytes(dateOfBirthString+"\n");
				}
			}
			randomAccessFile.seek(0);
			tmpRandomAccessFile.seek(0);
			while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
			{
				randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
			}
			randomAccessFile.setLength(tmpRandomAccessFile.length());
			randomAccessFile.close();
			tmpRandomAccessFile.setLength(0);
			tmpRandomAccessFile.close();

		}catch(IOException ioException)
		{
			throw new DAOException("IOException : "+ioException);
		}catch(Exception exception)
		{
			throw new DAOException("Exception : "+exception);
		}
	}
	public void delete(int rollNumber) throws DAOException
	{
		int vRollNumber;
		try
		{
			File file=new File(dataFile);
			if(file.exists()==false)
			{
				throw new DAOException("Invalid rollnumber");
			}
			RandomAccessFile randomAccessFile;
			randomAccessFile=new RandomAccessFile(file,"rw");
			boolean found=false;
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				vRollNumber=Integer.parseInt(randomAccessFile.readLine());
				if(rollNumber == vRollNumber)
				{
					found=true;
					break;
				}
				randomAccessFile.readLine();
				randomAccessFile.readLine();
				randomAccessFile.readLine();
				randomAccessFile.readLine();
			}
			if(found==false)
			{
				randomAccessFile.close();
				throw new DAOException("Invalid rollnumber");
			}
			randomAccessFile.seek(0);
			File tmpFile=new File("fhaltu.data");
			if(tmpFile.exists()) tmpFile.delete();
			RandomAccessFile tmpRandomAccessFile;
			tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				vRollNumber=Integer.parseInt(randomAccessFile.readLine());
				if(vRollNumber != rollNumber)
				{
					tmpRandomAccessFile.writeBytes(vRollNumber+"\n");
					tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
                                        tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
					tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
					tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
				}
				else
				{
					randomAccessFile.readLine();
					randomAccessFile.readLine();
					randomAccessFile.readLine();
					randomAccessFile.readLine();
				}
			}
			randomAccessFile.seek(0);
			tmpRandomAccessFile.seek(0);
			while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
			{
				randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
			}
			randomAccessFile.setLength(tmpRandomAccessFile.length());
			randomAccessFile.close();
			tmpRandomAccessFile.setLength(0);
			tmpRandomAccessFile.close();
		}catch(IOException ioException)
		{
			throw new DAOException("IOException : "+ioException);
		}catch(Exception exception)
		{
			throw new DAOException("Exception : "+exception);
		}
	}
	public StudentDTOInterface get(int rollNumber) throws DAOException
	{
		StudentDTOInterface studentDTOInterface;
		String rollNumberString;
		String genderString;
		String isIndianString;
		String dateOfBirthString;
		int vRollNumber;
		String vName;
		char vGender;
		boolean vIsIndian;
		java.util.Date vDateOfBirth;
		String pcs[];
		try
		{
		File file;
		file=new File(dataFile);
		if(file.exists()==false)
		{
			throw new DAOException("Invalid roll number"+rollNumber);
		}
		RandomAccessFile randomAccessFile;
		randomAccessFile=new RandomAccessFile(file,"rw");
		if(randomAccessFile.length()==0)
		{
			randomAccessFile.close();
			throw new DAOException("Invalid roll number"+rollNumber);
		}
		while(randomAccessFile.getFilePointer()<randomAccessFile.length())
		{
			rollNumberString=randomAccessFile.readLine();
			vName=randomAccessFile.readLine();
			genderString=randomAccessFile.readLine();
			isIndianString=randomAccessFile.readLine();
			dateOfBirthString=randomAccessFile.readLine();
			vRollNumber=Integer.parseInt(rollNumberString);
			if(vRollNumber==rollNumber)
			{
				vGender=genderString.charAt(0);
				vIsIndian=Boolean.parseBoolean(isIndianString);
				pcs=dateOfBirthString.split("/");
				int dd=Integer.parseInt(pcs[0]);
				int MM=Integer.parseInt(pcs[1]);
				int yyyy=Integer.parseInt(pcs[2]);
				vDateOfBirth=new java.util.Date(yyyy-1900,MM-1,dd);
				studentDTOInterface=new StudentDTO();
				studentDTOInterface.setRollNumber(vRollNumber);
				studentDTOInterface.setName(vName);
				studentDTOInterface.setGender(vGender);
				studentDTOInterface.setIsIndian(vIsIndian);
				studentDTOInterface.setDateOfBirth(vDateOfBirth);
				randomAccessFile.close();
				return studentDTOInterface;
			}
		}
		randomAccessFile.close();
		throw new DAOException("Invalid roll number"+rollNumber);
		}catch(IOException ioException)
		{
			throw new DAOException("IOException : "+ioException);
		}catch(Exception exception)
		{
			throw new DAOException("Exception : "+exception);
		}
	}
	public List<StudentDTOInterface> getAll() throws DAOException
	{
		List<StudentDTOInterface> students;
		students=new ArrayList<StudentDTOInterface>();
		StudentDTOInterface studentDTOInterface;
		String rollNumber;
		String gender;
		String isIndian;
		String dateOfBirth;
		int vRollNumber;
		String vName;
		char vGender;
		boolean vIsIndian;
		java.util.Date vDateOfBirth;
		int dd,mm,yyyy;
		String pcs[];
		try
		{
			File file=new File(dataFile);
			if(file.exists()==false)
			{
				return students;
			}
			RandomAccessFile randomAccessFile;
			randomAccessFile=new RandomAccessFile(file,"rw");
			if(randomAccessFile.length()==0)
			{
				randomAccessFile.close();
				return students;
			}
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				rollNumber=randomAccessFile.readLine();
				vName=randomAccessFile.readLine();
				gender=randomAccessFile.readLine();
				isIndian=randomAccessFile.readLine();
				dateOfBirth=randomAccessFile.readLine();
				vRollNumber=Integer.parseInt(rollNumber);
				vGender=gender.charAt(0);
				vIsIndian=Boolean.parseBoolean(isIndian);
				pcs=dateOfBirth.split("/");
				dd=Integer.parseInt(pcs[0]);
				mm=Integer.parseInt(pcs[1]);
				yyyy=Integer.parseInt(pcs[2]);
				vDateOfBirth=new java.util.Date(yyyy-1900,mm-1,dd);
				studentDTOInterface=new StudentDTO();
				studentDTOInterface.setRollNumber(vRollNumber);
				studentDTOInterface.setName(vName);
				studentDTOInterface.setGender(vGender);
				studentDTOInterface.setIsIndian(vIsIndian);
				studentDTOInterface.setDateOfBirth(vDateOfBirth);
				students.add(studentDTOInterface);
			}
			randomAccessFile.close();
			return students;
		}catch(IOException ioException)
		{
			throw new DAOException("IOException : "+ioException);
		}catch(Exception exception)
		{
			throw new DAOException("Exception : "+exception);
		}
	}
}
