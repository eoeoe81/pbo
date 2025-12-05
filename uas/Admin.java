public class Admin extends User {
    private String username;
    private String password;

    public Admin(String username, String password, String nama) {
        super(nama);
        this.username = username;
        this.password = password;
    }

    public boolean login(String user, String pass) {
        if (this.username.equals(user) && this.password.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void info() {
        System.out.println("[Admin] " + nama + " siap mengelola sistem.");
    }
}