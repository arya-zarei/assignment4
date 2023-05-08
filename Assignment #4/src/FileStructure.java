import java.util.Iterator;	//imports iterator
import java.util.ArrayList; //imports Array list for filesOfType method

public class FileStructure //linked structure that will store the information of the file objects
{
	private NLNode<FileObject> root; //root node variable
	
	public FileStructure(String fileObjectName)throws FileObjectException //throws pre-given FileObjectException
	{
        FileObject ObjectFile = new FileObject(fileObjectName); //file object
        root = new NLNode<FileObject>(ObjectFile, null); //fileObject node stored in root node 
        
        if (ObjectFile.isDirectory()) //recursive case
        {
        	FileStructureHelper(root); //function call to FileStructureHelper to take root if file object is a directory
        }
    }
	
	private void FileStructureHelper(NLNode<FileObject> file)throws FileObjectException //throws pre-given FileObjectException
	{
		if (file.getData().isFile())//base case
	    {
	        return; //if file is a file do nothing and return file
	    }
		/**since a FileObject can only be either a file or a directory if the if statement is false
		and else statement is run through, the fileObject must be a directory**/
		else //recursive case 
		{
		    FileObject ObjectFileHelper = file.getData(); //file object helper to go through all the files in the directory
		    Iterator<FileObject>iterator = ObjectFileHelper.directoryFiles(); //iterator containing all the files in the directory
		    
		    while (iterator.hasNext()) //loops through all the files in the directory
		    {	
		        FileObject childFileObject = iterator.next(); //child object returns the next file in the directory
		        NLNode<FileObject> childNode = new NLNode<>(childFileObject, file); //new child node as the top node / root
		        
		        file.addChild(childNode);//adds child node to file
		        
		        FileStructureHelper(childNode); //method calls itself directly 
		    }
		 }
	}
	
	 public NLNode<FileObject> getRoot() //gets root / top node
	 {
		 return root; //returns root value
	 }
	 
	 public Iterator<String> filesOfType(String type) //returns a string iterator with the names of all the files of the specified type
	 {
		 ArrayList<String> container = new ArrayList<>(); //container to store the names of the files with the given type
		 
	     filesOfTypeHelper(container,type,root); //calls container, type and, root to filesofType Helper method
	     
	     return container.iterator(); //returns the container iterator
	 }
	 
	 private void filesOfTypeHelper(ArrayList<String>container,String type,NLNode<FileObject>node) 
	 {
		 if (node.getData().isFile()) //if node (root) is a file
		 {
			 String fileName = node.getData().getLongName(); //fileName is the full name of root
			 
			 if (fileName.endsWith(type)) //if filename ends with specific type 
			 {
				 container.add(fileName); //add fileName to the container of the specific type
			 }
	     } 
		 
		 else 
		 {	 //get children of node (root) to get an iterator storing node file objects
			 Iterator<NLNode<FileObject>> iterator = node.getChildren(); 
			 
			 while (iterator.hasNext()) //while there is a next file
			 {
				 NLNode<FileObject> child = iterator.next(); //child is the next iteration
				 
				 filesOfTypeHelper(container,type,child); /** method calls itself directly **/ 
			 }
		 }
	 }
	 
	 public String findFile(String name) 
	 {
		 return findFileHelper(name,root); // String containing the absolute path to the file is returned
     }

	 private String findFileHelper(String name,NLNode<FileObject>node) //look for a file with the specified name
	 {
		 if (node.getData().isFile()) //if file object variable is a file
		 {
			 String fileName = node.getData().getName(); //fileName is name of node
			 
			 if (fileName.equals(name)) //if fileName is equal to file name string
			 {
				 return node.getData().getLongName(); // returns a String containing the absolute path (longName) to the file
			 }
		  } 
		 
		  else 
		  {
			  Iterator<NLNode<FileObject>> iterator = node.getChildren(); //iterator file object of get children node
			  
			  while (iterator.hasNext()) //while loop goes through all files
			  {
				  NLNode<FileObject> child = iterator.next(); //child is the next iteration
				  
				  String finder = findFileHelper(name,child); //string finder
				  
				  if (!finder.isEmpty()) //if finder is not empty
				  {
					  return finder; //return finder
				  }
			  }
		  }
		  return ""; //if file path not found return empty string 
	 }
}