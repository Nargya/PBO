package proyek.manajemen.musik;

import java.util.Date;
import java.util.List;

public class Pembelian {
    private String id;
    private Date tanggal;
    private List<AlatMusik> alatDibeli;
    private double totalHarga;

    public Pembelian(String id, Date tanggal, List<AlatMusik> alatDibeli, double totalHarga) {
        this.id = id;
        this.tanggal = tanggal;
        this.alatDibeli = alatDibeli;
        this.totalHarga = totalHarga;
    }

    // Getter untuk id
    public String getId() {
        return id;
    }

    // Getter untuk tanggal
    public Date getTanggal() {
        return tanggal;
    }

    // Getter untuk daftar alat yang dibeli
    public List<AlatMusik> getDaftarAlat() {
        return alatDibeli;
    }

    // Getter untuk total harga
    public double getTotalHarga() {
        return totalHarga;
    }
}
