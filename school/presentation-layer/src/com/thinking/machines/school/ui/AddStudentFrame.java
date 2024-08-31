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
 public class AddStudentFrame extends Frame implements ActionListener
{
private Label heading,rollNumberLabel,nameLabel,genderLabel,isIndianLabel,dateOfBirthLabel;
private TextField nameTextField,rollNumberTextField,dateOfBirthTextField;
private CheckboxGroup checkboxGroup;
private Checkbox isIndianCheckbox,femaleCheckbox,maleCheckbox,transgenderCheckbox;
private Button saveButton,exitButton;
public AddStudentFrame()
{
heading=new Label("Student add module");
Panel p1=new Panel();
p1.setLayout(new GridLayout(3,3));
p1.add(new Label("                      "));
p1.add(new Label("                      "));
p1.add(new Label("                      "));
p1.add(new Label("                      "));
p1.add(heading);
p1.add(new Label("                      "));
p1.add(new Label("                      "));
p1.add(new Label("                      "));
p1.add(new Label("                      "));

rollNumberLabel=new Label("Roll Number");
rollNumberTextField=new TextField(10);

nameLabel=new Label("Name");
nameTextField=new TextField(50);

checkboxGroup=new CheckboxGroup();
genderLabel=new Label("Gender");
maleCheckbox=new Checkbox("Male",checkboxGroup,true);
femaleCheckbox=new Checkbox("Female",checkboxGroup,false);
transgenderCheckbox=new Checkbox("Transgender",checkboxGroup,false);
Panel p2=new Panel();
p2.setLayout(new GridLayout(1,3));
p2.add(maleCheckbox);
p2.add(femaleCheckbox);
p2.add(transgenderCheckbox);

isIndianLabel=new Label("Is Indian");
isIndianCheckbox=new Checkbox();

dateOfBirthLabel=new Label("Date Of Birth");
dateOfBirthTextField=new TextField(10);

saveButton=new Button("Save");
exitButton=new Button("Exit");
Panel p3=new Panel();
p3.setLayout(new GridLayout(2,5));
p3.add(new Label("                          "));
p3.add(saveButton);
p3.add(new Label("                          "));
p3.add(exitButton);
p3.add(new Label("                          "));
p3.add(new Label("                      "));
p3.add(new Label("                      "));
p3.add(new Label("                      "));
p3.add(new Label("                      "));
p3.add(new Label("                      "));


Panel p4=new Panel();
p4.setLayout(new GridLayout(6,2));
p4.add(rollNumberLabel);
p4.add(rollNumberTextField);
p4.add(nameLabel);
p4.add(nameTextField);
p4.add(genderLabel);
p4.add(p2);
p4.add(isIndianLabel);
p4.add(isIndianCheckbox);
p4.add(dateOfBirthLabel);
p4.add(dateOfBirthTextField);
p4.add(new Label("                      "));
p4.add(new Label("                      "));

BorderLayout borderLayout=new BorderLayout();
setLayout(borderLayout);
add(p1,BorderLayout.NORTH);
add(p4,BorderLayout.CENTER);
add(p3,BorderLayout.SOUTH);
add(new Label("       "),BorderLayout.EAST);
add(new Label("                          "),BorderLayout.WEST);

this.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent windowEvent)
{
setVisible(false);
}
});

saveButton.addActionListener(this);
exitButton.addActionListener(this);

this.setLocation(800,50);
this.setSize(650,350);
this.setVisible(true);
}

public void actionPerformed(ActionEvent actionEvent)
{
if(actionEvent.getSource() == saveButton)
{
int rollNumber;
String name;
GENDER gender;
boolean isIndian;
String dateOfBirthString;
java.util.Date dateOfBirth;
rollNumber=Integer.parseInt(rollNumberTextField.getText());
name=nameTextField.getText();
if(maleCheckbox.getState() == true) gender=GENDER.MALE;
else if(femaleCheckbox.getState() == true) gender=GENDER.FEMALE;
else gender=GENDER.TRANSGENDER;
if(isIndianCheckbox.getState() == true) isIndian=true;
else isIndian=false;
dateOfBirthString=dateOfBirthTextField.getText();
try
{
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth=simpleDateFormat.parse(dateOfBirthString);
StudentBeanInterface studentBeanInterface;
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(rollNumber);
studentBeanInterface.setName(name);
studentBeanInterface.setGender(gender);
studentBeanInterface.setIsIndian(isIndian);
studentBeanInterface.setDateOfBirth(dateOfBirth);
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
studentManagerInterface.add(studentBeanInterface);
System.out.println("Student Saved");
}catch(BLException blException)
{
System.out.println(blException.getMessage());
System.out.println("Student not saved");
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
else if(actionEvent.getSource() == exitButton)
{
this.setVisible(false);
}
}
}