package com.thinking.machines.school.ui;

import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.enums.*;

import java.text.*;
import java.awt.*;
import java.awt.event.*;
 public class DeleteStudentFrame extends Frame implements ActionListener
{
private Label heading,nameLabel,rollNumberLabel,genderLabel,isIndianLabel,dateOfBirthLabel;
private Label nameValue,rollNumberValue,genderValue,isIndianValue,dateOfBirthValue;
private TextField rollNumberTextField;
private Button getButton,exitButton,deleteButton;
private Panel p3;
public DeleteStudentFrame()
{
heading=new Label("Student Delete module");
Panel p1=new Panel();
p1.setLayout(new GridLayout(3,3));

p1.add(new Label("               "));
p1.add(new Label("               "));
p1.add(new Label("               "));

p1.add(new Label("                      "));
p1.add(heading);
p1.add(new Label("                      "));

p1.add(new Label("               "));
p1.add(new Label("               "));
p1.add(new Label("               "));

rollNumberTextField=new TextField(10);
getButton=new Button("Get");
deleteButton=new Button("Delete");
exitButton=new Button("Exit");
Panel p2=new Panel();
p2.setLayout(new GridLayout(4,5));

p2.add(new Label("                          "));
p2.add(new Label("Roll No"));
p2.add(new Label("                          "));
p2.add(rollNumberTextField);
p2.add(new Label("                          "));

p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));

p2.add(getButton);
p2.add(new Label("                          "));
p2.add(deleteButton);
p2.add(new Label("                          "));
p2.add(exitButton);

p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));

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

p3=new Panel();
p3.setLayout(new GridLayout(6,4));

p3.add(new Label("  "));
p3.add(nameLabel);
p3.add(nameValue);
p3.add(new Label("  "));

p3.add(new Label("  "));
p3.add(rollNumberLabel);
p3.add(rollNumberValue);
p3.add(new Label("  "));

p3.add(new Label("  "));
p3.add(genderLabel);
p3.add(genderValue);
p3.add(new Label("  "));

p3.add(new Label("  "));
p3.add(isIndianLabel);
p3.add(isIndianValue);
p3.add(new Label("  "));

p3.add(new Label("  "));
p3.add(dateOfBirthLabel);
p3.add(dateOfBirthValue);
p3.add(new Label("  "));

p3.setVisible(false);

setLayout(new GridLayout(3,1));
add(p1);
add(p2);
add(p3);

/*
BorderLayout borderLayout=new BorderLayout();
setLayout(borderLayout);
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.CENTER);
add(p3,BorderLayout.SOUTH);
add(new Label("                          "),BorderLayout.EAST);
add(new Label("                          "),BorderLayout.WEST);
*/

this.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent windowEvent)
{
setVisible(false);
}
});

getButton.addActionListener(this);
exitButton.addActionListener(this);
deleteButton.addActionListener(this);

this.setLocation(800,50);
this.setSize(500,350);
this.setVisible(true);
}

public void actionPerformed(ActionEvent actionEvent)
{
try
{
StudentManagerInterface studentManagerInterface;
StudentBeanInterface studentBeanInterface;
if(actionEvent.getSource() == getButton)
{
int rollNumber;
String name;
GENDER gender;
boolean isIndian;
java.util.Date dateOfBirth;
rollNumber=Integer.parseInt(rollNumberTextField.getText());
studentManagerInterface=new StudentManager();
studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
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

p3.setVisible(true);
}
else if(actionEvent.getSource() == deleteButton)
{
int rollNumber;
rollNumber=Integer.parseInt(rollNumberTextField.getText());
studentManagerInterface=new StudentManager();
studentManagerInterface.delete(rollNumber);
System.out.println(rollNumber+" Deleted");
}
else if(actionEvent.getSource() == exitButton)
{
this.setVisible(false);
}
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}

}
}