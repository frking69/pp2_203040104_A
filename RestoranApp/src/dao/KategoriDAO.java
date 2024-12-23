/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DatabaseConnection;
import model.Kategori;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategoriDAO {
    public List<Kategori> getAllKategori() {
        List<Kategori> kategoriList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM kategori")) {
            while (rs.next()) {
                kategoriList.add(new Kategori(rs.getInt("id"), rs.getString("nama")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategoriList;
    }
    
    public String getKategoriNameById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT nama FROM kategori WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nama");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}