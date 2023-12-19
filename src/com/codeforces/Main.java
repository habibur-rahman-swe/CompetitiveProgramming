package com.codeforces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

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
			int[] a = readIntegers();
			int[] b = readIntegers();
			int[] c = readIntegers();
			
			PriorityQueue<Integer> pa = new PriorityQueue<>((x, y) -> a[x] - a[y]);
			PriorityQueue<Integer> pb = new PriorityQueue<>((x, y) -> b[x] - b[y]);
			PriorityQueue<Integer> pc = new PriorityQueue<>((x, y) -> c[x] - c[y]);
			
			for (int i = 0; i < n; i++) {
				pa.add(i);
				pb.add(i);
				pc.add(i);
				
				if (pa.size() > 3) {
					pa.poll();
				}
				if (pb.size() > 3) {
					pb.poll();
				}
				if (pc.size() > 3) {
					pc.poll();
				}
			}
			
			long ans = 0;
			
			for (int i : pa) {
				for (int j : pb) {
					for (int k : pc) {
						if (i != j && j != k && i != k) {
							ans = Math.max(ans, (long) a[i] + (long) b[j] + (long) c[k]);
						}
					}
				}
			}
			
			sb.append(ans);
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
