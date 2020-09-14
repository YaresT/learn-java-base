package lesson25;

import java.util.HashSet;
import java.util.Map;

public class MapTools {
    private MapTools() {
    }

    public static <K, V> boolean isUnique(final Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }

        final HashSet<V> set = new HashSet<>();
        for (V value : map.values()) {
            if (set.contains(value)) {
                return false;
            }

            set.add(value);
        }

        return true;
    }
}
