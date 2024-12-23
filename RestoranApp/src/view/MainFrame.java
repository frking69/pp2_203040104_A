/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dao.KategoriDAO;
import dao.MenuDAO;
import model.Kategori;
import model.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Kategori> cbKategori;
    private JTextField tfNama, tfHarga, tfStok;
    private JButton btnAdd, btnUpdate, btnDelete;

    private MenuDAO menuDAO = new MenuDAO();
    private KategoriDAO kategoriDAO = new KategoriDAO();

    public MainFrame() {
        setTitle("Restoran Menu Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelInput = new JPanel();
        GroupLayout layout = new GroupLayout(panelInput);
        panelInput.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblNama = new JLabel("Nama Menu:");
        JLabel lblKategori = new JLabel("Kategori:");
        JLabel lblHarga = new JLabel("Harga:");
        JLabel lblStok = new JLabel("Stok:");

        tfNama = new JTextField(20);
        cbKategori = new JComboBox<>();
        tfHarga = new JTextField(20);
        tfStok = new JTextField(20);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblNama)
                    .addComponent(lblKategori)
                    .addComponent(lblHarga)
                    .addComponent(lblStok))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(tfNama)
                    .addComponent(cbKategori)
                    .addComponent(tfHarga)
                    .addComponent(tfStok)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNama)
                    .addComponent(tfNama))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKategori)
                    .addComponent(cbKategori))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHarga)
                    .addComponent(tfHarga))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStok)
                    .addComponent(tfStok))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
        );

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama", "Kategori", "Harga", "Stok"}, 0);
        table = new JTable(tableModel);

        loadKategori();
        loadMenu();

        btnAdd.addActionListener(e -> addMenu());
        btnUpdate.addActionListener(e -> updateMenu());
        btnDelete.addActionListener(e -> deleteMenu());

        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadKategori() {
        List<Kategori> kategoriList = kategoriDAO.getAllKategori();
        for (Kategori kategori : kategoriList) {
            cbKategori.addItem(kategori);
        }
    }

    private void loadMenu() {
        tableModel.setRowCount(0);
        List<Menu> menuList = menuDAO.getAllMenu();
        for (Menu menu : menuList) {
            String kategoriNama = kategoriDAO.getKategoriNameById(menu.getKategoriId());
            tableModel.addRow(new Object[]{
                    menu.getId(),
                    menu.getNama(),
                    kategoriNama,
                    menu.getHarga(),
                    menu.getStok()
            });
        }
    }

    private void addMenu() {
        menuDAO.insertMenu(new Menu(
                0,
                tfNama.getText(),
                ((Kategori) cbKategori.getSelectedItem()).getId(),
                Double.parseDouble(tfHarga.getText()),
                Integer.parseInt(tfStok.getText())
        ));
        loadMenu();
    }

    private void updateMenu() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            menuDAO.updateMenu(new Menu(
                    (int) tableModel.getValueAt(selectedRow, 0),
                    tfNama.getText(),
                    ((Kategori) cbKategori.getSelectedItem()).getId(),
                    Double.parseDouble(tfHarga.getText()),
                    Integer.parseInt(tfStok.getText())
            ));
            loadMenu();
        }
    }

    private void deleteMenu() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            menuDAO.deleteMenu((int) tableModel.getValueAt(selectedRow, 0));
            loadMenu();
        }
    }
}