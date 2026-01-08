/*
Paulina Szmigielska - 100277 - grupa zajeciowa nr 3.
Aplikacja sluzca kontroli dostepu do parkingu.
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class ParkingApp {

    //Zmienne globalne:
    static ArrayList<String> bazaDanych = new ArrayList<>(Arrays.asList(
            "EPAHE36", "EPA1LP3", "EL3534HC"
    ));//Lista do przechowywania dodanych tablic rejestracyjnych
    static int opcja = 0;//Zmienna do wyboru opcji z menu
    static boolean pracuj = true; //Zmienna potrzebna do kontynuowania lub przerwania dzialania aplikacji
    static Scanner mySc = new Scanner(System.in); //Obiekt sluzacy do odczytu danych z klawiatury
    static int maxOpcja = 6; //Maksymalny numer dostepnej opcji w menu
    static int maxPojazdow = 8; //Maksymalna ilosc pojazdow w bazie

    //Funkcja glowna programu
    public static void main (String[] args) {
        System.out.println ("=============== WITAJ W PARKINGAPP ==============") ;
        //Petla glowna programu:
        while(pracuj){
            //Wyswietlenie menu:
            System.out.println ("Wybierz opcję") ;
            System.out.println ("1.Wyświetl listę pojazdów") ;
            System.out.println ("2.Dodaj pojazd");
            System.out.println ("3.Usuń pojazd") ;
            System.out.println ("4.Sprawdź uprawnienie pojazdu") ;
            System.out.println ("5.Statystyki parkingu") ;
            System.out.println ("6.Zakończ") ;
            //Wybor opcji przez uzytkownika:
            opcja = wybierzOpcje();
            //Wykonanie wybranej opcji:
            switch(opcja) {
                case 1:
                    wyswietlPojazdy();
                    poczekajNaEnter();
                    break;
                case 2:
                    dodajPojazd();
                    poczekajNaEnter();
                    break;
                case 3:
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

    //Funkcje pomocnicze:
    public static int wybierzOpcje(){
        int temp;
        while(true){
            //Sprawdzenie czy wprowadzono liczbe
            if (mySc.hasNextInt()){
                temp = mySc.nextInt();
                mySc.nextLine();

                //Sprawdzenie czy wybrany numer jest w zakresie dostepnych opcji
                if(temp >= 1 && temp <= maxOpcja){
                    return temp;
                }
                else{
                    System.out.println("Podana opcja nie istnieje. Podaj liczbę całkowitą od 1 do " + maxOpcja);
                }
            }
            //Wprowadzony tekst nie jest liczba:
            else{
                System.out.println("Podana opcja nie istnieje. Podaj liczbę całkowitą od 1 do " + maxOpcja);
                mySc.next();
                mySc.nextLine();
            }
        }
    }
    public static void wyswietlPojazdy(){
        //Sprawdzenie czy baza danych jest pusta.
        if (bazaDanych.isEmpty()){
            System.out.println("Baza danych jest pusta.");
        }
        else{
            //Wyswietlenie wszystkich elementow listy z tablicami rejestracyjnymi:
            System.out.println("Tablice rejestracyjne w bazie danych to: ");
            for(int i = 0; i < bazaDanych.size(); i++){
                System.out.println((i+1) + ". " + bazaDanych.get(i));
            }
            System.out.println("-------------------------------------------------------");
        }
    }
    public static void dodajPojazd(){
        //Sprawdzenie czy osiagnieto maksymalna ilosc pojazdow w bazie danych:
        if (bazaDanych.size() >= maxPojazdow) {
            System.out.println("Baza danych jest pełna. Maksymalna liczba pojazdów to: " + maxPojazdow);
            return;
        }

        String nowaTablica;
        while (true) {
            //Wporwadzenie nowe tablicy rejestracyjnej:
            System.out.println("Wprowadź tablice rejestracyjną i wciśnij ENTER");
            nowaTablica = mySc.nextLine().trim().toUpperCase();
            //Sprawdzenie czy nie wprowadzono pustego tekstu:
            if (!nowaTablica.isEmpty()) {
                break;
            }
            System.out.println("Nie podano tablicy (pusty tekst). Spróbuj ponownie.");
        }
        //Zabezpieczenie przed wprowadzeniem duplikatu:
        if (bazaDanych.contains(nowaTablica)) {
            System.out.println("Taka tablica już istnieje w bazie: " + nowaTablica);
            return;
        }
        //Dodanie nowej tablicy do bazy danych:
        bazaDanych.add(nowaTablica);
        System.out.println("Dodano nową tablicę: " + nowaTablica);
    }
    public static void usunPojazd() {
        //Sprawdzenie czy baza danych nie jest pusta:
        if (bazaDanych.isEmpty()) {
            System.out.println("Baza danych jest pusta. Nie ma czego usuwać.");
            return;
        }

        wyswietlPojazdy();
        System.out.println("Podaj ID pojazdu do usunięcia i wciśnij ENTER:");

        int id;
        while (true) {
            //Wprowadzenie przez uzytkownika id pojadu do usuniecia:
            if (mySc.hasNextInt()) {
                id = mySc.nextInt();
                mySc.nextLine();
                //Sprawdzenie czy wprowadzone id miesci sie w zakresie od 1 do ilosc tablic w bazie:
                if (id >= 1 && id <= bazaDanych.size()) {
                    break;
                } else {
                    System.out.println("ID poza zakresem. Podaj ID od 1 do " + bazaDanych.size());
                }
            }
            //Nie wprowadzono liczby:
            else {
                System.out.println("Podaj ID od 1 do " + bazaDanych.size());
                mySc.next();
                mySc.nextLine();
            }
        }
        //Usuwanie pojazdu z bazy danych:
        String usunieta = bazaDanych.remove(id - 1);
        System.out.println("Usunięto pojazd o ID " + id + ": " + usunieta);
    }
    public static void sprawdzUprawnieniePojazdu() {
        String tablica;

        while (true) {
            //Wprowadzenie tablicy rejstracyjnej do sprawdzenia.
            System.out.println("Podaj tablicę do sprawdzenia i wciśnij ENTER:");
            tablica = mySc.nextLine().trim().toUpperCase();
            //Sprawdzenie czy nie podano pustej linii.
            if (!tablica.isEmpty()) {
                break;
            }

            System.out.println("Nie podano tablicy (pusty tekst). Spróbuj ponownie.");
        }
        //Sprawdzenie czy podana tablica jest w bazie czy nie:
        if (bazaDanych.contains(tablica)) {
            System.out.println("Pojazd jest uprawniony (znaleziony w bazie).");
        }
        else {
            System.out.println("Pojazd nie jest uprawniony (brak w bazie).");
        }
    }
    public static void poczekajNaEnter() {
        //Funkcja stworzona aby uzytkownik musial wprowadzic enter aby powrocic do menu po wybraniu jedenj z opcji.
        System.out.println("Naciśnij ENTER, aby wrócić do menu...");
        mySc.nextLine();
    }
    public static void wyswietlStatystyki() {
        int pojemnosc = maxPojazdow;
        int liczbaPojazdow = bazaDanych.size();
        //Obliczenie wolnych miejsc parkinowych.
        int wolneMiejsca = pojemnosc - liczbaPojazdow;
        //Zabezpieczenie na wypadek nadmiaru samochodów na parkingu:
        if (wolneMiejsca < 0) {
            wolneMiejsca = 0;
        }

        double procentZajete = 0.0;
        //Obliczenie procentowego oblozenia parkingu.
        if (pojemnosc > 0) {
            procentZajete = (liczbaPojazdow * 100.0) / pojemnosc;
            if (procentZajete > 100.0) {
                procentZajete = 100.0;
            }
        }
        //Wyswietlenie statystyk:
        System.out.println("Statystyki parkingu:");
        System.out.println("Pojemność parkingu: " + pojemnosc);
        System.out.println("Liczba aut: " + liczbaPojazdow);
        System.out.println("Wolne miejsca: " + wolneMiejsca);
        System.out.printf("Zajęte miejsca: %.1f%%\n", procentZajete); //Wyswietlenie zminnej typu double z dokladnoscia do jednego miesjca po przecinku.
    }
}