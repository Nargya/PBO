/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasimanajemenalatmusik;

/**
 *
 * @author JFPS_1729
 */
public class Pembelian extends Transaksi {
    private AlatMusik alat;
    private int jumlah;
    
    public Pembelian(AlatMusik alat, int jumlah) {
        this.alat = alat;
        this.jumlah = jumlah;
    }
    
    @Override
    protected String generateId() {
        return "PBL-" + System.currentTimeMillis();
    }
    
    public void prosesPembelian() {
        setStatus("Completed");
    }
    
    public AlatMusik getAlat() { return alat; }
    public int getJumlah() { return jumlah; }
}