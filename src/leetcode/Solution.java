package leetcode;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.IntStream;

//1535. Find the Winner of an Array Game
class S1535 {
	public int getWinner(int[] arr, int k) {
		int left = 0, right = 1;
		int leftCnt = 0, rightCnt = 0;

		while (right < arr.length) {
			if (arr[left] > arr[right]) {
				leftCnt++;
				right++;
				rightCnt = 0;
			} else {
				left = right;
				leftCnt = rightCnt + 1;
				right++;
				rightCnt = 0;
			}
			if (leftCnt >= k)
				break;
		}
		return arr[left];
	}
}

//Build an Array With Stack Operation
class F1441 {
	public List<String> buildArray(int[] target, int n) {
		List<String> list = new ArrayList<>();

		for (int i = 1, idx = 0; i <= n; i++) {
			list.add("Push");
			if (target.length > idx && target[idx] == i) {
				idx++;
				if (idx == target.length)
					break;
				continue;
			}
			list.add("Pop");
		}

		return list;
	}
}

//1503. Last Moment Before All Ants Fall Out of a Plank
class F1503 {
	public int getLastMoment(int n, int[] left, int[] right) {
		Arrays.sort(left);
		Arrays.sort(right);

		if (left.length == 0) {
			return n - right[0];
		}

		if (right.length == 0) {
			return left[left.length - 1];
		}

		return Math.max(n - right[0], left[left.length - 1]);
	}
}

//2265. Count Nodes Equal to Average of Subtree
class CountNodesEqualToAverateOfSubtree {
	int ans = 0;

	public int averageOfSubtree(TreeNode root) {
		HashMap<TreeNode, Integer> hm = new HashMap<>();

		fillSum(hm, root);

		return ans;
	}

	private int fillSum(HashMap<TreeNode, Integer> hm, TreeNode root) {
		int cnt = 1;
		hm.put(root, root.val);

		if (root.left != null) {
			cnt += fillSum(hm, root.left);
			hm.put(root, hm.get(root) + hm.get(root.left));
		}

		if (root.right != null) {
			cnt += fillSum(hm, root.right);
			hm.put(root, hm.get(root) + hm.get(root.right));
		}

		if (hm.get(root) / cnt == root.val)
			++ans;

		return cnt;
	}
}

public class Solution {

	// ------------------------------------------------------------------
	public int numberOfPoints(List<List<Integer>> nums) {
		Collections.sort(nums, (a, b) -> (a.get(0) - b.get(0)));

		return 0;
	}

