import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta nama pelanggan dari pengguna
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = scanner.nextLine();

        // Membuat objek CustomerDriver berdasarkan input pengguna
        CustomerDriver customer = new CustomerDriver(namaPelanggan, "CUST123");
        Keranjang keranjang = new Keranjang();

        boolean selesai = false;

        while (!selesai) {
            System.out.println("\n=== Sistem Keranjang dan Transaksi ===");
            System.out.println("1. Tambah Barang ke Keranjang");
            System.out.println("2. Lihat Keranjang");
            System.out.println("3. Checkout");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama barang: ");
                    String barang = scanner.nextLine();
                    keranjang.tambahBarang(barang);
                    break;
                case 2:
                    keranjang.tampilkanBarang();
                    break;
                case 3:
                    if (keranjang.getBarang().isEmpty()) {
                        System.out.println("Keranjang kosong, tidak dapat checkout.");
                    } else {
                        Transaksi transaksi = new Transaksi(keranjang.getBarang());
                        customer.tambahTransaksi(transaksi);
                        keranjang.kosongkanKeranjang();
                        System.out.println("Checkout berhasil! Transaksi telah selesai.");
                    }
                    break;
                case 4:
                    customer.tampilkanRiwayatTransaksi();
                    break;
                case 5:
                    selesai = true;
                    System.out.println("Terima kasih telah menggunakan sistem ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }
}
