package lesson11;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("Введите строку:");
            userInput = scanner.nextLine();
            System.out.println(
                    markdownCustomCursiveHandler(userInput)
            );
        } while (true);
    }

    public static String markdownCustomCursiveHandler(@NotNull String input) {
        return input.replaceAll("([^*])\\*(.+?)\\*([^*])", "$1<em>$2</em>$3");
    }
}
