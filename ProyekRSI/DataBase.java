package ProyekRSI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataBase {
    private ArrayList<String> dataLaporan;

    public DataBase() {
        dataLaporan = new ArrayList<>();
        dataLaporan.add("Proyek A - Laporan Bulan Januari");
        dataLaporan.add("Proyek B - Laporan Bulan Februari");
        dataLaporan.add("Proyek C - Laporan Bulan Maret");
        dataLaporan.add("Proyek D - Laporan Bulan April");
    }

    public String getLaporan(String laporan) {
        if (dataLaporan.contains(laporan)) {
            System.out.println("Laporan ditemukan: " + laporan);
            return laporan;
        } else {
            return null;
        }
    }

    public List<String> searchProyek(String keyword) {
        return dataLaporan.stream()
                .filter(proyek -> proyek.toLowerCase().startsWith(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}