package lesson18;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final int BUFFER_SIZE = 1000;

    public static void main(final String[] args) {
        File cp1251File = new File("temp/cp1251.txt");
        File utfFile = new File("temp/utf.txt");

        reconvertFile(
                cp1251File,
                utfFile,
                "windows-1251",
                StandardCharsets.UTF_8.toString()
        );
    }

    private static void reconvertFile(
            final File fromFile,
            final File toFile,
            final String fromCharset,
            final String toCharset
    ) {
        if (!fromFile.exists()) {
            System.out.println("Файл с неверной кодировкой отсутствует");
            return;
        }

        try {
            if (!toFile.exists() && !toFile.createNewFile()) {
                throw new IOException("Ошибка файл в новой кодировке не создан");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (
                InputStreamReader fromReader = new InputStreamReader(
                        new FileInputStream(fromFile), fromCharset
                );
                OutputStreamWriter outputWriter = new OutputStreamWriter(
                        new FileOutputStream(toFile), toCharset
                );
        ) {
            char[] buffer = new char[BUFFER_SIZE];
            int readed;
            while ((readed = fromReader.read(buffer, 0, BUFFER_SIZE)) > 0) {
                outputWriter.write(buffer, 0, readed);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
