package sortcomparison;

import java.util.*;

public class BasicSorter implements Sorter {

	@Override
	public void insertionSort(String[] data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub

		for (int i = firstIndex; i < data.length; i++) {
			int index = i;
			var temp = data[i];

			for (int j = i; j < data.length; j++) {
				if (temp.compareTo(data[j]) > 0) {
					temp = data[j];
					index = j;
				}
			}

			data[index] = data[i];
			data[i] = temp;

		}
	}


	@Override
	public void quickSort(String[] data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub

		if (numberToSort > 1) {

			int pivot = partition(data, firstIndex, numberToSort);

			int left = pivot - firstIndex;
			int right = numberToSort - (left + 1);

			quickSort(data, firstIndex, left);
			quickSort(data, pivot + 1, right);
		}

	}


	@Override
	public int partition(String[] data, int firstIndex, int numberToPartition) {
		// TODO Auto-generated method stub

		var temp = data[firstIndex];
		data[firstIndex] = data[firstIndex + numberToPartition / 2];
		data[firstIndex + numberToPartition / 2] = temp;

		var pivot = data[firstIndex];
		int tbi = firstIndex + 1;
		int tsi = firstIndex + numberToPartition - 1;

		while (tbi < tsi) {
			while ((tbi < tsi) && (data[tbi].compareTo(pivot) <= 0))
				tbi++;
			while ((tsi > firstIndex) && (data[tsi].compareTo(pivot) > 0))
				tsi--;
			if (tbi < tsi) {
				temp = data[tsi];
				data[tsi] = data[tbi];
				data[tbi] = temp;
			}
		}

		if (pivot.compareTo(data[tsi]) >= 0) {
			temp = data[tsi];
			data[tsi] = data[firstIndex];
			data[firstIndex] = temp;
			return tsi;
		}

		return firstIndex;

	}


	@Override
	public void mergeSort(String[] data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub

		if (numberToSort > 1) {
			int half = firstIndex + numberToSort / 2;
			int leftSize = half - firstIndex;
			int rightSize = numberToSort - leftSize;

			mergeSort(data, firstIndex, leftSize);
			mergeSort(data, half, rightSize);
			if (data[half - 1].compareTo(data[half]) > 0)
				merge(data, firstIndex, leftSize, rightSize);

		}
	}


	@Override
	public void merge(String[] data, int firstIndex, int leftSegmentSize, int rightSegmentSize) {
		// TODO Auto-generated method stub
		var temp = new ArrayList<>();
		int leftIndex = 0, rightIndex = 0;

		while (leftIndex != leftSegmentSize && rightIndex != rightSegmentSize) {
			if (data[firstIndex + leftIndex].compareTo(data[firstIndex + leftSegmentSize + rightIndex]) >= 0) {
				temp.add(data[firstIndex + leftSegmentSize + rightIndex++]);

			} else {
				temp.add(data[firstIndex + leftIndex++]);
			}
		}

		for (int i = leftIndex; i < leftSegmentSize; i++) {
			temp.add(data[firstIndex + i]);

		}
		for (int i = rightIndex; i < rightSegmentSize; i++) {
			temp.add(data[firstIndex + leftSegmentSize + i]);

		}
		for (int i = 0; i < (leftSegmentSize + rightSegmentSize); i++)
			data[firstIndex + i] = (String) temp.get(i);

	}


	@Override
	public void heapSort(String[] data) {
		// TODO Auto-generated method stub

	}


	@Override
	public void heapify(String[] data) {
		// TODO Auto-generated method stub

	}

}
