import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import FIles.FileManagement;

public class Main {

	public static void main(String[] args)
	{
		String welcome_text = null;
		BufferedReader br = null;
		
		String root = null;
		
		//reading the file
		try
		{
			br = new BufferedReader(new FileReader("welcome.txt"));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) 
		    {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    welcome_text = sb.toString();
		    br.close();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		System.out.println(welcome_text);
		
		FileManagement f1 = new FileManagement();
		boolean exitflag = false;
		Scanner in = new Scanner(System.in);
		
		while(!exitflag)
		{
			//reading commands from terminal
			System.out.println("Command:");
			//reading command from user 
			//and spliting commands(ex. : command and location)
			String[] cmdtemp = in.nextLine().split(" ");
			int totalsizeofcommmand = cmdtemp.length;
			String[] cmd = new String[5];
			
			for(int i = 0 ; i < totalsizeofcommmand ; i++)
			{
				cmd[i] = cmdtemp[i];
			}
			
			//adding dummy commands (because of null pointer exception)
			for(int i = totalsizeofcommmand ;i < 4 ; i++)
			{
				cmd[i] = "";
			}
		
			switch(cmd[0])
			{
				//detecting all commands
				case "displaypath":
					if(root == null)
					{
						System.out.println("No path has been set!!");
					}
					else
					{
						System.out.println("path = " + root);
					}
					break;
				
					
				case "mount":
					if(cmd[2].isEmpty())
					{
						if(cmd[1].isEmpty())
						{
							System.out.println("Error!! No direcotry mentioned");
						}
						else
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							f1.open(cmd[1],0,"none");
						}
					}
					
					else if(cmd[2].equals("all"))
					{
						if(cmd[1].isEmpty())
						{
							System.out.println("Error!! No direcotry mentioned");
						}
						else
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							f1.open(cmd[1],0,cmd[2]);
						}
					}
					break;
					
				case "displayTree":
					f1.displayTree();
					break;
					
				case "path":
					if(cmd[1].equals("="))
					{
						if(!cmd[2].isEmpty())
						{
							
							root = new String(cmd[2]);
							
							f1.pushpath(cmd[2]);
							// debugging line :System.out.println(root);
						}
						else
						{
							System.out.println("Path is missing!!");
						}
					}
					else
					{
						System.out.println(" ' = '  is missing !!");
					}
					break;
				
				case "clear":
					for(int i = 0; i < 100; i++)
					{
					    System.out.println(" ");
					}
					break;
					
				case "buildTree":
					if(cmd[1].isEmpty())
					{
						System.out.println("Error!! No direcotry mentioned");
					}
					else
					{
						cmd[1] = cmd[1].replaceAll("#", root);
						cmd[1] = cmd[1].replaceAll("@", " ");
						f1.buildTree(cmd[1]);
						System.out.println("Tree has been Build!!!");
					}
					break;
					
				case "search":
					if(cmd[1].isEmpty())
					{
						System.out.println("Error!! No direcotry mentioned");
					}
					else
					{
						cmd[1] = cmd[1].replaceAll("@", " ");
						f1.searchFile(cmd[1]);
					}
					break;
					
				case "pathno":
					if(cmd[1].equals("="))
					{
						if(!cmd[2].isEmpty())
						{
							root = f1.selectpath(Integer.parseInt(cmd[2]));
						}
					}
					else
					{
						System.out.println("Incorrect statement");
					}
					break;
					
				case "vsort":
					if(cmd[1].isEmpty())
					{
						System.out.println("Error!! No direcotry mentioned");
					}
					else
					{
						if(cmd[2].isEmpty())
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							f1.virtualsort(cmd[1]);
						}
						else
						{
							if(cmd[2].equals("-p"))
							{
								cmd[1] = cmd[1].replaceAll("#", root);
								cmd[1] = cmd[1].replaceAll("@", " ");
								f1.physicalvsort(cmd[1]);
							}
							else
							{
								cmd[1] = cmd[1].replaceAll("#", root);
								cmd[1] = cmd[1].replaceAll("@", " ");
								//System.out.println(cmd[2]);
								f1.virtualsort_extension(cmd[1],  cmd[2]);
							}
						}
					}
					break;
					
				case "createdir":
					if(!cmd[2].isEmpty())
					{
						if(!cmd[1].isEmpty())
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							f1.createdir(cmd[1], cmd[2]);
							System.out.println("Directory created!!");
						}
					}
					else
					{
						System.out.println("Error!! No direcotry mentioned or mention name");
					}
					break;
					
				case "createfile":
					if(!cmd[2].isEmpty())
					{
						if(!cmd[1].isEmpty())
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							f1.createfile(cmd[1], cmd[2]);
							System.out.println("file created!!");
						}
					}
					else
					{
						System.out.println("Error!! No direcotry mentioned or mention name");
					}
					break;
					
					
				case "copyfile":
					if(!cmd[2].isEmpty())
					{
						if(!cmd[1].isEmpty())
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							cmd[2] = cmd[2].replaceAll("#", root);
							cmd[2] = cmd[2].replaceAll("@", " ");
							f1.copyfile(cmd[1], cmd[2]);
						}
						
					}
					else
					{
						System.out.println("destination folder is not mentioned");
					}
					break;
					
				case "delete":
					if(!cmd[1].isEmpty())
					{
						cmd[1] = cmd[1].replaceAll("#", root);
						cmd[1] = cmd[1].replaceAll("@", " ");
						f1.deletefile(cmd[1]);
					}
					else
					{
						System.out.println("Error!! No direcotry mentioned or mention name");
					}
					break;
					
				case "movefile":
					if(!cmd[2].isEmpty())
					{
						if(!cmd[1].isEmpty())
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							cmd[2] = cmd[2].replaceAll("#", root);
							cmd[2] = cmd[2].replaceAll("@", " ");
							f1.movefile(cmd[1], cmd[2]);
						}
						
					}
					else
					{
						System.out.println("destination folder is not mentioned");
					}
					break;
					
				case "msort":
					if(!cmd[2].isEmpty())
					{
						if(cmd[2].equals("-p"))
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							f1.physicalmsort(cmd[1]);
						}
					}
					else
					{
						if(!cmd[1].isEmpty())
						{
							cmd[1] = cmd[1].replaceAll("#", root);
							cmd[1] = cmd[1].replaceAll("@", " ");
							f1.musicsort(cmd[1]);
						}
						else
						{
							System.out.println("Error!! No direcotry mentioned or mention name");
						}
					}
					break;
					
				case "pathstack":
					f1.displayallpaths();
					break;
					
				case "exit":
					System.out.println("program has been stopped successfully.");
					return;
					
					
				default :
					System.out.println("'" + cmd[0] + "' " + " Command Not Found !! ");
			}
		}
		
	}

}
