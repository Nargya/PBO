/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasimanajemenalatmusik;

/**
 *
 * @author JFPS_1729
 */
import java.util.Date;
import java.text.SimpleDateFormat;

public class Peminjaman extends Transaksi {
    private User peminjam;
    private AlatMusik alat;
    private Date tglKembali;
    private int lamaHari;
    private int jumlah;
    private double hargaPerHari;
    
    public Peminjaman(User peminjam, AlatMusik alat, int lamaHari, int jumlah, double hargaPerHari) {
        this.peminjam = peminjam;
        this.alat = alat;
        this.lamaHari = lamaHari;
        this.jumlah = jumlah;
        this.hargaPerHari = hargaPerHari;
        this.tglKembali = new Date(System.currentTimeMillis() + (lamaHari * 24L * 60 * 60 * 1000));
    }
    
    @Override
    protected String generateId() {
        return "PMJ-" + System.currentTimeMillis();
    }
    
    public void prosesPeminjaman() {
        setStatus("Aktif");
    }
    
    public double hitungTotalHarga() {
        return jumlah * lamaHari * hargaPerHari;
    }
    
    public String getFormattedTglKembali() {
        return new SimpleDateFormat("dd-MM-yyyy").format(tglKembali);
    }
    
    // Getter methods
    public User getPeminjam() { return peminjam; }
    public AlatMusik getAlat() { return alat; }
    public Date getTglKembali() { return tglKembali; }
    public int getLamaHari() { return lamaHari; }
    public int getJumlah() { return jumlah; }
    public double getHargaPerHari() { return hargaPerHari; }
}