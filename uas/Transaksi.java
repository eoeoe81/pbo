public class Transaksi {
    private String idTransaksi;
    private DataPelanggan pelanggan;
    private RuteDomestik rute;
    private int jumlahKursi;
    private double totalHarga;
    private String status;
    private String metodeBayar;

    public Transaksi(String id, DataPelanggan pelanggan, RuteDomestik rute, int jumlahKursi) {
        this.idTransaksi = id;
        this.pelanggan = pelanggan;
        this.rute = rute;
        this.jumlahKursi = jumlahKursi;
        this.totalHarga = rute.getHarga() * jumlahKursi;
        this.status = "Belum Bayar";
        this.metodeBayar = "-";
    }

    public void bayar(String metode) {
        this.status = "Lunas";
        this.metodeBayar = metode;
    }

    public String getIdTransaksi() { return idTransaksi; }
    public String getStatus() { return status; }

    public void infoTransaksi() {
        System.out.println("Kode: " + idTransaksi);
        System.out.println("Pelanggan: " + pelanggan.getNama());
        System.out.println("Rute: " + rute.kotaAsal + " -> " + rute.kotaTujuan);
        System.out.println("Kursi: " + jumlahKursi + " | Total: Rp " + (long)totalHarga);
        System.out.println("Status: " + status + " (" + metodeBayar + ")");
        System.out.println("---------------------------");
    }
}