package Kelas;

public class Pesanan {
    public String id_layanan;
    public String nama_layanan;
    public String tgl_pesanan;
    public String jam_pesanan;
    public String alamat_pesanan;
    public String foto_layanan;

    public Pesanan(){

    }

    public Pesanan(String id_layanan, String nama_layanan, String tgl_pesanan, String jam_pesanan, String alamat_pesanan, String foto_layanan) {
        this.id_layanan = id_layanan;
        this.nama_layanan = nama_layanan;
        this.tgl_pesanan = tgl_pesanan;
        this.jam_pesanan = jam_pesanan;
        this.alamat_pesanan = alamat_pesanan;
        this.foto_layanan = foto_layanan;
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

    public String getTgl_pesanan() {
        return tgl_pesanan;
    }

    public void setTgl_pesanan(String tgl_pesanan) {
        this.tgl_pesanan = tgl_pesanan;
    }

    public String getJam_pesanan() {
        return jam_pesanan;
    }

    public void setJam_pesanan(String jam_pesanan) {
        this.jam_pesanan = jam_pesanan;
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
}
