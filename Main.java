import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Keranjang keranjang = new Keranjang();

        System.out.println("Selamat datang di sistem belanja!");

        while (true) {
            System.out.println("\nPilih menu:");
            System.out.println("1. Tambah Barang ke Keranjang");
            System.out.println("2. Hapus Barang dari Keranjang");
            System.out.println("3. Lihat Keranjang");
            System.out.println("4. Lakukan Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama barang: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan harga barang: ");
                    double harga = scanner.nextDouble();
                    keranjang.tambahBarang(new Barang(nama, harga));
                    break;
                case 2:
                    System.out.print("Masukkan nama barang yang ingin dihapus: ");
                    String namaHapus = scanner.nextLine();
                    keranjang.hapusBarang(new Barang(namaHapus, 0));
                    break;
                case 3:
                    keranjang.lihatBarang();
                    break;
                case 4:
                    System.out.print("Masukkan nama customer: ");
                    String customer = scanner.nextLine();
                    Transaksi transaksi = new Transaksi(customer, keranjang.getDaftarBarang());
                    System.out.print("Pilih metode pembayaran (QRIS/Bank Transfer/COD): ");
                    String metodePembayaran = scanner.nextLine();
                    Pembayaran.prosesPembayaran(transaksi, metodePembayaran);
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem belanja.");
                    scanner.close(); // Menutup scanner sebelum keluar
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

