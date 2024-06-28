/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BukuDAO {
    private Connection connection;

    public BukuDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public void addBuku(Buku buku) throws SQLException {
        String sql = "INSERT INTO Buku (judul, penulis, tahun_terbit, jumlah) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, buku.getJudul());
        statement.setString(2, buku.getPenulis());
        statement.setInt(3, buku.getTahunTerbit());
        statement.setInt(4, buku.getJumlah());
        statement.executeUpdate();
    }

    public void updateBuku(Buku buku) throws SQLException {
        String sql = "UPDATE Buku SET judul = ?, penulis = ?, tahun_terbit = ?, jumlah = ? WHERE id_buku = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, buku.getJudul());
        statement.setString(2, buku.getPenulis());
        statement.setInt(3, buku.getTahunTerbit());
        statement.setInt(4, buku.getJumlah());
        statement.setInt(5, buku.getIdBuku());
        statement.executeUpdate();
    }

    public void deleteBuku(int idBuku) throws SQLException {
        String sql = "DELETE FROM Buku WHERE id_buku = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idBuku);
        statement.executeUpdate();
    }

    public List<Buku> getAllBuku() throws SQLException {
        List<Buku> bukuList = new ArrayList<>();
        String sql = "SELECT * FROM Buku";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Buku buku = new Buku();
            buku.setIdBuku(resultSet.getInt("id_buku"));
            buku.setJudul(resultSet.getString("judul"));
            buku.setPenulis(resultSet.getString("penulis"));
            buku.setTahunTerbit(resultSet.getInt("tahun_terbit"));
            buku.setJumlah(resultSet.getInt("jumlah"));
            bukuList.add(buku);
        }

        return bukuList;
    }
}
