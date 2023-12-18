package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

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
			reader.readLine();
			int nk[] = readIntegers(); int n = nk[0], k = nk[1];
			int[] ar = readIntegers();
			int[] tm = readIntegers();
			
			long[] res = new long[n+2];
			
			Arrays.fill(res, Integer.MAX_VALUE);
			
			for (int i = 0; i < k; i++) {
				res[ar[i]] = Math.min(tm[i], res[ar[i]]);
			}
			
			for (int i = 2; i <= n; i++) {
				res[i] = Math.min(res[i-1] + 1, res[i]);
			}
			
			for (int i = n - 1; i >= 1; i--) {
				res[i] =  Math.min(res[i], res[i+1] + 1);
			}
			
			for (int i = 1; i <= n; i++) {
				sb.append(res[i] + " ");
			}
			
			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	private static int compare(Ath ath1, Ath ath2) {
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (ath1.ar[i] < ath2.ar[i]) ++cnt;
		}
		return cnt;
	}

	private static boolean notSup(int[] a, int[] b) {
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (a[i] > b[i])
				++cnt;
		}
		return cnt > 3;
	}

	static class Ath {
		int m = 5;
		int[] ar = new int[5];

		Ath(int[] ar) {
			this.ar = ar;
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
