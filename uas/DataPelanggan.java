public class DataPelanggan extends User {
    private String idPelanggan;
    private String noTelepon;
    private String email;

    public DataPelanggan(String id, String nama, String noTelepon, String email) {
        super(nama);
        this.idPelanggan = id;
        this.noTelepon = noTelepon;
        this.email = email;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNoTelepon() { return noTelepon; }
    public String getEmail() { return email; }

    public void setNama(String nama) { this.nama = nama; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public void info() {
        System.out.println("ID: " + idPelanggan + " | Nama: " + nama + " | Telp: " + noTelepon + " | Email: " + email);
    }
}