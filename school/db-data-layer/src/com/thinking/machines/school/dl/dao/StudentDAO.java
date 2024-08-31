package com.thinking.machines.school.dl.dao;
import java.util.*;
import java.io.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.exceptions.*;
import java.sql.*;
class DAOConnection
{
public static Connection getConnection() throws ClassNotFoundException,SQLException
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection connection;
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db?useSSL=false","school","school");
return connection;
}
}
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
		java.util.Date vDateOfBirth;
java.sql.Date dateOfBirth;
		rollNumber=studentDTOInterface.getRollNumber();
		name=studentDTOInterface.getName();
		gender=studentDTOInterface.getGender();
		isIndian=studentDTOInterface.getIsIndian();
		vDateOfBirth=studentDTOInterface.getDateOfBirth();
dateOfBirth=new java.sql.Date(vDateOfBirth.getYear(),vDateOfBirth.getMonth(),vDateOfBirth.getDate());
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select roll_number from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
boolean exists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(exists)
{
connection.close();
throw new DAOException("Roll number : "+rollNumber+" exists");
}
preparedStatement=connection.prepareStatement("insert into student values (?,?,?,?,?)");
preparedStatement.setInt(1,rollNumber);
preparedStatement.setString(2,name);
preparedStatement.setString(3,Character.toString(gender));
preparedStatement.setBoolean(4,isIndian);
preparedStatement.setDate(5,dateOfBirth);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException(classNotFoundException.getMessage());
}
catch(Exception exception)
{
throw new DAOException("Exception : "+exception.getMessage());
}
}
public void update(StudentDTOInterface studentDTOInterface) throws DAOException
{
		try
		{
		String dateOfBirthString;
		int vRollNumber;
		int rollNumber;
		String name;
		char gender;
		boolean isIndian;
		java.util.Date vDateOfBirth;
java.sql.Date dateOfBirth;
		rollNumber=studentDTOInterface.getRollNumber();
		name=studentDTOInterface.getName();
		gender=studentDTOInterface.getGender();
		isIndian=studentDTOInterface.getIsIndian();
		vDateOfBirth=studentDTOInterface.getDateOfBirth();
dateOfBirth=new java.sql.Date(vDateOfBirth.getYear(),vDateOfBirth.getMonth(),vDateOfBirth.getDate());
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select roll_number from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
boolean exists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(!exists)
{
connection.close();
throw new DAOException("Roll number : "+rollNumber+" doesn't exists");
}
preparedStatement=connection.prepareStatement("update student set name=?,gender=?,is_indian=?,date_of_birth=? where roll_number=?");
preparedStatement.setString(1,name);
preparedStatement.setString(2,Character.toString(gender));
preparedStatement.setBoolean(3,isIndian);
preparedStatement.setDate(4,dateOfBirth);
preparedStatement.setInt(5,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException(classNotFoundException.getMessage());
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
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select roll_number from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
boolean exists;
exists=resultSet.next();
if(!exists)
{
connection.close();
throw new DAOException("Roll Number "+rollNumber+" doesn't exists");
}
preparedStatement=connection.prepareStatement("delete from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException(classNotFoundException.getMessage());
}
catch(Exception exception)
{
throw new DAOException("Exception : "+exception);
}
}
public StudentDTOInterface get(int rollNumber) throws DAOException
{
StudentDTOInterface studentDTOInterface;
String name;
char gender;
boolean isIndian;
java.util.Date dateOfBirth;
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
boolean exists;
exists=resultSet.next();
if(exists)
{
name=resultSet.getString("name").trim();  // It is type of char, it will be padded with space on right
gender=resultSet.getString("gender").trim().charAt(0);
isIndian=resultSet.getBoolean("is_indian");
java.sql.Date vDateOfBirth;
vDateOfBirth=resultSet.getDate("date_of_birth");
dateOfBirth=new java.util.Date(vDateOfBirth.getYear()-1900,vDateOfBirth.getMonth()-1,vDateOfBirth.getDate());
studentDTOInterface=new StudentDTO();
studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender);
studentDTOInterface.setIsIndian(isIndian);
studentDTOInterface.setDateOfBirth(vDateOfBirth);
return studentDTOInterface;
}
else
{
connection.close();
throw new DAOException("Roll Number "+rollNumber+" doesn't exists");
}
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException(classNotFoundException.getMessage());
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
int rollNumber;
String name;
char gender;
boolean isIndian;
java.util.Date dateOfBirth;
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from student");
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
rollNumber=resultSet.getInt("roll_number");
name=resultSet.getString("name").trim();  // It is type of char, it will be padded with space on right
gender=resultSet.getString("gender").trim().charAt(0);
isIndian=resultSet.getBoolean("is_indian");
java.sql.Date vDateOfBirth;
vDateOfBirth=resultSet.getDate("date_of_birth");
dateOfBirth=new java.util.Date(vDateOfBirth.getYear()-1900,vDateOfBirth.getMonth()-1,vDateOfBirth.getDate());
studentDTOInterface=new StudentDTO();
studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender);
studentDTOInterface.setIsIndian(isIndian);
studentDTOInterface.setDateOfBirth(vDateOfBirth);
students.add(studentDTOInterface);
}
connection.close();
preparedStatement.close();
return students;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException(classNotFoundException.getMessage());
}
catch(Exception exception)
{
throw new DAOException("Exception : "+exception);
}
}
}
