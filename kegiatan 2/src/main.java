import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class main {
    
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        
        Book[] bookList = new Book[5];

        // Mengisi array dengan objek buku
        bookList[0] = new Book("9780141182605", "George Orwell", "1984","sejarah",4);
        bookList[1] = new Book("9780061120084", "Harper Lee", "To Kill a Mockingbird","sejarah",4);
        bookList[2] = new Book("9780141439556", "Jane Austen", "Pride and Prejudice","sejarah",3);
        bookList[3] = new Book("9780743273565", "F. Scott Fitzgerald", "The Great Gatsby","sejarah",1);
        bookList[4] = new Book("9780451524935", "William Golding", "Lord of the Flies","sejarah",2);
        
        
        
        do {
            System.out.println("Welcome to Library System");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Admin admin = new Admin();
                    admin.login();
                    break;
                case "2":
                    Student student = new Student();
                    student.login();
                    break;
                case "3":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("3"));

        scanner.close();
    }
}

class Book {
    public String id;
    public String author;
    public String title;
    public String category;
    public int Stock; 
    

    public Book(String id, String author, String title,String category, int Stock) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.category = category;
        this.Stock = Stock;
        
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Author: " + author + ", Title: " + title + ", Category: " + category + ", Stock: " + Stock;
    }
}

class Admin {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Admin Login ===");
        System.out.print("Username: ");
        String usernameInput = scanner.nextLine();
        System.out.print("Password: ");
        String passwordInput = scanner.nextLine();

        if (usernameInput.equals(ADMIN_USERNAME) && passwordInput.equals(ADMIN_PASSWORD)) {
            System.out.println("Login successful.");
            menuAdmin();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        
        ArrayList<Mahasiswa> daftarMahasiswa = loadMahasiswa();
        
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display Registered Students");
            System.out.println("3. Save Data to file");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    tambahDataMahasiswa(daftarMahasiswa);
                    break;
                case "2":
                    tampilkanDataMahasiswa(daftarMahasiswa);
                    break;
                case "3":
                    saveMahasiswa(daftarMahasiswa);
                    break;
                case "4":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("4"));
    }
    
    private static ArrayList<Mahasiswa> loadMahasiswa() {
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mahasiswa.dat"))) {
            daftarMahasiswa = (ArrayList<Mahasiswa>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File data mahasiswa tidak ditemukan. Membuat file baru.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return daftarMahasiswa;
    }
    
    private static void tambahDataMahasiswa(ArrayList<Mahasiswa> daftarMahasiswa) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan Nama Mahasiswa:");
        String nama = scanner.nextLine();
         int i = 1;
         String nim;
        do {           
            i = 1;
            System.out.println("Masukkan NIM Mahasiswa:");
            nim = scanner.nextLine();
            if (!nim.matches("\\d{15}")){
                System.out.println("NIM harus memiliki 15 digit dan hanya terdiri dari angka. Data tidak disimpan.");
                i = 0;
            }
        } while (i == 0);
        
        System.out.println("Masukkan Jurusan Mahasiswa:");
        String jurusan = scanner.nextLine();
        
        Mahasiswa mahasiswa = new Mahasiswa(nama, nim, jurusan);
        daftarMahasiswa.add(mahasiswa);

        System.out.println("Data mahasiswa ditambahkan.");
    }
    
    private static void tampilkanDataMahasiswa(ArrayList<Mahasiswa> daftarMahasiswa) {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Tidak ada data mahasiswa.");
        } else {
            System.out.println("Daftar Mahasiswa:");
            System.out.println("Universitas Muhammadiyah Malang");
            for (Mahasiswa mahasiswa : daftarMahasiswa) {
                System.out.println("Nama: " + mahasiswa.getNama() + ", NIM: " + mahasiswa.getNim() + ", Jurusan: " + mahasiswa.getJurusan());
            }
        }
    }
    
    private static void saveMahasiswa(ArrayList<Mahasiswa> daftarMahasiswa) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mahasiswa.dat"))) {
            oos.writeObject(daftarMahasiswa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayStudents() {
        // Display registered students
    }
}

class Student {
    
