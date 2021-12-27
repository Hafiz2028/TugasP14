import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Gaji implements PTABC{
    public Integer noPegawai;
    public String namaPegawai;
    public String Jabatan;
    static Connection hub;
    public Integer gajiPokok=0;
    public Integer jumlahHadir=0;
    public Integer Potongan=0;
    public Integer totalGaji=0;
    public Integer jumlahAbsen;
    String url = "jdbc:mysql://localhost:3306/data_pegawai";

    Scanner inputan = new Scanner (System.in);

    
    public void tampildata() throws SQLException{
        String pembuka="\nDaftar Data Pegawai yang telah diinputkan : ";
        System.out.println(pembuka.toUpperCase());
        String sql = "SELECT * FROM pegawai";
        hub= DriverManager.getConnection(url, "root", "");
        Statement statement = hub.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            System.out.println();
            System.out.println("Nomor Pegawai\t  : "+result.getInt("id_pegawai"));
            System.out.println("Nama pegawai\t  : "+result.getString("nama_pegawai"));
            System.out.println("Jabatan\t\t  : "+result.getString("jabatan"));
            System.out.println("Jumlah hari masuk : "+result.getInt("hari_masuk"));
            System.out.println("Total gaji\t  : "+result.getInt("gaji_total"));
            System.out.println();
        }
    }
    public void tampildata2() throws SQLException{
        String pembuka2="\nDaftar Nama Pegawai yang telah diinputkan : ";
        System.out.println(pembuka2.toUpperCase());
        String sql = "SELECT * FROM pegawai";
        hub= DriverManager.getConnection(url, "root", "");
        Statement statement = hub.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            System.out.println();
            System.out.println("Nomor Pegawai\t  : "+result.getInt("id_pegawai"));
            System.out.println("Nama pegawai\t  : "+result.getString("nama_pegawai"));
            System.out.println();
        }
    } 
    public void tambahdata() throws SQLException{
    String tambah = "Input Data Pegawai";
    System.out.println(tambah.toUpperCase());
    
    try{
    //nopegawai
        System.out.print("Nomor Pegawai\t\t\t\t: ");
        noPegawai=inputan.nextInt();
        inputan.nextLine();
        System.out.print("");

    //namapegawai
    System.out.print("Nama Pegawai\t\t\t\t: ");
        
        namaPegawai=inputan.nextLine();
        System.out.print("");
        
    
    
    //Jabatan
        int pilih;
    System.out.println("Daftar Jabatan = ");
    System.out.println("1. Direktur");
    System.out.println("2. Manager");
    System.out.println("3. Karyawan");
    System.out.println("4. Satpam");
    System.out.print("Masukkan pilihan Jabatan (1-4)\t: ");
    pilih=inputan.nextInt();
    if(pilih==1){
        Jabatan="Direktur";
        
    }else if (pilih==2){
        Jabatan="Manager";
        
    }else if (pilih==3){
        Jabatan="Karyawan";
        
    }else if (pilih==4){
        Jabatan="Satpam";
        
    }else{
        System.out.println("Jabatan tidak tersedia");
    }
    System.out.println("Jabatan \t\t\t\t: "+Jabatan);

    //GajiPokok
        
        if(Jabatan=="Direktur"){
            this.gajiPokok=30000000;
            
            
        }else if (Jabatan=="Manager"){
            this.gajiPokok=15000000;
            
            
        }else if (Jabatan=="Karyawan"){
            this.gajiPokok=10000000;
           
            
        }else if (Jabatan=="Satpam"){
            this.gajiPokok=5000000;
        

        }else{
        System.out.println("Gaji Pokok dari jabatan tidak ditemukan");
        
        }
        System.out.println("Gaji pokok \t\t\t\t: Rp"+gajiPokok);
        
    

        //JumlahHariMasuk
       
        System.out.print("Total Hari masuk (0-30)\t\t\t: ");
        jumlahHadir=inputan.nextInt();
        jumlahAbsen=30-jumlahHadir;
        System.out.print("");
        
        
        

        //Potongan
        Potongan=jumlahAbsen*50000;
        System.out.println("Potongan Gaji berdasarkan Absensi\t: Rp. "+Potongan);
        

        //TotalGaji
        totalGaji=gajiPokok-Potongan;
        System.out.println("Total Gaji (Gaji Pokok - Potongan)\t: Rp. "+totalGaji);
        
        String sql = "INSERT INTO pegawai (id_pegawai, nama_pegawai, jabatan, hari_masuk, gaji_total) VALUES ('"+noPegawai+"','"+namaPegawai+"','"+Jabatan+"','"+jumlahHadir+"','"+totalGaji+"')";
        hub = DriverManager.getConnection(url, "root", "");
        Statement statement = hub.createStatement();
        statement.execute(sql);
        String pengingat="\nData Pegawai berhasil ditambahkan";
        System.out.println(pengingat.toUpperCase());
    }
    catch(SQLException e){
        System.err.println("Kesalahan dalam menginput data");
    }
    catch (InputMismatchException e){
        System.err.println("Tipe data Inputan salah");
        System.exit(0);
        
    }

    }
    public void ubahdata() throws SQLException{
        
        String ubah= "Mengubah Data Pegawai";
        System.out.println(ubah.toUpperCase());
        tampildata();
        try{
        
        System.out.println("Nomor Pegawai yang akan di ubah : ");
        Integer noPegawai= Integer.parseInt(inputan.nextLine());
        
        String sql = "SELECT * FROM pegawai WHERE id_pegawai = "+ noPegawai;
        hub = DriverManager.getConnection(url, "root", "");
        Statement statement = hub.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if(result.next()){
            System.out.println("Nama baru ["+result.getString("nama_pegawai")+"]\t : ");
            String namaPegawai = inputan.nextLine();
            sql = "UPDATE pegawai SET nama_pegawai='"+namaPegawai+"' WHERE id_pegawai='"+noPegawai+"'";
            if(statement.executeUpdate(sql)>0){
                System.out.println("Data Pegawai Berhasil Diperbaharui (Nomor : "+noPegawai+")");
            }
        }
        statement.close();
    }
    catch(SQLException e){
        System.err.println("Error dalam Mengedit Data");
        System.err.println(e.getMessage());
    }
    catch(NumberFormatException e){
        System.err.println("Format data salah");
        System.exit(0);
    }
    }
    public void hapus(){
        String hapus="Hapus data pegawai";
        System.out.println(hapus.toUpperCase());
        try{
        tampildata();
        System.out.println("Nomor pegawai yang akan dihapus datanya : ");
        Integer noPegawai=Integer.parseInt(inputan.nextLine());
        String sql = "DELETE FROM pegawai WHERE id_pegawai = "+noPegawai;
        hub=DriverManager.getConnection(url, "root", "");
        Statement statement = hub.createStatement();
        if (statement.executeUpdate(sql) > 0){
            System.out.println("Data pegawai Berhasil Dihapus");
        }
    }
    catch(SQLException e){
        System.out.println("ERROR dalam menghapus data pegawai");
    }
    catch(Exception e){
        System.out.println("Data yang dimasukkan salah");
        System.exit(0);
    }
}
    public void caridata() throws SQLException{
        String cari="Cari data Pegawai";
        System.out.println(cari.toUpperCase());
        try{
        tampildata2();
        System.out.println("Nama Pegawai yang akan dicari : ");
        String find = inputan.nextLine();

        String sql = "SELECT * FROM pegawai WHERE nama_pegawai LIKE '%"+find+"%'";
        hub = DriverManager.getConnection(url,"root","");
        Statement statement = hub.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            System.out.println();
            System.out.println("Nomor Pegawai\t  : "+result.getInt("id_pegawai"));
            System.out.println("Nama pegawai\t  : "+result.getString("nama_pegawai"));
            System.out.println("Jabatan\t\t  : "+result.getString("jabatan"));
            System.out.println("Jumlah hari masuk : "+result.getInt("hari_masuk"));
            System.out.println("Total gaji\t  : "+result.getInt("gaji_total"));
            System.out.println();
        }
    }
    catch(SQLException e){
        System.out.println("ERROR dalam mencari data pegawai");
    }
    
}
}
