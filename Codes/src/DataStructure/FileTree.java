package DataStructure;

public class FileTree 
{
	FileTreeNode root;
    FileTreeNode commonRoot;

    public FileTree( FileTreeNode root ) 
    {
        this.root = root;
        commonRoot = null;
    }

    public void addElement( String elementValue ) 
    { 
        String[] list = elementValue.split("/");
        root.addElement(root.incrementalPath, list);

    }
    
   

    public void printTree() 
    {
        
        getCommonRoot();
        commonRoot.printNode(0);
    }

    public FileTreeNode getCommonRoot() 
    {
        if ( commonRoot != null)
            return commonRoot;
        else 
        {
            FileTreeNode current = root;
            while ( current.leafs.size() < 0 ) 
            {
                current = current.childs.get(0);
            }
            commonRoot = current;
            return commonRoot;
        }

    }


}


