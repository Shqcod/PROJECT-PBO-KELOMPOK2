import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListBarang listBarang = new ListBarang();
        Admin admin = new Admin("01", "admin01", "adminpass");
        AdminDriver adminDriver = new AdminDriver(admin, listBarang);

        while (true) {
            System.out.println("\n=== Sistem Admin ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Lihat Daftar Barang");
            System.out.println("5. Lihat Transaksi");
            System.out.println("6. Terima Transaksi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (pilihan) {
                case 1: // Tambah Barang
                    System.out.print("Masukkan nama barang: ");
                    String namaBarang = scanner.nextLine();
                    System.out.print("Masukkan harga barang: ");
                    double hargaBarang = scanner.nextDouble();
                    System.out.print("Masukkan stok barang: ");
                    int stokBarang = scanner.nextInt();
                    Barang barangBaru = new Barang(namaBarang, hargaBarang, stokBarang);
                    listBarang.tambahBarang(barangBaru);
                    System.out.println("Barang berhasil ditambahkan.");
                    break;
                case 2: // Hapus Barang
                    System.out.print("Masukkan nama barang yang ingin dihapus: ");
                    String namaBarangHapus = scanner.nextLine();
                    listBarang.hapusBarang(namaBarangHapus);
                    System.out.println("Barang berhasil dihapus.");
                    break;
                case 3: // Edit Barang
                    System.out.print("Masukkan nama barang yang ingin diubah: ");
                    String namaBarangEdit = scanner.nextLine();
                    System.out.print("Masukkan harga baru: ");
                    double hargaBaru = scanner.nextDouble();
                    System.out.print("Masukkan stok baru: ");
                    int stokBaru = scanner.nextInt();
                    listBarang.editBarang(namaBarangEdit, hargaBaru, stokBaru);
                    System.out.println("Barang berhasil diubah.");
                    break;
                case 4: // Lihat Daftar Barang
                    listBarang.lihatBarang();
                    break;
                case 5: // Lihat Transaksi
                    // Implementasi melihat transaksi
                    break;
                case 6: // Terima Transaksi
                    // Implementasi terima transaksi
                    break;
                case 0: // Keluar
                    System.out.println("Terima kasih! Keluar dari sistem.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        }
    }
}
