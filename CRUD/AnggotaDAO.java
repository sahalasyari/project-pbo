/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnggotaDAO {
    private Connection connection;

    public AnggotaDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public void addAnggota(Anggota anggota) throws SQLException {
        String sql = "INSERT INTO Anggota (nama, alamat, nomor_telepon) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, anggota.getNama());
        statement.setString(2, anggota.getAlamat());
        statement.setString(3, anggota.getNomorTelepon());
        statement.executeUpdate();
    }

    public void updateAnggota(Anggota anggota) throws SQLException {
        String sql = "UPDATE Anggota SET nama = ?, alamat = ?, nomor_telepon = ? WHERE id_anggota = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, anggota.getNama());
        statement.setString(2, anggota.getAlamat());
        statement.setString(3, anggota.getNomorTelepon());
        statement.setInt(4, anggota.getIdAnggota());
        statement.executeUpdate();
    }

    public void deleteAnggota(int idAnggota) throws SQLException {
        String sql = "DELETE FROM Anggota WHERE id_anggota = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idAnggota);
        statement.executeUpdate();
    }

    public List<Anggota> getAllAnggota() throws SQLException {
        List<Anggota> anggotaList = new ArrayList<>();
        String sql = "SELECT * FROM Anggota";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Anggota anggota = new Anggota();
            anggota.setIdAnggota(resultSet.getInt("id_anggota"));
            anggota.setNama(resultSet.getString("nama"));
            anggota.setAlamat(resultSet.getString("alamat"));
            anggota.setNomorTelepon(resultSet.getString("nomor_telepon"));
            anggotaList.add(anggota);
        }

        return anggotaList;
    }
}
