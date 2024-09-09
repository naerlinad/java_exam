/**
 * - Подумать над структурой класса Ноутбук для магазина техники - выделить поля и
 * методы. Реализовать в java.
 * - Создать множество ноутбуков.
 * - Написать метод, который будет запрашивать у пользователя критерий (или критерии)
 * фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно
 * хранить в Map. Например:
 * “Введите цифру, соответствующую необходимому критерию:
 * 1 - ОЗУ
 * 2 - Объем ЖД
 * 3 - Операционная система
 * 4 - Цвет …
 * - Далее нужно запросить минимальные значения для указанных критериев - сохранить
 * параметры фильтрации можно также в Map.
 * - Отфильтровать ноутбуки их первоначального множества и вывести проходящие по
 * условиям.
 */

import java.util.*;

public class LaptopCatalog {

    static Set<Laptop> getCatalog() {

        // Данные записанные в полях экземпляров, а также количество самих экземпляров можно изменять как угодно //

        Laptop lp1 = new Laptop("Apes", 16, 512, "i7",
                "GeForce RTX2050", 17.3, "IPS", "Yes", "Windows 11", 162599);

        Laptop lp2 = new Laptop("Apes", 8, 256, "i5",
                "GeForce RTX1050", 16, "IPS", "Yes", "Windows 11", 112699);

        Laptop lp3 = new Laptop("Apes", 4, 256, "i3",
                "Intel HD Graphics", 15.7, "LED", "No", "Windows 11", 44679);

        Laptop lp4 = new Laptop("Apple", 16, 512, "Apple M2",
                "Apple M2 8-core", 16, "IPS", "No", "MacOS", 362599);

        Laptop lp5 = new Laptop("Apple", 8, 256, "Apple M1",
                "Apple M1 8-core", 16, "IPS", "No", "MacOS", 112699);

        Laptop lp6 = new Laptop("Maple", 8, 256, "Ryzen3",
                "AMD Radeon Graphics", 15.7, "IPS", "No", "Linux", 32599);

        Laptop lp7 = new Laptop("Nicer", 16, 512, "Ryzen5",
                "GeForce RTX2050", 16, "IPS", "Yes", "(none)", 72699);

        Laptop lp8 = new Laptop("Maple", 16, 1024, "Ryzen5",
                "GeForce RTX3050", 17.3, "IPS", "Yes", "Linux", 82799);

        Laptop lp9 = new Laptop("Apes", 4, 256, "i3",
                "Intel HD Graphics", 15.7, "LED", "No", "Windows 11", 44679);

        return new HashSet<>(Arrays.asList(lp1, lp2, lp3, lp4, lp5, lp6, lp7, lp8, lp9));
    }

    static Map<Integer, String> getCriteriaList() {
        Map<Integer, String> criteriaList = new HashMap<>();
        criteriaList.put(1, "Brand");
        criteriaList.put(2, "RAM");
        criteriaList.put(3, "Storage");
        criteriaList.put(4, "CPU");
        criteriaList.put(5, "GPU");
        criteriaList.put(6, "Display Size");
        criteriaList.put(7, "Display Matrix");
        criteriaList.put(8, "Gaming");
        criteriaList.put(9, "Operating System");
        criteriaList.put(10, "Price");
        return criteriaList;
    }

    static Laptop getLpBlank() {
        return new Laptop(null, 0, 0, null, null,
                0, null, null, null, 0);
    }

    static Map<Integer, HashSet<Object>> getAvailableCriteria(Set<Laptop> catalog) {

        Map<Integer, HashSet<Object>> availableCriteria = new HashMap<>();

        Map<Integer, String> criteriaList = getCriteriaList();

        for (int i = 1; i <= criteriaList.size(); i++) {

            availableCriteria.put(i, new HashSet<>());

            for (Laptop lp : catalog) {
                availableCriteria.get(i).add(lp.get(i));
            }
        }
        return availableCriteria;
    }

