package ProyekRSI;

public class Donatur {
    private int ID;
    private String nama;
    private String email;

    public Donatur(int ID, String nama, String email) {
        this.ID = ID;
        this.nama = nama;
        this.email = email;
    }

    public void membukaHalamanLaporan() {
        System.out.println("Membuka halaman laporan proyek.");
    }

    public void downloadLaporanFile() {
        System.out.println("Laporan proyek diunduh.");
    }
}
