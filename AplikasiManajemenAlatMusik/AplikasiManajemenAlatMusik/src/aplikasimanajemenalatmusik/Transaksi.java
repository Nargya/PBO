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

public abstract class Transaksi {
    private String id;
    private Date tanggal;
    private String status;
    
    public Transaksi() {
        this.tanggal = new Date();
        this.status = "Pending";
        this.id = generateId();
    }
    
    protected abstract String generateId();
    
    public String getId() { return id; }
    public Date getTanggal() { return tanggal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}