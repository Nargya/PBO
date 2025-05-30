package proyek.manajemen.musik;

import java.util.List;

public class Laporan {
    private String idLaporan;
    private String bulan;
    private int totalPeminjaman;
    private int totalPembelian;
    private double totalPendapatan;

    public Laporan(String idLaporan, String bulan, int totalPeminjaman, int totalPembelian, double totalPendapatan) {
        this.idLaporan = idLaporan;
        this.bulan = bulan;
        this.totalPeminjaman = totalPeminjaman;
        this.totalPembelian = totalPembelian;
        this.totalPendapatan = totalPendapatan;
    }

    public void tampilkanRingkasanLaporan() {
        System.out.println("\n=== LAPORAN BULAN " + bulan.toUpperCase() + " ===");
        System.out.println("Total Peminjaman: " + totalPeminjaman);
        System.out.println("Total Pembelian: " + totalPembelian);
        System.out.println("Total Pendapatan: Rp " + totalPendapatan);
    }

    public static void generateLaporan(List<Peminjaman> daftarPeminjaman, List<Pembelian> daftarPembelian) {
        int totalPeminjaman = daftarPeminjaman.size();
        int totalPembelian = daftarPembelian.size();
        double totalPendapatan = 0;

        // Hitung pendapatan dari peminjaman
        for (Peminjaman p : daftarPeminjaman) {
            totalPendapatan += p.getDurasi() * 50000; // Harga per hari
        }

        // Hitung pendapatan dari pembelian
        for (Pembelian b : daftarPembelian) {
            totalPendapatan += b.getTotalHarga();
        }

        Laporan laporan = new Laporan("L001", "April", totalPeminjaman, totalPembelian, totalPendapatan);
        laporan.tampilkanRingkasanLaporan();
    }
}
