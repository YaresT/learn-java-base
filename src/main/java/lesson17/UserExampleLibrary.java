package lesson17;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserExampleLibrary {
    private static final Scanner IN_SCANNER = new Scanner(System.in);

    private static final String SAVE_FILE_PATH = "temp/user-example-library.json";

    private List<User> users = new ArrayList<>();

    public static void main(final String[] args) {
        new UserExampleLibrary().run();
    }

    private void load() {
        File saveFile = getSaveFile();
        if (saveFile.exists()) {
            try {

                users = new ArrayList<>(
                        Arrays.asList(
                                new ObjectMapper().readValue(saveFile, User[].class)
                        )
                );
                System.out.println(users.getClass());
                out();
            } catch (IOException e) {
                users.clear();
                e.printStackTrace();
            }
        } else {
            users.clear();
        }
    }

    private void save() {
        File saveFile = getSaveFile();

        if (!saveFile.exists()) {
            try {
                if (!saveFile.createNewFile()) {
                    throw new IOException("<f,f,f");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (saveFile.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(saveFile, users.toArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Произошла ошибка во время сохранения библиотеки");
        }
    }

    private File getSaveFile() {
        return new File(SAVE_FILE_PATH);
    }

    private void out() {
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                System.out.println(user.toString());
            }
        } else {
            System.out.println("Библиотека пуста загрузите пользователей по id");
        }
    }

    private void showMenu() {
        Menu[] availableMenuActions = Menu.values();
        for (int i = 0; i < availableMenuActions.length; i++) {
            System.out.printf(
                    "%d - %s%n",
                    i,
                    availableMenuActions[i].getDescription()
            );
        }
    }

    private Menu getNeedMenuAction() {
        int actionNumber;
        Menu[] availableMenuActions = Menu.values();

        do {
            showMenu();

            try {
                actionNumber = IN_SCANNER.nextInt();
            } catch (Exception ignored) {
                actionNumber = -1;
            }

            if (actionNumber < 0) {
                continue;
            }

            for (int i = 0; i < availableMenuActions.length; i++) {
                if (i == actionNumber) {
                    if (availableMenuActions[i] != Menu.EXIT) {
                        return availableMenuActions[i];
                    } else {
                        return null;
                    }
                }
            }

            System.out.println();
        } while (true);
    }

    public void run() {
        Menu action;

        do {
            action = getNeedMenuAction();
            if (action == null) {
                return;
            }

            action.action(this);
            System.out.println();
        } while (true);
    }

    private void loadNewUser() {
        int userId;
        do {
            System.out.println("\nВведите идентификатор пользователя");

            try {
                userId = IN_SCANNER.nextInt();
            } catch (Exception exception) {
                userId = -1;
            }
        } while (userId < 1);

        try {
            User newUser = UserLoader.loadById(userId);
            users.add(newUser);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки пользователя");
            e.printStackTrace();
        }
    }

    enum Menu {
        LOAD_FROM_FILE("Загрузить пользователей из файла") {
            public void action(final UserExampleLibrary library) {
                library.load();
            }
        },
        OUT("Вывести пользователей") {
            public void action(final UserExampleLibrary library) {
                library.out();
            }
        },
        SAVE("Сохранить") {
            public void action(final UserExampleLibrary library) {
                library.save();
            }
        },
        LOAD_USER("Загрузить пользователя с сервера") {
            public void action(final UserExampleLibrary library) {
                library.loadNewUser();
            }
        },
        EXIT("Выход из программы") {
            public void action(final UserExampleLibrary library) {
                throw new UnsupportedOperationException();
            }
        };

        private final String description;

        Menu(final String description) {
            this.description = description;
        }

        public abstract void action(UserExampleLibrary program);

        public String getDescription() {
            return this.description;
        }
    }
}
