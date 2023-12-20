package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

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

			int n = readInteger();

			String[] str = new String[n];

			for (int i = 0; i < n; i++) {
				str[i] = readString();
			}

			int[][] arr = new int[n][6];

			fillArray(arr, str);

			int ans = 0;

			for (int i = 0; i < 5; i++) {
				ans = Math.max(ans, subAns(arr, i));
			}

			sb.append(ans);

			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	private static int subAns(int[][] arr, int x) {
		int[] ans = new int[arr.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = 2 * arr[i][x] - arr[i][5];
		}
		
		int res = 0, idx = ans.length - 1;
		
		Arrays.sort(ans);
		
		while (idx >= 0 && res + ans[idx] > 0) {
			res += ans[idx];
			idx--;
		}
		
		return ans.length - idx - 1;
	}

	private static void fillArray(int[][] arr, String[] str) {
		for (int i = 0; i < arr.length; i++) {
			for (char c : str[i].toCharArray()) {
				arr[i][c - 'a']++;
			}
			for (int j = 0; j < 5; j++) {
				arr[i][5] += arr[i][j];
			}
		}
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
