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

public class ManajemenAlatMusik {
    private List<AlatMusik> daftarAlat;
    
    public ManajemenAlatMusik() {
        this.daftarAlat = new ArrayList<>();
    }
    
    public void tambahAlat(AlatMusik alat) {
        daftarAlat.add(alat);
        System.out.println("Alat Musik Berhasil Ditambahkan!");
    }
    
    public void hapusAlat(String id) {
        AlatMusik alat = cariAlat(id);
        if (alat != null) {
            daftarAlat.remove(alat);
            System.out.println("Alat Musik Berhasil Dihapus!");
        } else {
            System.out.println("Alat Musik Tidak Ditemukan!");
        }
    }
    
    public AlatMusik cariAlat(String id) {
        for (AlatMusik alat : daftarAlat) {
            if (alat.getId().equals(id)) {
                return alat;
            }
        }
        return null;
    }
    
    public void lihatData() {
        if (daftarAlat.isEmpty()) {
            System.out.println("Belum Ada Data Alat Musik!");
            return;
        }
        
        System.out.println("\n=== DAFTAR ALAT MUSIK ===");
        for (AlatMusik alat : daftarAlat) {
            System.out.println(alat);
        }
    }
    
    public List<AlatMusik> getDaftarAlat() {
        return daftarAlat;
    }
}