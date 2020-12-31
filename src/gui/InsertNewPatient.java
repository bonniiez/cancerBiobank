package gui;

import database.DBConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InsertNewPatient extends JFrame{
    private JPanel mainPanel;
    private JTextField pidTextField;
    private JTextField bdTextField;
    private JTextField cancerTextField;
    private JTextField metTextField;
    private JTextField sexTextField;
    private JTextField dateTextField;
    private JButton insertButton;
    private Connection connection = null;


    public InsertNewPatient() {
        super("Insert New Patient");
        createConnection();
        this.setContentPane(mainPanel);



        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    System.out.println("insert new patient connection closed ");
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pID = "ID"+pidTextField.getText();
                String birthDate = bdTextField.getText();
                String cancerType= cancerTextField.getText();
                String metastaticId = metTextField.getText();
                String sex = sexTextField.getText();
                String dateDx = dateTextField.getText();

                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


                try{
                    java.util.Date dob =  sdf.parse(birthDate);
                    java.sql.Date birthdateSql = new java.sql.Date(dob.getTime());

                    java.util.Date metId =  sdf.parse(metastaticId);
                    java.sql.Date metIdSql = new java.sql.Date(metId.getTime());

                    java.util.Date dateDxUtil =  sdf.parse(dateDx);
                    java.sql.Date dateDxSql = new java.sql.Date(dateDxUtil.getTime());
                    System.out.println("sql birthdate: " + birthdateSql);
                    System.out.println("java birthdate: " + birthDate);



                    String query = " INSERT INTO PATIENT (pID, MetastaticDx, BirthDate, Sex, CancerType, DateDx)"
                            + " values (?, ?, ?, ?, ?,?)";

                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1, pID);
                    ps.setDate(2, birthdateSql);
                    ps.setDate(3, metIdSql);
                    ps.setString(4, sex);
                    ps.setString(5, cancerType);
                    ps.setDate(6, dateDxSql);

                    ps.executeUpdate();


                }catch(SQLException err){
                    JOptionPane.showMessageDialog(new JFrame(), err.getMessage());

                    System.out.print("[EXCEPTION]"+ err.getMessage());

                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Unable to parse date(format should be yyyy-mm-dd)");

                    e1.printStackTrace();
                }
            }
        });
    }

    public void createConnection(){
        try{

            DBConnect dbHandler = new DBConnect();
            connection = dbHandler.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFrame(){

        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        this.setVisible(true);




    }
}
