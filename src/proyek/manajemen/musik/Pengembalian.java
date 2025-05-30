package proyek.manajemen.musik;

import java.util.Date;

public class Pengembalian {
    private String id;
    private Peminjaman peminjaman;
    private Date tanggalKembali;
    private String kondisiAlat;

    public Pengembalian(String id, Peminjaman peminjaman, Date tanggalKembali, String kondisiAlat) {
        this.id = id;
        this.peminjaman = peminjaman;
        this.tanggalKembali = tanggalKembali;
        this.kondisiAlat = kondisiAlat;
    }

    public String getId() {
        return id;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public String getKondisiAlat() {
        return kondisiAlat;
    }
}
