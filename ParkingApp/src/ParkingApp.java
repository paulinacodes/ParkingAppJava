import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
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
                    mySc.nextLine();
                    break;
                case 2:
                    // System.out.println("Wybrana opcja to: " + opcja);
                    //System.out.println("Dodawanie pojazdu");
                    dodajPojazd();
                    mySc.nextLine();
                    break;
                case 3:
                    System.out.println("Wybrana opcja to: " + opcja);
                    System.out.println("Usuwanie pojazdu.");
                    mySc.nextLine();
                    break;
                case 4:
                    System.out.println("Wybrana opcja to: " + opcja);
                    System.out.println("KONIEC DZIALANIA PROGRAMU");
                    pracuj = false;
                default:
                    System.out.println("Opcja poza zakresem");
            }
        };
    }

    public static int wybierzOpcje(){
        Scanner sc = new Scanner(System.in);
        int temp = 0;
        while(true){
            if (sc.hasNextInt()){
                temp = sc.nextInt();
                break;
            }
            else{
                System.out.println("Podana opcja nie istnieje. Podaj liczbę całkowitą od 1 do 4");
                sc.next();
            }
        };
        return temp;
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Wprowadź tablice rejestracyjną i wciśnij ENTER");
        String nowaTablica = sc.nextLine();
        bazaDanych.add(nowaTablica);
        System.out.println("Dodane nową tablice: " + nowaTablica);
    }
}