package lesson15;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    protected static final String TEMP_FILE_DIR = "temp/";

    public static void main(final String[] args) {
        createFile("createdFile.txt");
        renameFile("createdFile.txt", "rename.txt");
        copyFile("rename.txt", "copy.txt");
        deleteFile("createdFile.txt", "rename.txt", "copy.txt");
        directoryTreeListOut(new File("src"));
    }

    private static String getTempFileDir(final String fileName) {
        return TEMP_FILE_DIR + fileName;
    }

    private static void createFile(final String newFileName) {
        File file = new File(getTempFileDir(newFileName));

        System.out.println(file.getAbsolutePath());

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.printf("Файл %s был создан%n", newFileName);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace(System.out);
            }
        }
    }

    private static void copyFile(final String fileName, final String copyFileName) {
        File file = new File(getTempFileDir(fileName));

        if (file.exists()) {
            try {
                Files.copy(
                        file.toPath(),
                        Paths.get(getTempFileDir(copyFileName)),
                        StandardCopyOption.REPLACE_EXISTING
                );
                System.out.printf(
                        "Файл %s был скопирован в %s%n",
                        fileName,
                        copyFileName
                );
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace(System.out);
            }
        }
    }

    private static void renameFile(final String oldFileName, final String newFileName) {
        File oldFile = new File(getTempFileDir(oldFileName));

        if (oldFile.exists()) {
            File renamedFile = new File(getTempFileDir(newFileName));
            if (renamedFile.exists()) {
                System.out.printf(
                        "Файл %s не переименован в %s. Файл %s уже существует%n",
                        oldFileName,
                        newFileName,
                        newFileName
                );
            } else {
                try {
                    if (oldFile.renameTo(renamedFile)) {
                        System.out.printf(
                                "Файл %s был переименова в %s%n",
                                oldFileName,
                                newFileName
                        );
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace(System.out);
                }
            }
        }
    }

    private static void deleteFile(final String... fileNames) {
        System.out.println();
        for (String fileName : fileNames) {
            try {
                if (Files.deleteIfExists(Paths.get(getTempFileDir(fileName)))) {
                    System.out.printf(
                            "Файл %s был удалён%n",
                            fileName
                    );
                } else {
                    System.out.printf(
                            "Файл %s не был удалён%n",
                            fileName
                    );
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace(System.out);
            }
        }
    }

    private static void directoryTreeListOut(final File root, int level) {
        if (root.exists() && !root.isHidden()) {
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println(root.getName());
            level++;

            if (root.isDirectory()) {
                File[] nestedFiles = root.listFiles();
                if (nestedFiles != null && nestedFiles.length > 0) {
                    for (File nestedFile : nestedFiles) {
                        directoryTreeListOut(nestedFile, level);
                    }
                }
            }
        }
    }

    private static void directoryTreeListOut(final File rootDirectory) {
        directoryTreeListOut(rootDirectory, 0);
    }
}
