package com.example.mushfiq.practiceprojects;

public   class User {

    private String name;
    private String email;

    public User (){

    }
    public  User (String nam,String em){
         this.name=nam;
         this.email=em;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
