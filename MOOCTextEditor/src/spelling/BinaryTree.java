package spelling;
/**
Just for play, not tested, do not use!
**/
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E extends Comparable<E>> {
	TreeNode<E> root;
	
	
	//visit yourself visit left visit right
	public void preOrder(){
		this.preOrder(root);
	}
	
	private void preOrder(TreeNode<E> node){
		if(node!=null){
			node.visit();
			preOrder(node.getLeft());
			preOrder(node.getRight());
			
		}
	}
	//visit yourself last
	public void postOrder(){
		this.postOrder(root);
	}
	
	private void postOrder(TreeNode<E> node){
		if(node!=null){
			postOrder(node.getLeft());
			postOrder(node.getRight());
			node.visit();
		}
	}

    public boolean insert(E toinsert){
	 TreeNode<E> current = root;
	 
	 int comp = toinsert.compareTo(current.getValue());
	 while(comp<0 && current.getLeft()!=null || comp>0&& current.getRight()!=null){ 
		 if(comp<0) current=current.getLeft();
		 else current=current.getRight();
		 comp=toinsert.compareTo(current.getValue());
	 }
	 if(comp>0) current.addRight(toinsert);
	 else if (comp<0) current.addLeft(toinsert);
	 else return false;
	 return true;
 }

	public void	delete(TreeNode<E> node){
		if(node.getLeft()==null&& node.getRight()==null){
			node=null;
		}
		if(node.getLeft()==null){
			node.copy(node.getRight());
		}else if(node.getRight()==null){
			node.copy(node.getLeft());
		}else{
			TreeNode<E> smallestRight=findSmallestRight(node);
			node.setValue(smallestRight.getValue());
			delete(smallestRight);
		}		
	}

	private TreeNode<E> findSmallest(TreeNode<E> node){
		if(node.getLeft()==null && node.getRight()==null) return null;
		if(node.getLeft()==null) return node;
		return findSmallest(node.getLeft());
	}
	
	private TreeNode<E> findSmallestRight(TreeNode<E> node){
		if(node.getRight()==null) return null;
		TreeNode<E> right=node.getRight(); 
		return findSmallest(right);
	}
	
	public void inOrder(){
		this.inOrder(root);
	}
	//visit left self right
	private void inOrder(TreeNode<E> node){
		if(node!=null){
			inOrder(node.getLeft());
			node.visit();
			inOrder(node.getRight());
			
		}
	}	

	public void levelOrder(){
		Queue<TreeNode<E>> q= new LinkedList<TreeNode<E>>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode<E> current = q.remove();
			if(current !=null){
				current.visit();
				q.add(current.getLeft());
				q.add(current.getRight());
			}
			
		} 
	}
}

class TreeNode<E>{
	private E value;
	private TreeNode<E> parent;
	private TreeNode<E> left;
	private TreeNode<E> right;	
	
	public TreeNode(E val, TreeNode<E> parent){
		this.value=val;
		this.parent=parent;
		this.left=null;
		this.right=null;
	}

 	public void copy(TreeNode<E> other){
 		this.right=other.getRight();
 		this.left=other.getLeft();
 		this.value=other.getValue();
 	}
 
	public void setLeft(TreeNode<E> left){
		this.left=left;
	}
	
	public void setRight(TreeNode<E> right){
		this.right=right;
	}
	
	public void visit(){
		System.out.println(value);
	}	
	
	public TreeNode<E> addLeft(E val){
		this.left=new TreeNode(val,this);
		return this.left;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public TreeNode<E> getParent() {
		return parent;
	}

	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}

	public TreeNode<E> getLeft() {
		return left;
	}


	public TreeNode<E> getRight() {
		return right;
	}

	public TreeNode<E> addRight(E val) {
		this.right=new TreeNode(val,this);
		return this.right;
	}
	
	
	
	
}
