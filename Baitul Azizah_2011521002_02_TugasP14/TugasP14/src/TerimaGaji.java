import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
public class TerimaGaji extends Gaji {
    Integer hariMasuk = 0;
    Integer potongan = 0;
    Integer totalGaji = 0;
    Integer gajipokok = 0;
    Integer jabatan;
    Integer terimaGaji;
    static Connection conn;
    @Override
    public void Jabatan() {
        
            System.out.println("Pilihan jabatan ");
            System.out.println("    1. Direktur");
            System.out.println("    2. Manajer");
            System.out.println("    3. Supervisor");
            System.out.println("    4. Karyawan");
            System.out.print("Masukkan Jabatan  : ");
            jabatan = input.nextInt();
        //System.out.println("....................");

    }

    @Override
    public void GajiPokok() {

        switch (jabatan) {

            case 1: {
                gajipokok = 30000000;
                System.out.println("Gaji Pokok        : " + this.gajipokok);
                break;
            }

            case 2: {
                gajipokok = 15000000;
                System.out.println("Gaji Pokok        : " + this.gajipokok);
                break;
            }

            case 3 : {
                gajipokok = 10000000;
                System.out.println("Gaji Pokok        : " + this.gajipokok);
                break;
            }

            case 4: {
                gajipokok = 5000000;
                System.out.println("Gaji Pokok        : " + this.gajipokok);
                break;
            }

            default:
                System.out.println("Gaji pokok untuk jabatan yang diinputkan tidak tersedia");

        }

    }

    @Override
    public void JumlahHariMasuk() {
        if (jabatan >= 1 && jabatan <= 4) {
            System.out.print("Jumlah Hari Masuk : ");
            hariMasuk = input.nextInt();
        } else {
            System.out.println("Jumlah Hari Masuk Tidak tersedia");
        }

    }

    @Override
    public void Potongan() {
        if (jabatan >= 1 && jabatan <= 4) {
            potongan = ((30 - hariMasuk) * 50000);
            System.out.println("Potongan          : " + potongan);
        } else {
            System.out.println("Potongan Tidak tersedia");
        }

    }

    @Override
    public void TotalGaji() {
        if (jabatan >= 1 && jabatan <= 4) {
            totalGaji = gajipokok - potongan;
            System.out.println("Total Gaji        : " + totalGaji);
        } else {
            System.out.println("Total Gaji Tidak tersedia");
        }

    }

    public void terimaGaji() {

        terimaGaji = totalGaji;
    }

    public static void lihatdata() throws SQLException {
        String text1 = "\nTampilkan seluruh data pegawai";
        System.out.println(text1.toUpperCase());

        String sql = "SELECT * FROM db_pegawai";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {

            System.out.print("\nNo Pegawai\t: ");
            System.out.print(result.getInt("Nopegawai"));
            System.out.print("\nNama      \t: ");
            System.out.print(result.getString("Nama"));
            System.out.print("\nJabatan   \t: ");
            System.out.print(result.getString("Jabatan"));
            System.out.print("\nGaji Pokok\t: ");
            System.out.print(result.getInt("GajiPokok"));
            System.out.print("\nHari Masuk\t: ");
            System.out.print(result.getInt("HariMasuk"));
            System.out.print("\nPotongan  \t: ");
            System.out.print(result.getInt("Potongan"));
            System.out.print("\nTotal Gaji\t: ");
            System.out.print(result.getInt("Totalgaji"));
            System.out.print("\n");
        }
    }

    public static void tambahdata() throws SQLException {
        String text2 = "\n===Tambah Data Pegawai===";
        System.out.println(text2.toUpperCase());

        Scanner terimaInput = new Scanner(System.in);

        try {
            System.out.print("No Pegawai\t: ");
            int Nopegawai = terimaInput.nextInt();
            System.out.print("Nama      \t: ");
            String Nama = terimaInput.next();
            System.out.print("Jabatan   \t: ");
            String Jabatan = terimaInput.next();
            System.out.print("Gaji Pokok\t: ");
            int GajiPokok = terimaInput.nextInt();
            System.out.print("Hari Masuk\t: ");
            int HariMasuk = terimaInput.nextInt();
            System.out.print("Potongan  \t: ");
            int Potongan = terimaInput.nextInt();
            System.out.print("Total Gaji\t: ");
            int Totalgaji = terimaInput.nextInt();

           
            String sql = "INSERT INTO db_pegawai (Nopegawai, Nama, Jabatan, GajiPokok, HariMasuk, Potongan, Totalgaji) VALUES ('" + Nopegawai + "','" + Nama + "','" + Jabatan
            + "', '" + GajiPokok + "','" + HariMasuk + "', '" + Potongan + "', '" + Totalgaji + "')";

            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data");

        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan input data");
        } catch (InputMismatchException e) {
            System.err.println("Inputlah dengan angka saja");
        }
    }

