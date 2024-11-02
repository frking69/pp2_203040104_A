/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan6;

/**
 *
 * @author 62895
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementApp extends JFrame {
    // Komponen untuk Form Input
    private JTextField titleField, authorField;
    private JTextArea descriptionArea;
    private JRadioButton fictionRadio, nonFictionRadio;
    private JCheckBox hardcoverCheck, digitalCheck;
    private JComboBox<String> genreComboBox;
    private JList<String> languageList;
    private JSlider ratingSlider;
    private JSpinner yearSpinner;
    private DefaultTableModel tableModel;

    public LibraryManagementApp() {
        // Judul aplikasi
        setTitle("Library Management Application");

        // Mengatur menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel Form Input
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));

        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        formPanel.add(authorField);

        formPanel.add(new JLabel("Genre:"));
        genreComboBox = new JComboBox<>(new String[] { "Fantasy", "Science Fiction", "Mystery", "Biography" });
        formPanel.add(genreComboBox);

        formPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(3, 15);
        formPanel.add(new JScrollPane(descriptionArea));

        formPanel.add(new JLabel("Type:"));
        fictionRadio = new JRadioButton("Fiction");
        nonFictionRadio = new JRadioButton("Non-Fiction");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(fictionRadio);
        typeGroup.add(nonFictionRadio);
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(fictionRadio);
        typePanel.add(nonFictionRadio);
        formPanel.add(typePanel);

        formPanel.add(new JLabel("Format:"));
        hardcoverCheck = new JCheckBox("Hardcover");
        digitalCheck = new JCheckBox("Digital");
        JPanel formatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formatPanel.add(hardcoverCheck);
        formatPanel.add(digitalCheck);
        formPanel.add(formatPanel);

        formPanel.add(new JLabel("Languages:"));
        languageList = new JList<>(new String[] { "English", "Spanish", "French", "German" });
        languageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        formPanel.add(new JScrollPane(languageList));

        formPanel.add(new JLabel("Year:"));
        yearSpinner = new JSpinner(new SpinnerNumberModel(2023, 1800, 2023, 1));
        formPanel.add(yearSpinner);

        formPanel.add(new JLabel("Rating:"));
        ratingSlider = new JSlider(0, 10, 5);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        formPanel.add(ratingSlider);

        // Tombol Submit
        JButton submitButton = new JButton("Add Book");
        submitButton.addActionListener(new SubmitButtonListener());
        formPanel.add(submitButton);

        mainPanel.add(formPanel, BorderLayout.WEST);

        // Tabel untuk menampilkan data buku
        String[] columnNames = { "Title", "Author", "Genre", "Type", "Format", "Languages", "Year", "Rating" };
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mengambil nilai dari form input
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = (String) genreComboBox.getSelectedItem();
            String type = fictionRadio.isSelected() ? "Fiction" : "Non-Fiction";

            StringBuilder format = new StringBuilder();
            if (hardcoverCheck.isSelected()) format.append("Hardcover ");
            if (digitalCheck.isSelected()) format.append("Digital");

            String languages = String.join(", ", languageList.getSelectedValuesList());
            int year = (int) yearSpinner.getValue();
            int rating = ratingSlider.getValue();

            // Menambah data ke tabel
            tableModel.addRow(new Object[] { title, author, genre, type, format.toString(), languages, year, rating });

            // Membersihkan form setelah submit
            titleField.setText("");
            authorField.setText("");
            genreComboBox.setSelectedIndex(0);
 
            descriptionArea.setText("");
            hardcoverCheck.setSelected(false);
            digitalCheck.setSelected(false);
            languageList.clearSelection();
            yearSpinner.setValue(2023);
            ratingSlider.setValue(5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementApp::new);
    }
}
