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
			int n = readInteger();
			
			long maxSize = 0, maxSizeCost = 0;
			long leftMost = Integer.MAX_VALUE, lCost = 0;
			long rightMost = Integer.MIN_VALUE, rCost = 0;
			
			for (int i = 0; i < n; i++) {
				int[] lrc = readIntegers();
				long l = lrc[0], r = lrc[1], cost = lrc[2];
				
				if (r - l + 1 > maxSize || (r - l + 1 == maxSize && maxSizeCost > cost)) {
					maxSizeCost = cost;
					maxSize = r - l + 1;
				}
				if (l < leftMost || (l == leftMost && cost < lCost)) {
					lCost = cost;
					leftMost = l;
				}
				if (r > rightMost || (r == rightMost && cost < rCost)) {
					rCost = cost;
					rightMost = r;
				}
				
				long size = rightMost - leftMost + 1;
				long ans = 0;
				
				if (size == maxSize) {
					ans = Math.min(maxSizeCost, rCost + lCost);
				} else {
					ans = rCost + lCost;
				}
				sb.append(ans).append("\n");
			}

			//sb.append("\n");
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
