package DataStructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class Musichash 
{
	
	HashMap<String, ArrayList<String>> musichashmap = new HashMap<>();
	String filepath;
	
	public void displaysortedList()
	{
		Set<String> allextensions = musichashmap.keySet();
		
		for(String allext : allextensions)
		{
			String tempext = allext;
			System.out.println("Extension : "+ tempext);
			ArrayList<String> allextfiles = musichashmap.get(tempext);
			for(String ext : allextfiles )
			{
				System.out.println("\t" + "Filename:" + ext);
			}
			
		}
	}
	
	public void physicallysort(String filepath)
	{
		Set<String> allextensions = musichashmap.keySet();
		
		for(String allext : allextensions)
		{
			String tempext = allext;
			ArrayList<String> allextfiles = musichashmap.get(tempext);
			for(String tempfile : allextfiles )
			{
				File sourceFile = new File(filepath + "/" + tempfile);
				File destinationFile = new File(filepath + "/" + allext + "/" + tempfile);
				try {
					FileUtils.moveFile(sourceFile, destinationFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	void Storehashmap()
	{
		 System.out.println("serializing theData");
		  try 
		  {
			  String tempfilename = filepath.replaceAll("/", "_");
		      FileOutputStream fout = new FileOutputStream(tempfilename+ "_ext");
		      ObjectOutputStream oos = new ObjectOutputStream(fout);
		      oos.writeObject(musichashmap);
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
			    FileInputStream fin = new FileInputStream(tempfilename + "_ext");
			    ObjectInputStream ois = new ObjectInputStream(fin);
			    musichashmap = (HashMap<String , ArrayList<String>>) ois.readObject();
			    System.out.println("File detected");
			    ois.close();
			    return true;
		 }
		 catch (Exception e) 
		 {
			 System.out.println("File not detected");
			 return false;
		 }
		
	}
	
	public Musichash(String filepath)
	{
		this.filepath = filepath;
		musichashmap = new HashMap<>();
		if(!retrieveHashmap())
		{
			init();
			this.Storehashmap();
		}
		
		
	}
	
	public void init()
	{
		File folder = new File(filepath);
		
		File[] listOfFiles = folder.listFiles();
		System.out.println("Creating hashmap..");
		if(listOfFiles != null)
		{
			for (int i = 0; i < listOfFiles.length; i++)
			{
				if(listOfFiles[i].isFile())
				{
					String ext = listOfFiles[i].getName().substring(listOfFiles[i].getName().indexOf('(') + 1, listOfFiles[i].getName().indexOf(')') );
					System.out.println(ext);
					// Debugging line:System.out.println("Filename:" + listOfFiles[i].getName() + "Extension:" + ext);
					if(musichashmap.containsKey(ext))
					{
						musichashmap.get(ext).add(listOfFiles[i].getName());
					}
					else
					{
						ArrayList<String> temp = new ArrayList<>();
						temp.add(listOfFiles[i].getName());
						musichashmap.put(ext, temp);
					}
					
				}
			}
		}
		
	}

}
