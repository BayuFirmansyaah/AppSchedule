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
    String username,password;
     int number;
    
    getDataAkun(int no,String queryUsername,String queryPassword){
        this.number = no;
        this.username = queryUsername;
        this.password = queryPassword;
        System.out.println(queryUsername +" | "+queryPassword);
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
