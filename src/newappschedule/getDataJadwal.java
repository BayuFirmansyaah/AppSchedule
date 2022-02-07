/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newappschedule;

/**
 *
 * @author bayu firmansyah
 */
public class getDataJadwal {
    
    String hari,kelas,jam,kode;

    getDataJadwal(String queryKelas, String queryHari, String queryJam, String queryKode) {
          this.hari = queryHari;
          this.kelas = queryKelas;
          this.jam = queryJam;
          this.kode = queryKode;
    }
    
    public String getHari(){
        return hari;
    }
   
    public String getKelas(){
        return kelas;
    }
    
    public String getJam(){
        return jam;
    }
    
    public String getKode(){
        return kode;
    }
    
    
    public void setHari(String _hari){
        this.hari = _hari;
    }
    
    public void setKelas(String _kelas){
        this.kelas = _kelas;
    }
    
    public void setJam(String _jam){
        this.jam = _jam;
    }
    
    
    public void setKode(String _kode){
        this.kode = _kode;
    }
   
   
   
}
