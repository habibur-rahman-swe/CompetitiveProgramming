//package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Set;
import java.util.TreeSet;

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
			
			String s = readString();
			
			Set<String> set = new TreeSet<>();
			
			permutation(s.length(), s, set, new StringBuilder(""));
			
			sb.append(set.size());
			
			for (String x : set) {
				sb.append("\n").append(x);
			}
			
			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	private static void permutation(int n, String s, Set<String> set, StringBuilder sb) {
		if (sb.length() == n) {
			set.add(new String(sb));
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			permutation(n, s.substring(0, i) + s.substring(i+1), set, sb);
			sb.deleteCharAt(sb.length()-1);
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
