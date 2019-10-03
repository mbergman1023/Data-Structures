package dnasplicing;

import java.util.*;

import org.apache.commons.lang3.*;

public class LinkedDnaStrand implements DnaStrand {

	private DnaNode top, bottom;
	int nodeCount, appendCount;
	StringBuilder str;


	public LinkedDnaStrand(String dnaSequence) {
		// TODO Auto-generated constructor stub
		DnaNode newNode = new DnaNode(dnaSequence);
		top = newNode;
		bottom = newNode;
		nodeCount = 1;
		str = new StringBuilder(dnaSequence);
	}


	public LinkedDnaStrand() {
		str = new StringBuilder();

		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		if (top.next != null) {
			DnaNode cur = top.next;
			string.append(top.dnaSequence.toString());
			while (cur != null) {
				string.append(cur.dnaSequence.toString());
				cur = cur.next;
			}
			return string.toString();
		} else {
			return top.dnaSequence.toString();
		}

	}


	@Override
	public long getNucleotideCount() {
		// TODO Auto-generated method stub
		return str.length();
	}


	@Override
	public void append(String dnaSequence) {
		if (dnaSequence != null) {
			DnaNode anotherNode = new DnaNode(dnaSequence);
			if (top == null) {
				top = anotherNode;
				bottom = anotherNode;
			} else {
				anotherNode.next = null;
				anotherNode.previous = bottom;
				anotherNode.previous.next = anotherNode;
				bottom = anotherNode;
				str.append(dnaSequence);
				appendCount++;
			}

			nodeCount++;
		}

		// TODO Auto-generated method stub

	}


	@Override
	public DnaStrand cutSplice(String enzyme, String splicee) {
		LinkedDnaStrand newDna = new LinkedDnaStrand();
		var dna = top.dnaSequence;
		var dnaClone = dna.split(enzyme);
		if (dna.startsWith(enzyme)) {
			newDna.append(splicee);
		}
		int i = 0;
		for (var splits : dnaClone) {
			if (splits.isEmpty())
				continue;

			newDna.append(splits);

			if (dna.endsWith(enzyme) || i < dnaClone.length - 1) {
				newDna.append(splicee);
			}
			i++;

		}
		// TODO Auto-generated method stub
		return newDna;

	}


	@Override
	public DnaStrand createReversedDnaStrand() {
		LinkedDnaStrand reversedDna = new LinkedDnaStrand();
		ArrayList<String> reverse = new ArrayList<String>();
		DnaNode cur = top;
		while (cur != null) {
			reverse.add(cur.dnaSequence);
			cur = cur.next;
		}
		Collections.reverse(reverse);
		for (var reversed : reverse) {
			StringBuilder rev = new StringBuilder();
			rev.append(reversed);
			reversedDna.append(rev.reverse().toString());
		}
		// TODO Auto-generated method stub
		return reversedDna;
	}


	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return appendCount;
	}


	@Override
	public DnaNode getFirstDnaNode() {

		// TODO Auto-generated method stub
		return top;
	}


	@Override
	public int getDnaNodeCount() {

		// TODO Auto-generated method stub
		return nodeCount;
	}

}
