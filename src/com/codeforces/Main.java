package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

		int testCases = readInteger();
//		int testCases = 1;

//		long startTime = System.nanoTime();

		long[] pow = get2pow();

		for (int testCase = 1; testCase <= testCases; testCase++) {
			int[] nq = readIntegers();
			int n = nq[0], q = nq[1];
			long[] ar = readLongs();
			int[] qr = readIntegers();

			HashMap<Long, List<Integer>> hm = new HashMap<>();

			for (int i = 0; i < n; i++) {
				if ((ar[i] & 1) == 1)
					continue;
				if (hm.containsKey(ar[i]))
					hm.get(ar[i]).add(i);
				else {
					hm.put(ar[i], new ArrayList<>());
					hm.get(ar[i]).add(i);
				}
			}

			int idx = 0;
			while (idx < q && hm.size() > 0) {
				HashMap<Long, List<Integer>> tm = new HashMap<>();
				List<Long> keys = new ArrayList<>(hm.keySet());
				for (Long x : keys) {
					List<Integer> list = hm.remove(x);

					if (x % 2 == 1) {
						for (int y : list)
							ar[y] = x;
					} else {
						if (x % pow[qr[idx]] == 0) {
							if (tm.containsKey(x + pow[qr[idx] - 1])) {
								tm.get(x + pow[qr[idx] - 1]).addAll(list);
							} else {
								tm.put(x + pow[qr[idx] - 1], list);
							}
						} else {
							tm.put(x, list);
						}
					}
				}
				hm = new HashMap<>(tm);

				++idx;
			}

			for (long x : hm.keySet()) {
				List<Integer> list = hm.get(x);

				for (int z : list)
					ar[z] = x;
			}

			for (long x : ar)
				sb.append(x + " ");
			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	private static long[] get2pow() {
		long[] arr = new long[32];
		for (int i = 0; i <= 30; i++) {
			arr[i] = (long) Math.pow(2, i);
		}
		return arr;
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
