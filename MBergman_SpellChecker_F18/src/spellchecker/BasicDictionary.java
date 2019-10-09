package spellchecker;

import java.io.*;
import java.util.*;
import static sbcc.Core.*;

public class BasicDictionary implements Dictionary {

	private BinaryTree tree;

	private int count;


	public BasicDictionary() {
		tree = new BinaryTree();
		count = 0;
	}


	/**
	 * Replaces the current dictionary with words imported from the specified text
	 * file. Words in the file are in lexicographical (ASCII) order, one word per
	 * line.
	 * 
	 * @param filename
	 *            Name (possibly including a path) of the file to import.
	 * @throws Exception
	 */
	@Override
	public void importFile(String filename) throws Exception {

		tree = new BinaryTree();

		completeTree(readFileAsLines(filename));

	}


	void completeTree(List<String> words) {
		int x = 1;
		for (int i = 0; i < words.size(); i += (x / 2))
			x *= 2;

		int bottom = words.size() - ((x / 2) - 1);
		int index;

		if (bottom > (x / 4))
			index = ((x / 2) - 1) / 2 + (x / 4);

		else
			index = ((x / 2) - 1) / 2 + bottom;

		this.add(words.get(index));

		if (words.size() == 2) {
			this.add(words.get(1));
			this.add(words.get(0));
		} else if (words.size() != 1) {
			var left = words.subList(0, index);
			completeTree(left);

			var right = words.subList(index + 1, words.size());
			completeTree(right);
		}

	}


	/**
	 * Loads the specified file from a previously saved dictionary tree file. The
	 * file format is text, with one word per line.
	 * 
	 * @param filename
	 *            Name (possibly including a path) of the file to load from.
	 * @throws Exception
	 */
	@Override
	public void load(String filename) throws Exception {
		tree = new BinaryTree();
		for (var word : readFileAsLines(filename))
			this.add(word);

	}


	@Override
	public void save(String filename) throws Exception {
		var words = tree.saveTreePreOrder(tree.top);
		FileWriter fw = new FileWriter(filename);
		for (var word : words)
			fw.write(word + "\n");
		fw.close();
	}


	/*
	 * @return If the word is <b>found</b> this method returns <b>null</b>.
	 * Otherwise, it returns a String array organized as follows:
	 * 
	 * <pre> [0] = Preceeding word in the dictionary [1] = Succeeding word in the
	 * dictionary
	 * 
	 * e.g. if the unknown word was "spelm", the result might be:
	 * 
	 * [0] = "spells" [1] = "spelt"
	 * 
	 * If there is no preceeding or succeeding word in the dictionary, set the
	 * element to "". </pre>
	 */
	@Override
	public String[] find(String word) {
		String str[] = { "", "" };
		BinaryTreeNode cur = tree.top;
		while (true) {
			int compare = cur.value.compareToIgnoreCase(word);

			if (compare > 0) {
				str[1] = cur.value;
				if (cur.left != null)
					cur = cur.left;
				else
					return str;

			} else if (compare < 0) {
				str[0] = cur.value;
				if (cur.right != null)
					cur = cur.right;
				else
					return str;
			} else
				return null;

		}

	}


	@Override
	public void add(String word) {
		tree.insert(word.trim());
		count++;
	}


	@Override
	public BinaryTreeNode getRoot() {
		return tree.top;
	}


	public ArrayList<String> inOrder() {
		return tree.saveTreeInOrder(tree.top);
	}


	public ArrayList<String> preOrder() {
		return tree.saveTreePreOrder(tree.top);
	}


	@Override
	public int getCount() {
		return count;
	}

}