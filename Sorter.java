public class Sorter {

    private static int iterationCount; // Variabel untuk menyimpan jumlah iterasi

    public static void insertionSort(Mahasiswa[] arr, String key, boolean ascending) {
        iterationCount = 0; // Reset counter
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Mahasiswa keyMahasiswa = arr[i];
            int j = i - 1;

            while (j >= 0 && compareMahasiswa(arr[j], keyMahasiswa, key, ascending)) {
                arr[j + 1] = arr[j];
                j--;
                iterationCount++; // Hitung iterasi
            }
            arr[j + 1] = keyMahasiswa;
            iterationCount++; // Hitung iterasi
        }
    }

    public static void selectionSort(Mahasiswa[] arr, String key, boolean ascending) {
        iterationCount = 0; // Reset counter
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int idx = i;

            for (int j = i + 1; j < n; j++) {
                iterationCount++; // Hitung iterasi
                if (compareMahasiswa(arr[idx], arr[j], key, !ascending)) {
                    idx = j;
                }
            }

            Mahasiswa temp = arr[idx];
            arr[idx] = arr[i];
            arr[i] = temp;
        }
    }

    private static boolean compareMahasiswa(Mahasiswa mhs1, Mahasiswa mhs2, String key, boolean ascending) {
        int comparisonResult = 0;

        switch (key) {
            case "NIM" -> comparisonResult = mhs1.getNIM().compareTo(mhs2.getNIM());
            case "Nama" -> comparisonResult = mhs1.getNama().compareTo(mhs2.getNama());
            case "IPK" -> comparisonResult = Double.compare(mhs1.getIPK(), mhs2.getIPK());
        }

        return ascending ? comparisonResult > 0 : comparisonResult < 0;
    }

    public static int getIterationCount() {
        return iterationCount; // Ambil jumlah iterasi
    }
}
