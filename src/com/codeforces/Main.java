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

		for (int testCase = 1; testCase <= testCases; testCase++) {
			int[] nm = readIntegers();
			int[] a = readIntegers();
			int[] b = readIntegers();
			int[] color = new int[nm[0]];
			Arrays.fill(color, -1);

			HashMap<Integer, List<Integer>> adj = new HashMap<>();

			for (int i = 0; i < nm[0]; i++) {
				adj.put(i, new ArrayList<>());
			}
			boolean flag = true;
			for (int i = 0; i < nm[1]; i++) {
				if (a[i] == b[i])
					flag = false;
				adj.get(a[i] - 1).add(b[i] - 1);
				adj.get(b[i] - 1).add(a[i] - 1);
			}

			for (int i = 0; i < nm[0] & flag; i++) {
				if (color[i] == -1) {
					flag &= dfs(-1, i, color, adj);
				}
			}
			sb.append(flag ? "Yes" : "No");
			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	private static boolean dfs(int prev, int pres, int[] color, HashMap<Integer, List<Integer>> adj) {
		if (color[pres] != -1)
			return true;

		if (prev != -1) {
			color[pres] = (color[prev] + 1) % 2;
		}
		boolean flag = true;

		for (int x : adj.get(pres)) {
			if (color[x] == -1) {
				flag &= dfs(pres, x, color, adj);
			} else if (color[x] == color[pres]) {
				return false;
			}
		}

		return flag;
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
