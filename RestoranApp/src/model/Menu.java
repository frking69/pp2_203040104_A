/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Menu {
    private int id;
    private String nama;
    private int kategoriId;
    private double harga;
    private int stok;

    public Menu(int id, String nama, int kategoriId, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.kategoriId = kategoriId;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }
}