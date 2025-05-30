/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasimanajemenalatmusik;

/**
 *
 * @author JFPS_1729
 */
public class User {
    private String userId;
    private String nama;
    private String role;
    private String email;
    private String password;
    
    public User(String userId, String nama, String role, String email, String password) {
        this.userId = userId;
        this.nama = nama;
        this.role = role;
        this.email = email;
        this.password = password;
    }
    
    // Getter Dan Setter
    public String getUserId() { return userId; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public String toString() {
        return String.format("ID : %s || Nama : %s || Role : %s || Email : %s", 
                           userId, nama, role, email);
    }
}