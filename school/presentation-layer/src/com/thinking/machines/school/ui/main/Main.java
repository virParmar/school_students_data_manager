package com.thinking.machines.school.ui.main;
import com.thinking.machines.school.ui.*;
import com.thinking.machines.utils.*;

import java.awt.*;
import java.awt.event.*;

public class Main extends Frame implements ActionListener
{
private Label heading;
private MenuBar menuBar;
private Menu studentMenu;
private MenuItem addMenuItem,updateMenuItem,deleteMenuItem,viewMenuItem,viewAllMenuItem,exitMenuItem;
public Main()
{
this.heading=new Label("Student Data Management");

addMenuItem=new MenuItem("Add");
updateMenuItem=new MenuItem("Update");
deleteMenuItem=new MenuItem("Delete");
viewMenuItem=new MenuItem("View");
viewAllMenuItem=new MenuItem("View All");
exitMenuItem=new MenuItem("Exit");

studentMenu=new Menu("Student");
studentMenu.add(addMenuItem);
studentMenu.add(updateMenuItem);
studentMenu.add(deleteMenuItem);
studentMenu.add(viewMenuItem);
studentMenu.add(viewAllMenuItem);
studentMenu.add(exitMenuItem);

menuBar=new MenuBar();
menuBar.add(studentMenu);

addMenuItem.addActionListener(this);
updateMenuItem.addActionListener(this);
deleteMenuItem.addActionListener(this);
viewMenuItem.addActionListener(this);
viewAllMenuItem.addActionListener(this);
exitMenuItem.addActionListener(this);

this.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent we)
{
System.exit(0);
}
});

this.setMenuBar(menuBar);
this.setVisible(true);
this.setSize(600,700);
this.setLocation(150,50);
}

public void actionPerformed(ActionEvent actionEvent)
{
StudentUI studentUI;
studentUI=new StudentUI();
if(actionEvent.getSource() == addMenuItem)
{
AddStudentFrame addStudentFrame=new AddStudentFrame();
}
else if(actionEvent.getSource() == updateMenuItem)
{
UpdateStudentFrame updateStudentFrame=new UpdateStudentFrame();
}
else if(actionEvent.getSource() == deleteMenuItem)
{
DeleteStudentFrame deleteStudentFrame=new DeleteStudentFrame();
}
else if(actionEvent.getSource() == viewMenuItem)
{
SearchStudentFrame searchStudentFrame=new SearchStudentFrame();
}
else if(actionEvent.getSource() == viewAllMenuItem)
{
SearchAllStudentsFrame searchAllStudentsFrame=new SearchAllStudentsFrame();
}
else if(actionEvent.getSource() == exitMenuItem)
{
System.exit(0);
}
}
public static void main(String args[])
{
Main main=new Main();
}
}
