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
public class shortDataShow {
    String senin,selasa,rabu,kamis,jumat;
    int countable;
    
    shortDataShow(String querySenin,String querySelasa,String queryRabu,String queryKamis,String queryJumat){
        
        if(querySenin == "null"){
            this.countable +=1;
        }else{
            this.senin = querySenin;
            System.out.println("kelas :"+ querySenin +" di senin berhasil di tambahkan");
        }
        
        if(querySelasa == "null"){
            this.countable +=1;
        }else{
            this.selasa = querySelasa;
            System.out.println("kelas :"+ querySelasa+" di selasa berhasil di tambahkan");
        }
        
        if(queryRabu == "null"){
            this.countable +=1;
        }else{
            this.rabu = queryRabu;
            System.out.println("kelas :"+ queryRabu+" di hari rabu berhasil di tambahkan");
        }
        
        if(queryKamis == "null"){
            this.countable +=1;
        }else{
            this.kamis = queryKamis;
            System.out.println("kelas :"+ queryKamis+" di hari kamis berhasil di tambahkan");
        }
        
        if(queryJumat == "null"){
            this.countable +=1;
        }else{
            this.jumat = queryJumat;
            System.out.println("kelas :"+ queryJumat+" di hari jumat berhasil di tambahkan"); 
        }
    }
    
    
    public String Senin(){
        return senin;
    }
    
     public String Selasa(){
        return selasa;
    }
     
     
    public String Rabu(){
        return rabu;
    }
    
    
    public String Kamis(){
        return kamis;
    }
    
    
    public String Jumat(){
        return jumat;
    }
    
    
    public void setSenin(String _senin){
        this.senin = _senin;
    }
    
    public void setSelasa(String _selasa){
        this.selasa = _selasa;
    }
    
    public void setRabu(String _rabu){
        this.rabu = _rabu;
    }
    
    public void setKamis(String _kamis){
        this.kamis = _kamis;
    }
    
    public void setJumat(String _jumat){
        this.jumat = _jumat;
    }
     
     
    
}
