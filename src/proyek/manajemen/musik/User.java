package proyek.manajemen.musik;

public class User {
    private String id;
    private String password;
    private String role; // "admin" atau "user"

    // Konstruktor untuk user biasa
    public User(String id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Validasi login
    public boolean isValidLogin(String password) {
        return this.password.equals(password);
    }
}
