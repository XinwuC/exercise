package basis.sort;

import java.util.Arrays;

public class HeapSort {

	public int[] sort(int[] input) {
		if (null == input || input.length < 2)
			return input;

		buildMaxHeap(input);

		for (int heapsize = input.length; heapsize > 1; --heapsize) {
			swap(input, 0, heapsize - 1);
			heapify(input, 0, heapsize - 1);
		}

		return input;
	}

	private static void buildMaxHeap(int[] input) {
		for (int index = input.length >> 1; index >= 0; --index) {
			heapify(input, index, input.length);
		}
	}

	private static void heapify(int[] input, int index, int heapsize) {
		int left = index << 1;
		int right = left + 1;

		if (right < heapsize) {
			// has both left and right
			if (input[left] > input[right]) {
				if (input[index] < input[left]) {
					swap(input, index, left);
					heapify(input, left, heapsize);
				}
			} else if (input[index] < input[right]) {
				swap(input, index, right);
				heapify(input, right, heapsize);
			}
		} else {
			// no right child
			if (left < heapsize && input[index] < input[left]) {
				swap(input, index, left);
				heapify(input, left, heapsize);
			} // else either no left means it is leaf node or index is larger,
				// simply return
		}
	}

	private static void swap(int[] input, int leftIndex, int rightIndex) {
		int tmp = input[leftIndex] ^ input[rightIndex];
		input[leftIndex] = tmp ^ input[leftIndex];
		input[rightIndex] = tmp ^ input[rightIndex];
	}

	public static void main(String[] args) {
		int[] input = { 3, 5 };
		HeapSort.swap(input, 0, 1);
		System.out.println(Arrays.toString(input));

		HeapSort sort = new HeapSort();
		sort.sort(input);
		System.out.println(Arrays.toString(input));

		input = new int[] { 2, 4, 3 };
		sort.sort(input);
		System.out.println(Arrays.toString(input));

		input = new int[] { 2, 4, 2 };
		sort.sort(input);
		System.out.println(Arrays.toString(input));
		
		input = new int[] { 10, 12, 11, 3, 5, 8, 9, 10 };
		sort.sort(input);
		System.out.println(Arrays.toString(input));
	}

}
