package Kelas;

public class Pesanan {
    public String id_layanan;
    public String nama_layanan;
    public String waktu_pesanan;
    public String alamat_pesanan;
    public String foto_layanan;
    public String biaya_layanan;
    public String jenis_layanan;
    public String status;

    public Pesanan(){

    }

    public Pesanan(String id_layanan, String nama_layanan, String waktu_pesanan, String alamat_pesanan, String foto_layanan, String status, String biaya_layanan, String jenis_layanan) {
        this.id_layanan = id_layanan;
        this.nama_layanan = nama_layanan;
        this.waktu_pesanan = waktu_pesanan;
        this.alamat_pesanan = alamat_pesanan;
        this.foto_layanan = foto_layanan;
        this.status = status;
        this.biaya_layanan = biaya_layanan;
        this.jenis_layanan = jenis_layanan;
    }

    public String getId_layanan() {
        return id_layanan;
    }

    public void setId_layanan(String id_layanan) {
        this.id_layanan = id_layanan;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }

    public void setNama_layanan(String nama_layanan) {
        this.nama_layanan = nama_layanan;
    }

    public String getAlamat_pesanan() {
        return alamat_pesanan;
    }

    public void setAlamat_pesanan(String alamat_pesanan) {
        this.alamat_pesanan = alamat_pesanan;
    }

    public String getFoto_layanan() {
        return foto_layanan;
    }

    public void setFoto_layanan(String foto_layanan) {
        this.foto_layanan = foto_layanan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBiaya_layanan() {
        return biaya_layanan;
    }

    public void setBiaya_layanan(String biaya_layanan) {
        this.biaya_layanan = biaya_layanan;
    }

    public String getWaktu_pesanan() {
        return waktu_pesanan;
    }

    public void setWaktu_pesanan(String waktu_pesanan) {
        this.waktu_pesanan = waktu_pesanan;
    }

    public String getJenis_layanan() {
        return jenis_layanan;
    }

    public void setJenis_layanan(String jenis_layanan) {
        this.jenis_layanan = jenis_layanan;
    }
}
