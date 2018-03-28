package edu.metrostate.ics372.ui;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

import edu.metrostate.ics372.application.Application;
import edu.metrostate.ics372.domain.Trial;

import java.util.Calendar;

public class Controller extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	// explanatory variables
	private static final String NEWLINE = "\n";
	private static final int LOG_ROWS = 5;
	private static final int LOG_COLS = 20;
	private static final int LOG_MARGIN = 5;
    
	// JButton
	private JButton importBtn;
	private JButton exportBtn;
	private JButton activatePatientBtn;
	private JButton	deactivatePatientBtn;
	private JButton addReadingBtn;
	private JButton addPatientBtn;
	private JButton addClinicBtn;
	private JButton exitBtn;
	private JButton printReadingsBtn;
	
	// scroll pane
	private JScrollPane logScrollPane;
	
	// textArea
	private JTextArea log;
	
	// file chooser
	private JFileChooser fc;
	
	// panels
	private JPanel btnPanel;
	
	// application
	private Application application;
    
    
	private Controller() {
        super(new BorderLayout());
        initComponents();
        addActionListeners();
        addComponents();
        addPanels(); 
        loadSavedData();
    }
	
	private void initComponents() {	
		// application
		application = new Application();
		// panels
		btnPanel = new JPanel();
		// file chooser
		fc = new JFileChooser();
		// log
		log = new JTextArea();
		log.setRows(LOG_ROWS);
		log.setColumns(LOG_COLS);
        log.setMargin(new Insets(LOG_MARGIN, LOG_MARGIN, LOG_MARGIN, LOG_MARGIN));
        log.setEditable(false);
        logScrollPane = new JScrollPane(log);
        // buttons
		importBtn = new JButton("Import File");
		exportBtn = new JButton("Export JSON");
        activatePatientBtn = new JButton("Activate Patient");
        deactivatePatientBtn = new JButton("Deactivate Patient");
        addReadingBtn = new JButton("Add Reading");
        addPatientBtn = new JButton("Add Patient");
        addClinicBtn = new JButton("Add Clinic");
        printReadingsBtn = new JButton("Print Readings");
        exitBtn = new JButton("Exit");		
	}
	
	private void addActionListeners() {
        importBtn.addActionListener(this);       
        exportBtn.addActionListener(this);
        activatePatientBtn.addActionListener(this);
        deactivatePatientBtn.addActionListener(this);
        addReadingBtn.addActionListener(this);
        addPatientBtn.addActionListener(this);
        addClinicBtn.addActionListener(this);
        printReadingsBtn.addActionListener(this);
        exitBtn.addActionListener(this);
	}
	
	private void addComponents() {
        btnPanel.add(importBtn);
        btnPanel.add(exportBtn);
        btnPanel.add(addReadingBtn);
        btnPanel.add(addPatientBtn);
        btnPanel.add(addClinicBtn);
        btnPanel.add(activatePatientBtn);
        btnPanel.add(deactivatePatientBtn);
        btnPanel.add(printReadingsBtn);
        btnPanel.add(exitBtn);
	}
	
	private void addPanels() {
		this.add(btnPanel, BorderLayout.PAGE_START);
        this.add(logScrollPane, BorderLayout.CENTER);
	}
	
	private void loadSavedData() {
		application.loadData();
	}

    public void actionPerformed(ActionEvent e) {
    	performImport(e);
    	performExport(e);
    	performAddReading(e);
    	performAddPatient(e);
    	performAddClinic(e);
    	performActivatePatient(e);
    	performDeactivatePatient(e);
    	performPrintReadings(e);
    	performExit(e);
    }
    
    private void performImport(ActionEvent e) {
    	if (e.getSource() == importBtn) {
            int returnVal = fc.showOpenDialog(Controller.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                log.append("Opening: " + file.getAbsolutePath() + "." + NEWLINE);
                log.append(application.loadFile(file.getAbsolutePath())+NEWLINE);
                
            } else {
                log.append("Open command cancelled by user." + NEWLINE);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
    
    private void performExport(ActionEvent e) {
    	if (e.getSource() == exportBtn) {
            int returnVal = fc.showSaveDialog(Controller.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                log.append("Saving: " + file.getAbsolutePath() + "." + NEWLINE);
                log.append(application.saveFile(file.getAbsolutePath())+NEWLINE);
            } else {
                log.append("Save command cancelled by user." + NEWLINE);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
    
    private void performAddReading(ActionEvent e) {
    	if (e.getSource() == addReadingBtn) {
        	// Prompt user for record data.
        	JTextField patient_id = new JTextField(24);
        	JTextField reading_type = new JTextField(24);
        	JTextField reading_id = new JTextField(24);
        	JTextField reading_value = new JTextField(24);
        	JTextField reading_date = new JTextField(24);
        	JTextField clinic_id = new JTextField(24);
        	
        	JPanel myPanel = new JPanel();
        	myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
            myPanel.add(new JLabel("Patient ID:"));
            myPanel.add(patient_id);
            myPanel.add(new JLabel("Reading Type: "));
            myPanel.add(reading_type);
            myPanel.add(new JLabel("Reading ID: "));
            myPanel.add(reading_id);
            myPanel.add(new JLabel("Reading Value: "));
            myPanel.add(reading_value);
            myPanel.add(new JLabel("Reading Date: MM/DD/YYYY: "));
            myPanel.add(reading_date);
            myPanel.add(new JLabel("Clinic ID: "));
            myPanel.add(clinic_id);
            
            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                    "Please Enter Reading Values", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
            	String PatientIDText = patient_id.getText();
            	String ReadingTypeText = reading_type.getText();
            	String ReadingIDText = reading_id.getText();
            	String ReadingValue = reading_value.getText();
            	String ReadingDate = reading_date.getText();
            	String ClinicIDText = clinic_id.getText();
            	
            	// Parse Date.
            	boolean DateIsValid = true;
            	// create an empty Calendar object to store user input and get timestamp. 
            	Calendar aDate = Calendar.getInstance();
            	aDate.clear();
            	
            	// Try block: if the input is bad, we could generate a number of different
            	// exception types here: array index/out-of-bounds, parse errors, etc.
            	try {
            		String[] DateArray = ReadingDate.split("/", 3);
            		int MM = Integer.parseInt(DateArray[0]);
            		int DD = Integer.parseInt(DateArray[1]);
            		int YYYY = Integer.parseInt(DateArray[2]);
            		aDate.set(Calendar.MONTH, MM-1); 
            		aDate.set(Calendar.DAY_OF_MONTH, DD);
            		aDate.set(Calendar.YEAR, YYYY);
            		log.append(aDate.getTime().toString() + NEWLINE);
            		log.append(aDate.getTimeInMillis() + NEWLINE);
           		// so catch everything and blame the user. 
            	} catch (Exception thisException) {
            		DateIsValid = false;
            	}
            	
            	if ( ( PatientIDText.length() < 1 ) | (PatientIDText == null ) | 
            			( ReadingTypeText.length() < 1 ) | (ReadingTypeText == null ) |
            			( ReadingIDText.length() < 1 ) | (ReadingIDText == null ) |
            			( ReadingValue.length() < 1 ) | (ReadingValue == null ) |
            			DateIsValid != true |
            			  ClinicIDText.length() < 1 | ClinicIDText == null)
            	{
            		log.append("Could not add Reading - user left required input blank or malformed date." + NEWLINE);
            	} else {
                	log.append("Patient ID   : " + PatientIDText + NEWLINE);
                	log.append("Reading Type : " + ReadingTypeText + NEWLINE);
                	log.append("Reading ID   : " + ReadingIDText + NEWLINE);
                	log.append("Reading Value: " + ReadingValue + NEWLINE);
                	log.append("Reading Date : " + ReadingDate + NEWLINE);
                	log.append("Clinic ID    : " + ClinicIDText + NEWLINE);
                	log.append(application.addReading( PatientIDText, ReadingTypeText, ReadingIDText, ReadingValue, aDate.getTimeInMillis(), ClinicIDText ));
            	}
            } else {
            	log.append("User cancelled Add Reading." + NEWLINE);
            }
            log.setCaretPosition(log.getDocument().getLength());
        } 
    }
      
    private void performAddPatient(ActionEvent e) {
    	if (e.getSource() == addPatientBtn) {
        	
        	// Prompt user for record data.
        	JTextField patient_id = new JTextField(24);
        	
        	JPanel myPanel = new JPanel();
        	myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
            myPanel.add(new JLabel("Patient ID:"));
            myPanel.add(patient_id);
            
            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                    "Please Enter Patint ID", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
            	String PatientIDText = patient_id.getText();
            	
            	if ( ( PatientIDText.length() < 1 ) | (PatientIDText == null ) )
            	{
            		log.append("Could not add Patient - user left required input blank" + NEWLINE);
            	} else {
                	log.append("Patient ID   : " + PatientIDText + NEWLINE);
                	log.append(application.addPatient( PatientIDText ) );
            	}
            } else {
            	log.append("User cancelled Add Patient." + NEWLINE);
            }
            log.setCaretPosition(log.getDocument().getLength());
        
        } 
    }
    
    private void performAddClinic(ActionEvent e) {
    	if (e.getSource() == addClinicBtn) {
        	
        	// Prompt user for record data.
        	JTextField clinic_id = new JTextField(24);
        	JTextField clinic_name = new JTextField(24);
        	
        	JPanel myPanel = new JPanel();
        	myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
            myPanel.add(new JLabel("Clinic ID:"));
            myPanel.add(clinic_id);
            myPanel.add(new JLabel("Clinic Name:"));
            myPanel.add(clinic_name);
            
            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                    "Please Enter Clinic Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
            	String ClinicIDText = clinic_id.getText();
            	String ClinicNameText = clinic_name.getText();

            	if ( ( ClinicIDText.length() < 1 ) | (ClinicIDText == null ) |
            			(ClinicNameText.length() < 1) | (ClinicNameText == null))
            	{
            		log.append("Could not add Clinic - user left required input blank" + NEWLINE);
            	} else {
                	log.append("Clinic ID   : " + ClinicIDText + NEWLINE);
                	log.append("Clinic Name   : " + ClinicNameText + NEWLINE);
                	log.append(application.addClinic( ClinicIDText ) );
            	}
            } else {
            	log.append("User cancelled Add Clinic." + NEWLINE);
            }
            log.setCaretPosition(log.getDocument().getLength());
        } 
    }
    
    private void performActivatePatient(ActionEvent e) {
    	if (e.getSource() == activatePatientBtn) {
        	String PatientID = JOptionPane.showInputDialog("Please input a Patient ID to begin a study.");
            if ( ( PatientID != null ) && ( PatientID.length() > 0 ) )
            { 
            	log.append("Attempting to set patient ID: " + PatientID + " active for Trial." + NEWLINE);
                log.append(application.beginStudy(PatientID) + NEWLINE);
           	} else {
           		log.append("User cancelled command." + NEWLINE);
           	}
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
    
    private void performDeactivatePatient(ActionEvent e) {
    	if (e.getSource() == deactivatePatientBtn) {
        	String PatientID = JOptionPane.showInputDialog("Please input a Patient ID to end participation.");
            if ( ( PatientID != null ) && ( PatientID.length() > 0 ) )
            { 
            	log.append("Attempting to set patient ID: " + PatientID + " inactive for Trial." + NEWLINE);
            	log.append(application.endStudy(PatientID) + NEWLINE);
           	} else {
           		log.append("User cancelled command." + NEWLINE);
           	}
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
    
    private void performPrintReadings(ActionEvent e) {
    	if (e.getSource() == printReadingsBtn ) {
            log.append( application.printReadings() );
        }
    }
    
    private void performExit(ActionEvent e) {
    	if (e.getSource() == exitBtn) {
        	String[] options = { "Save first...", "Don't save.", "Cancel." };
        	
        	int choice = JOptionPane.showOptionDialog(null, "Do you want to save before closing?",
                    "Exit Program", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
        	if ( choice == 0 )
        	{
                log.append("Saving all the data...");
                log.append(application.saveData()+NEWLINE);        		
                System.exit(0);
        	}
        	
            if ( choice == 1 )
            {
            	System.exit(0);
            }
            if ( choice == 2 )
            {
                log.append("Exit command cancelled by user." + NEWLINE);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Trial Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Controller());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    	
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}