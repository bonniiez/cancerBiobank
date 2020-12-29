package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PatientGui extends JFrame implements ActionListener{
    private JTextField typePatientPiDTextField;
    private JButton searchButton;
    private JButton insertNewPatientButton;
    private JPanel patientSearchBarPanel;
    private JPanel patientFormMainPanel;
    private JPanel patientTablePanel;


    public PatientGui() {
        super("Patient panel");
        this.setContentPane(patientFormMainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // get pID from text field
                // call DAO and get employees for the pID
                // if pID doesnt exist, then get all employees
                // print out employees
            }
        });
    }
    public void showFrame(){

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        typePatientPiDTextField.setPreferredSize(new Dimension(150,20));
        contentPane.add(typePatientPiDTextField);

        contentPane.add(searchButton);
        contentPane.add(insertNewPatientButton);

        insertNewPatientButton.addActionListener(this);
        searchButton.addActionListener(this);


        // anonymous inner class for closing the window
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // size the window to obtain a best fit for the components
//        this.pack();
        this.setVisible(true);




    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
