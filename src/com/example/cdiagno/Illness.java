package com.example.cdiagno;

public class Illness {
      private String name;
      private String des;
      private double chance;
      
      public Illness()
      {
    	  
      }
      public String getName(){
    	  return this.name;
      }
      public String getDes(){
    	  return this.des;
      }
      public double getChance(){
    	  return this.chance;
      }
      public void setName(String name){
    	  this.name = name;
      }
      
      public void setChance(double chance){
    	  this.chance = chance;
      }
      
      public void setDes(String des){
    	  this.des = des;
      } 
}
