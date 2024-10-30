package ProyekRSI;

public class MainApp {
    public static void main(String[] args) {
        Donatur donatur = new Donatur(1, "Ilman", "ilman@example.com");
        DataBase dataBase = new DataBase();
        LaporanController controller = new LaporanController(dataBase);
        HalamanLaporanPerkembangan halaman = new HalamanLaporanPerkembangan(donatur, controller);
        
        halaman.showUI();
    }
}