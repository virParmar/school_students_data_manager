package com.thinking.machines.utils;
import java.io.*;
public class Keyboard
{
	public static String getString()
	{
		String value="";
		try
		{
			InputStreamReader inputStreamReader;
			inputStreamReader=new InputStreamReader(System.in);
			BufferedReader bufferedReader;
			bufferedReader=new BufferedReader(inputStreamReader);
			value=bufferedReader.readLine();
		}catch(IOException ioException)
		{
		}
		return value;
	}
	public static long getLong()
	{
		long value=0;
		try
		{
			value=Long.parseLong(getString());
		}catch(Exception exception)
		{
		}
		return value;
	}
	public static int getInt()
	{
		int value=0;
		try
		{
			value=Integer.parseInt(getString());
		}catch(Exception exception)
		{
		}
		return value;
	}
	public static byte getByte()
	{
		byte value=0;
		try
		{
			value=Byte.parseByte(getString());
		}catch(Exception exception)
		{
		}
		return value;
	}
	public static short getShort()
	{
		short value=0;
		try
		{
			value=Short.parseShort(getString());
		}catch(Exception exception)
		{
		}
		return value;
	}
	public static double getDouble()
	{
		double value=0.0;
		try
		{
			value=Double.parseDouble(getString());
		}catch(Exception exception)
		{
		}
		return value;
	}
	public static char getCharacter()
	{
		String string="";
		char value=(char)0;
		try
		{
			string=getString();
			if(string.length()>0) value=string.charAt(0);
		}catch(Exception exception)
		{
		}
		return value;
	}
	public static float getFloat()
	{
		float value=0.0f;
		try
		{
			value=Float.parseFloat(getString());
		}catch(Exception exception)
		{
		}
		return value;
	}
	public static boolean getBoolean()
	{
		boolean value=false;
		try
		{
			value=Boolean.parseBoolean(getString());
		}catch(Exception exception)
		{
		}
		return value;
	}
	public static String getString(String message)
	{
		System.out.print(message);
		return getString();
	}
	public static long getLong(String message)
	{
		System.out.print(message);
		return getLong();
	}
	public static int getInt(String message)
	{
		System.out.print(message);
		return getInt();
	}
	public static byte getByte(String message)
	{
		System.out.print(message);
		return getByte();
	}
	public static short getShort(String message)
	{
		System.out.print(message);
		return getShort();
	}
	public static double getDouble(String message)
	{
		System.out.print(message);
		return getDouble();
	}
	public static float getFloat(String message)
	{
		System.out.print(message);
		return getFloat();
	}
	public static char getCharacter(String message)
	{
		System.out.print(message);
		return getCharacter();
	}
	public static boolean getBoolean(String message)
	{
		System.out.print(message);
		return getBoolean();
	}
}
