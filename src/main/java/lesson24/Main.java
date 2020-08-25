package lesson24;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        Set<String> stringSet = new HashSet<>(
                Arrays.asList("foo", "buzz", "bar", "fork", "port", "spoon", "!", "dude")
        );
        System.out.println(stringSet.toString());

        System.out.println(removeEvenLength(stringSet).toString());

        removeEvenLengthSecond(stringSet);

        System.out.println(stringSet);
    }

    public static List<String> removeEvenLength(final Set<String> set) {
        return set.stream().filter(p -> (p.length() % 2) == 1).collect(Collectors.toList());
    }

    public static void removeEvenLengthSecond(final Set<String> set) {
        set.removeIf(curr -> curr.length() % 2 == 0);
    }
}
