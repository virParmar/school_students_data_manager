package com.thinking.machines.school.bl.comparators;
import com.thinking.machines.school.bl.beans.interfaces.*;
import java.util.*;
public class StudentRollNumberComparator implements Comparator<StudentBeanInterface>
{
	public int compare(StudentBeanInterface left,StudentBeanInterface right)
	{
		return left.getRollNumber()-right.getRollNumber();
	}
}
