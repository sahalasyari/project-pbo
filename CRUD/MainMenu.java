
package CRUD;
import AnggotaDAO;
import BukuDAO;
import Peminjaman;
import PeminjamanDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BukuDAO bukuDAO = null;
        AnggotaDAO anggotaDAO = null;
        PeminjamanDAO peminjamanDAO = null;

        try {
            bukuDAO = new BukuDAO();
            anggotaDAO = new AnggotaDAO();
            peminjamanDAO = new PeminjamanDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("Menu Utama:");
            System.out.println("1. Manajemen Buku");
            System.out.println("2. Manajemen Anggota");
            System.out.println("3. Transaksi Peminjaman");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageBuku(scanner, bukuDAO);
                    break;
                case 2:
                    manageAnggota(scanner, anggotaDAO);
                    break;
                case 3:
                    managePeminjaman(scanner, peminjamanDAO);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void manageBuku(Scanner scanner, BukuDAO bukuDAO) {
        System.out.println("Manajemen Buku:");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Ubah Buku");
        System.out.println("3. Hapus Buku");
        System.out.println("4. Lihat Buku");
        System.out.print("Pilih menu: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addBuku(scanner, bukuDAO);
                break;
            case 2:
                updateBuku(scanner, bukuDAO);
                break;
            case 3:
                deleteBuku(scanner, bukuDAO);
                break;
            case 4:
                viewBuku(bukuDAO);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void manageAnggota(Scanner scanner, AnggotaDAO anggotaDAO) {
        System.out.println("Manajemen Anggota:");
        System.out.println("1. Tambah Anggota");
        System.out.println("2. Ubah Anggota");
        System.out.println("3. Hapus Anggota");
        System.out.println("4. Lihat Anggota");
        System.out.print("Pilih menu: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addAnggota(scanner, anggotaDAO);
                break;
            case 2:
                updateAnggota(scanner, anggotaDAO);
                break;
            case 3:
                deleteAnggota(scanner, anggotaDAO);
                break;
            case 4:
                viewAnggota(anggotaDAO);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void managePeminjaman(Scanner scanner, PeminjamanDAO peminjamanDAO) {
        System.out.println("Transaksi Peminjaman:");
        System.out.println("1. Peminjaman Buku");
        System.out.println("2. Pengembalian Buku");
        System.out.println("3. Lihat Riwayat Peminjaman");
        System.out.print("Pilih menu: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addPeminjaman(scanner, peminjamanDAO);
                break;
            case 2:
                System.out.println("Pengembalian Buku (belum diimplementasikan)");
                break;
            case 3:
                viewPeminjaman(peminjamanDAO);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void addBuku(Scanner scanner, BukuDAO bukuDAO) {
        System.out.print("Judul: ");
        String judul = scanner.next();
        System.out.print("Penulis: ");
        String penulis = scanner.next();
        System.out.print("Tahun Terbit: ");
        int tahunTerbit = scanner.nextInt();
        System.out.print("Jumlah: ");
        int jumlah = scanner.nextInt();

        Buku buku = new Buku();
        buku.setJudul(judul);
        buku.setPenulis(penulis);
        buku.setTahunTerbit(tahunTerbit);
        buku.setJumlah(jumlah);

        try {
            bukuDAO.addBuku(buku);
            System.out.println("Buku berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateBuku(Scanner scanner, BukuDAO bukuDAO) {
        System.out.print("ID Buku: ");
        int idBuku = scanner.nextInt();
        System.out.print("Judul: ");
        String judul = scanner.next();
        System.out.print("Penulis: ");
        String penulis = scanner.next();
        System.out.print("Tahun Terbit: ");
        int tahunTerbit = scanner.nextInt();
        System.out.print("Jumlah: ");
        int jumlah = scanner.nextInt();

        Buku buku = new Buku();
        buku.setIdBuku(idBuku);
        buku.setJudul(judul);
        buku.setPenulis(penulis);
        buku.setTahunTerbit(tahunTerbit);
        buku.setJumlah(jumlah);

        try {
            bukuDAO.updateBuku(buku);
            System.out.println("Buku berhasil diubah.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBuku(Scanner scanner, BukuDAO bukuDAO) {
        System.out.print("ID Buku: ");
        int idBuku = scanner.nextInt();

        try {
            bukuDAO.deleteBuku(idBuku);
            System.out.println("Buku berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewBuku(BukuDAO bukuDAO) {
        try {
            List<Buku> bukuList = bukuDAO.getAllBuku();
            for (Buku buku : bukuList) {
                System.out.println("ID: " + buku.getIdBuku() + ", Judul: " + buku.getJudul() + ", Penulis: " + buku.getPenulis() + ", Tahun Terbit: " + buku.getTahunTerbit() + ", Jumlah: " + buku.getJumlah());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addAnggota(Scanner scanner, AnggotaDAO anggotaDAO) {
        System.out.print("Nama: ");
        String nama = scanner.next();
        System.out.print("Alamat: ");
        String alamat = scanner.next();
        System.out.print("Nomor Telepon: ");
        String nomorTelepon = scanner.next();

        Anggota anggota = new Anggota();
        anggota.setNama(nama);
        anggota.setAlamat(alamat);
        anggota.setNomorTelepon(nomorTelepon);

        try {
            anggotaDAO.addAnggota(anggota);
            System.out.println("Anggota berhasil ditambahkan.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateAnggota(Scanner scanner, AnggotaDAO anggotaDAO) {
        System.out.print("ID Anggota: ");
        int idAnggota = scanner.nextInt();
        System.out.print("Nama: ");
        String nama = scanner.next();
        System.out.print("Alamat: ");
        String alamat = scanner.next();
        System.out.print("Nomor Telepon: ");
        String nomorTelepon = scanner.next();

        Anggota anggota = new Anggota();
        anggota.setIdAnggota(idAnggota);
        anggota.setNama(nama);
        anggota.setAlamat(alamat);
        anggota.setNomorTelepon(nomorTelepon);

        try {
            anggotaDAO.updateAnggota(anggota);
            System.out.println("Anggota berhasil diubah.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteAnggota(Scanner scanner, AnggotaDAO anggotaDAO) {
        System.out.print("ID Anggota: ");
        int idAnggota = scanner.nextInt();

        try {
            anggotaDAO.deleteAnggota(idAnggota);
            System.out.println("Anggota berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewAnggota(AnggotaDAO anggotaDAO) {
        try {
            List<Anggota> anggotaList = anggotaDAO.getAllAnggota();
            for (Anggota anggota : anggotaList) {
                System.out.println("ID: " + anggota.getIdAnggota() + ", Nama: " + anggota.getNama() + ", Alamat: " + anggota.getAlamat() + ", Nomor Telepon: " + anggota.getNomorTelepon());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addPeminjaman(Scanner scanner, PeminjamanDAO peminjamanDAO) {
        System.out.print("ID Anggota: ");
        int idAnggota = scanner.nextInt();
        System.out.print("ID Buku: ");
        int idBuku = scanner.nextInt();
        System.out.print("Tanggal Pinjam (yyyy-mm-dd): ");
        String tanggalPinjamStr = scanner.next();
        System.out.print("Tanggal Kembali (yyyy-mm-dd): ");
        String tanggalKembaliStr = scanner.next();

        Date tanggalPinjam = Date.valueOf(tanggalPinjamStr);
        Date tanggalKembali = Date.valueOf(tanggalKembaliStr);

        Peminjaman peminjaman = new Peminjaman();
        peminjaman.setIdAnggota(idAnggota);
        peminjaman.setIdBuku(idBuku);
        peminjaman.setTanggalPinjam(tanggalPinjam);
        peminjaman.setTanggalKembali(tanggalKembali);

        try {
            peminjamanDAO.addPeminjaman(peminjaman);
            System.out.println("Peminjaman berhasil dicatat.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewPeminjaman(PeminjamanDAO peminjamanDAO) {
        try {
            List<Peminjaman> peminjamanList = peminjamanDAO.getAllPeminjaman();
            for (Peminjaman peminjaman : peminjamanList) {
                System.out.println("ID: " + peminjaman.getIdPeminjaman() + ", ID Anggota: " + peminjaman.getIdAnggota() + ", ID Buku: " + peminjaman.getIdBuku() + ", Tanggal Pinjam: " + peminjaman.getTanggalPinjam() + ", Tanggal Kembali: " + peminjaman.getTanggalKembali());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
