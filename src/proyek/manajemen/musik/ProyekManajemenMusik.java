package proyek.manajemen.musik;

import java.util.*;

public class ProyekManajemenMusik {
    private static Scanner scanner = new Scanner(System.in);
    private static List<AlatMusik> daftarAlatMusik = new ArrayList<>();
    private static List<Pembelian> daftarPembelian = new ArrayList<>();
    private static List<Peminjaman> daftarPeminjaman = new ArrayList<>();
    private static List<Pengembalian> daftarPengembalian = new ArrayList<>();
    private static List<User> daftarUser = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        // Data awal alat musik
        daftarAlatMusik.add(new AlatMusik("A01", "Gitar Elektrik", "Petik", "Yamaha", "Tersedia", 5, 3_500_000, 100_000));
        daftarAlatMusik.add(new AlatMusik("A02", "Drum", "Pukul", "Pearl", "Tersedia", 2, 15_000_000, 500_000));
        daftarAlatMusik.add(new AlatMusik("A03", "Gitar Akustik", "Petik", "Cort", "Tersedia", 10, 500_000, 50_000));
        daftarAlatMusik.add(new AlatMusik("A04", "Bass", "Petik", "Ibanez", "Tersedia", 4, 3_000_000, 100_000));
        daftarAlatMusik.add(new AlatMusik("A05", "Ampli Gitar", "Elektronik", "Marshall", "Tersedia", 3, 1_500_000, 200_000));

        // Menambahkan akun admin
        daftarUser.add(new User("Lia", "lia123", "admin"));
        daftarUser.add(new User("Naufal", "naufal123", "admin"));
        daftarUser.add(new User("Joel", "joel123", "admin"));

