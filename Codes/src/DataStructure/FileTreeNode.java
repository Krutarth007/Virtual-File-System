package DataStructure;

import java.util.ArrayList;
import java.util.Arrays;

public class FileTreeNode 
{
	
    ArrayList<FileTreeNode> childs;
    ArrayList<FileTreeNode> leafs;
    String data;
    String incrementalPath;

    public FileTreeNode( String nodeValue, String incrementalPath ) 
    {
        childs = new ArrayList<FileTreeNode>();
        leafs = new ArrayList<FileTreeNode>();
        data = nodeValue;
        this. incrementalPath = incrementalPath;
    }

    public boolean isLeaf() 
    {
        return childs.isEmpty() && leafs.isEmpty();
    }

    public void addElement(String currentPath, String[] list) 
    {

        
        while( list[0] == null || list[0].equals("") )
            list = Arrays.copyOfRange(list, 1, list.length);

        FileTreeNode currentChild = new FileTreeNode(list[0], currentPath+"/"+list[0]);
        if ( list.length == 1 ) 
        {
            leafs.add( currentChild );
            return;
        }
        else 
        {
        
            int index = childs.indexOf(currentChild);
            if ( index == -1 ) 
            {
                childs.add(currentChild);
                currentChild.addElement(currentChild.incrementalPath, Arrays.copyOfRange(list, 1, list.length));
            }
            else 
            {
                FileTreeNode nextChild = childs.get(index);
                nextChild.addElement(currentChild.incrementalPath, Arrays.copyOfRange(list, 1, list.length));
            }
        }
    }
    


    @Override
    public boolean equals(Object obj) 
    {
        FileTreeNode cmpObj = (FileTreeNode)obj;
        return incrementalPath.equals( cmpObj.incrementalPath ) && data.equals( cmpObj.data );
    }

    public void printNode( int increment ) 
    {
        for (int i = 0; i < increment; i++) 
        {
            System.out.print(" ");
        }
        System.out.println(incrementalPath + (isLeaf() ? " -> " + data : "")  );
        for( FileTreeNode n: childs)
            n.printNode(increment+2);
        for( FileTreeNode n: leafs)
            n.printNode(increment+2);
    }

    @Override
    public String toString() 
    {
        return data;
    }

	
}
