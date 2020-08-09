package lesson21;

import java.util.Random;

public class Arrays {
    public static void toLeft(final Object[]... arrays) {
        for (Object[] array : arrays) {
            System.arraycopy(array, 1, array, 0, array.length - 1);
            array[array.length - 1] = null;
        }
    }

    public static Integer[][] getRandInt2DArray(final int columns, final int rows) {
        Integer[][] multidimensionalArray = new Integer[columns][];

        for (int j = 0; j < columns; j++) {
            multidimensionalArray[j] = getRandIntArray(rows);
        }

        return multidimensionalArray;
    }

    public static Integer[] getRandIntArray(final int length) {
        Random rand = new Random();

        Integer[] array = new Integer[length];

        for (int j = 0; j < length; j++) {
            array[j] = rand.nextInt(100);
        }

        return array;
    }

    public static void main(final String[] args) {
        Integer[][] multidimensionalArray = getRandInt2DArray(5, 5);
        for (Integer[] integers : multidimensionalArray) {
            System.out.println(java.util.Arrays.toString(integers));
        }

        toLeft(multidimensionalArray);

        System.out.println();
        for (Integer[] integers : multidimensionalArray) {
            System.out.println(java.util.Arrays.toString(integers));
        }
    }
}