    static void setCriteria(Laptop wantedLp, Map<String, String> chosenCriteria, Map<Integer, HashSet<Object>> availableCriteria) {

        Scanner sc = new Scanner(System.in);
        Map<Integer, String> criteriaList = getCriteriaList();

        while (true) {
            System.out.println("\nSelect criteria to set." +
                    "\nNotice: if chosen a numeric criterion like memory, chosen value is treated as a minimum," +
                    "\nso lesser values are meant to be filtered out, but the greater will be shown.");
            for (int i = 1; i <= criteriaList.size(); i++) {
                System.out.printf("%d -- %s%n", i, criteriaList.get(i));
            }
            if (!chosenCriteria.isEmpty()) {
                System.out.println("\ns -- See all chosen criteria." +
                        "\nc -- Clear all criteria.");
            }
            System.out.println("f -- Finish selecting.");

            String input = sc.nextLine();

            if (input.equals("f")) {
                break;
            } else if (input.equals("s")) {
                System.out.println(chosenCriteria);
            } else if (input.equals("c")) {
                wantedLp = getLpBlank();
                chosenCriteria.clear();
                System.out.println("All criteria has been cleared.");
            } else {
                try {
                    int k = Integer.parseInt(input);
                    Object[] temp = availableCriteria.get(k).toArray();
                    Arrays.sort(temp);

                    for (int i = 0; i < temp.length; i++) {
                        System.out.printf("%d -- %s%n", i + 1, temp[i]);
                    }

                    input = sc.nextLine();

                    int t = Integer.parseInt(input) - 1;

                    wantedLp.set(k, temp[t]);
                    chosenCriteria.put(criteriaList.get(k), temp[t].toString());

                    System.out.println("\nNew criterion has been set.");
                    System.out.println(chosenCriteria);

                } catch (Exception e) {
                    System.err.println("Invalid input value!");
                }
            }
        }
    }

    static Set<Laptop> filterCatalog (Set<Laptop> catalog, Laptop wantedLp) {
        Set<Laptop> filteredCatalog = new HashSet<>();
        for(Laptop lp: catalog) {
            boolean f = true;
            for (int i = 1; i <= getCriteriaList().size() && f; i++) {
                if (lp.get(i).getClass().toString().contains("String")) {
                    if (wantedLp.get(i) != null && !lp.get(i).equals(wantedLp.get(i))) {
                        f = false;
                    }
                } else if (lp.get(i).getClass().toString().contains("Double")) {
                    if ((double) lp.get(i) < (double) wantedLp.get(i)) {
                        f = false;
                    }
                } else {
                    if ((int) lp.get(i) < (int) wantedLp.get(i)) {
                        f = false;
                    }
                }
            }
            if (f) filteredCatalog.add(lp);
        }
        return filteredCatalog;
    }

    static void showCatalog(Set<Laptop> catalog) {
        for (Laptop lp : catalog) System.out.println(lp + System.lineSeparator());
    }

    public static void main(String[] args) {

        Set<Laptop> catalog = getCatalog();

        Scanner sc = new Scanner(System.in);

        System.out.println("\nHello, dear user! This program serves as catalog of laptops which currently are being in stock.");

        while (true) {
            System.out.println("\nEnter one of the numbers or keyboard keys below to get executed corresponding command:" +
                    "\n1 -- Show all laptops in the catalog." +
                    "\n2 -- Choose and set criteria to filter laptops." +
                    "\nq -- Quit the program.");

            String input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    showCatalog(catalog);
                }
                case "2" -> {
                    Map<Integer, HashSet<Object>> availableCriteria = getAvailableCriteria(catalog);

                    Laptop wantedLp = getLpBlank();
                    Map<String, String> chosenCriteria = new LinkedHashMap<>();

                    setCriteria(wantedLp, chosenCriteria, availableCriteria);

                    Set<Laptop> filteredCatalog = filterCatalog(catalog, wantedLp);

                    if (filteredCatalog.isEmpty()) {
                        System.out.println("By these criteria " + chosenCriteria + System.lineSeparator() +
                                "No matches have been found.");
                    } else {
                        showCatalog(filteredCatalog);
                        if (filteredCatalog.size() > 1) {
                            System.out.println("By these criteria " + chosenCriteria + System.lineSeparator() +
                                    "Only " + filteredCatalog.size() + " matches have been found, shown above.");
                        } else {
                            System.out.println("By the criteria " + chosenCriteria + System.lineSeparator() +
                                    "Only 1 match has been found, shown above.");
                        }
                    }

                }
                case "q" -> {
                    sc.close();
                    System.out.println("Goodbye");
                    System.exit(0);
                }
            }
        }
    }
}
