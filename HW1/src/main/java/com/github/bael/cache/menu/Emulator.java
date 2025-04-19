package com.github.bael.cache.menu;

import com.github.bael.cache.Cache;
import com.github.bael.cache.impl.FileLoader;
import java.io.Console;

public class Emulator {
    private final FileLoader fileLoader;
    private final Cache<String, String> cache;

    public Emulator(String directory) {
        fileLoader = new FileLoader(directory);
        cache = new Cache<>(fileLoader);
    }

    public static void main(String[] args) {
        System.out.println("Укажите директорию, где хранятся файлы для загрузки в кэш:");
        Console console = System.console();
        String path = console.readLine();
        Emulator emulator = new Emulator(path);

        String availableCommand = """
            Доступные команды
              - загрузить: команда загрузить требует параметра - имени файла. Эта команда загружает содержимое файла в кэш.
              - прочитать: команда прочитать требует параметра - имени файла. Эта команда читает содержимое файла из кэша.
              - выход: команда выйти из приложения
            """;
        String command;
        do {
            System.out.println("Наберите команду:\n" + availableCommand);
            var commandStr = console.readLine();
            if (commandStr.equalsIgnoreCase("выход")) {
                System.out.println("Выходим.");
                return;
            }

            String[] commandArr = commandStr.trim().split("\\s+");
            if (commandArr.length != 2) {
                System.out.println("Некорректная команда, должно быть название команды и файла. ");
                continue;
            }
            command = commandArr[0];
            var fileName = commandArr[1];

            switch (command.trim().toLowerCase()) {
                case "загрузить":
                    emulator.cache.get(fileName);
                    System.out.println(">Выполнено");
                    break;
                case "прочитать":
                    System.out.println(emulator.cache.get(fileName));
                    System.out.println(">Выполнено");
                    break;
                default:
                    System.out.println("Команда нераспознана.");
            }
        } while (true);
    }


}
