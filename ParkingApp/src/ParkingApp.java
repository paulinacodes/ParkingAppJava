import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class ParkingApp {

    static ArrayList<String> bazaDanych = new ArrayList<>(Arrays.asList(
            "EPAHE36", "EPA1LP3", "EL3534HC"
    ));
    static int opcja = 0;
    static boolean pracuj = true;
    static Scanner mySc = new Scanner(System.in);
    static int maxOpcja = 6;
    static int maxPojazdow = 8;


    public static void main (String[] args) {
        System.out.println ("=============== WITAJ W PARKINGAPP ==============") ;

        while(pracuj){
            System.out.println ("Wybierz opcję") ;
            System.out.println ("1.Wyświetl listę pojazdów") ;
            System.out.println ("2.Dodaj pojazd");
            System.out.println ("3.Usuń pojazd") ;
            System.out.println ("4.Sprawdź uprawnienie pojazdu") ;
            System.out.println ("5.Statystyki parkingu") ;
            System.out.println ("6.Zakończ") ;
            opcja = wybierzOpcje();

            switch(opcja) {
                case 1:
                  //  System.out.println("Wybrana opcja to: " + opcja);
                    wyswietlPojazdy();
                    poczekajNaEnter();
                    break;
                case 2:
                    // System.out.println("Wybrana opcja to: " + opcja);
                    //System.out.println("Dodawanie pojazdu");
                    dodajPojazd();
                    poczekajNaEnter();
                    break;
                case 3:
                    //System.out.println("Wybrana opcja to: " + opcja);
                    //System.out.println("Usuwanie pojazdu.");
                    usunPojazd();
                    poczekajNaEnter();
                    break;
                case 4:
                    sprawdzUprawnieniePojazdu();
                    poczekajNaEnter();
                    break;
                case 5:
                    wyswietlStatystyki();
                    poczekajNaEnter();
                    break;
                case 6:
                    System.out.println("Wybrana opcja to: " + opcja);
                    System.out.println("KONIEC DZIALANIA PROGRAMU");
                    pracuj = false;
                    break;
                default:
                    System.out.println("Opcja poza zakresem");
            }
        }
    }

    public static int wybierzOpcje(){
        int temp;
        while(true){
            if (mySc.hasNextInt()){
                temp = mySc.nextInt();
                mySc.nextLine(); // czyści ENTER po liczbie

                //Sprawdzenie czy wybrany numer jest w zakresie dostepnych opcji
                if(temp >= 1 && temp <= maxOpcja){
                    return temp;
                }
                else{
                    System.out.println("Podana opcja nie istnieje. Podaj liczbę całkowitą od 1 do " + maxOpcja);
                }
            }
            else{
                System.out.println("Podana opcja nie istnieje. Podaj liczbę całkowitą od 1 do " + maxOpcja);
                mySc.next();
                mySc.nextLine();
            }
        }
    }
    public static void wyswietlPojazdy(){

        if (bazaDanych.isEmpty()){
            System.out.println("Baza danych jest pusta.");
        }
        else{
            System.out.println("Tablice rejestracyjne w bazie danych to: ");
            for(int i = 0; i < bazaDanych.size(); i++){
                System.out.println((i+1) + ". " + bazaDanych.get(i));
            }
            System.out.println("-------------------------------------------------------");
        }
    }
    public static void dodajPojazd(){
        if (bazaDanych.size() >= maxPojazdow) {
            System.out.println("Baza danych jest pełna. Maksymalna liczba pojazdów to: " + maxPojazdow);
            return;
        }

        String nowaTablica;
        while (true) {
            System.out.println("Wprowadź tablice rejestracyjną i wciśnij ENTER");
            nowaTablica = mySc.nextLine().trim().toUpperCase();
            if (!nowaTablica.isEmpty()) {
                break;
            }
            System.out.println("Nie podano tablicy (pusty tekst). Spróbuj ponownie.");
        }

        if (bazaDanych.contains(nowaTablica)) {
            System.out.println("Taka tablica już istnieje w bazie: " + nowaTablica);
            return;
        }

        bazaDanych.add(nowaTablica);
        System.out.println("Dodano nową tablicę: " + nowaTablica);
    }
    public static void usunPojazd() {
        if (bazaDanych.isEmpty()) {
            System.out.println("Baza danych jest pusta. Nie ma czego usuwać.");
            return;
        }

        wyswietlPojazdy();
        System.out.println("Podaj ID pojazdu do usunięcia i wciśnij ENTER:");

        int id;
        while (true) {
            if (mySc.hasNextInt()) {
                id = mySc.nextInt();
                mySc.nextLine(); // czyści ENTER

                if (id >= 1 && id <= bazaDanych.size()) {
                    break;
                } else {
                    System.out.println("ID poza zakresem. Podaj ID od 1 do " + bazaDanych.size());
                }
            } else {
                System.out.println("Podaj ID od 1 do " + bazaDanych.size());
                mySc.next();
                mySc.nextLine();
            }
        }

        String usunieta = bazaDanych.remove(id - 1);
        System.out.println("Usunięto pojazd o ID " + id + ": " + usunieta);
    }
    public static void sprawdzUprawnieniePojazdu() {
        String tablica;

        while (true) {
            System.out.println("Podaj tablicę do sprawdzenia i wciśnij ENTER:");
            tablica = mySc.nextLine().trim().toUpperCase();

            if (!tablica.isEmpty()) {
                break;
            }

            System.out.println("Nie podano tablicy (pusty tekst). Spróbuj ponownie.");
        }

        if (bazaDanych.contains(tablica)) {
            System.out.println("Pojazd jest uprawniony (znaleziony w bazie).");
        } else {
            System.out.println("Pojazd nie jest uprawniony (brak w bazie).");
        }
    }
    public static void poczekajNaEnter() {
        System.out.println("Naciśnij ENTER, aby wrócić do menu...");
        mySc.nextLine();
    }
    public static void wyswietlStatystyki() {
        int pojemnosc = maxPojazdow;
        int liczbaPojazdow = bazaDanych.size();

        int wolneMiejsca = pojemnosc - liczbaPojazdow;
        if (wolneMiejsca < 0) {
            wolneMiejsca = 0;
        }

        double procentZajete = 0.0;
        if (pojemnosc > 0) {
            procentZajete = (liczbaPojazdow * 100.0) / pojemnosc;
            if (procentZajete > 100.0) {
                procentZajete = 100.0;
            }
        }

        System.out.println("Statystyki parkingu:");
        System.out.println("Pojemność parkingu: " + pojemnosc);
        System.out.println("Liczba aut: " + liczbaPojazdow);
        System.out.println("Wolne miejsca: " + wolneMiejsca);
        System.out.printf("Zajęte miejsca: %.1f%%\n", procentZajete);
    }

}