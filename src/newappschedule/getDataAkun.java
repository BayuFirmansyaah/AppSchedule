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
   
   getDataAkun(int number,String username,String password){
       this.number = number;
       this.username = username;
       this.password = password;
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
   
   
   public void setNumber(int number){
       this.number = number;
   }
   
   public void setUsername(String username){
       this.username = username;
   }
   
   public void setPassword(String password){
       this.password = password;
   }
   
   
   
    
}
