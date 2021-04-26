package com.compasso.error;

import java.io.Serializable;

public  class ReturnErrorMessage implements Serializable {
    private  int status_code;
    private   String message;


    public  ReturnErrorMessage(int staus_code,String message){
        this.status_code = staus_code;
        this.message = message;
    }

    public  String Returnmessage(){
      return "{" + "status_cod=" + this.status_code + ", message='" +  this.message + '\'' + '}';
    }
  
    @Override
    public String toString() {
      return "{" + "status_cod=" + this.status_code + ", message='" +  this.message + '\'' + '}';
    }
}