    //static HashMap<String, ArrayList<Book>> borrowedBooksMap = new HashMap<>();
    
    public void login() {
        ArrayList<Mahasiswa> daftarMahasiswa = loadMahasiswa();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Student Login ===");
        System.out.print("Enter student NIM: ");
        String nimInput = scanner.nextLine();
        
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if(nimInput.equals(mahasiswa.getNim())){
                System.out.println("login student succses");
                menuStudent();
            }
            
        }
        
        System.out.println("wrong nim");
        
    }
    
    private static ArrayList<Mahasiswa> loadMahasiswa() {
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mahasiswa.dat"))) {
            daftarMahasiswa = (ArrayList<Mahasiswa>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File data mahasiswa tidak ditemukan. Membuat file baru.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return daftarMahasiswa;
    }

    public void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Student Menu =====");
        System.out.println("1. Buku terpinjam");
        System.out.println("2. pinjam Buku");
        System.out.println("3. logout");
        System.out.println("Choose option (1-3): ");
        String option = scanner.nextLine();
        
        switch (option) {
            case "1":
                displayBuku();
                menuStudent();
                break;
            case "2":
                borrowBook();
                break;
            case "3":
                System.out.println("Logging out...");
                break;
            }
                             
    }
    
    void borrowBook() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Pinjam Buku ===");
            displayBuku();
            System.out.println("99. Kembali");

            System.out.print("Input id buku yang ingin dipinjam (input 99 untuk kembali): ");
            int bookId = scanner.nextInt();
            scanner.nextLine();

            if (bookId == 99) {
                return;
            }

//            Book bookToBorrow = findBookById(bookId);
//            if (bookToBorrow != null && !isBookAlreadyBorrowed(bookToBorrow)) {
//                borrowedBooksMap.computeIfAbsent(nim, k -> new ArrayList<>()).add(bookToBorrow);
//                System.out.println("Buku berhasil dipinjam.");
//                return;
//            } else {
//                System.out.println("Buku tidak tersedia atau sudah dipinjam sebelumnya. Silakan coba lagi.");
//            }
        }
    }
    
//    Book findBookById(int id) {
//        
//        Book[] bookList = new Book[5];
//
//        // Mengisi array dengan objek buku
//        bookList[0] = new Book("9780141182605", "George Orwell", "1984","sejarah",4);
//        bookList[1] = new Book("9780061120084", "Harper Lee", "To Kill a Mockingbird","sejarah",4);
//        bookList[2] = new Book("9780141439556", "Jane Austen", "Pride and Prejudice","sejarah",3);
//        bookList[3] = new Book("9780743273565", "F. Scott Fitzgerald", "The Great Gatsby","sejarah",1);
//        bookList[4] = new Book("9780451524935", "William Golding", "Lord of the Flies","sejarah",2);
//        
//        
//        for (Book book : bookList) {
//            if (book.id.equals(id)) {
//                return book;
//            }
//        }
//        return null;
//    }
    
//    boolean isBookAlreadyBorrowed(Book book) {
//        ArrayList<Book> borrowedBooks = borrowedBooksMap.getOrDefault(nim, new ArrayList<>());
//        return borrowedBooks.contains(book);
//    }
    
    public void displayBuku(){
        
        Book[] bookList = new Book[5];

        // Mengisi array dengan objek buku
        bookList[0] = new Book("9780141182605", "George Orwell", "1984","sejarah",4);
        bookList[1] = new Book("9780061120084", "Harper Lee", "To Kill a Mockingbird","sejarah",4);
        bookList[2] = new Book("9780141439556", "Jane Austen", "Pride and Prejudice","sejarah",3);
        bookList[3] = new Book("9780743273565", "F. Scott Fitzgerald", "The Great Gatsby","sejarah",1);
        bookList[4] = new Book("9780451524935", "William Golding", "Lord of the Flies","sejarah",2);
        
        System.out.println("List of Books:");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
    
}

class Mahasiswa implements Serializable {
    private static final long serialVersionUID = 1L;

    public String nama;
    public String nim;
    public String jurusan;

    public Mahasiswa(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }
    
    public String getJurusan() {
        return jurusan;
    }
}

