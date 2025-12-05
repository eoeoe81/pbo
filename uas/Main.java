import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<DataPelanggan> daftarPelanggan = new ArrayList<>();
    static ArrayList<RuteDomestik> daftarRute = new ArrayList<>();
    static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        isiDataOwal();
        
        Admin admin = new Admin("admin", "123", "Budi");
        System.out.println("=== LOGIN ===");
        System.out.print("User: ");
        String u = scanner.nextLine();
        System.out.print("Pass: ");
        String p = scanner.nextLine();

        if (admin.login(u, p)) {
            System.out.println("Login Berhasil!");
            jalankanMenu();
        } else {
            System.out.println("Login Gagal.");
        }
    }

    public static void jalankanMenu() {
        while (true) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Kelola Pelanggan (Lihat/Tambah/Edit/Hapus)");
            System.out.println("2. Kelola Tiket/Rute (Lihat/Edit)"); // [POIN 8]
            System.out.println("3. Beli Tiket (Buat Pesanan)");
            System.out.println("4. Pembayaran (Detail Transaksi)");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            if (scanner.hasNextInt()) {
                int pil = scanner.nextInt();
                scanner.nextLine();

                if (pil == 1) menuPelanggan();
                else if (pil == 2) menuTiket();
                else if (pil == 3) menuBeliTiket();
                else if (pil == 4) menuBayar(); 
                else if (pil == 0) {
                    System.out.println("Bye bye!");
                    System.exit(0);
                } else salahInput();
            } else {
                scanner.nextLine();
                salahInput();
            }
        }
    }

    public static void menuPelanggan() {
        System.out.println("\n--- KELOLA PELANGGAN ---");
        System.out.println("1. Lihat Pelanggan");
        System.out.println("2. Tambah Pelanggan");
        System.out.println("3. Edit Pelanggan (Cari by ID)");
        System.out.println("4. Hapus Pelanggan (Cari by ID)");
        System.out.println("9. Kembali");
        System.out.print("Pilih: ");
        
        int pil = scanner.nextInt();
        scanner.nextLine();

        if (pil == 1) lihatPelanggan();
        else if (pil == 2) tambahPelanggan();
        else if (pil == 3) editPelanggan();
        else if (pil == 4) hapusPelanggan();
    }

    public static void lihatPelanggan() {
        System.out.println("\n--- LIST PELANGGAN ---");
        if (daftarPelanggan.isEmpty()) System.out.println("Data Kosong.");
        else {
            for (DataPelanggan p : daftarPelanggan) {
                p.info();
            }
        }
    }

    public static void tambahPelanggan() {
        System.out.println("\n--- TAMBAH PELANGGAN ---");
        
        String idBaru = "P0" + (daftarPelanggan.size() + 1);
        System.out.println("ID Otomatis: " + idBaru);

        System.out.print("Nama: ");
        String nama = kapital(scanner.nextLine()); 

        String telp = "";
        while (true) {
            System.out.print("No Telp (Angka): ");
            if (scanner.hasNextLong()) { 
                telp = scanner.next();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Error: Harap masukkan angka saja!");
                scanner.nextLine();
            }
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();

        daftarPelanggan.add(new DataPelanggan(idBaru, nama, telp, email));
        System.out.println("Sukses disimpan!");
    }

    public static void editPelanggan() {
        lihatPelanggan();
        System.out.print("\nMasukkan ID Pelanggan yang mau diedit: ");
        String idCari = scanner.nextLine();

        DataPelanggan p = cariPelanggan(idCari);
        
        if (p == null) {
            System.out.println("Error: ID Pelanggan tidak ditemukan!");
            tanyaLanjut();
        } else {
            System.out.println("--- EDIT (Tekan Enter jika tidak ingin mengubah) ---");
            
            System.out.print("Nama Lama (" + p.getNama() + ") -> Baru: ");
            String n = scanner.nextLine();
            if (!n.isEmpty()) p.setNama(kapital(n));

            System.out.print("Telp Lama (" + p.getNoTelepon() + ") -> Baru: ");
            String t = scanner.nextLine();
            if (!t.isEmpty()) p.setNoTelepon(t); // Validasi angka diskip biar simpel disini

            System.out.print("Email Lama (" + p.getEmail() + ") -> Baru: ");
            String e = scanner.nextLine();
            if (!e.isEmpty()) p.setEmail(e);

            System.out.println("Data berhasil diperbarui!");
        }
    }

    public static void hapusPelanggan() {
        lihatPelanggan();
        System.out.print("\nMasukkan ID Pelanggan yang mau dihapus: ");
        String idCari = scanner.nextLine();
        
        DataPelanggan p = cariPelanggan(idCari);
        
        if (p == null) {
            System.out.println("Error: ID tidak ditemukan.");
            tanyaLanjut();
        } else {
            daftarPelanggan.remove(p);
            System.out.println("Data ID " + idCari + " berhasil dihapus.");
        }
    }

    public static void menuTiket() {
        System.out.println("\n--- LIST RUTE ---");
        for (RuteDomestik r : daftarRute) {
            r.infoRute();
        }
        System.out.println("\n1. Edit Harga/Jadwal Tiket");
        System.out.println("9. Kembali");
        System.out.print("Pilih: ");
        int pil = scanner.nextInt(); 
        scanner.nextLine();

        if (pil == 1) {
            System.out.print("Masukkan ID Rute: ");
            String id = scanner.nextLine();
            RuteDomestik r = cariRute(id);
            if(r != null) {
                System.out.print("Harga Baru (Input 0 jika tetap): ");
                double h = scanner.nextDouble(); scanner.nextLine();
                if(h > 0) r.setHarga(h);
                System.out.println("Update berhasil.");
            } else {
                System.out.println("Rute tidak ada.");
            }
        }
    }

    public static void menuBeliTiket() {
        System.out.println("\n--- PEMBELIAN TIKET ---");
        
        lihatPelanggan();
        System.out.println("");
        for(RuteDomestik r : daftarRute) r.infoRute();

        System.out.print("\nMasukkan ID Pelanggan: ");
        DataPelanggan p = cariPelanggan(scanner.nextLine());
        
        System.out.print("Masukkan ID Rute: ");
        RuteDomestik r = cariRute(scanner.nextLine());

        if (p == null || r == null) {
            System.out.println("Error: ID Pelanggan atau Rute Salah!");
            tanyaLanjut();
            return;
        }

        System.out.print("Jumlah Kursi: ");
        int qty = scanner.nextInt(); scanner.nextLine();

        double total = r.getHarga() * qty;
        System.out.println("Total Bayar: Rp " + (long)total);
        System.out.print("Konfirmasi Pesanan? (y/n): ");
        String konfir = scanner.nextLine();

        if (konfir.equalsIgnoreCase("y")) {
            String idTrx = "TRX" + (daftarTransaksi.size() + 1);
            Transaksi baru = new Transaksi(idTrx, p, r, qty);
            daftarTransaksi.add(baru);
            System.out.println("Pesanan Berhasil Dibuat! Kode: " + idTrx);
            System.out.println("Silakan ke menu Pembayaran utk membayar.");
        } else {
            System.out.println("Dibatalkan.");
        }
    }

    public static void menuBayar() {
        System.out.println("\n--- STATUS TRANSAKSI ---");
        boolean adaTagihan = false;
        for (Transaksi t : daftarTransaksi) {
            t.infoTransaksi();
            if (t.getStatus().equals("Belum Bayar")) adaTagihan = true;
        }

        if (!adaTagihan) {
            System.out.println("Tidak ada tagihan yang belum dibayar.");
            return;
        }

        System.out.print("\nMasukkan Kode Transaksi utk Membayar: ");
        String idTrx = scanner.nextLine();

        Transaksi t = null;
        for (Transaksi trx : daftarTransaksi) {
            if (trx.getIdTransaksi().equalsIgnoreCase(idTrx)) {
                t = trx;
                break;
            }
        }

        if (t != null) {
            if (t.getStatus().equals("Lunas")) {
                System.out.println("Transaksi ini sudah lunas!");
            } else {
                System.out.print("Metode Pembayaran (Cash/Transfer/QRIS): ");
                String metode = scanner.nextLine();
                t.bayar(metode);
                System.out.println("PEMBAYARAN BERHASIL! Status Lunas.");
            }
        } else {
            System.out.println("Kode Transaksi tidak ditemukan.");
            tanyaLanjut();
        }
    }

    public static String kapital(String input) {
        if (input == null || input.isEmpty()) return input;
        String[] kata = input.split(" ");
        String hasil = "";
        for (String k : kata) {
            if (k.length() > 0) {
                hasil += k.substring(0, 1).toUpperCase() + k.substring(1).toLowerCase() + " ";
            }
        }
        return hasil.trim();
    }

    public static DataPelanggan cariPelanggan(String id) {
        for (DataPelanggan p : daftarPelanggan) {
            if (p.getIdPelanggan().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    public static RuteDomestik cariRute(String id) {
        for (RuteDomestik r : daftarRute) {
            if (r.getIdRute().equalsIgnoreCase(id)) return r;
        }
        return null;
    }

    public static void tanyaLanjut() {
        System.out.print("Ingin lanjut ke menu utama? (y/n): ");
        String jawab = scanner.nextLine();
        if (!jawab.equalsIgnoreCase("y")) {
            System.out.println("Program Berhenti.");
            System.exit(0);
        }
    }
    
    public static void salahInput() {
        System.out.println("Input salah. Coba lagi.");
    }

    public static void isiDataOwal() {
        daftarPelanggan.add(new DataPelanggan("P01", "Dewi Sartika", "0812345", "dewi@gmail.com"));
        daftarPelanggan.add(new DataPelanggan("P02", "Budi Sudarsono", "0812346", "budi@gmail.com"));
        
        daftarRute.add(new RuteDomestik("R01", "Jakarta", "Bali", "20-Des", "08:00", 1500000, "Garuda"));
        daftarRute.add(new RuteDomestik("R02", "Medan", "Surabaya", "21-Des", "10:00", 2000000, "Lion"));
        daftarRute.add(new RuteDomestik("R03", "Padang", "Jakarta", "22-Des", "07:30", 1200000, "Citilink"));
        daftarRute.add(new RuteDomestik("R04", "Yogyakarta", "Makassar", "23-Des", "14:00", 1800000, "Batik Air"));
        daftarRute.add(new RuteDomestik("R05", "Bandung", "Medan", "24-Des", "16:45", 2100000, "Super Air Jet"));
    }
}