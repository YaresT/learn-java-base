package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Сортировщик слиянием для коллекций
 */
public class ArrayListMergeSorter<T> {

    public static void main(String[] args) {
        ArrayListMergeSorter<String> sorter = new ArrayListMergeSorter<>();

        ArrayList<String> list = new ArrayList<>(Arrays.asList("fourscore", "thor", "one", "threes"));

        for (String value : sorter.sortArray(list, Comparator.comparing(String::length))) {
            System.out.print(value + " ");
        }
    }

    public List<T> sortArray(List<T> unsorted, Comparator<T> listComparator) {
        if (unsorted == null || unsorted.size() < 2) {
            return unsorted;
        }

        int sizeCenter = unsorted.size() / 2;

        return mergeArray(
                sortArray(unsorted.subList(0, sizeCenter), listComparator),
                sortArray(unsorted.subList(sizeCenter, unsorted.size()), listComparator),
                listComparator
        );
    }

    public List<T> mergeArray(List<T> left, List<T> right, Comparator<T> listComparator) {
        List<T> merge = new ArrayList<>();

        int positionLeft = 0;
        int positionRight = 0;
        int mergeLength = left.size() + right.size();

        for (int i = 0; i < mergeLength; i++) {
            if (positionLeft == left.size()) {
                merge.add(right.get(positionRight));
                positionRight++;
            } else if (positionRight == right.size()) {
                merge.add(left.get(positionLeft));
                positionLeft++;
            } else if (listComparator.compare(left.get(positionLeft), right.get(positionRight)) < 0) {
                merge.add(left.get(positionLeft));
                positionLeft++;
            } else {
                merge.add(right.get(positionRight));
                positionRight++;
            }
        }

        return merge;
    }
}