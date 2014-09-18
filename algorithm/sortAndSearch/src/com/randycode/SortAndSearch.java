package com.randycode;

public class SortAndSearch {

	// quick sort
	public static void quickSort(int[] num, int low, int high) {
		if (low < high) {
			int left = low, right = high;
			while (left < right) {
				while (left < right && num[left] <= num[right]) {
					--right;
				}
				int temp = num[left];
				num[left] = num[right];
				num[right] = temp;
				while (left < right && num[left] <= num[right]) {
					++left;
				}
				temp = num[left];
				num[left] = num[right];
				num[right] = temp;
			}
			quickSort(num, low, left - 1);
			quickSort(num, left + 1, high);
		}
	}

	// quick search nth-largest number
	public static int quickSearchNthLargest(int[] num, int low, int high,
			int nth) {
		if (low < high) {
			int left = low, right = high, temp = 0;
			while (left < right) {
				while (left < right && num[left] >= num[right]) {
					--right;
				}
				temp = num[left];
				num[left] = num[right];
				num[right] = temp;
				while (left < right && num[left] >= num[right]) {
					++left;
				}
				temp = num[left];
				num[left] = num[right];
				num[right] = temp;
			}
			if (left == nth - 1) {
				return num[left];
			} else if (left > nth - 1) {
				return quickSearchNthLargest(num, low, left - 1, nth);
			} else {
				return quickSearchNthLargest(num, left + 1, high, nth);
			}
		}
		return num[low];
	}

	// non-recursive binary search
	public static int search(int[] num, int target) {
		int low = 0, high = num.length - 1;
		int index = (low + high) / 2;
		while (num[index] != target) {
			if (num[index] < target) {
				low = index + 1;
			} else {
				high = index - 1;
			}
			if (low > high) {
				return -1;
			}
			index = (low + high) / 2;
		}
		return index;
	}

	// recursive binary search
	public static int search(int[] num, int low, int high, int target) {
		if (low > high) {
			return -1;
		}
		int index = (low + high) / 2;
		if (num[index] == target) {
			return index;
		}
		if (num[index] < target) {
			return search(num, index + 1, high, target);
		}
		return search(num, low, index - 1, target);
	}

	public static void main(String[] args) {
		int[] num = { 5, 4, 3, 6, 1, 8, 7 };
		System.out.println("第二大的数是："
				+ quickSearchNthLargest(num, 0, num.length - 1, 2));
		quickSort(num, 0, num.length - 1);
		for (int i : num) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("8所在位置是：" + search(num, 8));
	}
}
