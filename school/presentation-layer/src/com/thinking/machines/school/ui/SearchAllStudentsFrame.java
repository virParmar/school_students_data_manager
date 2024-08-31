package com.thinking.machines.school.ui;

import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.enums.*;

import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
 public class SearchAllStudentsFrame extends Frame implements ActionListener
{
private Label heading,pageCount,nameLabel,rollNumberLabel,genderLabel,isIndianLabel,dateOfBirthLabel;
private Label nameValue,rollNumberValue,genderValue,isIndianValue,dateOfBirthValue;
private TextField rollNumberTextField;
private Button firstButton,previousButton,nextButton,lastButton;

int rollNumber;
String name;
GENDER gender;
boolean isIndian;
java.util.Date dateOfBirth;
int current=1,last;
java.util.ArrayList<StudentBeanInterface> blStudentsArrayList;

public SearchAllStudentsFrame()
{
heading=new Label("Student Search All Module");
pageCount=new Label();
Panel p1=new Panel();
p1.setLayout(new GridLayout(3,3));

p1.add(new Label("               "));
p1.add(new Label("               "));
p1.add(new Label("               "));

p1.add(pageCount);
p1.add(heading);
p1.add(new Label("               "));

p1.add(new Label("               "));
p1.add(new Label("               "));
p1.add(new Label("               "));

rollNumberLabel=new Label("Roll number");
nameLabel=new Label("Name");
genderLabel=new Label("Gender");
isIndianLabel=new Label("Is Indian");
dateOfBirthLabel=new Label("Date Of Birth");
rollNumberValue=new Label();
nameValue=new Label();
genderValue=new Label();
isIndianValue=new Label();
dateOfBirthValue=new Label();

Panel p2=new Panel();
p2.setLayout(new GridLayout(6,2));

p2.add(new Label("  "));
p2.add(nameLabel);
p2.add(nameValue);
p2.add(new Label("  "));

p2.add(new Label(" "));
p2.add(rollNumberLabel);
p2.add(rollNumberValue);
p2.add(new Label("  "));

p2.add(new Label(" "));
p2.add(genderLabel);
p2.add(genderValue);
p2.add(new Label("  "));

p2.add(new Label(" "));
p2.add(isIndianLabel);
p2.add(isIndianValue);
p2.add(new Label("  "));

p2.add(new Label("  "));
p2.add(dateOfBirthLabel);
p2.add(dateOfBirthValue);
p2.add(new Label("  "));

firstButton=new Button("First");
previousButton=new Button("Previous");
nextButton=new Button("Next");
lastButton=new Button("Last");
Panel p3=new Panel();
p3.setLayout(new GridLayout(2,9));

p3.add(new Label("     "));
p3.add(firstButton);
p3.add(new Label("     "));
p3.add(previousButton);
p3.add(new Label("     "));
p3.add(nextButton);
p3.add(new Label("     "));
p3.add(lastButton);
p3.add(new Label("     "));

p3.add(new Label("     "));
p3.add(new Label("     "));
p3.add(new Label("     "));
p3.add(new Label("     "));
p3.add(new Label("     "));
p3.add(new Label("     "));
p3.add(new Label("     "));
p3.add(new Label("     "));
p3.add(new Label("     "));

BorderLayout borderLayout=new BorderLayout();
setLayout(borderLayout);
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(p3,BorderLayout.SOUTH);
add(new Label("     "),BorderLayout.EAST);
add(new Label("     "),BorderLayout.WEST);

this.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent windowEvent)
{
setVisible(false);
}
});

firstButton.addActionListener(this);
previousButton.addActionListener(this);
nextButton.addActionListener(this);
lastButton.addActionListener(this);

startPrinting();

this.setLocation(800,50);
this.setSize(500,400);
this.setVisible(true);
}

public void startPrinting()
{
java.util.List<StudentBeanInterface> blStudentsList;
StudentManagerInterface studentManagerInterface;
try
{
studentManagerInterface=new StudentManager();
blStudentsList=studentManagerInterface.getAll(ORDER_BY.ROLL_NUMBER);
blStudentsArrayList=new ArrayList<>();
for(StudentBeanInterface studentBeanInterface : blStudentsList)
{
blStudentsArrayList.add(studentBeanInterface);
}

current=1;
last=blStudentsArrayList.size();
pageCount.setText(current+" / "+last);
this.printStudent();
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}

public void printStudent()
{
StudentBeanInterface studentBeanInterface=blStudentsArrayList.get(current-1);
rollNumber=studentBeanInterface.getRollNumber();
name=studentBeanInterface.getName();
gender=studentBeanInterface.getGender();
isIndian=studentBeanInterface.getIsIndian();
dateOfBirth=studentBeanInterface.getDateOfBirth();

rollNumberValue.setText(Integer.toString(rollNumber));
nameValue.setText(name);
genderValue.setText(gender.toString());
if(isIndian == true) isIndianValue.setText("Yes");
else isIndianValue.setText("No");
dateOfBirthValue.setText(dateOfBirth.toString());
}
public void actionPerformed(ActionEvent actionEvent)
{
if(actionEvent.getSource() == firstButton)
{
current=1;
pageCount.setText(1+" / "+last);
}
else if(actionEvent.getSource() == previousButton)
{
if(current>1)
{
current--;
pageCount.setText(current+" / "+last);
}
}
else if(actionEvent.getSource() == nextButton)
{
if(current<last)
{
current++;
pageCount.setText(current+" / "+last);
}
}
else if(actionEvent.getSource() == lastButton)
{
current=last;
pageCount.setText(last+" / "+last);
}
this.printStudent();
}
}