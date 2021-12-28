import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
public class Gaji implements PTABC {

    String namaPegawai;
    String noPegawai;
    String alamat;
    Scanner input = new Scanner(System.in);

    @Override
    public void NoPegawai() {
        System.out.print("No Pegawai        : ");
        noPegawai = input.nextLine();

    }

    @Override
    public void NamaPegawai() {
        System.out.print("Nama Pegawai      : ");
        namaPegawai = input.nextLine();

    }

    @Override
    public void Alamat() {
        System.out.print("Alamat Pegawai    : ");
        alamat = input.nextLine();
    };

    public void Jabatan() {

    };

    public void GajiPokok() {

    };

    public void JumlahHariMasuk() {

    };

    public void Potongan() {

    };

    public void TotalGaji() {

    };

}
