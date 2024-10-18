import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BiodataTeman extends JFrame implements ActionListener {
    private JTextField namaField, teleponField;
    private JTextArea biodataArea;
    private JButton simpanButton;

    public BiodataTeman() {
        super("Aplikasi Biodata Teman");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // Atur layout

        // Buat komponen
        JLabel namaLabel = new JLabel("Input Nama:");
        namaField = new JTextField(20);
        JLabel teleponLabel = new JLabel("Input Nomor Telepon:");
        teleponField = new JTextField(20);
        simpanButton = new JButton("Simpan");
        simpanButton.addActionListener(this);
        biodataArea = new JTextArea(10, 20);
        biodataArea.setEditable(false);
        biodataArea.setLineWrap(true); // Wrap teks agar tidak keluar dari textarea

        // Tambahkan komponen ke frame
        add(namaLabel);
        add(namaField);
        add(teleponLabel);
        add(teleponField);
        add(simpanButton);
        add(new JScrollPane(biodataArea)); // Gunakan JScrollPane untuk textarea

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simpanButton) {
            String nama = namaField.getText();
            String telepon = teleponField.getText();

            if (!nama.isEmpty() && !telepon.isEmpty()) {
                // Tambah data ke textarea dengan pemisah "-"
                biodataArea.append(nama + " - " + telepon + "\n");
                namaField.setText("");
                teleponField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Silahkan masukkan nama dan nomor telepon.");
            }
        }
    }

    public static void main(String\[] args) {
        SwingUtilities.invokeLater(() -> new BiodataTeman());
    }
}
