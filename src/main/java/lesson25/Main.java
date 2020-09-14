package lesson25;


import java.util.HashMap;

public class Main {
    public static void main(final String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "A");
        map.put("C", "A");
        map.put("B", "B");
        map.put("D", "D");

        System.out.println(MapTools.isUnique(map));
    }
}
