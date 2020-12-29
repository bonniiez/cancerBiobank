package gui;

import delegates.loginDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame implements ActionListener{
    private JPanel panel1;

    // login gui componenets
    private JTextField usernameTextField;
    private JPasswordField pwdPasswordField;
    private JButton loginButton;
    private JLabel userLabel;
    private JLabel pwdLabel;

    private loginDelegate delegate;
    private static final int TEXT_FIELD_WIDTH= 10;


    public Login() {
        super("User Login");
    }

    public void showFrame(loginDelegate delegate){
        this.delegate = delegate;
        // loginAttempts = 0;

        usernameTextField = new JTextField(TEXT_FIELD_WIDTH);
        pwdPasswordField = new JPasswordField(TEXT_FIELD_WIDTH);

//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                delegate.login(usernameTextField.getText(), String.valueOf(pwdPasswordField));
//            }
//        });

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//

        // place the username label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(userLabel, c);
        contentPane.add(userLabel);

        // place the text field for the username
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(usernameTextField, c);
        contentPane.add(usernameTextField);

        // place password label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 10, 10, 0);
        gb.setConstraints(pwdLabel, c);
        contentPane.add(pwdLabel);

        // place the password field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0, 0, 10, 10);
        gb.setConstraints(pwdPasswordField, c);
        contentPane.add(pwdPasswordField);

        // place the login button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(loginButton, c);
        contentPane.add(loginButton);

        // register login button with action event handler
        loginButton.addActionListener(this);

        // anonymous inner class for closing the window
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        // make the window visible
        this.setVisible(true);

        // place the cursor in the text field for the username
        usernameTextField.requestFocus();




//        JFrame frame = new JFrame("Login");
//        frame.setContentPane(new Login().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);

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
        usernameTextField.setText("");
        pwdPasswordField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e){

        String passText = new String(pwdPasswordField.getPassword());
        delegate.login(usernameTextField.getText(), passText);
    }

}
