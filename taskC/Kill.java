import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.*;
import java.util.*;

public class Kill{
	public static void main(String[] args) {
		HashMap<Integer,LinkedList<String>> kill = new HashMap<>();
		List<String> lines = null;
		
		try{
			lines = Files.readAllLines(Paths.get(args[0]), Charset.defaultCharset());
		} catch(IOException e){
			e.printStackTrace();
		}

		System.out.println(kill.toString());
	}
}