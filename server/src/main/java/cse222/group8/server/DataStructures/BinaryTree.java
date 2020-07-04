package cse222.group8.server.DataStructures;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Class for a binary tree that stores type E objects.  @param <E>  the type parameter
 *
 * @param <E> the type parameter
 */
@SuppressWarnings("serial")
public class BinaryTree<E> implements Serializable {

	/**
	 * Class to encapsulate a tree node.  @param <E>  the type parameter
	 *
	 * @param <E> the type parameter
	 */
	protected static class Node<E> implements Serializable {
		// Data Fields
		/**
		 * The information stored in this node.
		 */
		protected E data;
		/**
		 * Reference to the left child.
		 */
		protected Node<E> left;
		/**
		 * Reference to the right child.
		 */
		protected Node<E> right;

		// Constructors

		/**
		 * Construct a node with given data and no children.
		 *
		 * @param data The data to store in this node
		 */
		public Node(E data) {
			this.data = data;
			left = null;
			right = null;
		}

		// Methods
		/**
		 * Return a string representation of the node.
		 * 
		 * @return A string representation of the data fields.
		 */

		public String toString() {
			return data.toString();
		}

	}

	// Data Field
	/**
	 * The root of the binary tree
	 */
	protected Node<E> root;

	/**
	 * Instantiates a new Binary tree.
	 */
	public BinaryTree() {
		root = null;
	}

	/**
	 * Instantiates a new Binary tree.
	 *
	 * @param root the root
	 */
	protected BinaryTree(Node<E> root) {
		this.root = root;
	}

	/**
	 * Instantiates a new Binary tree.
	 *
	 * @param data      the data
	 * @param leftTree  the left tree
	 * @param rightTree the right tree
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<>(data);

		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}

	}

	/**
	 * Gets left subtree.
	 *
	 * @return the left subtree
	 */
	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left != null) {
			return new BinaryTree<>(root.left);
		} else {
			return null;
		}
	}

	/**
	 * Gets right subtree.
	 *
	 * @return the right subtree
	 */
	public BinaryTree<E> getRightSubtree(){
		if(root!= null&& root.right != null){
			return new BinaryTree<>(root.right);
		}
		return null;
	}

	public int size() {
		return(size(root));
	}
	private int size(Node node) {
		if (node == null) return(0);
		else {
			return(size(node.left) + 1 + size(node.right));
		}
	}

	/**
	 * Gets data.
	 *
	 * @return the data
	 */
	public E getData(){
		if(root == null){
			return null;
		}
		return root.data;
	}

	/**
	 * Is leaf boolean.
	 *
	 * @return the boolean
	 */
	public boolean isLeaf() {
		return (root.left == null && root.right == null);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, sb);
		return sb.toString();
	}


	private void preOrderTraverse(Node<E> node,StringBuilder sb){
		if (node != null) {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, sb);
			preOrderTraverse(node.right, sb);
		}
	}

	/**
	 * Read binary tree binary tree.
	 *
	 * @param scan the scan
	 * @return the binary tree
	 */
	public static BinaryTree<String> readBinaryTree(Scanner scan) {
		String data = scan.next();
		if(data.equals("null")){
			return null;
		} else{
			BinaryTree<String> leftTree = readBinaryTree(scan);
			BinaryTree<String> rightTree = readBinaryTree(scan);
			return new BinaryTree<String>(data,leftTree,rightTree);
		}
	}
}
