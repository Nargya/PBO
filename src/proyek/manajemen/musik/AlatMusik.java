package proyek.manajemen.musik;

public class AlatMusik {
    private String id, nama, jenis, merk, status;
    private int stok;
    private double hargaPembelian;
    private double hargaPeminjamanPerHari;

    // Konstruktor lengkap
    public AlatMusik(String id, String nama, String jenis, String merk, String status, int stok,
                     double hargaPembelian, double hargaPeminjamanPerHari) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.merk = merk;
        this.status = status;
        this.stok = stok;
        this.hargaPembelian = hargaPembelian;
        this.hargaPeminjamanPerHari = hargaPeminjamanPerHari;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String getMerk() {
        return merk;
    }

    public String getStatus() {
        return status;
    }

    public int getStok() {
        return stok;
    }

    public double getHargaPembelian() {
        return hargaPembelian;
    }

    public double getHargaPeminjamanPerHari() {
        return hargaPeminjamanPerHari;
    }

    // Setters
    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nama + " | Stok: " + stok + " | Harga Beli: Rp" + hargaPembelian +
                " | Sewa/Hari: Rp" + hargaPeminjamanPerHari;
    }
}
