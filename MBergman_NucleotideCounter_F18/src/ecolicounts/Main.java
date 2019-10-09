package ecolicounts;

import static sbcc.Core.*;

import java.io.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class Main {

	public static void main(String[] args) throws IOException {

		String dna = readFile(readLine());

		println("#A = " + countMatches(dna, 'A') + "\n#C = " + countMatches(dna, 'C') + "\n#G = "
				+ countMatches(dna, 'G') + "\n#T = " + countMatches(dna, 'T'));

	}
}
