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
   int no,id;
   
   getDataAkun(int id,int no, String username,String password){
       this.id = id;
       this.no = no;
       this.username = username;
       this.password = password;
   }
   
   public int getId(){
       return id;
   }
   
   public void setId(int id){
       this.id = id; 
   }
   
   public int getNo(){
       return no;
   }
   
   public void setNo(int no){
       this.no = no; 
   }
   
   public String getUsername(){
       return username;
   }
   
   public void setUsername(String username){
       this.username = username; 
   }


    public String getPassword(){
       return password;
   }
   
   public void setPassword(String password){
       this.password = password; 
   }
    
}
