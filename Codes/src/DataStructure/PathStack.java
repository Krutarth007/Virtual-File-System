package DataStructure;

import java.util.ArrayList;

public class PathStack 
{
	
	ArrayList<String> pathlist;
	
	public PathStack()
	{
		pathlist = new ArrayList<>();
	}
	
	public boolean pop()
	{
		if(pathlist.isEmpty())
		{
			return false;
		}
		else
		{
			pathlist.remove(0);
			return true;
		}
	}
	
	public void push(String temp)
	{
		pathlist.add(temp);
	}
	
	public ArrayList<String> getallpath()
	{
		return pathlist;
	}

	
	public String setpath(int i)
	{
		return pathlist.get(i-1);
	}
	
}


