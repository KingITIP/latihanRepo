/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project.perpustakaan;
import java.util.Scanner;
/**
 *
 * @author zamza
 */
public class studentLogin {
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
