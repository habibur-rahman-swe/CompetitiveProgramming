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

//		int testCases = readInteger();
		int testCases = 1;

//		long startTime = System.nanoTime();

		for (int testCase = 1; testCase <= testCases; testCase++) {
			int n = readInteger();
			int[] arr = readIntegers();

			int[] brr = new int[n];

			brr[0] = 0;

			for (int i = 1; i < n; i++) {
				brr[i] = arr[i - 1] ^ brr[i - 1];
			}

			for (int k = 0; k < 20; k++) {
				int count = 0;
				for (int i = 0; i < n; i++) {
					if ((brr[i] & 1 << k) > 0)
						count--;
					else
						count++;
				}
				if (count < 0) {
					for (int i = 0; i < n; i++) {
						brr[i] = brr[i] ^ (1 << k);
					}
				}
			}
			
			StringBuilder res = new StringBuilder();
			Arrays.stream(brr).forEach(x -> res.append(x).append(" "));
			
			sb.append(res);
			sb.append("\n");
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
