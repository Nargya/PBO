/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasimanajemenalatmusik;

/**
 *
 * @author JFPS_1729
 */
public class AlatMusik {
    private String id;
    private String nama;
    private String jenis;
    private int stok;
    private double harga;
    
    public AlatMusik(String id, String nama, String jenis, int stok, double harga) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.stok = stok;
        this.harga = harga;
    }
    
    // Getter Dan Setter
    public String getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    
    @Override
    public String toString() {
        return String.format("ID : %s || Nama : %s || Jenis : %s || Stok : %d || Harga : Rp%,.2f", 
                           id, nama, jenis, stok, harga);
    }
}