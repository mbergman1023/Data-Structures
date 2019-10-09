package rockcountdown;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import org.apache.commons.lang3.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class Main {

	public static void main(String[] args) {
		try {
			String s = readLine();
			String songFile = readFile(s);
			String[] songInfo = songFile.split("\r\n");

			var songs = new ArrayList<Song>();
			for (var eachSong : songInfo)
				songs.add(new Song(eachSong));

			Collections.reverse(songs);

			for (var output : songs)
				println(output.rank + "\t" + output.title);

			println("\nComplete");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
