import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program {

    // static Scanner scanner;
    static Connection conn;

    public static void main(String[] args) throws Exception {

        Scanner Input = new Scanner(System.in);
        String pilihanUser;
        boolean isLanjutkan = true;

        String url = "jdbc:mysql://localhost:3306/pegawai";

       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Class Driver ditemukan");
            while (isLanjutkan) {
                System.out.println("==================================");
                System.out.println("========Database Pegawai===========");
                System.out.println("===================================");
                System.out.println("1. Lihat Data ");
                System.out.println("2. Tambah Data ");
                System.out.println("3. Ubah Data ");
                System.out.println("4. Hapus Data ");
                System.out.println("5. Cari Data ");

                System.out.print("\nInputkan Pilihan : ");
                pilihanUser = Input.next();

                switch (pilihanUser) {
                    case "1":
                        lihatdata();
                        break;
                    case "2":
                        tambahdata();
                        break;
                    case "3":
                        ubahdata();
                        break;
                    case "4":
                        hapusdata();
                        break;
                    case "5":
                        caridata();
                        break;
                    default:
                        System.err.println("\nInput anda tidak ditemukan\nSilakan input ulang");
                }

                System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
                pilihanUser = Input.next();
                isLanjutkan = pilihanUser.equalsIgnoreCase("y");
            }
            System.out.println("\nBye.... Selamat Berjumpa Kembali!!!");

        } catch (ClassNotFoundException ex) {
            System.err.println("Driver Error");
            System.exit(0);
        } catch (SQLException e) {
            System.err.println("Tidak berhasil koneksi");
        }
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