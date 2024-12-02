//  Nama    : Nofa Nisrina Salsabila
//  NIM     : 235150700111005
//  Kelas   : Algoritma Dan Struktur Data (C)

public class Sorter {

    private static int iterasi; 

    // Method sorting Insertion
    public static void insertionSort(Mahasiswa[] arr, String key, boolean ascending) {
        iterasi = 0; // Reset counter
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Mahasiswa keyMahasiswa = arr[i];
            int j = i - 1;

            while (j >= 0 && perbandingan(arr[j], keyMahasiswa, key, ascending)) {
                arr[j + 1] = arr[j];
                j--;
                iterasi++; // Hitung iterasi
            }
            arr[j + 1] = keyMahasiswa;
            iterasi++; // Hitung iterasi
        }
    }

    // Method sorting Selection
    public static void selectionSort(Mahasiswa[] arr, String key, boolean ascending) {
        iterasi = 0; // Reset counter
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int index = i;

            for (int j = i + 1; j < n; j++) {
                iterasi++; // Hitung iterasi
                if (perbandingan(arr[index], arr[j], key, !ascending)) {
                    index = j;
                }
            }

            Mahasiswa temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    private static boolean perbandingan(Mahasiswa mhs1, Mahasiswa mhs2, String key, boolean ascending) {
        int hasil = 0;

        switch (key) {
            case "NIM" -> hasil = mhs1.getNIM().compareTo(mhs2.getNIM());
            case "Nama" -> hasil = mhs1.getNama().compareTo(mhs2.getNama());
            case "IPK" -> hasil = Double.compare(mhs1.getIPK(), mhs2.getIPK());
        }

        return ascending ? hasil > 0 : hasil < 0;
    }

    //Method Get Iterasi
    public static int getIterasi() {
        return iterasi; 
    }
}
