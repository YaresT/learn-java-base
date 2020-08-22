package lesson33;

public class Main {
    public static void main(final String[] args) {
        String testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";

        StringCharCounter counter = new StringCharCounter(testString);

        System.out.printf(
                "'%c' has occurred maximum times in String: %d",
                counter.getMaxRepeatedSymbol(),
                counter.getMaxRepeatedSymbolCount()
        );
    }
}
