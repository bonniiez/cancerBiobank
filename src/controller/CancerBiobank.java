package controller;

import delegates.loginDelegate;
import gui.Login;

import database.DBConnect;
import gui.PatientGui;

import java.sql.SQLException;

public class CancerBiobank implements loginDelegate {
    private DBConnect dbHandler = null;
    private Login loginGui = null;
    private PatientGui patientGui = null;

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

     boolean isConnectedStatus;
     isConnectedStatus = dbHandler.login(username,password);
     System.out.println("cancerbiobank .. connected");
     loginGui.dispose();

     if (isConnectedStatus){
         patientGui = new PatientGui();
         patientGui.setVisible(true);
//         patientGui.showFrame();
     } else {
         loginGui.handleLoginFailed();

     }


 }

    public static void main(String args[]){
     CancerBiobank biobank = new CancerBiobank();
     biobank.start();
    }


}
