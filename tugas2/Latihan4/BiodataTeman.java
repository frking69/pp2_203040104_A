public class FormPendaftaranNasabah extends JFrame implements ActionListener {

private JTextField namaField;
private JList<String> jenisTabunganList;
private DefaultListModel<String> jenisTabunganModel;
private JSpinner frekuensiSpinner;
private JPasswordField passwordField, confirmPasswordField;
private JSpinner tanggalLahirSpinner;
private JTextArea outputArea;
private JMenuBar menuBar;
private JMenu menuFile;
private JMenuItem menuReset, menuExit;
private JButton simpanButton;

public FormPendaftaranNasabah() {
    super("Form Pendaftaran Nasabah Bank");
    setSize(450, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(9, 2)); // Atur layout grid

    // Inisialisasi komponen
    JLabel namaLabel = new JLabel("Nama:");
    namaField = new JTextField(20);
    JLabel jenisTabunganLabel = new JLabel("Jenis Tabungan:");
    jenisTabunganModel = new DefaultListModel<>();
    jenisTabunganModel.addElement("Tabungan Simpel");
    jenisTabunganModel.addElement("Tabungan Giro");
    jenisTabunganModel.addElement("Tabungan Berjangka");
    jenisTabunganModel.addElement("Tabungan Valas");
    jenisTabunganList = new JList<>(jenisTabunganModel);
    JLabel frekuensiLabel = new JLabel("Frekuensi Transaksi/Bulan:");
    frekuensiSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    JLabel passwordLabel = new JLabel("Password:");
    passwordField = new JPasswordField();
    JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
    confirmPasswordField = new JPasswordField();
    JLabel tanggalLahirLabel = new JLabel("Tanggal Lahir:");
    tanggalLahirSpinner = new JSpinner(new SpinnerDateModel());
    outputArea = new JTextArea(10, 20);
    outputArea.setEditable(false);
    outputArea.setLineWrap(true);
    simpanButton = new JButton("Simpan");
    simpanButton.addActionListener(this);

    // Inisialisasi menu bar
    menuBar = new JMenuBar();
    menuFile = new JMenu("File");
    menuReset = new JMenuItem("Reset");
    menuExit = new JMenuItem("Exit");
    menuReset.addActionListener(this);
    menuExit.addActionListener(this);
    menuFile.add(menuReset);
    menuFile.add(menuExit);
    menuBar.add(menuFile);

    // Atur layout dan tambahkan komponen ke frame
    add(namaLabel);
    add(namaField);
    add(jenisTabunganLabel);
    add(new JScrollPane(jenisTabunganList));
    add(frekuensiLabel);
    add(frekuensiSpinner);
    add(passwordLabel);
    add(passwordField);
    add(confirmPasswordLabel);
    add(confirmPasswordField);
    add(tanggalLahirLabel);
    add(tanggalLahirSpinner);
    add(new JLabel("")); // Tambah spasi kosong untuk mengatur layout
    add(simpanButton);
    add(new JScrollPane(outputArea));

    // Atur menu bar
    setJMenuBar(menuBar);
    setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == menuReset) {
        resetForm();
    } else if (e.getSource() == menuExit) {
        System.exit(0);
    } else if (e.getSource() == simpanButton) {
        simpanData();
    }
}

private void resetForm() {
    namaField.setText("");
    jenisTabunganList.clearSelection();
    frekuensiSpinner.setValue(1);
    passwordField.setText("");
    confirmPasswordField.setText("");
    tanggalLahirSpinner.setValue(new Date());
    outputArea.setText("");
}

private void simpanData() {
    String nama = namaField.getText();
    String jenisTabungan = (String) jenisTabunganList.getSelectedValue();
    int frekuensi = (Integer) frekuensiSpinner.getValue();
    String password = new String(passwordField.getPassword());
    String confirmPassword = new String(confirmPasswordField.getPassword());
    Date tanggalLahir = (Date) tanggalLahirSpinner.getValue();

    if (nama.isEmpty() || jenisTabungan == null || password.isEmpty() || confirmPassword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Silahkan masukkan semua data.");
        return;
    }

    if (!password.equals(confirmPassword)) {
        outputArea.setText("Password dan Confirm Password tidak cocok.");
        return;
    }

    // Format tanggal lahir
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(tanggalLahir);
    String formattedTanggalLahir = String.format("%02d/%02d/%04d", 
        calendar.get(Calendar.DAY_OF_MONTH), 
        calendar.get(Calendar.MONTH) + 1, 
        calendar.get(Calendar.YEAR));

    // Tampilkan data ke textarea
    outputArea.append("Nama: " + nama + "\n");
    outputArea.append("Jenis Tabungan: " + jenisTabungan + "\n");
    outputArea.append("Frekuensi Transaksi/Bulan: " + frekuensi + "\n");
    outputArea.append("Tanggal Lahir: " + formattedTanggalLahir + "\n");
    outputArea.append("Password Cocok!" + "\n");

    resetForm();
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new FormPendaftaranNasabah());
}
}