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
 public class UpdateStudentFrame extends Frame implements ActionListener
{
private Label heading,nameLabel,rollNumberLabel,genderLabel,isIndianLabel,dateOfBirthLabel;
private TextField rollNumberTextField,nameTextField,dateOfBirthTextField;
private CheckboxGroup checkboxGroup;
private Checkbox isIndianCheckbox,maleCheckbox,femaleCheckbox,transgenderCheckbox;
private Button getButton,exitButton,updateButton;
Panel p3;

public UpdateStudentFrame()
{
heading=new Label("Student Update Module");
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
updateButton=new Button("Update");
exitButton=new Button("Exit");
Panel p2=new Panel();
p2.setLayout(new GridLayout(6,5));

p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));

p2.add(new Label("                          "));
p2.add(new Label("Roll No"));
p2.add(rollNumberTextField);
p2.add(new Label("                          "));
p2.add(new Label("                          "));

p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));

p2.add(getButton);
p2.add(new Label("  "));
p2.add(updateButton);
p2.add(new Label("  "));
p2.add(exitButton);

p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));

p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));
p2.add(new Label("                          "));

rollNumberLabel=new Label("Roll No.");
nameLabel=new Label("Name");
genderLabel=new Label("Gender");
isIndianLabel=new Label("Is Indian");
dateOfBirthLabel=new Label("Date Of Birth");

nameTextField=new TextField(50);

checkboxGroup=new CheckboxGroup();
maleCheckbox=new Checkbox("Male",checkboxGroup,false);
femaleCheckbox=new Checkbox("Female",checkboxGroup,false);
transgenderCheckbox=new Checkbox("Transgender",checkboxGroup,false);

isIndianCheckbox=new Checkbox();

dateOfBirthTextField=new TextField(10);

Panel p4=new Panel();
p4.setLayout(new GridLayout(1,3));
p4.add(maleCheckbox);
p4.add(femaleCheckbox);
p4.add(transgenderCheckbox);

p3=new Panel();
p3.setLayout(new GridLayout(5,4));

p3.add(new Label("  "));
p3.add(nameLabel);
p3.add(nameTextField);
p3.add(new Label("  "));

p3.add(new Label("  "));
p3.add(genderLabel);
p3.add(p4);
p3.add(new Label("  "));

p3.add(new Label("  "));
p3.add(isIndianLabel);
p3.add(isIndianCheckbox);
p3.add(new Label("  "));

p3.add(new Label("  "));
p3.add(dateOfBirthLabel);
p3.add(dateOfBirthTextField);
p3.add(new Label("  "));

p3.add(new Label("  "));
p3.add(new Label("  "));
p3.add(new Label("  "));
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
updateButton.addActionListener(this);

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

rollNumberTextField.setText(Integer.toString(rollNumber));
nameTextField.setText(name);
if(isIndian == true) isIndianCheckbox.setState(true);
else isIndianCheckbox.setState(false);
if(gender == GENDER.MALE) maleCheckbox.setState(true);
else if(gender == GENDER.FEMALE)femaleCheckbox.setState(true);
else transgenderCheckbox.setState(true);
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
dateOfBirthTextField.setText(simpleDateFormat.format(dateOfBirth));

p3.setVisible(true);
}
else if(actionEvent.getSource() == updateButton)
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
else gender=GENDER.FEMALE;
if(isIndianCheckbox.getState() == true) isIndian=true;
else isIndian=false;
dateOfBirthString=dateOfBirthTextField.getText();

SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth=simpleDateFormat.parse(dateOfBirthString);
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(rollNumber);
studentBeanInterface.setName(name);
studentBeanInterface.setGender(gender);
studentBeanInterface.setIsIndian(isIndian);
studentBeanInterface.setDateOfBirth(dateOfBirth);
studentManagerInterface=new StudentManager();
studentManagerInterface.update(studentBeanInterface);
System.out.println("Student Updated");

}
else if(actionEvent.getSource() == exitButton)
{
this.setVisible(false);
}
}catch(BLException blException)
{
System.out.println(blException.getMessage());
System.out.println("Student not saved");
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}

}
}