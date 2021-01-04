package gui;

import database.DBConnect;
import net.proteanit.sql.DbUtils;
import sun.jvm.hotspot.ui.treetable.JTreeTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class PatientGui extends JFrame implements ActionListener{
    private Connection connection = null;
    private JTextField typePatientPiDTextField;
    private JButton searchButton;
    private JButton insertNewPatientButton;
    private JPanel patientSearchBarPanel;
    private  JPanel patientFormMainPanel;
    private JPanel patientTablePanel;
    private JTable patientTable;
    private JButton deleteButton;
    private JButton treatmentsButton;
    String columnNames[] = {"pID", "MetastaticDx", "BirthDate","Sex","CancerType","DateDx"};


    public PatientGui() {
        super("Patient panel");
        createConnection();

        this.setContentPane(patientFormMainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        patientTablePanel.setLayout(new BorderLayout()); // null pointer exception without setting layout
        patientTablePanel.setPreferredSize(new Dimension(150,200));

        JScrollPane scrollPane = new JScrollPane(patientTable);
        patientTablePanel.add(scrollPane);

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





        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ResultSet rSet;

                try{
                    String pID = typePatientPiDTextField.getText();

                    PreparedStatement pst;
                    if (pID.isEmpty()){
                        String query="SELECT * FROM Patient";
                         pst = connection.prepareStatement(query);
                        rSet = pst.executeQuery();
//                        rSet = queryStmt.executeQuery("SELECT * FROM Patient");


                    }else  {
                        String query="SELECT * FROM Patient WHERE pID='"+pID+"' ";
                         pst = connection.prepareStatement(query);
                        rSet = pst.executeQuery();
//                        rSet = queryStmt.executeQuery("SELECT * FROM Patient WHERE pID='"+pID+"' ");



                    }

                    patientTable.setModel(DbUtils.resultSetToTableModel(rSet));

//                    patientTablePanel.setLayout(new BorderLayout()); // null pointer exception without setting layout
//                    JScrollPane scrollPane = new JScrollPane(patientTable);
//                    patientTablePanel.add(scrollPane);
//                    patientTable.setFillsViewportHeight(true);





                    /*
                    while(rSet.next()){

                        String patientID = rSet.getString("pID");
                        String metastaticDx = rSet.getString("MetastaticDx");
                        String bdate = rSet.getString("BirthDate");
                        String sex = rSet.getString("Sex");
                        String cancerType = rSet.getString("CancerType");                        String DateDx = rSet.getString("DateDx");
                        String dateDx = rSet.getString("DateDx");

                        System.out.print(patientID + ", " + cancerType +  "\n ");

//                        Object[] dataObj = new Object[]{patientID, cancerType};
//                        patientTable = createTable(dataObj);

//                        tableModel.addColumn(new Object[]{"pID", "CancerType"});
//                        tableModel.addRow(new Object[]{patientID, cancerType});

//                        patientTable = createTable();
//                        patientFormMainPanel.add(patientTable);

                        model.addColumn(new Object[]{columnNames});
                        model.addRow(new Object[]{patientID, metastaticDx, bdate,sex,cancerType,dateDx});
                    }
                    */

                    rSet.close();
                    pst.close(); // close statement to prevent memory leaks
                }catch (SQLException err){
                    System.out.print("[EXCEPTION]"+ err.getMessage());
                }
            }
        });

        // insert a new patient record
        insertNewPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertNewPatient insertPat = new InsertNewPatient();
                insertPat.showFrame();

            }

        });

        // delete an existing patient record
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = patientTable.getSelectedRow();
                    String pID = patientTable.getValueAt(row,0).toString();
                    pID.substring(2);
                    String sqlc = "DELETE FROM PATIENT WHERE pID=?";
                    PreparedStatement ps = connection.prepareStatement(sqlc);
                    ps.setString(1, pID);
                    ps.executeUpdate();



                   refreshPatientTable();

                    ps.close();



                } catch (SQLException e1) {

                    JOptionPane.showMessageDialog(new JFrame(),  e1.getMessage());

                    e1.printStackTrace();
                }


            }
        });
        treatmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreatmentTherapy tt = new TreatmentTherapy();
                tt.setVisible(true);

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

        this.setVisible(true);

    }

    public void refreshPatientTable(){
        // refresh the updated table
        searchButton.doClick();
//        try{
//            String query="SELECT * FROM Patient";
//            PreparedStatement pst = connection.prepareStatement(query);
//            ResultSet rSet = pst.executeQuery();
//            patientTable.setModel(DbUtils.resultSetToTableModel(rSet));
//            System.out.println("refreshed patient table");
//        }catch(SQLException e){
//            e.printStackTrace();
//
//
//        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

