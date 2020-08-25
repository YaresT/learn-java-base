package lesson29;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Person> map = createMap();

        for (Person person : map.values()) {
            System.out.println(person);
        }

        removeTheDuplicates(map);

        System.out.println("After:");
        for (Person person : map.values()) {
            System.out.println(person);
        }
    }

    public static Map<String, Person> createMap() {
        Map<String, Person> books = new HashMap<>();
        Person person1 = new Person(29, "Петрова", "жен");
        Person person2 = new Person(34, "Сидорова", "жен");
        Person person3 = new Person(34, "Тихонова", "жен");
        Person person4 = new Person(35, "Петров", "муж");
        books.put("Key1", person1);
        books.put("Key2", person1);
        books.put("Key3", person2);
        books.put("Key4", person3);
        books.put("Key5", person4);
        books.put("Key6", person4);

        return books;
    }

    public static void removeTheDuplicates(Map<String, Person> map) {
        final Iterator<Map.Entry<String, Person>> iterator = map.entrySet().iterator();
        final HashSet<Person> valueSet = new HashSet<>();

        while (iterator.hasNext()) {
            final Map.Entry<String, Person> next = iterator.next();

            if (!valueSet.add(next.getValue())) {
                iterator.remove();
            }
        }
    }
}
