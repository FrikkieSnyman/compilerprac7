import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.io.*;
import java.util.*;

public class InOut{
	public static void main(String[] args) {
		HashMap<Integer,LinkedList<String>> in = new HashMap<>();
		HashMap<Integer,LinkedList<String>> out = new HashMap<>();
		List<String> lines = null;
		
		try{
			lines = Files.readAllLines(Paths.get(args[0]), Charset.defaultCharset());
		} catch(IOException e){
			e.printStackTrace();
		}

		Gen gen = new Gen();
		gen.main(args);
		Succ succ = new Succ();
		succ.main(args);
		Kill kill = new Kill();
		kill.main(args);

		HashMap<Integer,LinkedList<String>> tmpin = new HashMap<>();
		HashMap<Integer,LinkedList<String>> tmpout = new HashMap<>();

		for (int i = 0; i < lines.size(); ++i){
			in.put(i+1,new LinkedList<String>());
			out.put(i+1,new LinkedList<String>());
		}

		while(true){
			tmpin = in;
			tmpout = out;
			in = copyMap(tmpin);
			out = copyMap(tmpout);

			if (compare(tmpin,in) && compare(tmpout,out)){
				break;
			}
		}

		System.out.println("In sets: " + in.toString());
		System.out.println("Out sets: " + out.toString());
		
	}

	private static HashMap<Integer,LinkedList<String>> copyMap(HashMap<Integer,LinkedList<String>> maptocopy){
		HashMap<Integer,LinkedList<String>> newMap = new HashMap<>();
		Set<Integer> keys = maptocopy.keySet();
		Integer[] k = new Integer[maptocopy.size()];
		k = keys.toArray(k);
		for (int i = 0; i < k.length;++i){
			newMap.put(k[i],maptocopy.get(k[i]));
		}


		return newMap;
	}

	private static boolean compare(HashMap<Integer,LinkedList<String>> left, HashMap<Integer,LinkedList<String>> right){
		Set<Integer> leftKeys = left.keySet();
		Set<Integer> rightKeys = right.keySet();
		if (leftKeys.size() != rightKeys.size()){
			return false;
		}

		Integer[] lk = new Integer[leftKeys.size()];
		lk = leftKeys.toArray(lk);
		Integer[] rk = new Integer[rightKeys.size()];
		rk = rightKeys.toArray(rk);

		for (int i = 0; i < lk.length; ++i){
			LinkedList<String> leftList = left.get(lk[i]);
			LinkedList<String> rightList = right.get(rk[i]);
			if (leftList.size() != rightList.size()){
				return false;
			}
			for (int j = 0; j < leftList.size(); ++j){
				if (!leftList.get(j).equals(rightList.get(j))){
					return false;
				}
			}
		}

		return true;
	}
}