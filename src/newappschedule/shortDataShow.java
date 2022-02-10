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
    int number;
    
     shortDataShow(int jam,String querySenin, String querySelasa, String queryRabu, String queryKamis, String queryJumat) {
          this.number = jam;
          this.senin = querySenin;
          this.selasa = querySelasa;
          this.rabu = queryRabu;
          this.kamis = queryKamis;
          this.jumat = queryJumat;
    }
    
    
    public int getNumber(){
        return number;
    }
    
    public String getSenin(){
        return senin;
    }
    
     public String getSelasa(){
        return selasa;
    }
     
     
    public String getRabu(){
        return rabu;
    }
    
    
    public String getKamis(){
        return kamis;
    }
    
    
    public String getJumat(){
        return jumat;
    }
    
    
    public void setNumber(int _number){
        this.number = _number;
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
