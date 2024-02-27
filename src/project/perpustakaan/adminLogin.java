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
public class adminLogin {
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
    
}
