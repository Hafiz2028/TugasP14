import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;



public class Program {
    static Connection hub;//inisial scanner
    public static void main(String[] args) throws Exception {
    Scanner inputan = new Scanner (System.in);
    String pilih;
    boolean isLanjutkan=true;

    String url = "jdbc:mysql://localhost:3306/data_pegawai";
    
    DateFormat waktuTanggal =new SimpleDateFormat("dd MMMM yyyy");
    DateFormat waktuDetail =new SimpleDateFormat("hh:mm:ss");
    Date Tanggal =new Date();

    String Pembukaan ="Selamat Datang di Database Pegawai";
    String Informasi ="Silahkan lihat Informasi Gaji dan Jabatan anda";
    String Akhiran="informasi pengaksesan";
    String selesai = "!!program selesai!!";
    System.out.println("------------------------------------------------");
    System.out.println(Pembukaan.toUpperCase());
    System.out.println("");
    System.out.println(Informasi.toLowerCase());
    System.out.println("------------------------------------------------");
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        hub = DriverManager.getConnection(url,"root","");
        System.out.println("Class Driver ditemukan!!");
        Gaji gaji=new Gaji();
        
        while (isLanjutkan){
            System.out.println("------------------------------------------------");
            System.out.println(Pembukaan.toUpperCase());
            System.out.println("");
            System.out.println(Informasi.toLowerCase());
            System.out.println("------------------------------------------------");
            System.out.println("1. MENAMPILKAN Data Pegawai Kantor");
            System.out.println("2. MENAMBAHKAN Data Pegawai Kantor");
            System.out.println("3. MENGUBAH Data Pegawai Kantor");
            System.out.println("4. MENGHAPUS Data Pegawai Kantor");
            System.out.println("5. MENCARI Data Pegawai Kantor");
            System.out.println("\nInputkan Pilihan (1 - 5) : ");
            pilih=inputan.next();
            System.out.println("------------------------------------------------");
            
            switch(pilih){
                case "1":
                gaji.tampildata();break;
                case "2":
                gaji.tambahdata();break;
                case "3":
                gaji.ubahdata();break;
                case "4":
                gaji.hapus();break;
                case "5":
                gaji.caridata();break;
                default:
                System.out.println("Inputan Anda salah");
            }
            System.out.println("\nApakah anda ingin lanjut (y/n) ? ");
            pilih=inputan.next();
            isLanjutkan=pilih.equalsIgnoreCase("y");
        }
        System.out.println("================================================");
        System.out.println(Akhiran.toUpperCase());
        System.out.println("");
        System.out.println("-> Waktu Akses\t\t\t\t: "+waktuDetail.format(Tanggal)+" WIB");
        System.out.println("-> Tanggal Akses\t\t\t: "+waktuTanggal.format(Tanggal));
        System.out.println("================================================");
        System.out.println(selesai.toLowerCase());
    }
    catch(ClassNotFoundException ex){
        System.err.println("Driver ERROR");
        System.exit(0);
    }
    catch(SQLException e){
        System.err.println("Tidak Terhubung dengan Database");
    }
    inputan.close();
}

}
