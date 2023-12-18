package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
			int x = readInteger();
			int n = (int) Math.pow(2, x);

			int[] arr = new int[x];

			for (int i = 0; i < n; i++) {
				if (i > 0) {
					increment(arr);
				}

				for (int j = 0; j < arr.length; j++) {
					int val = arr[j];
					if (j > 0) val ^= arr[j-1];
					sb.append(val);
				}
				
				sb.append("\n");
			}

			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	private static void increment(int[] arr) {
		int carry = 1;
		for (int i = arr.length - 1; i >= 0; i--) {
			arr[i] += carry;
			carry = 0;
			if (arr[i] > 1) {
				arr[i] = 0;
				carry = 1;
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
