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

public class Pengembalian {
    private Peminjaman peminjaman;
    private Date tglPengembalian;
    private double denda;
    
    public Pengembalian(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
        this.tglPengembalian = new Date();
        hitungDenda();
    }
    
    private void hitungDenda() {
        if (tglPengembalian.after(peminjaman.getTglKembali())) {
            long diff = tglPengembalian.getTime() - peminjaman.getTglKembali().getTime();
            long daysLate = diff / (1000 * 60 * 60 * 24);
            
            // Denda Dihitung Per Hari
            // Menggunakan 5% Dari Harga Sewa Per Hari Setiap Item
            double dendaPerItemPerHari = peminjaman.getHargaPerHari() * 0.05;
            this.denda = daysLate * peminjaman.getJumlah() * dendaPerItemPerHari;
        } else {
            this.denda = 0;
        }
    }
    
    public void prosesPengembalian() {
        peminjaman.setStatus("Kembali");
    }
    
    // Getter Methods
    public double getDenda() { 
        return denda; 
    }
    
    public Date getTglPengembalian() {
        return tglPengembalian;
    }
    
    public Peminjaman getPeminjaman() {
        return peminjaman;
    }
}