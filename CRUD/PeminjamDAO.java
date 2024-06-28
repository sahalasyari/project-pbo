
package CRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeminjamanDAO {
    private Connection connection;

    public PeminjamanDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public void addPeminjaman(Peminjaman peminjaman) throws SQLException {
        String sql = "INSERT INTO Peminjaman (id_anggota, id_buku, tanggal_pinjam, tanggal_kembali) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, peminjaman.getIdAnggota());
        statement.setInt(2, peminjaman.getIdBuku());
        statement.setDate(3, peminjaman.getTanggalPinjam());
        statement.setDate(4, peminjaman.getTanggalKembali());
        statement.executeUpdate();
    }

    public List<Peminjaman> getAllPeminjaman() throws SQLException {
        List<Peminjaman> peminjamanList = new ArrayList<>();
        String sql = "SELECT * FROM Peminjaman";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Peminjaman peminjaman = new Peminjaman();
            peminjaman.setIdPeminjaman(resultSet.getInt("id_peminjaman"));
            peminjaman.setIdAnggota(resultSet.getInt("id_anggota"));
            peminjaman.setIdBuku(resultSet.getInt("id_buku"));
            peminjaman.setTanggalPinjam(resultSet.getDate("tanggal_pinjam"));
            peminjaman.setTanggalKembali(resultSet.getDate("tanggal_kembali"));
            peminjamanList.add(peminjaman);
        }

        return peminjamanList;
    }
}
