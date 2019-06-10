package DataStructure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class TreeHash
{
	HashMap<String,String> treehashmap = new  HashMap<>();
	String filepath;
	public boolean existhash ;
	
	public TreeHash(String filepath)
	{
		this.filepath = filepath;
		treehashmap = new HashMap<>();
		retrieveHashmap();
		
	}
	
	public void Storehashmap()
	{
		 System.out.println("serializing theData");
		  try 
		  {
			  String tempfilename = filepath.replaceAll("/", "_");
		      FileOutputStream fout = new FileOutputStream(tempfilename + "_treehashmap");
		      ObjectOutputStream oos = new ObjectOutputStream(fout);
		      oos.writeObject(treehashmap);
		      oos.close();
		      
		  }
		   catch (Exception e)
		  {
			   e.printStackTrace(); 
		  }
	}
	
	boolean retrieveHashmap()
	{
		 try 
		 {
			 String tempfilename = filepath.replaceAll("/", "_");
			    FileInputStream fin = new FileInputStream(tempfilename + "_treehashmap");
			    ObjectInputStream ois = new ObjectInputStream(fin);
			    treehashmap = (HashMap<String ,String>) ois.readObject();
			    System.out.println("File detected");
			    ois.close();
			    existhash = true;
			    return true;
		 }
		 catch (Exception e) 
		 {
			 existhash = false;
			 System.out.println("File not detected");
			 return false;
		 }
		
	}
	
	public void addelements(String filename,String fullpath)
	{
		filename = filename.replaceAll("\\s","");
		// Debugging line :System.out.println("filename (hashmap)" +filename);
		treehashmap.put(filename, fullpath);
	}
	
	public String getelement(String filename)
	{
		if(treehashmap.containsKey(filename))
		{
			return treehashmap.get(filename);
		}
		else
		{
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
