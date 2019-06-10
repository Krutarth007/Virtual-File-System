package DataStructure;

import java.awt.geom.Path2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.io.FileUtils;



public class ExtensionHashmap 
{
	HashMap<String, ArrayList<String>> extensionhashmap;
	String filepath;
	
	
	void Storehashmap()
	{
		 System.out.println("serializing theData");
		  try 
		  {
			  String tempfilename = filepath.replaceAll("/", "_");
		      FileOutputStream fout = new FileOutputStream(tempfilename+ "_ext");
		      ObjectOutputStream oos = new ObjectOutputStream(fout);
		      oos.writeObject(extensionhashmap);
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
			    extensionhashmap = (HashMap<String , ArrayList<String>>) ois.readObject();
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
	
	public ExtensionHashmap(String filepath)
	{
		this.filepath = filepath;
		extensionhashmap = new HashMap<>();
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
					String ext = listOfFiles[i].getName().substring(listOfFiles[i].getName().lastIndexOf(".") + 1);
					// Debugging line:System.out.println("Filename:" + listOfFiles[i].getName() + "Extension:" + ext);
					if(extensionhashmap.containsKey(ext))
					{
						extensionhashmap.get(ext).add(listOfFiles[i].getName());
					}
					else
					{
						ArrayList<String> temp = new ArrayList<>();
						temp.add(listOfFiles[i].getName());
						extensionhashmap.put(ext, temp);
					}
					
				}
			}
		}
		
	}
	
	public void displaysortedList()
	{
		Set<String> allextensions = extensionhashmap.keySet();
		
		for(String allext : allextensions)
		{
			String tempext = allext;
			System.out.println("Extension : "+ tempext);
			ArrayList<String> allextfiles = extensionhashmap.get(tempext);
			for(String ext : allextfiles )
			{
				System.out.println("\t" + "Filename:" + ext);
			}
			
		}
	}
	
	public void displayextfiles(String ext)
	{
		
		System.out.println("Extension : "+ ext);
		ArrayList<String> allextfiles = extensionhashmap.get(ext);
		if(allextfiles == null)
		{
			System.out.println("There is no file with "+ ext +" extension");
			return;
		}
		for(String tempfiles : allextfiles )
		{
			System.out.println("\t" + "Filename:" + tempfiles);
		}
		
	}
	
	public void physicallysort(String filepath)
	{
		Set<String> allextensions = extensionhashmap.keySet();
		
		for(String allext : allextensions)
		{
			String tempext = allext;
			ArrayList<String> allextfiles = extensionhashmap.get(tempext);
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
	
	

}
