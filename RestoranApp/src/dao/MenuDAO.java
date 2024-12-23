/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DatabaseConnection;
import model.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    public List<Menu> getAllMenu() {
        List<Menu> menuList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT menu.id, menu.nama, menu.kategori_id, menu.harga, menu.stok FROM menu")) {
            while (rs.next()) {
                menuList.add(new Menu(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getInt("kategori_id"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    public void insertMenu(Menu menu) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO menu (nama, kategori_id, harga, stok) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, menu.getNama());
            ps.setInt(2, menu.getKategoriId());
            ps.setDouble(3, menu.getHarga());
            ps.setInt(4, menu.getStok());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMenu(Menu menu) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE menu SET nama = ?, kategori_id = ?, harga = ?, stok = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, menu.getNama());
            ps.setInt(2, menu.getKategoriId());
            ps.setDouble(3, menu.getHarga());
            ps.setInt(4, menu.getStok());
            ps.setInt(5, menu.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM menu WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}