public class Mahasiswa {
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
