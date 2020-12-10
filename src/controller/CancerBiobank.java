package controller;

import delegates.loginDelegate;
import gui.Login;

import database.DBConnect;

public class CancerBiobank implements loginDelegate {
    private DBConnect dbHandler = null;
    private Login loginGui = null;

    public CancerBiobank(){
        dbHandler = new DBConnect();
    }

    private void start(){
        loginGui = new Login();
        loginGui.showFrame(this);  // implement showFrame in LoginGui
    }

/**
 * LoginDelegate implementaion
 * connects mySQL database with given username/password
**/

 public void login(String username, String password){
        boolean isConnected = dbHandler.login(username,password);

        if(isConnected){
            System.out.println("cancerbiobank .. connected");
        }else{
            loginGui.handleLoginFailed();


        }

    }

    public static void main(String args[]){
     CancerBiobank biobank = new CancerBiobank();
     biobank.start();
    }


}
