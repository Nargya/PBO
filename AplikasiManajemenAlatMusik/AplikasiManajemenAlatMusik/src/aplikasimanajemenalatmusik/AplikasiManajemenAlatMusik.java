/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplikasimanajemenalatmusik;

/**
 *
 * @author JFPS_1729
 */
import java.util.*;

public class AplikasiManajemenAlatMusik {
    private static ManajemenAlatMusik manajemenAlat = new ManajemenAlatMusik();
    private static ManajemenUser manajemenUser = new ManajemenUser();
    private static List<Transaksi> daftarTransaksi = new ArrayList<>();
    private static User userLogin;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        initTestData();
        showMenuUtama();
    }
    
    private static void initTestData() {
        // Tambah User Test
        manajemenUser.tambahUser(new User("ADM01", "Ersa Amelia", "Admin", "ersa321@gmail.com", "admin1"));
        manajemenUser.tambahUser(new User("ADM02", "Naufal Anargya", "Admin", "naufal321@gmail.com", "admin2"));
        manajemenUser.tambahUser(new User("USR01", "Light Yagami", "Pengguna", "light321@gmail.com", "pengguna1"));
        manajemenUser.tambahUser(new User("USR02", "Masashi Kishimoto", "Pengguna", "masashi321@gmail.com", "pengguna2"));
        
        // Tambah Instrument Test
        manajemenAlat.tambahAlat(new AlatMusik("AM01", "Gitar Klasik - Mahogani", "Alat Musik Petik",15, 1500000));
        manajemenAlat.tambahAlat(new AlatMusik("AM02", "Piano Digital - Yamaha", "Alat Musik Tekan", 8, 4000000));
        manajemenAlat.tambahAlat(new AlatMusik("AM03", "Drum Elektrik - Mapex", "Alat Musik Pukul", 5, 7000000));
        manajemenAlat.tambahAlat(new AlatMusik("AM04", "Biola Akustik - Stradivarius", "Alat Musik Gesek", 18, 2000000));
        manajemenAlat.tambahAlat(new AlatMusik("AM05", "Saxophone Alto - Selmer", "Alat Musik Tiup", 12, 1500000));
        manajemenAlat.tambahAlat(new AlatMusik("AM06", "Bass Elektrik - Fender", "Alat Musik Petik", 7, 2500000));
        manajemenAlat.tambahAlat(new AlatMusik("AM07", "Keyboard Arranger - Roland", "Alat Musik Tekan", 5, 7000000));
        manajemenAlat.tambahAlat(new AlatMusik("AM08", "Cajon Kayu - Meinl", "Alat Musik Pukul", 3, 1300000));
        manajemenAlat.tambahAlat(new AlatMusik("AM09", "Harmonika Kromatik - Hohner", "Alat Musik Tiup", 17, 7500000));
        manajemenAlat.tambahAlat(new AlatMusik("AM10", "Ukulele Sopran - Kala", "Alat Musik Petik", 10, 800000));
    }
    
    private static void showMenuUtama() {
        while (true) {
            System.out.println("\n=== APLIKASI MANAJEMEN ALAT MUSIK ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Keluar");
            System.out.print("Pilihan : ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Terima Kasih Sudah Menggunakan Aplikasi Kami!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan Tidak Valid!");
            }
        }
    }
    
    private static void register() {
        System.out.println("\n=== REGISTRASI USER BARU ===");
        
        // Membuat User ID
        String lastUserId = "";
        if (!manajemenUser.getDaftarUser().isEmpty()) {
            lastUserId = manajemenUser.getDaftarUser().get(manajemenUser.getDaftarUser().size()-1).getUserId();
        }
        String newId = generateNextUserId(lastUserId);
        
        System.out.println("ID User Anda : " + newId);
        System.out.print("Nama Lengkap : ");
        String nama = scanner.nextLine();
    
        String role = "User";
        
        System.out.print("Email    : ");
        String email = scanner.nextLine();
        
        // Memeriksa Apakah Email Sudah Terdaftar/Belum
        if (manajemenUser.isEmailExists(email)) {
            System.out.println("Email Yang Anda Gunakan Sudah Terdaftar! Silakan Gunakan Email Lain.");
            return;
        }
        
        System.out.print("Password : ");
        String password = scanner.nextLine();
        
        // Membuat User Baru
        User newUser = new User(newId, nama, role, email, password);
        manajemenUser.tambahUser(newUser);
        
        System.out.println("\nRegistrasi Berhasil! Silakan Login Dengan Akun Anda.");
    }
    
    private static String generateNextUserId(String lastUserId) {
        if (lastUserId.isEmpty()) {
            return "USR01";
        }
        
        String prefix = lastUserId.substring(0, 3);
        int num = Integer.parseInt(lastUserId.substring(3));
        num++;
        
        return String.format("%s%02d", prefix, num);
    }
    
    private static void login() {
        System.out.print("Email    : ");
        String email = scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();
        
        userLogin = manajemenUser.login(email, password);
        
        if (userLogin != null) {
            System.out.println("Login Berhasil! Selamat Datang Di Toko E-Orkes Kami!!" + userLogin.getNama());
            showMenuUser();
        } else {
            System.out.println("Login Gagal! Email Atau Password Anda Salah.");
        }
    }
    
    private static void showMenuUser() {
        while (true) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Manajemen Alat Musik");
            System.out.println("2. Manajemen User");
            System.out.println("3. Transaksi");
            System.out.println("4. Laporan");
            System.out.println("5. Logout");
            System.out.print("Pilihan: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    menuManajemenAlatMusik();
                    break;
                case 2:
                    if (userLogin.getRole().equals("Admin") || userLogin.getRole().equals("Admin")) {
                    menuManajemenUser();
                    } else {
                        System.out.println("Anda Tidak Memiliki Akses Untuk Fitur Manajemen User. Silahkan Kembali!");
                    }
                    break;
                case 3:
                    menuTransaksi();
                    break;
                case 4:
                    menuLaporan();
                    break;
                case 5:
                    userLogin = null;
                    return;
                default:
                    System.out.println("Pilihan Tidak Valid!");
            }
        }
    }
    
    private static void menuManajemenAlatMusik() {
        while (true) {
            System.out.println("\n=== MANAJEMEN ALAT MUSIK ===");
            System.out.println("1. Tambah Alat Musik");
            System.out.println("2. Edit Alat Musik");
            System.out.println("3. Hapus Alat Musik");
            System.out.println("4. Lihat Daftar Alat Musik");
            System.out.println("5. Kembali");
            System.out.print("Pilihan : ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    tambahAlatMusik();
                    break;
                case 2:
                    editAlatMusik();
                    break;
                case 3:
                    hapusAlatMusik();
                    break;
                case 4:
                    manajemenAlat.lihatData();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan Tidak Valid!");
            }
        }
    }
    
    private static void tambahAlatMusik() {
        System.out.print("ID AM : ");
        String id = scanner.nextLine();
        System.out.print("Nama  : ");
        String nama = scanner.nextLine();
        System.out.print("Jenis : ");
        String jenis = scanner.nextLine();
        System.out.print("Stok  : ");
        int stok = scanner.nextInt();
        System.out.print("Harga : ");
        double harga = scanner.nextDouble();
        scanner.nextLine();
        
        manajemenAlat.tambahAlat(new AlatMusik(id, nama, jenis, stok, harga));
    }
    
    private static void editAlatMusik() {
        System.out.print("Masukkan ID Alat Musik Yang Akan Diedit : ");
        String id = scanner.nextLine();
        
        AlatMusik alat = manajemenAlat.cariAlat(id);
        if (alat == null) {
            System.out.println("Alat Musik Tidak Ada Dalam Daftar!");
            return;
        }
        
        System.out.print("Nama AM Baru (" + alat.getNama() + ") : ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) alat.setNama(nama);
        
        System.out.print("Jenis Baru (" + alat.getJenis() + ") : ");
        String jenis = scanner.nextLine();
        if (!jenis.isEmpty()) alat.setJenis(jenis);
        
        System.out.print("Stok Baru (" + alat.getStok() + ") : ");
        String stokStr = scanner.nextLine();
        if (!stokStr.isEmpty()) alat.setStok(Integer.parseInt(stokStr));
        
        System.out.print("Harga Baru (" + alat.getHarga() + ") : ");
        String hargaStr = scanner.nextLine();
        if (!hargaStr.isEmpty()) alat.setHarga(Double.parseDouble(hargaStr));
        
        System.out.println("Data Alat Musik Berhasil Diupdate!");
    }
    
    private static void hapusAlatMusik() {
        System.out.print("Masukkan ID Alat Musik Yang Akan Dihapus : ");
        String id = scanner.nextLine();
        manajemenAlat.hapusAlat(id);
    }
    
    private static void menuManajemenUser() {
        while (true) {
            System.out.println("\n=== MANAJEMEN USER ===");
            System.out.println("1. Tambah User");
            System.out.println("2. Edit User");
            System.out.println("3. Hapus User");
            System.out.println("4. Lihat Daftar User");
            System.out.println("5. Kembali");
            System.out.print("Pilihan : ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    tambahUser();
                    break;
                case 2:
                    editUser();
                    break;
                case 3:
                    hapusUser();
                    break;
                case 4:
                    manajemenUser.lihatData();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan Tidak Valid!");
            }
        }
    }
    
    private static void tambahUser() {
        System.out.print("ID User          : ");
        String id = scanner.nextLine();
        System.out.print("Nama             : ");
        String nama = scanner.nextLine();
        System.out.print("Role(Admin/User) : ");
        String role = scanner.nextLine();
        System.out.print("Email            : ");
        String email = scanner.nextLine();
        
        // Memeriksa Apakah Email Sudah Terdaftar/Belum
        if (manajemenUser.isEmailExists(email)) {
            System.out.println("Email Sudah Terdaftar! Silakan Gunakan Email Lain.");
            return;
        }
        
        System.out.print("Password         : ");
        String password = scanner.nextLine();
        
        manajemenUser.tambahUser(new User(id, nama, role, email, password));
        System.out.println("User Telah Berhasil Ditambahkan!");
    }
    
    private static void editUser() {
        System.out.print("Masukkan ID User Yang Akan Diedit : ");
        String id = scanner.nextLine();
        
        User user = manajemenUser.cariUser(id);
        if (user == null) {
            System.out.println("User Tidak Ditemukan!");
            return;
        }
        
        System.out.print("Nama Baru (" + user.getNama() + "): ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) user.setNama(nama);
        
        System.out.print("Role Baru (" + user.getRole() + "): ");
        String role = scanner.nextLine();
        if (!role.isEmpty()) user.setRole(role);
        
        System.out.print("Email Baru (" + user.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) user.setEmail(email);
        
        System.out.print("Password Baru : ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) user.setPassword(password);
        
        System.out.println("Data User Telah Berhasil Diupdate!");
    }
    
    private static void hapusUser() {
        System.out.print("Masukkan ID User Yang Akan Dihapus : ");
        String id = scanner.nextLine();
        manajemenUser.hapusUser(id);
    }
    
    private static void menuTransaksi() {
        while (true) {
            System.out.println("\n=== MENU TRANSAKSI ===");
            System.out.println("1. Pembelian Alat Musik");
            System.out.println("2. Peminjaman Alat Musik");
            System.out.println("3. Pengembalian Alat Musik");
            System.out.println("4. Lihat Semua Transaksi");
            System.out.println("5. Kembali");
            System.out.print("Pilihan : ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    pembelianAlatMusik();
                    break;
                case 2:
                    peminjamanAlatMusik();
                    break;
                case 3:
                    pengembalianAlatMusik();
                    break;
                case 4:
                    laporanTransaksi();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan Tidak Valid!");
            }
        }
    }
    
    private static void pembelianAlatMusik() {
        // Daftar Instrument Yang Tersedia
        manajemenAlat.lihatData();
        
        System.out.print("ID Alat Musik : ");
        String idAlat = scanner.nextLine();
        
        AlatMusik alat = manajemenAlat.cariAlat(idAlat);
        if (alat == null) {
            System.out.println("Alat Musik Tidak Ditemukan!");
            return;
        }
        
        System.out.print("Jumlah : ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
        
        Pembelian pembelian = new Pembelian(alat, jumlah);
        pembelian.prosesPembelian();
        daftarTransaksi.add(pembelian);
        
        // Update Stok
        alat.setStok(alat.getStok() - jumlah);
        
        System.out.println("Pembelian Anda Telah Berhasil!");
    }
    
    private static void peminjamanAlatMusik() {
        // Daftar Instrument Yang Tersedia
        manajemenAlat.lihatData();
    
        // Input Instrument ID
        System.out.print("\nID Alat Musik Yang Akan Dipinjam : ");
        String idAlat = scanner.nextLine();
    
        // Menentukan Instrument Tersedia/Tidak
        AlatMusik alat = manajemenAlat.cariAlat(idAlat);
            if (alat == null) {
            System.out.println("Alat Musik Tidak Ditemukan!");
            return;
        }
    
        // Memeriksa Ketersediaan Instrument
        System.out.println("\nInformasi Alat :");
        System.out.println("Nama                : " + alat.getNama());
        System.out.println("Stok Tersedia       : " + alat.getStok());
        System.out.println("Harga Sewa Per Hari : Rp" + alat.getHarga() * 0.05);
    
        if (alat.getStok() <= 0) {
            System.out.println("Alat Musik Tidak Tersedia!");
            return;
        }
    
        // Input Detail Deskripsi Peminjaman
        System.out.print("\nJumlah Yang Akan Dipinjam (Maximal " + alat.getStok() + ") : ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
    
        if (jumlah <= 0 || jumlah > alat.getStok()) {
            System.out.println("Jumlah Tidak Valid!");
            return;
        }
    
        System.out.print("Lama Peminjaman (Hari) : ");
        int hari = scanner.nextInt();
        scanner.nextLine();
    
        if (hari <= 0) {
        System.out.println("Durasi Peminjaman Harus Lebih Dari 0 Hari!");
        return;
        }

        // Hitung Harga Sewa (5% Dari Harga Barang Per Hari Per Barang)
        double hargaPerHari = alat.getHarga() * 0.05;
        double totalHarga = jumlah * hari * hargaPerHari;

        // Deskripsi Rincian Peminjaman
        System.out.println("\n=== RINCIAN PEMINJAMAN ===");
        System.out.println("Alat Musik          : " + alat.getNama());
        System.out.println("Jumlah              : " + jumlah);
        System.out.println("Harga Sewa Per Hari : Rp" + hargaPerHari);
        System.out.println("Lama Peminjaman     : " + hari + " Hari");
        System.out.println("Total Harga         : Rp" + totalHarga);

        // Konfirmasi Peminjaman
        System.out.print("\nKonfirmasi Peminjaman (Y/N)? ");
        String konfirmasi = scanner.nextLine();
    
        if (konfirmasi.equalsIgnoreCase("y")) {
            // Membuat Transaksi Peminjaman
            Peminjaman peminjaman = new Peminjaman(userLogin, alat, hari, jumlah, hargaPerHari);
            peminjaman.prosesPeminjaman();
            daftarTransaksi.add(peminjaman);
        
            // Update Stok
            alat.setStok(alat.getStok() - jumlah);
        
            System.out.println("\nPeminjaman Sudah Berhasil!");
            System.out.println("ID Transaksi : " + peminjaman.getId());
        } else {
                System.out.println("Peminjaman Dibatalkan");
        }
    }

    private static void pengembalianAlatMusik() {
        System.out.println("\n=== DAFTAR PEMINJAMAN AKTIF ===");
        boolean adaPeminjaman = false;
        
        for (Transaksi t : daftarTransaksi) {
            if (t instanceof Peminjaman && t.getStatus().equals("Aktif")) {
                Peminjaman p = (Peminjaman) t;
                if (p.getPeminjam().getUserId().equals(userLogin.getUserId())) {
                    System.out.println("ID    : " + p.getId() + 
                                    " Alat    : " + p.getAlat().getNama() + 
                                    " (x" + p.getJumlah() + ")" +
                                    " Kembali : " + p.getFormattedTglKembali());
                    adaPeminjaman = true;
                }
            }
        }
        
        if (!adaPeminjaman) {
            System.out.println("Tidak Ada Peminjaman Aktif!");
            return;
        }
        
        System.out.print("\nID Peminjaman Yang Akan Dikembalikan : ");
        String id = scanner.nextLine();
        
        for (Transaksi t : daftarTransaksi) {
            if (t instanceof Peminjaman && t.getId().equals(id) && t.getStatus().equals("Aktif")) {
                Peminjaman p = (Peminjaman) t;
                
                // Proses Pengembalian
                Pengembalian pengembalian = new Pengembalian(p);
                pengembalian.prosesPengembalian();
                
                // Input Data Alat Musik Kembali
                p.getAlat().setStok(p.getAlat().getStok() + p.getJumlah());
                
                System.out.println("\nPengembalian Sudah Berhasil!");
                System.out.println("Alat : " + p.getAlat().getNama() + " (x" + p.getJumlah() + ")");
                
                if (pengembalian.getDenda() > 0) {
                    System.out.println("Denda Keterlambatan : Rp" + pengembalian.getDenda());
                }
                
                return;
            }
        }
        System.out.println("Peminjaman Tidak Ditemukan Atau Sudah Dikembalikan!");
    }
    
    private static void menuLaporan() {
        while (true) {
            System.out.println("\n=== MENU LAPORAN ===");
            System.out.println("1. Laporan Stok Alat Musik");
            System.out.println("2. Laporan Transaksi");
            System.out.println("3. Laporan User");
            System.out.println("4. Kembali");
            System.out.print("Pilihan : ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    laporanStok();
                    break;
                case 2:
                    laporanTransaksi();
                    break;
                case 3:
                    laporanUser();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan Tidak Valid!");
            }
        }
    }
    
    private static void laporanStok() {
        System.out.println("\n=== LAPORAN STOK ALAT MUSIK ===");
        manajemenAlat.lihatData();
        
        int totalStok = 0;
        for (AlatMusik alat : manajemenAlat.getDaftarAlat()) {
            totalStok += alat.getStok();
        }
        System.out.println("Total Stok : " + totalStok);
    }
    
    private static void laporanTransaksi() {
        System.out.println("\n=== LAPORAN TRANSAKSI ===");

        // Show all transactions
        if (daftarTransaksi.isEmpty()) {
            System.out.println("Belum Ada Transaksi!");
            return;
        }

        System.out.println("\n=== DAFTAR TRANSAKSI ===");
        for (Transaksi t : daftarTransaksi) {
            if (t instanceof Pembelian) {
                Pembelian p = (Pembelian) t;
                System.out.println("[PEMBELIAN] ID : " + p.getId() + 
                                 " - Alat   : " + p.getAlat().getNama() + 
                                 " - Jumlah : " + p.getJumlah() + 
                                 " - Total  : Rp" + (p.getAlat().getHarga() * p.getJumlah()));
            } else if (t instanceof Peminjaman) {
                Peminjaman p = (Peminjaman) t;
                System.out.println("[PEMINJAMAN] ID : " + p.getId() + 
                                 " - Peminjam : " + p.getPeminjam().getNama() + 
                                 " - Alat     : " + p.getAlat().getNama() + 
                                 " - Jumlah   : " + p.getJumlah() +
                                 " - Lama     : " + p.getLamaHari() + " Hari" +
                                 " - Status   : " + p.getStatus());
            }
        }

        // Kalkulasi Total
        int totalPembelian = 0;
        int totalPeminjaman = 0;
        double totalPendapatanPembelian = 0;
        double totalPendapatanPeminjaman = 0;

        for (Transaksi t : daftarTransaksi) {
            if (t instanceof Pembelian) {
                totalPembelian++;
                Pembelian p = (Pembelian) t;
                totalPendapatanPembelian += p.getAlat().getHarga() * p.getJumlah();
            } else if (t instanceof Peminjaman) {
                totalPeminjaman++;
                Peminjaman p = (Peminjaman) t;
                totalPendapatanPeminjaman += p.hitungTotalHarga();
            }
        }

        System.out.println("\n=== RINGKASAN ===");
        System.out.println("Total Pembelian             : " + totalPembelian);
        System.out.println("Total Peminjaman            : " + totalPeminjaman);
        System.out.println("Total Pendapatan Pembelian  : Rp" + totalPendapatanPembelian);
        System.out.println("Total Pendapatan Peminjaman : Rp" + totalPendapatanPeminjaman);
        System.out.println("Total Pendapatan Akhir      : Rp" + (totalPendapatanPembelian + totalPendapatanPeminjaman));
    }
    
    private static void laporanUser() {
        System.out.println("\n=== LAPORAN USER ===");
        manajemenUser.lihatData();
        
        int totalAdmin = 0;
        int totalMember = 0;
        
        for (User user : manajemenUser.getDaftarUser()) {
            if (user.getRole().equalsIgnoreCase("Admin")) {
                totalAdmin++;
            } else {
                totalMember++;
            }
        }
        
        System.out.println("Total Admin  : " + totalAdmin);
        System.out.println("Total Member : " + totalMember);
    }
}