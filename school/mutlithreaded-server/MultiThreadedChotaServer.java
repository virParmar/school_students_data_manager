import java.io.*;
import java.net.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.enums.*;

import java.text.*;

class ServerException extends Exception
{
public ServerException(String message)
{
super(message);
}
}

class RequestProcessor extends Thread
{
private Socket socket;
public RequestProcessor(Socket socket)
{
this.socket=socket;
this.start();
}

public void run()
{
InputStream inputStream;
InputStreamReader inputStreamReader;
OutputStream outputStream;
OutputStreamWriter outputStreamWriter;
StringBuffer stringBuffer;
int oneByte;
String requestString;
String responseString="";
try
{
inputStream=this.socket.getInputStream();
inputStreamReader=new InputStreamReader(inputStream);
outputStream=this.socket.getOutputStream();
outputStreamWriter=new OutputStreamWriter(outputStream);
stringBuffer=new StringBuffer();
while(true)
{
oneByte=inputStreamReader.read();
if(oneByte==-1) break;
if(oneByte=='#') break;
stringBuffer=stringBuffer.append((char)oneByte);
}
requestString=stringBuffer.toString();
System.out.println("Request arrived : "+requestString);
String pcs[]=requestString.split(",");
String command=pcs[0];
int rollNumber;
String name;
char gender;
boolean isIndian;
java.util.Date dateOfBirth;
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String dobPcs[];
if(command.equals("add"))
{
System.out.println("Add method got called");
rollNumber=Integer.parseInt(pcs[1]);
name=pcs[2];
gender=pcs[3].charAt(0);
char ii=pcs[4].charAt(0);
if(pcs[4].equals("true") || pcs[4].equals("True")) isIndian=true;
else isIndian=false;
dateOfBirth=simpleDateFormat.parse(pcs[5]);
StudentBeanInterface studentBeanInterface;
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(rollNumber);
studentBeanInterface.setName(name);
if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(isIndian);
studentBeanInterface.setDateOfBirth(dateOfBirth);
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
studentManagerInterface.add(studentBeanInterface);
responseString="true#";
outputStreamWriter.write(responseString);
outputStreamWriter.flush();
System.out.println("Response sent : "+responseString);
}
else if(command.equals("update"))
{
rollNumber=Integer.parseInt(pcs[1]);
name=pcs[2];
gender=pcs[3].charAt(0);
char ii=pcs[4].charAt(0);
if(ii=='Y' || ii=='y') isIndian=true;
else isIndian=false;
dateOfBirth=simpleDateFormat.parse(pcs[5]);
StudentBeanInterface studentBeanInterface;
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(rollNumber);
studentBeanInterface.setName(name);
if(gender=='M') studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='F') studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(isIndian);
studentBeanInterface.setDateOfBirth(dateOfBirth);
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
System.out.println("Update method got called");
studentManagerInterface.update(studentBeanInterface);
responseString="true,Student Updated#";
outputStreamWriter.write(responseString);
outputStreamWriter.flush();
System.out.println("Response sent : "+responseString);
}
else if(command.equals("delete"))
{
System.out.println("Student delete got called");
rollNumber=Integer.parseInt(pcs[1]);
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
studentManagerInterface.delete(rollNumber);
System.out.println("step1");
responseString="true#";
outputStreamWriter.write(responseString);
outputStreamWriter.flush();
System.out.println("Response sent : "+responseString);
}
else if(command.equals("getByRollNumber"))
{
rollNumber=Integer.parseInt(pcs[1]);
StudentBeanInterface studentBeanInterface;
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
rollNumber=studentBeanInterface.getRollNumber();
name=studentBeanInterface.getName();
GENDER vGender=studentBeanInterface.getGender();
if(vGender==GENDER.MALE) gender='M';
else if(vGender==GENDER.FEMALE) gender='F';
else gender='T';
isIndian=studentBeanInterface.getIsIndian();
char ii;
if(isIndian==true) ii='Y';
else ii='N';
dateOfBirth=studentBeanInterface.getDateOfBirth();

responseString="true,"+rollNumber+","+name+","+gender+","+ii+","+dateOfBirth+"#";
outputStreamWriter.write(responseString);
outputStreamWriter.flush();
System.out.println("Response sent : "+responseString);
}
}catch(Exception exception)
{
responseString=exception.getMessage();
}
/*
try
{


outputStream=this.socket.getOutputStream();
outputStreamWriter=new OutputStreamWriter(outputStream);
outputStreamWriter.write(responseString);
outputStreamWriter.flush();
System.out.println("Response sent : "+responseString);

}catch(IOException ioException)
{
System.out.println(ioException);
}*/
}
}

class MultiThreadedChotaServer
{
private ServerSocket serverSocket;
private int portNumber;
public MultiThreadedChotaServer(int portNumber)
{
this.portNumber=portNumber;
try
{
this.serverSocket=new ServerSocket(this.portNumber);
}catch(Exception exception)
{
System.out.println(exception);
}
}

public void startServer()
{
try
{
Socket cs;
while(true)
{
System.out.println("Server is ready to accept connection on : "+this.portNumber+".......");
cs=this.serverSocket.accept();
new RequestProcessor(cs);
}
}catch(Exception exception)
{
System.out.println(exception);
}
}

public static void main(String args[])
{
if(args.length != 1)
{
System.out.println("Usage [java MultiThreadedChotaServer port_number]");
return;
}

try
{
int portNumber;
portNumber=Integer.parseInt(args[0]);
if(portNumber<1 || portNumber>65535)
{
throw new ServerException("Port number should be between 1 to 65535");
}
MultiThreadedChotaServer multiThreadedChotaServer;
multiThreadedChotaServer=new MultiThreadedChotaServer(portNumber);
multiThreadedChotaServer.startServer();
}catch(ServerException serverException)
{
System.out.println(serverException.getMessage());
}catch(NumberFormatException numberFormatException)
{
System.out.println("Port number should be a number");
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
}