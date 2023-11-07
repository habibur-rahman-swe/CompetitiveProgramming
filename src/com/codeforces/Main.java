package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Main {

	public static BufferedReader reader;

	public static void main(String[] args) throws IOException {

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder("");

		try {
			reader = new BufferedReader(new FileReader(new File("src/com/codeforces/input.txt")));
		} catch (Exception e) {
			reader = new BufferedReader(new InputStreamReader(System.in));
		}

//		int testCases = readInteger();
		int testCases = 1;

//		long startTime = System.nanoTime();

		for (int testCase = 1; testCase <= testCases; testCase++) {
			int[] nk = readIntegers();
			int n = nk[0], k = nk[1];
			int[][] stor = new int[n][3];

			for (int i = 0; i < n; i++) {
				int[] ar = readIntegers();
				stor[i][0] = ar[0];
				stor[i][1] = ar[1];
				stor[i][2] = i;
			}

			Arrays.sort(stor, Comparator.comparingInt(a -> a[0]));

			TreeMap<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
			List<Integer> answer = new ArrayList<Integer>();

			for (int i = 1, p = 0, size = 0; i <= 2E5; i++) {
				while (p < n && stor[p][0] == i) {
					if (!map.containsKey(stor[p][1])) {
						map.put(stor[p][1], new ArrayList<>());
					}
					map.get(stor[p][1]).add(stor[p][2]);

					if (size == k) {
						int large = map.lastKey();
						answer.add(map.get(large).remove(map.get(large).size() - 1));

						if (map.get(large).size() == 0)
							map.remove(large);
					} else {
						size++;
					}
					p++;
				}
				if (map.containsKey(i)) {
					size -= map.get(i).size();
					map.remove(i);
				}
			}

			sb.append(answer.size() + "\n");
			for (int x : answer)
				sb.append(x + 1 + " ");
//			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	// ------------------------------------------------------------------------------------
	private static String readString() throws IOException {
		String s = reader.readLine();
		return s;
	}

	private static int readInteger() throws NumberFormatException, IOException {
		int num = Integer.parseInt(reader.readLine());
		return num;
	}

	private static long readLong() throws NumberFormatException, IOException {
		long num = Long.parseLong(reader.readLine());
		return num;
	}

	private static int[] readIntegers() throws NumberFormatException, IOException {
		String nums[] = reader.readLine().split(" ");
		int[] arr = new int[nums.length];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(nums[i]);
		}

		return arr;
	}

	private static long[] readLongs() throws NumberFormatException, IOException {
		String nums[] = reader.readLine().split(" ");
		long[] arr = new long[nums.length];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = Long.parseLong(nums[i]);
		}

		return arr;
	}
}
