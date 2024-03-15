import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Mahasiswa> daftarMahasiswa = loadMahasiswa();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Keluar");

            Scanner scanner = new Scanner(System.in);
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahDataMahasiswa(daftarMahasiswa);
                    break;
                case 2:
                    tampilkanDataMahasiswa(daftarMahasiswa);
                    break;
                case 3:
                    saveMahasiswa(daftarMahasiswa);
                    System.out.println("Adios.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
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
}

class Mahasiswa implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nama;
    private String nim;
    private String jurusan;

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
