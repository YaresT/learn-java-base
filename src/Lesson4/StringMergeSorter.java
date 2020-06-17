package Lesson4;

import java.util.Comparator;

/**
 * Сортировщик слиянием для строк
 */
public class StringMergeSorter {

    public static void main(String[] args) {
        String[] result = sortArray(new String[]{"fourscore", "thor", "one", "threes"});

        for (String value : result) {
            System.out.print(value + " ");
        }
    }

    public static String[] sortArray(String[] unsorted) {
        Comparator<String> comparator = Comparator.comparing(String::length);

        return sortArray(unsorted, comparator);
    }

    public static String[] sortArray(String[] unsorted, Comparator<String> stringComparator) {
        if (unsorted == null || unsorted.length < 2) {
            return unsorted;
        }

        int lengthCenter = unsorted.length / 2;

        String[] left = new String[lengthCenter];

        System.arraycopy(unsorted, 0, left, 0, left.length);

        String[] right = new String[unsorted.length - lengthCenter];
        System.arraycopy(unsorted, lengthCenter, right, 0, right.length);

        return mergeArray(sortArray(left, stringComparator), sortArray(right, stringComparator), stringComparator);
    }

    public static String[] mergeArray(String[] left, String[] right, Comparator<String> stringComparator) {
        String[] merge = new String[left.length + right.length];

        int positionLeft = 0;
        int positionRight = 0;

        for (int i = 0; i < merge.length; i++) {
            if (positionLeft == left.length) {
                merge[i] = right[positionRight];
                positionRight++;
            } else if (positionRight == right.length) {
                merge[i] = left[positionLeft];
                positionLeft++;
            } else if (stringComparator.compare(left[positionLeft], right[positionRight]) < 0) {
                merge[i] = left[positionLeft];
                positionLeft++;
            } else {
                merge[i] = right[positionRight];
                positionRight++;
            }
        }
        return merge;
    }
}