package com.personal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MainTest {
	public static void main(String args[]) {
		int[] nk = new int[2]; int n = nk[0], k = nk[1];
		int[][] stor = new int[n][3];
		
		for (int i = 0; i < n; i++) {
			int[] ar = new int[4];
			stor[i][0] = ar[0];
			stor[i][1] = ar[1];
			stor[i][2] = i;
		}
		
		Arrays.sort(stor, Comparator.comparingInt(a -> a[0]));
		
//		for (int x[] : stor) {
//			for (int y : x) {
//				System.out.print(y + " ");
//			}
//			System.out.println();
//		}
		
		int p = 0;
		
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
		List<Integer> answer = new ArrayList<Integer>();
		
		int size = 0;
		
		for (int i = 1; i <= (int)2E5; i++) {
			while (p < n && i == stor[p][0]) {
				if (!map.containsKey(stor[p][1])) {
					map.put(stor[p][1], new ArrayList<>());
				}
				map.get(stor[p][1]).add(stor[p][2]);
				
				if (size  == k) {
					int large = map.lastKey();
					answer.add(map.get(large).remove(map.get(large).size()-1));
					
					if (map.get(large).size() == 0) map.remove(large);
				} else  {
					++size;
				}
				p++;
			}
			if (map.containsKey(i)) {
				size -= map.get(i).size();
				map.remove(i);
			}
		}
		
	}
}
