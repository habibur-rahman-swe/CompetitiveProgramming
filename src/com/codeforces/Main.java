package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

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
			int q = readInteger();
			int[][] arr = new int[q][2];
			
			for (int i = 0; i < q; i++) {
				arr[i] = readIntegers();
			}
			
			HashMap<Integer, Integer> map = new HashMap<>();
			
			for (int i = 0; i < 30; i++) {
				map.put(i, (int)Math.pow(2, i));
			}
			
			int[] dp = new int[30];
			
			for (int[] xy : arr) {
				int x = xy[0];
				int y = xy[1];
				
				if (x == 1) {
					dp[y]++;
				} else {
					sb.append(isPossible(y, dp, map) ? "YES" : "NO").append("\n");
				}
			}
			
			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	private static boolean isPossible(int w, int[] dp, HashMap<Integer, Integer> map) {
		for (int i = 29; i >= 0; i--) {
			if (dp[i] > 0 && map.get(i) <= w) {
				int t1 = dp[i];
				int t2 = w / map.get(i);
				int t = Math.min(t1, t2);
				
				w -= t * map.get(i);
			}
			if (w == 0) return true;
		}
		return false;
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
