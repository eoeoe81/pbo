public class RuteJadwal {
    protected String idRute;
    protected String kotaAsal;
    protected String kotaTujuan;
    protected String tanggalBerangkat;
    protected String waktuBerangkat;
    protected double harga;

    public RuteJadwal(String id, String asal, String tujuan, String tgl, String waktu, double harga) {
        this.idRute = id;
        this.kotaAsal = asal;
        this.kotaTujuan = tujuan;
        this.tanggalBerangkat = tgl;
        this.waktuBerangkat = waktu;
        this.harga = harga;
    }

    public void tambahRute() {
        System.out.println("Rute ditambahkan: " + kotaAsal + " -> " + kotaTujuan);
    }
    
    public void infoRute() {
        System.out.println(idRute + ": " + kotaAsal + " -> " + kotaTujuan + " - Rp " + harga);
    }

    public double getHarga() {
        return harga;
    }
    
    public String getIdRute() {
        return idRute;
    }
}