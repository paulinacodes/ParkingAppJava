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

    public static void main (String[] args) {
        System.out.println ("=============== WITAJ W PARKINGAPP ==============") ;

        while(pracuj){
            System.out.println ("Wybierz opcję") ;
            System.out.println ("1.Wyświetl listę pojazdów") ;
            System.out.println ("2.Dodaj pojazd");
            System.out.println ("3.Usuń pojazd") ;
            System.out.println ("4.Zakończ") ;
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
                if(temp >= 1 && temp <= 4){
                    return temp;
                }
                else{
                    System.out.println("Podana opcja nie istnieje. Podaj liczbę całkowitą od 1 do 4");
                }
            }
            else{
                System.out.println("Podana opcja nie istnieje. Podaj liczbę całkowitą od 1 do 4");
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
        System.out.println("Wprowadź tablice rejestracyjną i wciśnij ENTER");
        String nowaTablica = mySc.nextLine();
        bazaDanych.add(nowaTablica);
        System.out.println("Dodane nową tablice: " + nowaTablica);
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
                mySc.nextLine(); // wyrzuć błędną linię
            }
        }

        String usunieta = bazaDanych.remove(id - 1);
        System.out.println("Usunięto pojazd o ID " + id + ": " + usunieta);
    }
    public static void poczekajNaEnter() {
        System.out.println("Naciśnij ENTER, aby wrócić do menu...");
        mySc.nextLine();
    }

}