	public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
		int ans[] = new int[] { -1, -1 };
		for (int i = 0, minIdx = 0, maxIdx = 0; i < nums.length - indexDifference; i++) {
			if (nums[i] < nums[minIdx]) {
				minIdx = i;
			}
			if (nums[i] > nums[maxIdx]) {
				maxIdx = i;
			}
			if (Math.abs(nums[minIdx] - nums[i + indexDifference]) >= valueDifference) {
				ans[0] = minIdx;
				ans[1] = i + indexDifference;
				return ans;
			}
			if (Math.abs(nums[maxIdx] - nums[i + indexDifference]) >= valueDifference) {
				ans[0] = maxIdx;
				ans[1] = i + indexDifference;
				return ans;
			}
		}
		return ans;
	}

	public String shortestBeautifulSubstring(String s, int k) {
		PriorityQueue<String> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0, cnt = 0; i < s.length(); i++) {
			if (s.charAt(i) == 1) {
				++cnt;
			}
			sb.append(s.charAt(i));

			while (sb.length() > 0 && sb.charAt(0) == '0') {
				sb.deleteCharAt(0);
			}
			if (cnt > k) {
				while (sb.length() > 0 && cnt > k) {
					if (sb.charAt(0) == '1')
						--cnt;
					sb.deleteCharAt(0);
				}
			}
			pq.add(new String(sb));
		}
		return pq.size() == 0 ? pq.peek() : "";
	}

	public int maxSum(int[] nums) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
		int ans = 0;
		for (int i = 1; i <= 9; i++) {
			for (int x : nums) {
				int temp = x;
				int maxDigit = 0;
				while (temp > 0) {
					maxDigit = Math.max(maxDigit, temp % 10);
					temp /= 10;
				}
				if (maxDigit == i) {
					pq.add(x);
				}
			}
			if (pq.size() > 1) {
				ans = Math.max(ans, pq.poll() + pq.poll());
			}
			pq.clear();
		}
		return ans;
	}

	public int findNumberOfLIS(int[] nums) {

		TreeMap<Integer, Integer> tm = new TreeMap<>();

		int[] dp = new int[nums.length];

		for (int i = 0; i < nums.length; i++) {
			Integer lower = tm.lowerKey(nums[i]);
			if (lower != null) {
				dp[i] = tm.get(lower) + 1;
				tm.put(lower, dp[i]);
			} else {
				dp[i] = 1;
				tm.put(nums[i], 1);
			}
		}

		int ans = IntStream.of(dp).reduce(1, (a, b) -> a * b);

		return ans;
	}

	public void solveSudoku(char[][] board) {
		solve(board);
	}

	boolean solve(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == '.') {
					for (char num = '1'; num <= '9'; num++) {
						if (isSafe(board, i, j, num)) {
							board[i][j] = num;
							if (solve(board)) // found the ans
								return true;
							else
								// backtrack
								board[i][j] = '.';
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	boolean isSafe(char[][] board, int row, int col, char num) { // check the row
		for (int i = 0; i < board.length; i++) { // check if the number is in the row
			if (board[i][col] == num)
				return false;
			// check the column
			// check if the number is in the column
			if (board[row][i] == num)
				return false;
		}
		int sqrt = (int) (Math.sqrt(board.length));
		int rowStart = row - row % sqrt;
		int colStart = col - col % sqrt;
		for (int r = rowStart; r < rowStart + sqrt; r++) {
			for (int c = colStart; c < colStart + sqrt; c++) {
				if (board[r][c] == num)
					return false;
			}
		}
		return true;
	}

	private static <E> void printArray(E[] arr) {
		for (E element : arr) {
			System.out.println("Element: " + element);
		}
	}

	public int getZero() {
		return 0;
	}

	public int getAns(int[] nums, int k) {
		int ans = 0;

		Arrays.sort(nums);

		for (int i = 0, j = 0; i < nums.length; i++) {
			if (nums[i] - nums[j] <= 2 * k) {
				ans = Math.max(ans, i - j + 1);
			} else {
				while (nums[i] - nums[j] > 2 * k) {
					j++;
				}
			}
		}

		return ans;
	}

	public int minimumIndex(List<Integer> nums) {
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> map2 = new HashMap<>();

		for (int x : nums) {
			map.put(x, map.getOrDefault(x, 0) + 1);
		}

		for (int i = 0; i < nums.size(); i++) {
			int x = nums.get(i);
			map2.put(x, map2.getOrDefault(x, 0) + 1);
			int m1 = map2.get(x);
			int m2 = map.get(x) - map2.get(x);

			if (m1 * 2 > i + 1 && m2 * 2 > nums.size() - i)
				return i;
		}

		return -1;
	}

	public int longestSubsequence(int[] arr, int difference) {

		HashMap<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i] - difference, 0) + 1);
		}

		int max = 1;

		for (int x : map.keySet()) {
			max = Math.max(max, map.get(x));
		}

		return max;
	}

	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		int i = 0;

		while (i < n) {
			int c = nums[i] - 1;

			if (nums[i] > 0 && c < n) {
				if (nums[i] != nums[c]) {
					int temp = nums[i];
					nums[i] = nums[c];
					nums[c] = temp;
				} else {
					i++;
				}
			} else {
				i++;
			}
		}

		for (i = 0; i < n; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}

		return n + 1;
	}

	// 6924
	public int longestValidSubstring(String word, List<String> forbidden) {
		return 0;
	}
}

class MyHashMap {
	static class Node {
		int key, value;
		Node next;

		Node(int _key, int _value) {
			key = _key;
			value = _value;
		}
	}

	Node[] nodes = new Node[10006];

	public MyHashMap() {

	}

	public void put(int key, int value) {
		// Collision Handling in put:
		// In the put method,
		// you are updating the value if the key already exists.
		// However, you should also check if the key exists when the cur.next is null
		// (i.e., at the end of the linked list).
		int index = key % nodes.length;
		if (nodes[index] != null) {
			Node prev = null;
			Node cur = nodes[index];
			while (cur != null && cur.key != key) {
				prev = cur;
				cur = cur.next;
			}

			if (cur != null) {
				cur.value = value;
			} else {
				Node item = new Node(key, value);
				prev.next = item;
			}
		} else {
			Node item = new Node(key, value);
			nodes[index] = item;
		}
	}

	public int get(int key) {
		int index = key % nodes.length;
		if (nodes[index] != null) {
			Node cur = nodes[index];
			while (cur != null) {
				if (cur.key == key) {
					return cur.value;
				}
				cur = cur.next;
			}
		}
		return -1;
	}

	public void remove(int key) {
		// Remove Method: The remove method is missing handling for removing the first
		// node in the linked list.
		// You should update the nodes[index] reference if you remove the first node.
		int index = key % nodes.length;
		if (nodes[index] != null) {
			Node prev = null;
			Node cur = nodes[index];
			while (cur != null && cur.key != key) {
				prev = cur;
				cur = cur.next;
			}

			if (cur != null) {
				if (prev != null) {
					prev.next = cur.next;
				} else {
					nodes[index] = cur.next;
				}
			}
		}
	}
}

/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj
 * = new MyHashMap(); obj.put(key,value); int param_2 = obj.get(key);
 * obj.remove(key);
 */

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Node {
	boolean forbidden;
	char val;
	Node[] arr = new Node[26];
}