    public static void ubahdata() throws SQLException {
        String text3 = "\n===Ubah Data Pegawai===";
        System.out.println(text3.toUpperCase());

        Scanner terimaInput = new Scanner(System.in);

        try {
            lihatdata();
            System.out.print("Masukkan No. Pegawai yang akan di ubah atau update : ");
            Integer Nopegawai = Integer.parseInt(terimaInput.nextLine());

            String sql = "SELECT * FROM db_pegawai WHERE Nopegawai = " + Nopegawai;

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {

                System.out.print("Nama " + result.getString("Nama") + "       \t: ");
                String Nama = terimaInput.nextLine();

                System.out.print("Jabatan [" + result.getString("Jabatan") + "]     \t: ");
                String Jabatan = terimaInput.nextLine();

                System.out.print("Gaji Pokok [" + result.getInt("GajiPokok") + "]\t: ");
                Integer GajiPokok = terimaInput.nextInt();

                System.out.print("Hari Masuk [" + result.getInt("HariMasuk") + "]   \t: ");
                Integer HariMasuk = terimaInput.nextInt();

                System.out.print("Potongan [" + result.getInt("Potongan") + "]      \t: ");
                Integer Potongan = terimaInput.nextInt();

                System.out.print("Total Gaji [" + result.getInt("Totalgaji") + "]\t: ");
                Integer Totalgaji = terimaInput.nextInt(); 


                sql = "UPDATE db_pegawai SET Nama ='" + Nama + "',Jabatan='" + Jabatan + "',GajiPokok='" + GajiPokok + "',HariMasuk='" + HariMasuk + "',Potongan= '" + Potongan + "',Totalgaji='" + Totalgaji + "'";


               
                if (statement.executeUpdate(sql) > 0) {
                    System.out.println("Berhasil memperbaharui data pegawai (No. Pegawai " + Nopegawai+ ")");
                }
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
    }

    public static void hapusdata() {
        String text4 = "\n===Hapus Data Pegawai===";
        System.out.println(text4.toUpperCase());

        Scanner terimaInput = new Scanner(System.in);

        try {
            lihatdata();
            System.out.print("Ketik No Pegawai  yang akan Anda Hapus : ");
            Integer Nopegawai = Integer.parseInt(terimaInput.nextLine());

            String sql = "DELETE FROM db_pegawai WHERE Nopegawai = " + Nopegawai;
            Statement statement = conn.createStatement();
            // ResultSet result = statement.executeQuery(sql);

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("Berhasil menghapus data pegawai (No. Pegawai " + Nopegawai + ")");
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan dalam menghapus data pegawai");
        }
    }

    public static void caridata() throws SQLException {
        String text5 = "\n===Cari Data Pegawai===";
        System.out.println(text5.toUpperCase());

        Scanner terimaInput = new Scanner(System.in);

        System.out.print("Masukkan Nama Pegawai : ");

        String keyword = terimaInput.nextLine();
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM db_pegawai WHERE Nama LIKE '%" + keyword + "%'";
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            System.out.print("\nNo Pegawai\t: ");
            System.out.print(result.getInt("Nopegawai"));
            System.out.print("\nNama      \t: ");
            System.out.print(result.getString("Nama"));
            System.out.print("\nJabatan   \t: ");
            System.out.print(result.getString("Jabatan"));
            System.out.print("\nGaji Pokok\t: ");
            System.out.print(result.getInt("GajiPokok"));
            System.out.print("\nHari Masuk\t: ");
            System.out.print(result.getInt("HariMasuk"));
            System.out.print("\nPotongan\t: ");
            System.out.print(result.getInt("Potongan"));
            System.out.print("\nTotal Gaji\t: ");
            System.out.print(result.getInt("Totalgaji"));
           
        }
    }





}
