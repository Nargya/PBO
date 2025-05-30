package proyek.manajemen.musik;

import java.util.Date;

public class Peminjaman {
    private String id;
    private User user;
    private AlatMusik alat;
    private Date tanggalPinjam;
    private int durasi;
    private int jumlah; // Tambahan penting

    public Peminjaman(String id, User user, AlatMusik alat, Date tanggalPinjam, int durasi, int jumlah) {
        this.id = id;
        this.user = user;
        this.alat = alat;
        this.tanggalPinjam = tanggalPinjam;
        this.durasi = durasi;
        this.jumlah = jumlah;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public AlatMusik getAlat() {
        return alat;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public int getDurasi() {
        return durasi;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
