/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasimanajemenalatmusik;

/**
 *
 * @author JFPS_1729
 */
import java.util.ArrayList;
import java.util.List;

public class ManajemenUser {
    private List<User> daftarUser;
    
    public ManajemenUser() {
        this.daftarUser = new ArrayList<>();
    }
    
    public void tambahUser(User user) {
        daftarUser.add(user);
        System.out.println("User Berhasil Ditambahkan!");
    }
    
    public void hapusUser(String id) {
        User user = cariUser(id);
        if (user != null) {
            daftarUser.remove(user);
            System.out.println("User Berhasil Dihapus!");
        } else {
            System.out.println("User Tidak Ditemukan!");
        }
    }
    
    public User cariUser(String id) {
        for (User user : daftarUser) {
            if (user.getUserId().equals(id)) {
                return user;
            }
        }
        return null;
    }
    
    public User login(String email, String password) {
        for (User user : daftarUser) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public void lihatData() {
        if (daftarUser.isEmpty()) {
            System.out.println("Belum Ada Data User!");
            return;
        }
        
        System.out.println("\n=== DAFTAR USER ===");
        for (User user : daftarUser) {
            System.out.println(user);
        }
    }
    
    public List<User> getDaftarUser() {
        return daftarUser;
    }
    
    // Method Baru Mengecek Apakah Email Sudah Terdaftar/Belum
    public boolean isEmailExists(String email) {
        for (User user : daftarUser) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}