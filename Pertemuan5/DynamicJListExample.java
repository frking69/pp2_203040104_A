/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan5;

/**
 *
 * @author 62895
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicJListExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dynamic JList Example");

        // Membuat DefaultListModel
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Dynamic Item 1");
        listModel.addElement("Dynamic Item 2");
        listModel.addElement("Dynamic Item 3");

        // Membuat JList dengan model
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // JScrollPane untuk JList
        JScrollPane scrollPane = new JScrollPane(list);

        // Tombol untuk menambahkan item baru
        JButton addButton = new JButton("Add Item");
        addButton.setBounds(50, 170, 100, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.addElement("New Item");
            }
        });

        // Tombol untuk menghapus item yang dipilih
        JButton removeButton = new JButton("Remove Item");
        removeButton.setBounds(160, 170, 120, 30);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        // ... (Pengaturan layout dan frame - sama seperti contoh sebelumnya)
    }
}
