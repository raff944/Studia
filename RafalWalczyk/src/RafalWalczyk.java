import java.io.*; // Import biblioteki do operacji wejścia/wyjścia
import java.util.ArrayList; // Pobranie bliblioteki do obsługi listy
import java.util.Scanner; // Pobranie bliblioteki do wprowadzania danych przez użytkownika

//Stworzenie klasy głównej
public class RafalWalczyk {
    public static void main(String[] args) {

        // Drukowanie w programie dannych studenta
        System.out.println("Rafał Walczyk, nr indeksu 123793, podstawy programowania, Społeczna Akademia Nauk w Łodzi");

        //Tworzenie nowego skanera do wprowadzenia danych przez użytkownika
        //test komentarza
        Scanner scanner = new Scanner(System.in);

        // Wczytanie listy aktywnych zadań z pliku (jeśli istnieje)
        ArrayList<String> aktywneZadania = wczytajZPliku();
        ArrayList<String> rozwiazaneZadania = new ArrayList<>();
        ArrayList<String> usunieteZadania = new ArrayList<>();

        // Lista opcji menu
        ArrayList<String> menu = new ArrayList<>();
        menu.add(">>> MENU <<<");
        menu.add("\u001B[34m1. Dodaj nowe zadanie\u001B[0m");
        menu.add("\u001B[34m2. Sprawdź listę aktywnych zadań\u001B[0m");
        menu.add("\u001B[34m3. Oznacz zadanie jako rozwiązane\u001B[0m");
        menu.add("\u001B[34m4. Usuń zadanie\u001B[0m");
        menu.add("\u001B[34m5. Lista rozwiązanych zadań\u001B[0m");
        menu.add("\u001B[34m6. Lista usuniętych zadań\u001B[0m");
        menu.add("\u001B[34m7. Raport zadań\u001B[0m");
        menu.add("\u001B[34m8. Wyjście\u001B[0m");

        //Wyświetlenie przywitania w programie
        System.out.println("\u001B[32mCześć, jakie masz dziś zadania? :)\u001B[0m");

        //Rozpoczęcie pętli programu do czasu jej zakończenia poprzez wybór odpowiedniej opcji z menu która zakańcza działanie programu
        while (true) {
            try {
                //Pobieranie oraz drukowanie listy menu w którym za pomocą scannera możemy wybierać opcje 
                menu.forEach(System.out::println);
                System.out.print("Wprowadź numer opcji: ");
                int opcja = scanner.nextInt();
                scanner.nextLine(); // Czyszczenie bufora wejściowego

                // Obsługa wyboru użytkownika
                switch (opcja) {
                    // Tworzenie nowego zadania z dopisaniem do listy aktywnych zadań oraz dodanie aktywnego zadania które zostaje zapisane w pliku txt
                    case 1 -> {
                        System.out.print("Dodaj zadanie: ");
                        String zadanie = scanner.nextLine();
                        aktywneZadania.add(zadanie);
                        zapiszDoPliku(aktywneZadania); // Zapis zaktualizowanej listy do pliku
                        System.out.println("Dodano nowe zadanie!\n");
                    }
                    
                    // Prezentowanie listy aktywnych zadań oraz pokazanie komunikatu jeśli nie ma aktywnych zadań
                    case 2 -> {
                        System.out.println("\nTwoja lista aktywnych zadań:");
                        if (aktywneZadania.isEmpty()) {
                            System.out.println("Brak aktywnych zadań.");
                        } else {
                            for (int i = 0; i < aktywneZadania.size(); i++) {
                                System.out.println((i + 1) + ". " + aktywneZadania.get(i));
                            }
                        }
                        System.out.println();
                    }

                    // Wybór zadania które ma trafić do listy rozwiązanych zadań oraz pokazanie komunikatu jeśli nie ma aktywnych zadań
                    case 3 -> {
                        System.out.println("\nWybierz numer zadania do oznaczenia jako rozwiązane:");
                        if (aktywneZadania.isEmpty()) {
                            System.out.println("Brak aktywnych zadań do rozwiązania.");
                        } else {
                            for (int i = 0; i < aktywneZadania.size(); i++) {
                                System.out.println((i + 1) + ". " + aktywneZadania.get(i));
                            }
                            int indeks = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (indeks >= 0 && indeks < aktywneZadania.size()) {
                                rozwiazaneZadania.add(aktywneZadania.remove(indeks));
                                zapiszDoPliku(aktywneZadania);
                                System.out.println("Zadanie oznaczone jako rozwiązane.");
                            } else {
                                System.out.println("Nieprawidłowy numer zadania.");
                            }
                        }
                        System.out.println();
                    }

                    // Wybór zadań które mają zostać usunięte z listy aktywnych zdań oraz pokazanie komunikatu jeśli jest brak aktywnych zadań
                    case 4 -> {
                        System.out.println("\nWybierz numer zadania do usunięcia:");
                        if (aktywneZadania.isEmpty()) {
                            System.out.println("Brak aktywnych zadań do usunięcia.");
                        } else {
                            for (int i = 0; i < aktywneZadania.size(); i++) {
                                System.out.println((i + 1) + ". " + aktywneZadania.get(i));
                            }
                            int indeks = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (indeks >= 0 && indeks < aktywneZadania.size()) {
                                usunieteZadania.add(aktywneZadania.remove(indeks));
                                zapiszDoPliku(aktywneZadania);
                                System.out.println("Zadanie usunięte.");
                            } else {
                                System.out.println("Nieprawidłowy numer zadania.");
                            }
                        }
                        System.out.println();
                    }

                    // Wyświetlanie listy rozwiązanych zadań oraz jeśli lista jest pusta pokazywany jest odpowiedni komunikat
                    case 5 -> {
                        System.out.println("\nLista rozwiązanych zadań:");
                        if (rozwiazaneZadania.isEmpty()) {
                            System.out.println("Brak rozwiązanych zadań.");
                        } else {
                            rozwiazaneZadania.forEach(zad -> System.out.println("- " + zad));
                        }
                        System.out.println();
                    }

                    // Wyświetlanie listy usuniętych zadań oraz jeśli lista jest pusta pokazywany jest odpowiedni komunikat
                    case 6 -> {
                        System.out.println("\nLista usuniętych zadań:");
                        if (usunieteZadania.isEmpty()) {
                            System.out.println("Brak usuniętych zadań.");
                        } else {
                            usunieteZadania.forEach(zad -> System.out.println("- " + zad));
                        }
                        System.out.println();
                    }

                    // Tworzenie raportu pobranie ilości rekordów w każdej z list aktywnych, rozwiązanych i usuniętych zadań oraz wyliczenie procentowe zadań
                    case 7 -> {
                        int totalTasks = aktywneZadania.size() + rozwiazaneZadania.size() + usunieteZadania.size();
                        if (totalTasks > 0) {
                            double procentRozwiazane = (rozwiazaneZadania.size() * 100.0) / totalTasks;
                            double procentUsuniete = (usunieteZadania.size() * 100.0) / totalTasks;
                            double procentAktywne = (aktywneZadania.size() * 100.0) / totalTasks;

                            System.out.println("\nRaport:");
                            System.out.printf("- Aktywne zadania: %.2f%%\n", procentAktywne);
                            System.out.printf("- Rozwiązane zadania: %.2f%%\n", procentRozwiazane);
                            System.out.printf("- Usunięte zadania: %.2f%%\n", procentUsuniete);
                        } else {
                            System.out.println("\nNie dodano żadnych zadań, brak danych do raportu.");
                        }
                        System.out.println();
                    }
                    // Część kodu odpowiedzialna za zamknięcie programu
                    case 8 -> {
                        System.out.println("Dziękujemy za skorzystanie z programu. Do zobaczenia!");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("\u001B[31mNieprawidłowy wybór. Spróbuj ponownie.\u001B[0m");
                }

            // Obsługa wyjątku gdzie wybrana jest opcja z menu jako litera nie liczba
            } catch (java.util.InputMismatchException e) {
                System.out.println("\u001B[31mBłąd: Wprowadź liczbę odpowiadającą opcji z menu.\u001B[0m");
                scanner.nextLine();
            }
        }
    }

    // Stworzenie metody która umożliwia zapisanie do pliku oraz tworzy plik o nazwie aktywne_zadania.txt
    public static void zapiszDoPliku(ArrayList<String> aktywneZadania) {
        try (FileWriter writer = new FileWriter("aktywne_zadania.txt")) {
            for (String zadanie : aktywneZadania) {
                writer.write(zadanie + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mBłąd zapisu do pliku: " + e.getMessage() + "\u001B[0m");
        }
    }

    // Stworzenie metody która umożliwi odczytania zapisanego pliku zadań 
    public static ArrayList<String> wczytajZPliku() {
        ArrayList<String> aktywneZadania = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("aktywne_zadania.txt"))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                aktywneZadania.add(linia);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\u001B[33mPlik nie istnieje, utworzono nową listę aktywnych zadań.\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31mBłąd odczytu z pliku: " + e.getMessage() + "\u001B[0m");
        }
        return aktywneZadania;
    }
}
