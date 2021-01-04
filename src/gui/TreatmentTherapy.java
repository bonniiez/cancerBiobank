package gui;

import database.DBConnect;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentTherapy extends JFrame{
    private JPanel mainPanel;
    private JTable treatmentTable;
    private JTable therapyTable;
    private JPanel treatmentPanel;
    private JPanel therapyPanel;
    private JPanel joinPanel;


    private JLabel joinTreatmentTherapyLabel;
    private JButton joinSearchButton;
    private JTable joinTreatmentTherapyTable;
    private JButton treatmentRefreshButton;
    private JButton therapyRefreshButton;
    private JComboBox comboBox1;
    private String selectedPT;
    private JLabel treatmentLabel;
    private JLabel therapyLabel;
    private JPanel joinSearchBar;
    private Connection connection;



    public TreatmentTherapy(){
        super("Treatment and Therapy");

        createConnection();
        this.setContentPane(mainPanel);


        mainPanel.setLayout(new BorderLayout(8,6));
        mainPanel.setSize(400,500);

        treatmentPanel = new JPanel();
        treatmentPanel.setBorder(new LineBorder(Color.black, 1));
        treatmentPanel.add(treatmentLabel);
        treatmentPanel.add(treatmentRefreshButton);
        treatmentPanel.add(treatmentTable);

        JScrollPane scrollPaneTreatment = new JScrollPane(treatmentTable);
        treatmentPanel.add(scrollPaneTreatment);


        therapyPanel = new JPanel();
        therapyPanel.setBorder(new LineBorder(Color.black, 1));
        therapyPanel.add(therapyLabel);
        therapyPanel.add(therapyRefreshButton);
        therapyPanel.add(therapyTable);

        JScrollPane scrollPaneTherapy = new JScrollPane(therapyTable);
        therapyPanel.add(scrollPaneTherapy);


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(treatmentPanel);
        topPanel.add(therapyPanel);

        joinPanel = new JPanel();
        selectedPT = "";
        joinPanel.setLayout(new BoxLayout(joinPanel, BoxLayout.Y_AXIS));
        String progressionTypes[] = new String[]{"","Clinical", "Chemical"};
        comboBox1 = new JComboBox(progressionTypes);
;

        joinSearchBar = new JPanel();
        joinSearchBar.setLayout(new BoxLayout(joinSearchBar, BoxLayout.X_AXIS));
        joinSearchBar.add(comboBox1, BorderLayout.CENTER);
        joinSearchBar.add(joinSearchButton,  BorderLayout.LINE_END);


        joinPanel.add(joinTreatmentTherapyLabel);
        joinPanel.add(joinSearchBar);
        joinPanel.add(joinTreatmentTherapyTable, BorderLayout.PAGE_END);

        JScrollPane scrollPanelJoin = new JScrollPane(joinTreatmentTherapyTable);

        joinPanel.add(scrollPanelJoin);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(joinPanel, BorderLayout.SOUTH);

        this.pack();



        treatmentRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query = "SELECT * FROM Treatment";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    treatmentTable.setModel(DbUtils.resultSetToTableModel(rs));
                    pack();
                    ps.close();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        therapyRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
//
                    String query = "SELECT * FROM Therapy";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    therapyTable.setModel(DbUtils.resultSetToTableModel(rs));
                    pack();

                    ps.close();
                }catch(SQLException e1){
                    e1.printStackTrace();

                }
            }
        });
        joinSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String query = "SELECT *\n" +
                            "FROM treatment t\n" +
                            "WHERE t.progressionType = '"+selectedPT+"' AND EXISTS (SELECT *\n" +
                            "FROM therapy th)";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    joinTreatmentTherapyTable.setModel(DbUtils.resultSetToTableModel(rs));
                    pack();

                    ps.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String[] progressionTypes = new String[]{"Clinical", "Chemical"};
//                comboBox1 = new JComboBox(progressionTypes);
////                comboBox1.addActionListener(this);

                JComboBox cb = (JComboBox)e.getSource();
                 selectedPT = (String)cb.getSelectedItem();
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
}
