package ramzet89.dictionary.service;

import org.springframework.stereotype.Service;
import ramzet89.dictionary.io.IOHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class MainMenuService {
    private Map<Menu, Runnable> methods;

    private final IOHelper ioHelper;
    private final DictionaryFillService dictionaryFillService;
    private final LearningService learningService;
    private final StorageService storageService;

    public MainMenuService(IOHelper ioHelper,
                           DictionaryFillService dictionaryFillService,
                           LearningService learningService,
                           StorageService storageService) {
        this.ioHelper = ioHelper;
        this.dictionaryFillService = dictionaryFillService;
        this.learningService = learningService;
        this.storageService = storageService;

        Map<Menu, Runnable> map = new HashMap<>();
        map.put(Menu.INPUT, dictionaryFillService::fillDictionary);
//        map.put(Menu.LEARN, learningService::prepareAndLearn);
        map.put(Menu.DICTIONARY,
                () -> storageService.printDictionary(storageService.getDictionary()));
        map.put(Menu.EXIT,
                () -> System.exit(0));
        methods = Collections.unmodifiableMap(map);
    }

    public void mainMenu() {
        while (true) {
            printMenu();
            String userInput = ioHelper.readLine();
            Menu menuItem = Menu.getByNum(userInput);
            if (Objects.isNull(menuItem)) {
                continue;
            }
            methods.get(menuItem).run();
        }
    }

    private void printMenu() {
        for (Menu menu: Menu.values()) {
            ioHelper.print(menu.getItem());
        }
    }

    private enum Menu {
        INPUT("1. Занести в словарь", "1"),
        LEARN("2. Учить слова", "2"),
        DICTIONARY("3. Показать словарь", "3"),
        EXIT("0. Выход", "0");

        Menu(String item, String num) {
            this.item = item;
            this.num = num;
        }

        private String item;
        private String num;

        public String getItem() {
            return item;
        }

        public String getNum() {
            return num;
        }

        public static Menu getByNum(String num) {
            for (Menu menu: values()) {
                if (num.equals(menu.getNum())) {
                    return menu;
                }
            }
            return null;
        }
    }
}
