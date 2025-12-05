public class RuteDomestik extends RuteJadwal {
    private String maskapai;

    public RuteDomestik(String id, String asal, String tujuan, String tgl, String waktu, double harga, String maskapai) {
        super(id, asal, tujuan, tgl, waktu, harga); 
        this.maskapai = maskapai;
    }
    
    public void setHarga(double harga) { this.harga = harga; }
    public void setWaktu(String waktu) { this.waktuBerangkat = waktu; }

    @Override
    public void infoRute() {
        System.out.println(idRute + ": " + kotaAsal + " -> " + kotaTujuan + " (" + maskapai + ") [" + tanggalBerangkat + " " + waktuBerangkat + "] - Rp " + (long)harga);
    }
}