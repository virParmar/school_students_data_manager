package com.thinking.machines.school.ui.main;
import com.thinking.machines.school.ui.*;
import com.thinking.machines.utils.*;

import java.awt.*;
import java.awt.event.*;

public class Main1 extends Frame implements ActionListener
{
private Label heading;
private Button add,update,delete,get,getAll;
public Main1()
{
this.heading=new Label("Student Data Management");

add=new Button("Add");
update=new Button("Update");
delete=new Button("Delete");
get=new Button("Get Student");
getAll=new Button("Get All");

this.add(heading);
this.add(add);
this.add(update);
this.add(delete);
this.add(get);
this.add(getAll);

add.addActionListener(this);
update.addActionListener(this);
delete.addActionListener(this);
get.addActionListener(this);
getAll.addActionListener(this);

this.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent we)
{
System.exit(0);
}
});

this.setVisible(true);
this.setSize(600,700);
this.setLocation(150,50);
this.setLayout(new GridLayout(6,1));
}

public void actionPerformed(ActionEvent actionEvent)
{
StudentUI studentUI;
studentUI=new StudentUI();
if(actionEvent.getSource() == add)
{
AddStudentFrame addStudentFrame=new AddStudentFrame();
}
else if(actionEvent.getSource() == update)
{
UpdateStudentFrame updateStudentFrame=new UpdateStudentFrame();
}
else if(actionEvent.getSource() == delete)
{
DeleteStudentFrame deleteStudentFrame=new DeleteStudentFrame();
}
else if(actionEvent.getSource() == get)
{
SearchStudentFrame searchStudentFrame=new SearchStudentFrame();
}
else if(actionEvent.getSource() == getAll)
{
SearchAllStudentsFrame searchAllStudentsFrame=new SearchAllStudentsFrame();
}
}
public static void main(String args[])
{
Main1 main=new Main1();
}
}
