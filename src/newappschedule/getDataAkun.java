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
public class getDataAkun {
    int number;
    String username,password;
    
    getDataAkun(int no,String _username,String _password){
        this.number = no;
        this.username = _username;
        this.password = _password;
    }
    
    public int getNumber(){
        return number;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    
    
   public void setNumber(int no){
       this.number = no;
   }
   
   public void setUsername(String _username){
       this.username = _username;
   }
   
   
   public void setPassword(String _password){
       this.password = _password;
   }
   
   
    
}