        while (true) {
            System.out.println("\n=== LOGIN / REGISTRASI ===");
            if (login()) {
                boolean isRunning = true;
                while (isRunning) {
                    System.out.println("\n=== MENU UTAMA ===");
                    if (loggedInUser.getRole().equals("admin")) {
                        System.out.println("1. Tambah Alat Musik");
                        System.out.println("2. Lihat Laporan");
                        System.out.println("3. Logout");
                        System.out.println("4. Keluar");
                    } else {
                        System.out.println("1. Pembelian Alat Musik");
                        System.out.println("2. Peminjaman Alat Musik");
                        System.out.println("3. Pengembalian Alat Musik");
                        System.out.println("4. Logout");
                        System.out.println("5. Keluar");
                    }
                    System.out.print("Pilih menu: ");
                    int pilihan = scanner.nextInt();
                    scanner.nextLine();

                    if (loggedInUser.getRole().equals("admin")) {
                        switch (pilihan) {
                            case 1:
                                tambahAlatMusik();
                                break;
                            case 2:
                                tampilkanLaporan();
                                break;
                            case 3:
                                System.out.println("Logout berhasil.");
                                loggedInUser = null;
                                isRunning = false;
                                break;
                            case 4:
                                System.out.println("Terima kasih!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                                break;
                        }
                    } else {
                        switch (pilihan) {
                            case 1:
                                buatPembelian();
                                break;
                            case 2:
                                buatPeminjaman();
                                break;
                            case 3:
                                buatPengembalian();
                                break;
                            case 4:
                                System.out.println("Logout berhasil.");
                                loggedInUser = null;
                                isRunning = false;
                                break;
                            case 5:
                                System.out.println("Terima kasih!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                                break;
                        }
                    }
                }
            }
        }
    }

    private static boolean login() {
        System.out.println("1. Login");
        System.out.println("2. Registrasi");
        System.out.print("Pilih opsi: ");
        int opsi = scanner.nextInt();
        scanner.nextLine();

        if (opsi == 1) {
            return performLogin();
        } else if (opsi == 2) {
            return performRegistration();
        } else {
            System.out.println("Pilihan tidak valid.");
            return false;
        }
    }

    private static boolean performLogin() {
        System.out.print("Masukkan ID: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        if (id.equals("Lia") && password.equals("lia123") ||
            id.equals("Naufal") && password.equals("naufal123") ||
            id.equals("Joel") && password.equals("joel123")) {
            loggedInUser = new User(id, password, "admin");
            System.out.println("Login berhasil sebagai Admin.");
            return true;
        }

        for (User user : daftarUser) {
            if (user.getId().equals(id) && user.isValidLogin(password)) {
                loggedInUser = user;
                System.out.println("Login berhasil sebagai User.");
                return true;
            }
        }

        System.out.println("ID atau password salah.");
        return false;
    }

    private static boolean performRegistration() {
        System.out.print("Masukkan ID untuk registrasi: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Password untuk registrasi: ");
        String password = scanner.nextLine();

        for (User user : daftarUser) {
            if (user.getId().equals(id)) {
                System.out.println("ID sudah terdaftar. Coba ID lain.");
                return false;
            }
        }

        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Role (user): ");
        String role = scanner.nextLine();

        User newUser = new User(id, password, role);
        daftarUser.add(newUser);
        loggedInUser = newUser;
        System.out.println("Registrasi berhasil! Login sebagai User.");
        return true;
    }

    private static void tambahAlatMusik() {
        System.out.print("Masukkan ID alat: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan nama alat: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan stok alat: ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan harga pembelian: ");
        double hargaPembelian = scanner.nextDouble();
        System.out.print("Masukkan harga peminjaman per hari: ");
        double hargaSewa = scanner.nextDouble();
        scanner.nextLine();

        AlatMusik alat = new AlatMusik(id, nama, "Jenis", "Merk", "Tersedia", stok, hargaPembelian, hargaSewa);
        daftarAlatMusik.add(alat);
        System.out.println("Alat musik berhasil ditambahkan!");
    }

    private static void buatPembelian() {
        if (daftarAlatMusik.isEmpty()) {
            System.out.println("Tidak ada alat musik.");
            return;
        }

        List<AlatMusik> pembelian = new ArrayList<>();
        System.out.println("Pilih alat musik yang dibeli:");
        for (int i = 0; i < daftarAlatMusik.size(); i++) {
            AlatMusik alat = daftarAlatMusik.get(i);
            System.out.println((i + 1) + ". " + alat.getNama() + " - Harga: Rp" + alat.getHargaPembelian() + " - Stok: " + alat.getStok());
        }

        int pilih = scanner.nextInt();
        scanner.nextLine();

        if (pilih < 1 || pilih > daftarAlatMusik.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        AlatMusik alatDipilih = daftarAlatMusik.get(pilih - 1);

        System.out.print("Masukkan jumlah yang ingin dibeli: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        if (jumlah <= 0 || jumlah > alatDipilih.getStok()) {
            System.out.println("Jumlah tidak valid atau stok tidak cukup.");
            return;
        }

        double total = alatDipilih.getHargaPembelian() * jumlah;
        alatDipilih.setStok(alatDipilih.getStok() - jumlah);

        for (int i = 0; i < jumlah; i++) {
            pembelian.add(alatDipilih);
        }

        Pembelian pb = new Pembelian("PB" + (daftarPembelian.size() + 1), new Date(), pembelian, total);
        daftarPembelian.add(pb);

        System.out.println("Total harga: Rp" + total);
        System.out.println("Pembelian berhasil!");

        cetakNotaPembelian(pb);
    }

    private static void buatPeminjaman() {
    if (daftarAlatMusik.isEmpty()) return;

    System.out.println("Pilih alat musik yang ingin dipinjam:");
    for (int i = 0; i < daftarAlatMusik.size(); i++) {
        AlatMusik alat = daftarAlatMusik.get(i);
        System.out.println((i + 1) + ". " + alat.getNama() +
                " - Harga Sewa/Hari: Rp" + alat.getHargaPeminjamanPerHari() +
                " - Stok: " + alat.getStok());
    }

    int pilih = scanner.nextInt();
    scanner.nextLine();

    if (pilih < 1 || pilih > daftarAlatMusik.size()) {
        System.out.println("Pilihan tidak valid.");
        return;
    }

    AlatMusik alatDipilih = daftarAlatMusik.get(pilih - 1);

    System.out.print("Masukkan jumlah yang ingin dipinjam: ");
    int jumlah = scanner.nextInt();
    scanner.nextLine();

    if (jumlah <= 0 || jumlah > alatDipilih.getStok()) {
        System.out.println("Jumlah tidak valid atau stok tidak cukup.");
        return;
    }

    System.out.print("Berapa hari ingin dipinjam? ");
    int durasi = scanner.nextInt();
    scanner.nextLine();

    // Buat satu objek peminjaman dengan jumlah yang diminta
    Peminjaman peminjaman = new Peminjaman(
            "PJ" + (daftarPeminjaman.size() + 1),
            loggedInUser,
            alatDipilih,
            new Date(),
            durasi,
            jumlah
    );
    daftarPeminjaman.add(peminjaman);

    alatDipilih.setStok(alatDipilih.getStok() - jumlah);
    System.out.println("Peminjaman " + jumlah + " unit alat berhasil!");

    // Cetak nota
    cetakNotaPeminjaman(peminjaman);
    }

    
    private static void buatPengembalian() {
    if (daftarPeminjaman.isEmpty()) {
        System.out.println("Tidak ada peminjaman yang perlu dikembalikan.");
        return;
    }

    System.out.println("Daftar peminjaman yang perlu dikembalikan:");
    for (int i = 0; i < daftarPeminjaman.size(); i++) {
        Peminjaman peminjaman = daftarPeminjaman.get(i);
        System.out.println((i + 1) + ". " + peminjaman.getAlat().getNama() + " - Peminjaman ID: " + peminjaman.getId());
    }

    System.out.print("Pilih nomor peminjaman yang ingin dikembalikan: ");
    int pilih = scanner.nextInt();
    scanner.nextLine(); // konsumsi newline

    if (pilih < 1 || pilih > daftarPeminjaman.size()) {
        System.out.println("Pilihan tidak valid.");
        return;
    }

    Peminjaman peminjaman = daftarPeminjaman.get(pilih - 1);

    System.out.print("Masukkan jumlah yang dikembalikan: ");
    int jumlah = scanner.nextInt();
    scanner.nextLine(); // konsumsi newline

    if (jumlah <= 0 || jumlah > peminjaman.getJumlah()) {
        System.out.println("Jumlah tidak valid.");
        return;
    }

    // Update jumlah alat yang dipinjam
    peminjaman.setJumlah(peminjaman.getJumlah() - jumlah);

    // Hapus peminjaman jika jumlahnya sudah 0
    if (peminjaman.getJumlah() == 0) {
        daftarPeminjaman.remove(peminjaman);
    }

    // Tambah stok alat
    AlatMusik alatDipinjam = peminjaman.getAlat();
    alatDipinjam.setStok(alatDipinjam.getStok() + jumlah);

    // Input kondisi alat
    System.out.print("Masukkan kondisi alat saat dikembalikan: ");
    String kondisiAlat = scanner.nextLine();

    // Buat objek pengembalian
    Pengembalian pengembalian = new Pengembalian(
        "PG" + (daftarPengembalian.size() + 1),
        peminjaman,
        new Date(),
        kondisiAlat
    );

    // Tambahkan ke daftar pengembalian
    daftarPengembalian.add(pengembalian);

    System.out.println("Pengembalian berhasil dicatat!");
}


    private static void tampilkanLaporan() {
        System.out.println("=== LAPORAN PEMBELIAN ===");
        for (Pembelian pembelian : daftarPembelian) {
            System.out.println("ID: " + pembelian.getId() + ", Tanggal: " + pembelian.getTanggal() + ", Total: Rp" + pembelian.getTotalHarga());
        }

        System.out.println("\n=== LAPORAN PEMINJAMAN ===");
        for (Peminjaman peminjaman : daftarPeminjaman) {
            System.out.println("ID: " + peminjaman.getId() + ", Tanggal: " + peminjaman.getTanggalPinjam() + ", Durasi: " + peminjaman.getDurasi());
        }

        System.out.println("\n=== LAPORAN PENGEMBALIAN ===");
        for (Pengembalian pengembalian : daftarPengembalian) {
            System.out.println("ID: " + pengembalian.getId() + ", Tanggal: " + pengembalian.getTanggalKembali());
        }
    }

    private static void cetakNotaPembelian(Pembelian pembelian) {
        System.out.println("\n=== Nota Pembelian ===");
        for (AlatMusik alat : pembelian.getDaftarAlat()) {
            System.out.println("Alat: " + alat.getNama() + " - Harga: Rp" + alat.getHargaPembelian());
        }
        System.out.println("Total Pembelian: Rp" + pembelian.getTotalHarga());
    }

    private static void cetakNotaPeminjaman(Peminjaman p) {
    System.out.println("\n=== Nota Peminjaman ===");
    System.out.println("Alat: " + p.getAlat().getNama());
    System.out.println("Jumlah: " + p.getJumlah());
    System.out.println("Durasi: " + p.getDurasi() + " hari");
    System.out.println("Tanggal Pinjam: " + p.getTanggalPinjam());
    System.out.println("=======================\n");
    }
}
