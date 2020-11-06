package cs2321;
import java.util.Iterator;

import net.datastructures.*;
	

/**
 * @author ruihong-adm
 *
 * @param <E>
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{
	
	node root;
	int size;
	
	class node implements Position<E>{
		E element;
		node leftChild;
		node rightChild;
		node parent;

		@Override
		public E getElement() throws IllegalStateException {
			return element;
		}
		
		public node leftChild() {
			return leftChild;
		}
		
		public node rightChild() {
			return rightChild;
		}
		
		public node parent() {
			return parent;
		}
	}
	
	@Override
	public Position<E> root() {
		return root;
	}
	
	public  LinkedBinaryTree( ) {
		root = new node();
		add(root);
	}
	
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		node position = convert(p);
		return position.parent;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		ArrayList<Position<E>> children = new ArrayList<Position<E>>();
		children.addLast(left(p));
		children.addLast(right(p));
		return children;
	}

	@Override
	/* count only direct child of the node, not further descendant. */
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		if(isExternal(p)) {
			return 0;
		}
		else if(left(p).getElement()!=null & right(p).getElement()!=null) {
			return 2;
		}
		return 1;
	}

	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		return !isExternal(p);
	}

	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		return left(p)==null & right(p)==null;
	}

	@Override
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		return p.equals(root);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		node pos = convert(p);
		if(pos.leftChild().getElement()==null) {
			return null;
		}
		return pos.leftChild;
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		node pos = convert(p);
		if(pos.rightChild().getElement()==null) {
			return null;
		}
		return pos.rightChild;
	}

	public void setElement(Position<E> p, E element) throws IllegalArgumentException {
		node pos = convert(p);
		pos.element = element;
		
	}
	
	@Override
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		Position<E> left = left(parent(p));
		Position<E> right = right(parent(p));
		if(!right.equals(p)) {
			return right;
		}
		else if(!left.equals(p)) {
			return left;
		}
		return null;
	}
	
	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if(numChildren(root)!=0) {
			throw new IllegalStateException();
		}
		root.element = e;
		add(root);
		size = 1;
		return root;
	}
	
	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		node position = convert(p);
		position = position.leftChild();
		if(position.getElement()!=null) {
			throw new IllegalArgumentException();
		}
		
		position.element = e;
		add(position);
		size++;
		return position;
	}

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		node position = convert(p);
		position = position.rightChild();
		if(position.getElement()!=null) {
			throw new IllegalArgumentException();
		}
		
		position.element = e;
		add(position);
		size++;
		return position;
	}
	
	/* Attach trees t1 and t2 as left and right subtrees of external Position. 
	 * if p is not external, throw IllegalArgumentExeption.
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		node position = convert(p);
		if(isInternal(p)) {
			throw new IllegalArgumentException();
		}
		position.leftChild = t1.root;
		position.rightChild = t2.root;
		t1.root.parent = position;
		t2.root.parent = position;
		size = size +t1.size+t2.size;
	}
	
	
	/**
	 * If p is an external node ( that is it has no child), remove it from the tree.
	 * If p has one child, replace it with its child. 
	 * If p has two children, throw IllegalAugumentException. 
	 * @param p who has at most one child. 
	 * @return the element stored at position p if p was removed.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		node position = convert(p);
		if(position.leftChild().getElement()!= null & position.rightChild().getElement()!=null) {
			throw new IllegalArgumentException();
		}
		node child;
		if(position.leftChild.getElement()!=null) {
			child = position.leftChild();
		}
		else {
			child = position.rightChild();
		}
		
		if(isRoot(position)) {
			root = null;
			size = 0;
			return position.getElement();
		}
		
		if(position.parent().leftChild().equals(position)) {
			position.parent.leftChild = child;
			child.parent = position.parent;
		}
		else {
			position.parent.rightChild = child;
			child.parent = position.parent;
		}
		size--;
		return position.getElement();
	}
	
	private void add(node position) {
		position.leftChild = new node();
		position.rightChild = new node();
		position.leftChild.parent = position;
		position.rightChild.parent = position;
	}
	
	private node convert(Position<E> p) throws IllegalArgumentException {
		try {
			node position = (node) p;
			return position;
		}
		catch (IllegalArgumentException e) {
			throw e;
		}
	}
}
