package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

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
			char[] s = readString().toCharArray();

			Map<Long, Integer> map = new HashMap<>();

			long dc = 0, kc = 0;

			for (char c : s) {
				if (c == 'D') {
					++dc;
				} else {
					++kc;
				}

				long a = dc;
				long b = kc;

				if (a == 0)
					b = 1;
				else if (b == 0)
					a = 1;
				else {
					long gcd = gcd(a, b);
					a /= gcd;
					b /= gcd;
				}

				long key = a * 1000000 + b;

				map.put(key, map.getOrDefault(key, 0) + 1);

				sb.append(map.get(key)).append(" ");
			}

			sb.append("\n");
		}

		writer.write(sb.toString());
		writer.flush();
	}

	private static long gcd(long a, long b) {
		return (a % b == 0 ? b : gcd(b, a % b));
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
