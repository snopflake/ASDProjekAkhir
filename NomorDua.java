import java.util.Scanner;

public class NomorDua {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Data Mahasiswa Dummy
        Mahasiswa[] mahasiswa = {
            new Mahasiswa("12345", "Alice", 3.5),
            new Mahasiswa("54321", "Bob", 3.8),
            new Mahasiswa("11111", "Charlie", 3.2),
            new Mahasiswa("22222", "Diana", 3.7),
            new Mahasiswa("33333", "Edward", 3.4)
        };

        // Menampilkan data mahasiswa sebelum penambahan
        System.out.println("\nData Mahasiswa sebelum penambahan:");
        for (Mahasiswa mhs : mahasiswa) {
            System.out.println(mhs);
        }

        // Input tambahan data mahasiswa dari user
        System.out.println("\nApakah Anda ingin menambahkan data mahasiswa? (y/n)");
        char tambah = scanner.next().charAt(0);
        scanner.nextLine(); // Mengonsumsi newline

        if (tambah == 'y' || tambah == 'Y') {
            System.out.println("Masukkan jumlah mahasiswa yang ingin ditambahkan:");
            int jumlahTambahan = scanner.nextInt();
            scanner.nextLine(); // Mengonsumsi newline

            // Membuat array baru untuk menggabungkan data dummy dan data tambahan
            Mahasiswa[] newMahasiswa = new Mahasiswa[mahasiswa.length + jumlahTambahan];

            // Copy data dummy ke array baru
            System.arraycopy(mahasiswa, 0, newMahasiswa, 0, mahasiswa.length);

            // Input data tambahan
            for (int i = 0; i < jumlahTambahan; i++) {
                System.out.println("Masukkan data mahasiswa ke-" + (i + 1) + ":");
                System.out.print("NIM: ");
                String NIM = scanner.nextLine();
                System.out.print("Nama: ");
                String Nama = scanner.nextLine();
                System.out.print("IPK: ");
                double IPK = scanner.nextDouble();
                scanner.nextLine(); // Mengonsumsi newline

                newMahasiswa[mahasiswa.length + i] = new Mahasiswa(NIM, Nama, IPK);
            }

            // Mengupdate array mahasiswa
            mahasiswa = newMahasiswa;
        }

        // Menampilkan data mahasiswa setelah penambahan
        System.out.println("\nData Mahasiswa setelah penambahan:");
        for (Mahasiswa mhs : mahasiswa) {
            System.out.println(mhs);
        }

        // Memilih jenis algoritma sorting
        System.out.println("\nPilih algoritma sorting yang akan digunakan:");
        System.out.println("(1) Insertion Sort");
        System.out.println("(2) Selection Sort");
        int sortingChoice = scanner.nextInt();

        if (sortingChoice != 1 && sortingChoice != 2) {
            System.out.println("Pilihan tidak valid. Program dihentikan.");
            return;
        }

        // Pilih NIM/Nama/IPK untuk pengurutan
        System.out.println("\nPilih pengurutan berdasarkan:");
        System.out.println("(1) NIM");
        System.out.println("(2) Nama");
        System.out.println("(3) IPK");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 3) {
            System.out.println("Pilihan tidak valid. Program dihentikan.");
            return;
        }

        // Pilih Ascending/Descending
        System.out.println("Pilih urutan pengurutan:");
        System.out.println("(1) Ascending");
        System.out.println("(2) Descending");
        int orderChoice = scanner.nextInt();

        if (orderChoice != 1 && orderChoice != 2) {
            System.out.println("Pilihan tidak valid. Program dihentikan.");
            return;
        }

        // Menentukan apakah Ascending/Descending
        boolean ascending = orderChoice == 1;

        // Menentukan key pengurutan
        String key;
        switch (choice) {
            case 1 -> key = "NIM";
            case 2 -> key = "Nama";
            case 3 -> key = "IPK";
            default -> {
                System.out.println("Pilihan tidak valid. Program dihentikan.");
                return;
            }
        }

        // Memilih dan menjalankan algoritma sorting
        if (sortingChoice == 1) {
            insertionSort(mahasiswa, key, ascending);
        } else {
            selectionSort(mahasiswa, key, ascending);
        }

        // Menampilkan data mahasiswa setelah pengurutan
        System.out.println("\nData Mahasiswa setelah pengurutan berdasarkan " + key + " (" + (ascending ? "ascending" : "descending") + "):");
        for (Mahasiswa mhs : mahasiswa) {
            System.out.println(mhs);
        }
    }

    // Method Insertion Sort
    public static void insertionSort(Mahasiswa[] arr, String key, boolean ascending) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Mahasiswa keyMahasiswa = arr[i];
            int j = i - 1;

            while (j >= 0 && compareMahasiswa(arr[j], keyMahasiswa, key, ascending)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = keyMahasiswa;
        }
    }

    // Method Selection Sort
    public static void selectionSort(Mahasiswa[] arr, String key, boolean ascending) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int idx = i;

            for (int j = i + 1; j < n; j++) {
                if (compareMahasiswa(arr[idx], arr[j], key, !ascending)) {
                    idx = j;
                }
            }

            // Menukar elemen
            Mahasiswa temp = arr[idx];
            arr[idx] = arr[i];
            arr[i] = temp;
        }
    }

    // Method Pembanding
    public static boolean compareMahasiswa(Mahasiswa mhs1, Mahasiswa mhs2, String key, boolean ascending) {
        int comparisonResult = 0;

        switch (key) {
            case "NIM" -> comparisonResult = mhs1.getNIM().compareTo(mhs2.getNIM());
            case "Nama" -> comparisonResult = mhs1.getNama().compareTo(mhs2.getNama());
            case "IPK" -> comparisonResult = Double.compare(mhs1.getIPK(), mhs2.getIPK());
        }

        return ascending ? comparisonResult > 0 : comparisonResult < 0;
    }

    // Class Mahasiswa
    static class Mahasiswa {
        private String NIM;
        private String Nama;
        private double IPK;

        public Mahasiswa(String NIM, String Nama, double IPK) {
            this.NIM = NIM;
            this.Nama = Nama;
            this.IPK = IPK;
        }

        public String getNIM() {
            return NIM;
        }

        public String getNama() {
            return Nama;
        }

        public double getIPK() {
            return IPK;
        }

        @Override
        public String toString() {
            return "NIM: " + NIM + ", Nama: " + Nama + ", IPK: " + IPK;
        }
    }
}
