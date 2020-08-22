package lesson30;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(final String[] args) {
        String testString = "teterf";

        System.out.println(getFirstUniqueSymbol(testString));
    }

    public static Character getFirstUniqueSymbol(final String string) {
        Map<Character, Integer> charCountMap = new LinkedHashMap<>();

        char current;
        for (int i = 0; i < string.length(); i++) {
            current = string.charAt(i);
            if (charCountMap.containsKey(current)) {
                charCountMap.put(current, charCountMap.get(current) + 1);
            } else {
                charCountMap.put(current, 1);
            }
        }

        for (Map.Entry<Character, Integer> symbol : charCountMap.entrySet()) {
            if (symbol.getValue() == 1) {
                return symbol.getKey();
            }
        }

        throw new RuntimeException("Not found unique symbols");
    }
}
