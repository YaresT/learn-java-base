package lesson33;

import java.util.HashMap;
import java.util.Map;

public class StringCharCounter {
    private Map<Character, Integer> charCountMap;

    private Map.Entry<Character, Integer> maxCountEntry;

    public StringCharCounter(final String string) {
        setCharCountMap(string);
        setMaxCountEntry();
    }

    public int getSymbolCount(final char symbol) {
        if (charCountMap.containsKey(symbol)) {
            return charCountMap.get(symbol);
        }

        throw new StringIndexOutOfBoundsException("Symbol not found");
    }

    public Character getMaxRepeatedSymbol() {
        return maxCountEntry.getKey();
    }

    public Integer getMaxRepeatedSymbolCount() {
        return maxCountEntry.getValue();
    }

    private void setMaxCountEntry() {
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxCountEntry = entry;
            }
        }
    }

    private void setCharCountMap(final String string) {
        charCountMap = new HashMap<>();

        for (char symbol : string.toCharArray()) {
            if (charCountMap.containsKey(symbol)) {
                charCountMap.put(symbol, charCountMap.get(symbol) + 1);
            } else {
                charCountMap.put(symbol, 1);
            }
        }
    }
}
