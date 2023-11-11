package atcoder.simple;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List<String> version = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("src/input.txt");
		Scanner sc = new Scanner(file);
		List<String> list = new ArrayList<>();
		
		while (sc.hasNext()) {
			list.add(sc.nextLine().trim());
		}
		
		addAllGivenVersion(list.get(0), version);
		
		StringBuilder sb = new StringBuilder();
		int v = 1;
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for (int i = 1; i < list.size(); i++) {
			String[] s = list.get(i).split(" ");
			
			switch(s[0]) {
			case "Add":
				++v;
				version.add(s[1]);
				map.put(i, v);
				break;
			case "Check":
				String[] t = s[1].split(":");
				int vs = Integer.parseInt(t[1]);
				if (version.indexOf(s[2]) <= map.getOrDefault(vs, 0)) {
					sb.append("Yes\n");
				} else {
					
					sb.append("No\n");
				}
				break;
			default:
				
			}
		}
		
		System.out.println(sb);
		
		sc.close();
	}

	private static void addAllGivenVersion(String string, List<String> version) {
		String s[] = string.split(", ");
		for (int i = 0; i < s.length; i++) {
			if (i == 0) {
				version.add(s[i].substring(1));
			} else if (i == s.length - 1) {
				version.add(s[i].substring(0, s[i].length() - 1));
			} else {
				version.add(s[i]);
			}
		}
		
	}

	
	
}
