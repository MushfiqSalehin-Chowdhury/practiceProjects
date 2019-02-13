package com.example.mushfiq.practiceprojects;

public   class User {

    public String name;
    public String email;

    public User (){

    }

    public  User (String nam,String em){
         this.name=nam;
         this.email=em;
    }

    public String getName (){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

}
