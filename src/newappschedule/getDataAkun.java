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
   
   getDataAkun(int queryNumber, String queryUsername, String queryPassword){
       this.username = queryUsername;
       this.password = queryPassword;
       this.number = queryNumber;
   }
   
   public int getNumber(){
       return this.number;
   }
   
   public String getUsername(){
       return this.username;
   }
   
   public String getPassword(){
       return this.password;
   }
   
   
   public void setNumber(int queryNumber){
       this.number = queryNumber;
   }
   
   public void setUsername(String queryUsername){
       this.username = queryUsername;
   }
   
   public void setPassword(String queryPassword){
       this.password = queryPassword;
   }
   
   
   
    
}
