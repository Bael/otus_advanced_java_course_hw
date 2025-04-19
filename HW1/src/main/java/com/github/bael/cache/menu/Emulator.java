package com.github.bael.cache.menu;

import com.github.bael.cache.Cache;
import com.github.bael.cache.CacheLoadingException;
import com.github.bael.cache.impl.FileLoader;
import java.io.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Emulator {

    private final static Logger logger = LoggerFactory.getLogger(Emulator.class);
    private final FileLoader fileLoader;
    private final Cache<String, String> cache;

    public Emulator(String directory) {
        fileLoader = new FileLoader(directory);
        cache = new Cache<>(fileLoader);
    }

    public static void main(String[] args) {
        logger.info("Укажите директорию, где хранятся файлы для загрузки в кэш:");
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
            logger.info("Наберите команду:\n{}", availableCommand);
            var commandStr = console.readLine();
            if (commandStr.equalsIgnoreCase("выход")) {
                logger.info("Выходим.");
                return;
            }

            String[] commandArr = commandStr.trim().split("\\s+");
            if (commandArr.length != 2) {
                logger.info("Некорректная команда, должно быть название команды и файла. ");
                continue;
            }
            command = commandArr[0];
            var fileName = commandArr[1];

            switch (command.trim().toLowerCase()) {
                case "загрузить":
                    emulator.loadFile(fileName);
                    logger.info(">Выполнено");
                    break;
                case "прочитать":
                    logger.info(emulator.cache.get(fileName));
                    logger.info(">Выполнено");
                    break;
                default:
                    logger.info("Команда нераспознана.");
            }
        } while (true);
    }

    private void loadFile(String fileName) {
        try {
            cache.get(fileName);
        } catch (CacheLoadingException e) {
            logger.error("Ошибка загрузки файла: ", e);
        }
    }

}
