/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.perpustakaan;
import java.util.Scanner;

/**
 *
 * @author zamza
 */
public class projectLoginPerpustakaan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Library System =====");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Student");
        System.out.println("3. Exit");

        System.out.print("Choose option(1-3): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                adminLogin();
                break;
            case 2:
                studentLogin();
                break;
            case 3:
                System.out.println("adios");
                break;
            default:
                System.out.println("Pilihan tidak valid. Program akan keluar.");
                break;
        }
    }

    private static void adminLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String enteredPassword = scanner.nextLine();

        String allowedUsername = "admin";
        String allowedPassword = "admin";

        if (enteredUsername.equals(allowedUsername) && enteredPassword.equals(allowedPassword)) {
            System.out.println("Succsesful Login as Admin");
        } else {
            System.out.println("Admin User Not Found!!");
        }
    }

    private static void studentLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your NIM: ");
        String input = scanner.nextLine();

        if (input.length() != 15) {
            System.out.println("Error: User Not Found.");
            return;
        }

        if (!input.matches("\\d+")) {
            System.out.println("Error: NIM harus terdiri dari digit angka.");
            return;
        }

        System.out.println("Successful Login as Student");
    }
    
}
