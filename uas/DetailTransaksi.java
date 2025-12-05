public class DetailTransaksi implements Pembayaran {
    private String idDetail;
    private double totalBayar;
    private String metodePembayaran;

    public DetailTransaksi(String id, double total, String metode) {
        this.idDetail = id;
        this.totalBayar = total;
        this.metodePembayaran = metode;
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Memproses pembayaran via " + metodePembayaran + "...");
    }

    @Override
    public void cetakBukti() {
        System.out.println("=== BUKTI BAYAR ===");
        System.out.println("ID: " + idDetail);
        System.out.println("Total: Rp " + totalBayar);
        System.out.println("Status: LUNAS");
        System.out.println("===================");
    }
}