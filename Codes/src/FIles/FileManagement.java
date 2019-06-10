package FIles;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import DataStructure.ExtensionHashmap;
import DataStructure.FileTree;
import DataStructure.FileTreeNode;
import DataStructure.Musichash;
import DataStructure.PathStack;
import DataStructure.TreeHash;


public class FileManagement 
{
	FileTree tree = new FileTree(new FileTreeNode("root", "root"));
	ArrayList<String> allFiles = new ArrayList<>();
	TreeHash treehash;
	ExtensionHashmap exthash;
	PathStack pathstack = new PathStack() ;
	//for opening files in given directory
	public void open(String pathname,int Tab,String extracmd)
	{
		
		File folder = new File(pathname);
		StringBuilder tabstring = new StringBuilder("-");
		int temp = Tab;
		int nexttemp = temp + 1 ;
		while(temp > 0)
		{
			tabstring.append("-");
			temp--;
		}
		
		
		File[] listOfFiles = folder.listFiles();

		//debugging line
		//System.out.println("Pathname: " + pathname  + " exctracommand :" + extracmd);
	

		if(listOfFiles != null)
		{
		
			for (int i = 0; i < listOfFiles.length; i++) 
			{
				  
			      if (listOfFiles[i].isFile()) 
			      {
			    	  
			    	  System.out.println( tabstring + "File :" + listOfFiles[i].getAbsolutePath());
			      }
			      else if (listOfFiles[i].isDirectory()) 
			      {
			    	  	
			    		
				         System.out.println(tabstring + "Directory :" + listOfFiles[i].getAbsolutePath());
				         if(extracmd.equals("all"))
				         {
				        	 open(listOfFiles[i].getPath(),nexttemp,extracmd);
				         }
			    	
			      		    	 
			      }
			}
		}
		else
			System.out.println("There is no folder or files named "+pathname+" !!!");
		
		    
	}
	
	public void pushpath(String path)
	{
		pathstack.push(path);
	}
	
	public void displayallpaths()
	{
		ArrayList<String> temp = pathstack.getallpath();
		int i = 1;
		
		for(String x : temp)
		{
			System.out.println( i +". " + x);
			i++;
		}
	}
	
	public String selectpath(int i)
	{
		return pathstack.setpath(i);
	}
	
	public void buildTree(String pathname)
	{
		treehash = new TreeHash(pathname);
		this.helperbuildTree(pathname);
		if(!treehash.existhash)
		{
			treehash.Storehashmap();
		}
		for(String s : allFiles)
		{
			tree.addElement(s);			
		}
		
		
	}
	
	public void copyfile(String src,String dest)
	{
		try 
		{
			FileUtils.copyFile(new File(src), new File(dest));
			System.out.println("File has been copied!");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Unable to copy the file!!");
		}
	}
	
	public void createdir(String pathname,String dir)
	{
		File tempfile = new File(pathname+"/"+dir);
		tempfile.mkdirs();
	}
	
	public void createfile(String pathname,String dir)
	{
		File tempfile = new File(pathname+"/"+dir);
		try 
		{
			tempfile.createNewFile();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayTree()
	{
		tree.printTree();
	}
	
	public void helperbuildTree(String pathname)
	{
		File folder = new File(pathname);
		File[] listOfFiles = folder.listFiles();
		
		if(listOfFiles != null)
		{
		
			for (int i = 0; i < listOfFiles.length; i++) 
			{
				if(!treehash.existhash)	
				{
					
					treehash.addelements(listOfFiles[i].getName(), listOfFiles[i].getPath());
				}
					
				if (listOfFiles[i].isFile()) 
				{
					allFiles.add(listOfFiles[i].getPath());
				}
				else if (listOfFiles[i].isDirectory()) 
				{
					allFiles.add(listOfFiles[i].getPath());
			    	helperbuildTree(listOfFiles[i].getPath());
				}
			}		
		}
		else
		{
			//System.out.println("There is no folder or files named "+pathname+" !!!");
		}
	}
	
	public void searchFile(String filename)
	{
		String s = treehash.getelement(filename);
		
		if(s != null)
		{
			System.out.println("File location:" + s);
		}
		else
		{
			System.out.println("Nothing found");
		}
	}
	
	public void virtualsort(String pathname)
	{
		exthash = new ExtensionHashmap(pathname);
		exthash.displaysortedList();
	}
	
	public void virtualsort_extension(String pathname,String ext)
	{
		if(exthash == null)
		{
			exthash = new ExtensionHashmap(pathname);
		}
		exthash.displayextfiles(ext);
	}
	
	public void physicalvsort(String pathname)
	{
		if(exthash == null)
		{
			exthash = new ExtensionHashmap(pathname);
		}
		exthash.physicallysort(pathname);
	}
	
	public void deletefile(String pathname)
	{
		File file = new File(pathname);
		
		if(file.delete())
		{
			System.out.println("file/direcotry is deleted");
		}
		else
		{
			try
			{
				FileUtils.deleteDirectory(new File(pathname));
				System.out.println("direcotry has been deleted!!");
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Unable to delete!!");
			}
		}
	}

	public void movefile(String src , String dest)
	{
		try 
		{
			FileUtils.moveFile(new File(src), new File(dest));
			System.out.println("File has been moved");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("unable to move file");
		}
	}

	
	public void musicsort(String pathname)
	{
		Musichash mhash = new Musichash(pathname);
		mhash.displaysortedList();
		
	}
	
	public void physicalmsort(String pathname)
	{
		Musichash mhash = new Musichash(pathname);
		mhash.physicallysort(pathname);
	}
}
