package spellchecker;

import java.util.*;

public class BinaryTree {

	public BinaryTreeNode top, cur;


	public BinaryTree() {
		super();
		// TODO Auto-generated constructor stub
		top = null;
	}


	public void insert(String word) {
		word = word.trim();
		if (top == null)
			top = new BinaryTreeNode(word);

		cur = top;

		while (true) {
			int compare = cur.value.compareToIgnoreCase(word);
			if (compare > 0) {
				if (cur.left == null) {
					cur.left = new BinaryTreeNode(word);
					break;
				} else
					cur = cur.left;

			} else if (compare < 0) {
				if (cur.right == null) {
					cur.right = new BinaryTreeNode(word);
					break;
				} else
					cur = cur.right;

			} else
				break;

		}
	}


	public ArrayList<String> saveTreePreOrder(BinaryTreeNode node) {
		var preOrder = new ArrayList<String>();
		if (node != null) {

			preOrder.add(node.value);
			preOrder.addAll(saveTreePreOrder(node.left));
			preOrder.addAll(saveTreePreOrder(node.right));
			return preOrder;

		}
		return preOrder;
	}


	public ArrayList<String> saveTreeInOrder(BinaryTreeNode node) {
		var inOrder = new ArrayList<String>();
		if (node != null) {

			inOrder = saveTreeInOrder(node.left);
			inOrder.add(node.value);
			inOrder.addAll(saveTreeInOrder(node.right));
			return inOrder;

		}
		return inOrder;
	}

}