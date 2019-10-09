package spellchecker;

import static sbcc.Core.*;

import java.io.*;
import java.util.regex.*;

public class BasicSpellChecker implements SpellChecker {

	BasicDictionary dict;

	String doc;

	int index;


	public BasicSpellChecker() {
		super();
		dict = new BasicDictionary();
		index = 0;
		doc = "";
	}


	@Override
	public void importDictionary(String filename) throws Exception {
		dict.importFile(filename);
	}


	@Override
	public void loadDictionary(String filename) throws Exception {
		dict.load(filename);

	}


	@Override
	public void saveDictionary(String filename) throws Exception {
		dict.save(filename);
	}


	@Override
	public void loadDocument(String filename) throws Exception {
		doc = readFile(filename);
	}


	@Override
	public void saveDocument(String filename) throws Exception {
		FileWriter fw = new FileWriter(filename);

		var strToBytes = doc.getBytes();
		for (var str : strToBytes)
			fw.write(str);

		fw.close();

	}


	@Override
	public String getText() {
		return doc;
	}


	@Override

	/*
	 * Look for a word that doesn't appear in the dictionary if found return
	 * String[] such that: [0] - Original word [1] - Index of start of word in
	 * document [2] - Previous Word in dictionary [3] - Next word in dictionary
	 * 
	 */

	public String[] spellCheck(boolean continueFromPrevious) {

		Pattern p = Pattern.compile("\\b[\\w|']+\\b");
		Matcher m = p.matcher(doc);

		if (continueFromPrevious != true)
			index = 0;

		while (m.find(index)) {
			index = m.end();
			var str = dict.find(m.group());
			if (str != null) {
				String[] returnStr = { m.group(), String.valueOf(m.start()), str[0], str[1] };
				return returnStr;
			}
		}
		return null;
	}


	@Override
	public void addWordToDictionary(String word) {
		dict.add(word);
	}


	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {

		doc = doc.substring(0, startIndex) + replacementText + doc.substring(endIndex);
		index = endIndex + (replacementText.length() - (endIndex - startIndex));
	}

}
