package gui;

import delegates.loginDelegate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JButton testbutton;
    private JPanel panel1;
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;

    private loginDelegate delegate;
    private static final int TEXT_FIELD_WIDTH= 10;

    // login gui components
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        testbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "hello world");
            }
        });
    }

    public void showFrame(loginDelegate delegate){
        this.delegate = delegate;
        // loginAttempts = 0;


        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


//    public static void main(String[] args){
//        JFrame frame = new JFrame("Login");
//        frame.setContentPane(new Login().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//
//    }

    public void handleLoginFailed(){
        System.out.println("handle login failed");
    }
}
