package ProyekRSI;

import java.util.List;

public class LaporanController {
    private DataBase dataBase;

    public LaporanController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String getLaporanProyek(String laporan) {
        System.out.println("Mengambil laporan proyek: " + laporan);
        return dataBase.getLaporan(laporan);
    }

    public List<String> getRecommendations(String keyword) {
        return dataBase.searchProyek(keyword);
    }

    public void showError(String pesan) {
        System.out.println("Error: " + pesan);
        javax.swing.JOptionPane.showMessageDialog(null, pesan);
    }
}