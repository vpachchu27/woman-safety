package com.example.signup;

public class users {
    private String name;
    private long mobileno1;
    private long mobileno2;



    public users(String name, long mobileno1, long mobileno2, long mobileno3, String password, String inputText) {
        this.name = name;
        this.mobileno1 = mobileno1;
        this.mobileno2 = mobileno2;
        this.mobileno3 = mobileno3;
        this.password = password;
        this.inputText = inputText;
    }

    private long mobileno3;
    private String password;


    public users()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public long getMobileno1() {
        return mobileno1;
    }

    public void setMobileno1(long mobileno1) {
        this.mobileno1 = mobileno1;
    }

    public long getMobileno2() {
        return mobileno2;
    }

    public void setMobileno2(long mobileno2) {
        this.mobileno2 = mobileno2;
    }

    public long getMobileno3() {
        return mobileno3;
    }

    public void setMobileno3(long mobileno3) {
        this.mobileno3 = mobileno3;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    private String inputText;

}
