import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MahasiswaGUI extends JFrame {
    private Mahasiswa[] mahasiswa;
    private DefaultTableModel tableModel;
    private JTable table;

    public MahasiswaGUI() {
        // Data dummy awal
        mahasiswa = new Mahasiswa[]{
            new Mahasiswa("235150700111001", "Mursid", 3.5),
            new Mahasiswa("205150407111012", "Kak Gem", 3.8),
            new Mahasiswa("215150700111002", "Kak Kev", 3.2),
            new Mahasiswa("235150700111050", "Beyonce", 3.7),
            new Mahasiswa("225150401111010", "Taylor Swift", 3.4)
        };

        setTitle("Pengurutan Data Mahasiswa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Tabel
        tableModel = new DefaultTableModel(new String[]{"NIM", "Nama", "IPK"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        loadTableData();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Kontrol (Filter dan Sorting)
        JPanel controlPanel = new JPanel(new GridLayout(3, 3, 10, 10));

        JComboBox<String> keyComboBox = new JComboBox<>(new String[]{"NIM", "Nama", "IPK"});
        JComboBox<String> orderComboBox = new JComboBox<>(new String[]{"Ascending", "Descending"});
        JComboBox<String> algorithmComboBox = new JComboBox<>(new String[]{"Insertion Sort", "Selection Sort"});
        JButton sortButton = new JButton("Sort");

        controlPanel.add(new JLabel("Urutkan Berdasarkan:"));
        controlPanel.add(keyComboBox);
        controlPanel.add(orderComboBox);
        controlPanel.add(new JLabel("Pilih Algoritma:"));
        controlPanel.add(algorithmComboBox);
        controlPanel.add(sortButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Panel Tambah Data
        JPanel inputPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        JTextField nimField = new JTextField();
        JTextField namaField = new JTextField();
        JTextField ipkField = new JTextField();
        JButton addButton = new JButton("Tambah Data");

        inputPanel.add(new JLabel("NIM:"));
        inputPanel.add(nimField);
        inputPanel.add(new JLabel("Nama:"));
        inputPanel.add(namaField);
        inputPanel.add(new JLabel("IPK:"));
        inputPanel.add(ipkField);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Action Listener untuk Sorting
        sortButton.addActionListener(e -> {
            String key = (String) keyComboBox.getSelectedItem();
            boolean ascending = "Ascending".equals(orderComboBox.getSelectedItem());
            String algorithm = (String) algorithmComboBox.getSelectedItem();

            if ("Insertion Sort".equals(algorithm)) {
                Sorter.insertionSort(mahasiswa, key, ascending);
            } else if ("Selection Sort".equals(algorithm)) {
                Sorter.selectionSort(mahasiswa, key, ascending);
            }

            loadTableData();
        });

        // Action Listener untuk Menambahkan Data
        addButton.addActionListener(e -> {
            try {
                String nim = nimField.getText().trim();
                String nama = namaField.getText().trim();
                double ipk = Double.parseDouble(ipkField.getText().trim());

                if (nim.isEmpty() || nama.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "NIM dan Nama tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Mahasiswa newMahasiswa = new Mahasiswa(nim, nama, ipk);
                mahasiswa = addMahasiswa(mahasiswa, newMahasiswa);

                loadTableData();
                nimField.setText("");
                namaField.setText("");
                ipkField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IPK harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        for (Mahasiswa mhs : mahasiswa) {
            tableModel.addRow(new Object[]{mhs.getNIM(), mhs.getNama(), mhs.getIPK()});
        }
    }

    private Mahasiswa[] addMahasiswa(Mahasiswa[] mahasiswa, Mahasiswa newMahasiswa) {
        Mahasiswa[] newArr = new Mahasiswa[mahasiswa.length + 1];
        System.arraycopy(mahasiswa, 0, newArr, 0, mahasiswa.length);
        newArr[mahasiswa.length] = newMahasiswa;
        return newArr;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaGUI gui = new MahasiswaGUI();
            gui.setVisible(true);
        });
    }
}
