import java.util.Comparator;
import java.util.Iterator;

public class NLNode<T> //non-linear data structure that will store the information about the file system
{
	private NLNode<T> parent; //reference to the parent of node
	private ListNodes<NLNode<T>> children; //reference to a list storing the children of node
	private T data; //data object stored in node
	
	public NLNode()
	{	/** sets instance variables null while children is set to empty ListNodes object **/
		parent = null; 
		data = null;
		children = new ListNodes<NLNode<T>>();
	}
	
	public NLNode(T data, NLNode<T> parent)
	{
		children = new ListNodes<NLNode<T>>(); //initializes children variable 
		this.data = data; //this data is set to T data
		this.parent = parent; //this parent is set to NLNode<T> parent
	}
	
	public void setParent(NLNode<T> p)
	{
		this.parent = p; //set this parent to p
	}
	
	public NLNode<T> getParent()
	{
		return parent; //returns parent variable
	}
	
	public void addChild(NLNode<T> newChild)
	{
		children.add(newChild); //adds the given node newChild to the list of children of this node
		newChild.setParent(this); //newChild's parent is set to this node
	}
	
	 public Iterator<NLNode<T>> getChildren() 
	 {
		 return children.getList(); //Returns list containing the children of this node
	 }
	 
	 public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter)
	 {
		 return children.sortedList(sorter); //Returns the children of this node in the order of parameter sorter
	 }
	 
	 public T getData()
	 {
		 return this.data; //returns variable data
	 }
	 
	 public void setData(T d)
	 {
		 data = d; //stores d in data
	 }
}