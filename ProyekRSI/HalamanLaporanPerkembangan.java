package ProyekRSI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HalamanLaporanPerkembangan extends JFrame {
    private Donatur donatur;
    private LaporanController controller;
    private JTextField txtProyek;
    private JButton btnDownload, btnTampilkan;
    private JList<String> proyekList;
    private DefaultListModel<String> listModel;

    public HalamanLaporanPerkembangan(Donatur donatur, LaporanController controller) {
        this.donatur = donatur;
        this.controller = controller;

        setTitle("Laporan Perkembangan Proyek");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Cari Laporan Perkembangan Proyek", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblTitle, BorderLayout.NORTH);

        txtProyek = new JTextField();
        txtProyek.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateList();
            }
        });

        panel.add(txtProyek, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        proyekList = new JList<>(listModel);
        proyekList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        proyekList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                txtProyek.setText(proyekList.getSelectedValue());
            }
        });

        panel.add(new JScrollPane(proyekList), BorderLayout.SOUTH);
        add(panel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        btnTampilkan = new JButton("Tampilkan Laporan");
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String proyekPilihan = txtProyek.getText();
                String laporan = controller.getLaporanProyek(proyekPilihan);
                if (laporan != null) {
                    JOptionPane.showMessageDialog(null, "Laporan: " + laporan);
                    btnDownload.setEnabled(true);
                } else {
                    controller.showError("Maaf, laporan tidak tersedia.");
                    btnDownload.setEnabled(false);
                }
            }
        });
        buttonPanel.add(btnTampilkan);

        btnDownload = new JButton("Unduh Laporan");
        btnDownload.setEnabled(false);
        btnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                donatur.downloadLaporanFile();
                JOptionPane.showMessageDialog(null, "Laporan proyek diunduh.");
            }
        });
        buttonPanel.add(btnDownload);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void showUI() {
        setVisible(true);
    }

    private void updateList() {
        String keyword = txtProyek.getText();
        List<String> results = controller.getRecommendations(keyword);
        listModel.clear();
        for (String proyek : results) {
            listModel.addElement(proyek);
        }
    }
}