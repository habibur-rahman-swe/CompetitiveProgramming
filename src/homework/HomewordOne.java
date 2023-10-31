package homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomewordOne {
	public static void main(String[] args) {
		
		// created a array list
		List<Integer> arrayList = new ArrayList<>();
		int max = 1_000_000;
		
		// inserting 1_000_000 values in the array list
		for (int i = 0; i < max; i++) {
			arrayList.add(i);
		}
		
		// created a linked list
		List<Integer> linkedList = new LinkedList<>();
		
		// inserting 1_000_000 values in the linked list
		for (int i = 0; i < max; i++) {
			linkedList.add(i);
		}
		
		
	}
	
	// a method that takes list as an argument and adds specific amount of integers into the beginning of list
	public static void addElementsToBeginning(List<Integer> list, int numberOfElementsToAdd) {
		for (int i = 0; i < numberOfElementsToAdd; i++) {
			list.add(0, i);
		}
	}
}
