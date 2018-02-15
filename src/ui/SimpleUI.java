package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.List;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import dao.TimetablerDAO;
import data.Availability;
import data.Settings;
import data.TimeSlot;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import util.DateHelper;

public class SimpleUI extends JFrame {

	private String[] daysOfWeek = { "Monday", "Tuesday", 
			"Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRoomName;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_2;
	
	// settings page
	private JSpinner spnSettingsNumberSemesters;
	private JCheckBox chkSettingsLunchActive;
	private JSpinner spnSettingsLunchStart;
	private JSpinner spnSettingsLunchEnd;
	private JSpinner spnSettingsTimeBias;
	private JSpinner spnSettingsMaxTutorHours;
	private JSpinner spnSettingsMaxTutorMins;
	private JSpinner spnSettingsMaxCourseHours;
	private JSpinner spnSettingsMaxCourseMins;
	private JCheckBox chkSettingsDayBiasActive;
	private JCheckBox chkSettingsTimeBiasActive;
	private JCheckBox chkSettingsCourseBias;
	private JCheckBox chkSettingsTutorBias;
	private JCheckBox chkSettingsCourseStrong;
	private JCheckBox chkSettingsTutorStrong;
	private JComboBox cmbSettingsDayBias;
	
	// rooms page
	private JSpinner spnRoomsStartTime1;
	private JSpinner spnRoomsEndTime1;
	private JSpinner spnRoomsEndTime2;
	private JSpinner spnRoomsStartTime2;
	private JSpinner spnRoomsEndTime3;
	private JSpinner spnRoomsStartTime3;
	private JSpinner spnRoomsEndTime4;
	private JSpinner spnRoomsStartTime4;
	private JSpinner spnRoomsEndTime5;
	private JSpinner spnRoomsStartTime5;
	private JSpinner spnRoomsEndTime6;
	private JSpinner spnRoomsStartTime6;
	private JSpinner spnRoomsSemester;
	private JComboBox cmbRoomsDay;
	private JButton btnRoomsNew;
	private JButton btnRoomsEdit;
	private JButton btnRoomsDelete;
	private JButton btnRoomsCancel;
	private JButton btnRoomsOK;
	private JCheckBox chkRoomsSameAllSemesters;
	private JCheckBox chkRoomsActive1;
	private JCheckBox chkRoomsActive2;
	private JCheckBox chkRoomsActive3;
	private JCheckBox chkRoomsActive4;
	private JCheckBox chkRoomsActive5;
	private JCheckBox chkRoomsActive6;
	
	// tutor page
	private JSpinner spnTutorsStartTime1;
	private JSpinner spnTutorsEndTime1;
	private JSpinner spnTutorsStartTime2;
	private JSpinner spnTutorsEndTime2;
	private JSpinner spnTutorsStartTime3;
	private JSpinner spnTutorsEndTime3;
	private JSpinner spnTutorsStartTime4;
	private JSpinner spnTutorsEndTime4;
	private JSpinner spnTutorsStartTime5;
	private JSpinner spnTutorsEndTime5;
	private JSpinner spnTutorsStartTime6;
	private JSpinner spnTutorsEndTime6;
	private JSpinner spnTutorsSemester;
	private JComboBox cmbTutorsDay;
	private JButton btnTutorsNew;
	private JButton btnTutorsEdit;
	private JButton btnTutorsDel;
	private JButton btnTutorsCancel;
	private JButton btnTutorsOK;
	private JCheckBox chkTutorsActive1;
	private JCheckBox chkTutorsActive2;
	private JCheckBox chkTutorsActive3;
	private JCheckBox chkTutorsActive4;
	private JCheckBox chkTutorsActive5;
	private JCheckBox chkTutorsActive6;
	private JCheckBox chkTutorsSameAllSemesters;
	
	// course page
	private JSpinner spnCoursesStartTime1;
	private JSpinner spnCoursesEndTime1;
	private JSpinner spnCoursesStartTime2;
	private JSpinner spnCoursesEndTime2;
	private JSpinner spnCoursesStartTime3;
	private JSpinner spnCoursesEndTime3;
	private JSpinner spnCoursesStartTime4;
	private JSpinner spnCoursesEndTime4;
	private JSpinner spnCoursesStartTime5;
	private JSpinner spnCoursesEndTime5;
	private JSpinner spnCoursesStartTime6;
	private JSpinner spnCoursesEndTime6;
	private JSpinner spnCoursesSemester;
	private JComboBox cmbCoursesDay;
	private JButton btnCoursesNew;
	private JButton btnCoursesEdit;
	private JButton btnCoursesDel;
	private JButton btnCoursesCancel;
	private JButton btnCoursesOK;
	private JCheckBox chkCoursesActive1;
	private JCheckBox chkCoursesActive2;
	private JCheckBox chkCoursesActive3;
	private JCheckBox chkCoursesActive4;
	private JCheckBox chkCoursesActive5;
	private JCheckBox chkCoursesActive6;
	private JCheckBox chkCoursesSameAllSemesters;
	
	// session page
	private JSpinner spnSessionLengthHours;
	private JSpinner spnSessionsLengthMinutes;
	private JComboBox cmbSessionsCourse;
	private JComboBox cmbSessionsTutor;
	private JButton btnSessionsNew;
	private JButton btnSessionsEdit;
	private JButton btnSessionsDel;
	private JButton btnSessionsCancel;
	private JButton btnSessionsOK;
	private JButton btnSessionsRoomsAdd;
	private JButton btnSessionsRoomsDel;
	
	// timetabler page
	private JSpinner spnTimetableMaxVersions;
	private JSpinner spnTimetableViewVersion;
	
	// main pages
	private JTabbedPane tabbedPane;
	private JPanel panelSettings;
	private JPanel panelRooms;
	private JPanel panelTutors;
	private JPanel panelCourses;
	private JPanel panelSessions;
	private JPanel panelTimetables;
	
	// handy containers for all the availability spinners
	ArrayList<ArrayList<JSpinner[]>> availabilitySpinners;
	ArrayList<ArrayList<JCheckBox>> availabilityCheckBoxes;
	ArrayList<JComboBox> availabilityCombo;
	ArrayList<JSpinner> availabilitySemester;
	ArrayList<JCheckBox> availabilitySameAllSemesters;
	
	ArrayList<Availability[]> displayedAvailability;
	
	private TimetablerDAO timetablerDAO;
	
	// handy int labels instead of an enum
	private final int ROOMS = 0;
	private final int TUTORS = 1;
	private final int COURSES = 2;
	
	private int currentTabIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleUI frame = new SimpleUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimpleUI() {
		setupGUI();
		initiateDAO();
		initiateNothingLoaded();
		
	}
	
	private void initiateDAO() {
		currentTabIndex = 0;
	}
	
	/**
	 * this is how the program should be setup before a new timetable is made, it should 
	 * prevent access to any tab but the 1st and it should disable the settings
	 */
	private void initiateNothingLoaded() {
		panelSettings.getComponent(0).setVisible(false);
		panelSettings.getComponent(1).setVisible(false);
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(3, false);
		tabbedPane.setEnabledAt(4, false);
		tabbedPane.setEnabledAt(5, false);
		
		// adds three default availability, one each for rooms, tutors and courses
		displayedAvailability = new ArrayList<Availability[]>();
		displayedAvailability.add(new Availability[] { Availability.getDefaultAvailability() });
		displayedAvailability.add(new Availability[] { Availability.getDefaultAvailability() });
		displayedAvailability.add(new Availability[] { Availability.getDefaultAvailability() });
	}
	
	/**
	 * sets the settings on screen to whatever is held by the DAO
	 */
	private void initiateSettings() {
		Settings settings = timetablerDAO.getSettings();
		spnSettingsNumberSemesters.setValue(settings.getNumberOfSemesters());
		chkSettingsLunchActive.setSelected(settings.isHasLunchTime());
		chkSettingsDayBiasActive.setSelected(settings.isDayBiasActive());
		chkSettingsTimeBiasActive.setSelected(settings.isTimeBiasActive());
		chkSettingsCourseBias.setSelected(settings.isCourseGroupingBiasActive());
		chkSettingsTutorBias.setSelected(settings.isTutorGroupingBiasActive());
		chkSettingsCourseStrong.setSelected(settings.isCourseGroupingBiasStrong());
		chkSettingsTutorStrong.setSelected(settings.isTutorGroupingBiasStrong());
		
		spnSettingsLunchStart.setValue(DateHelper.GetDate(settings.getLunchTimeSlot().getStartTime()));
		spnSettingsLunchEnd.setValue(DateHelper.GetDate(settings.getLunchTimeSlot().getEndTime()));

		spnSettingsMaxCourseHours.setValue(settings.getMaxDailyCourseMinutes() / 60);
		spnSettingsMaxTutorHours.setValue(settings.getMaxDailyTutorMinutes() / 60);
		spnSettingsMaxCourseMins.setValue(settings.getMaxDailyCourseMinutes() % 60);
		spnSettingsMaxTutorMins.setValue(settings.getMaxDailyTutorMinutes() % 60);
		
		spnSettingsTimeBias.setValue(DateHelper.GetDate(settings.getTimeBias()));
		cmbSettingsDayBias.setSelectedIndex(settings.getDayBias());

	}
	
	/**
	 * 
	 */
	private void updateSettings() {
		// bad practice to set like this but making all these 
		// setters in the DAO seems wasteful
		
		Settings settings = timetablerDAO.getSettings();
		
		// take the settings from the screen and save them to the DAO
		settings.setCourseGroupingBiasActive(chkSettingsCourseBias.isSelected());
		settings.setCourseGroupingBiasStrong(chkSettingsCourseStrong.isSelected());
		settings.setDayBias(cmbSettingsDayBias.getSelectedIndex());
		settings.setDayBiasActive(chkSettingsDayBiasActive.isSelected());
		settings.setHasLunchTime(chkSettingsLunchActive.isSelected());
		settings.setLunchTimeStart(DateHelper.GetTimeInMinutes((Date) spnSettingsLunchStart.getValue()));
		settings.setLunchTimeEnd(DateHelper.GetTimeInMinutes((Date) spnSettingsLunchEnd.getValue()));
		settings.setMaxDailyCourseMinutes((int) spnSettingsMaxCourseMins.getValue() 
				+ (((int) spnSettingsMaxCourseHours.getValue()) * 60));
		settings.setMaxDailyTutorMinutes((int) spnSettingsMaxTutorMins.getValue() 
				+ (((int) spnSettingsMaxTutorHours.getValue()) * 60));
		settings.setNumberOfSemesters((int) spnSettingsNumberSemesters.getValue());
		settings.setTimeBias(DateHelper.GetTimeInMinutes((Date) spnSettingsTimeBias.getValue()));
		settings.setTimeBiasActive(chkSettingsTimeBiasActive.isSelected());
		settings.setTutorGroupingBiasActive(chkSettingsTutorBias.isSelected());
		settings.setTutorGroupingBiasStrong(chkSettingsTutorStrong.isSelected());
		
		// update the semester details in the relevant tabs
		((SpinnerNumberModel) spnRoomsSemester.getModel()).setMaximum(settings.getNumberOfSemesters());
		
		System.out.println("settings updated");
		
	}
	
	/**
	 * call this to stop the user changing the amount of semesters. 
	 * Should be called when "New" is clicked on any page.
	 */
	private void fixSemesters() {
		spnSettingsNumberSemesters.setEnabled(false);
	}
	
	private void initiateRooms() {
		panelRooms.getComponent(4).setVisible(false);
		btnRoomsEdit.setEnabled(false);
		btnRoomsDelete.setEnabled(false);
		
	}
	
	private void doNewRoomAction() {
		fixSemesters();
		panelRooms.getComponent(4).setVisible(true);
		txtRoomName.setText("");
		// initiate default room availability
		chkRoomsSameAllSemesters.setSelected(true);
		Availability[] availability = new Availability[timetablerDAO.getSettings().getNumberOfSemesters()];
		for (int i = 0; i < availability.length; i++) {
			availability[i] = Availability.getDefaultAvailability();
		}
		displayedAvailability.set(ROOMS, availability);
		displayAvailability(ROOMS);		
	}
	
	private void updateRoomAvailability() {
		// TODO: currently saves the info to the day or semester 
		// newly selected rather than the previously selected one
		// save the displayed data
		saveAvailability(ROOMS);
		// update the displayed data
		displayAvailability(ROOMS);
	}
	
	/**
	 * should be called any time the displayed data is changed
	 * @param page
	 */
	private void saveAvailability(int page) {
		// remove the saved availability on the currently displayed semester and day
		displayedAvailability.get(ROOMS)[(int) spnRoomsSemester.getValue() - 1]
				.removeAllOnDay(cmbRoomsDay.getSelectedIndex());
		// add in the new one(s)
		int semester = (int) availabilitySemester.get(page).getValue();
		for (int i = 0; i < 6; i++) {
			if (availabilityCheckBoxes.get(page).get(i).isSelected()) {
				displayedAvailability
				.get(page)[semester -1].addTimeSlot(
						new TimeSlot(availabilityCombo.get(page).getSelectedIndex(),
								DateHelper.GetTimeInMinutes((Date) availabilitySpinners.get(page).get(i)[0].getValue()),
								DateHelper.GetTimeInMinutes((Date) availabilitySpinners.get(page).get(i)[1].getValue())));
			}
		}		
	}
	
	
	private void displayAvailability(int page) {
		Availability[] availability = displayedAvailability.get(ROOMS);
		// get the semester to display
		int semesterToDisplay = ((int) availabilitySemester.get(page).getValue()) - 1;
		// get the day to display1
		int dayDisplay = cmbRoomsDay.getSelectedIndex();
		// get the time slots
		ArrayList<TimeSlot> timeSlots = availability[semesterToDisplay].getTimeSlotsForDay(dayDisplay);
		// populate the spinners and check boxes
		for (int i = 0; i < timeSlots.size(); i++) {
			availabilityCheckBoxes.get(page).get(i).setSelected(true);
			availabilitySpinners.get(page).get(i)[0].setValue(DateHelper.GetDate(timeSlots.get(i).getStartTime()));
			availabilitySpinners.get(page).get(i)[0].setEnabled(true);
			availabilitySpinners.get(page).get(i)[1].setValue(DateHelper.GetDate(timeSlots.get(i).getEndTime()));
			availabilitySpinners.get(page).get(i)[1].setEnabled(true);
		}
		// blank out the unused ones
		for (int i = timeSlots.size(); i < 6; i++) {
			availabilityCheckBoxes.get(page).get(i).setSelected(false);
			availabilitySpinners.get(page).get(i)[0].setValue(DateHelper.GetDate(9 * 60));
			availabilitySpinners.get(page).get(i)[0].setEnabled(false);
			availabilitySpinners.get(page).get(i)[1].setValue(DateHelper.GetDate(16 * 60));
			availabilitySpinners.get(page).get(i)[1].setEnabled(false);
		}
	}
	
	private void roomSelected() {
		btnRoomsEdit.setEnabled(true);
		btnRoomsDelete.setEnabled(true);
	}
	
	private void fileNewSelected() {
		panelSettings.getComponent(0).setVisible(true);
		panelSettings.getComponent(1).setVisible(true);
		tabbedPane.setEnabledAt(1, true);
		tabbedPane.setEnabledAt(2, true);
		tabbedPane.setEnabledAt(3, true);
		tabbedPane.setEnabledAt(4, true);
		tabbedPane.setEnabledAt(5, true);
		
		timetablerDAO = new TimetablerDAO();
		
		initiateSettings();
		initiateRooms();
	}
	
	/**
	 * all the bits of the GUI on all tabs. Messy.
	 */
	private void setupGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 527);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileNewSelected();
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// performs actions if the tab previously selected is one 
				// that requires data auto-saving upon navigating away
				if (currentTabIndex == 0 && tabbedPane.getSelectedIndex() != 0) {
					// settings was last selected so update on navigating away from the page
					updateSettings();
				}
				currentTabIndex = tabbedPane.getSelectedIndex();
			}
		});
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		panelSettings = new JPanel();
		tabbedPane.addTab("Start", null, panelSettings, null);
		panelSettings.setLayout(null);
		
		Panel panel_9 = new Panel();
		panel_9.setBackground(new Color(176, 224, 230));
		panel_9.setBounds(349, 97, 304, 307);
		panelSettings.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblAdvanced = new JLabel("Advanced Settings");
		lblAdvanced.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdvanced.setBounds(10, 11, 139, 14);
		panel_9.add(lblAdvanced);
		
		JLabel lblChangingTheseWill = new JLabel("Changing these will change the ordering");
		lblChangingTheseWill.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblChangingTheseWill.setBounds(10, 27, 246, 14);
		panel_9.add(lblChangingTheseWill);
		
		JLabel lblOfSessionsIn = new JLabel("of sessions in the final timetables.");
		lblOfSessionsIn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblOfSessionsIn.setBounds(10, 41, 246, 14);
		panel_9.add(lblOfSessionsIn);
		
		chkSettingsDayBiasActive = new JCheckBox("Day Bias:");
		chkSettingsDayBiasActive.setBackground(new Color(176, 224, 230));
		chkSettingsDayBiasActive.setBounds(6, 62, 95, 23);
		panel_9.add(chkSettingsDayBiasActive);
		
		cmbSettingsDayBias = new JComboBox(daysOfWeek);
		cmbSettingsDayBias.setBounds(107, 63, 119, 20);
		panel_9.add(cmbSettingsDayBias);
		
		chkSettingsTimeBiasActive = new JCheckBox("Time Bias:");
		chkSettingsTimeBiasActive.setBackground(new Color(176, 224, 230));
		chkSettingsTimeBiasActive.setBounds(6, 88, 96, 23);
		panel_9.add(chkSettingsTimeBiasActive);
		
		spnSettingsTimeBias = new JSpinner();
		spnSettingsTimeBias.setBounds(108, 89, 62, 20);
		spnSettingsTimeBias.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnSettingsTimeBias.getModel()).setCalendarField(Calendar.MINUTE);
		spnSettingsTimeBias.setEditor(new JSpinner.DateEditor(spnSettingsTimeBias, "h:mm a"));
		panel_9.add(spnSettingsTimeBias);
		
		chkSettingsCourseBias = new JCheckBox("Course grouping bias");
		chkSettingsCourseBias.setBackground(new Color(176, 224, 230));
		chkSettingsCourseBias.setBounds(6, 114, 151, 23);
		panel_9.add(chkSettingsCourseBias);
		
		chkSettingsTutorBias = new JCheckBox("Tutor grouping bias");
		chkSettingsTutorBias.setBackground(new Color(176, 224, 230));
		chkSettingsTutorBias.setBounds(6, 140, 151, 23);
		panel_9.add(chkSettingsTutorBias);
		
		chkSettingsCourseStrong = new JCheckBox("Strong");
		chkSettingsCourseStrong.setBackground(new Color(176, 224, 230));
		chkSettingsCourseStrong.setBounds(157, 114, 69, 23);
		panel_9.add(chkSettingsCourseStrong);
		
		chkSettingsTutorStrong = new JCheckBox("Strong");
		chkSettingsTutorStrong.setBackground(new Color(176, 224, 230));
		chkSettingsTutorStrong.setBounds(157, 140, 69, 23);
		panel_9.add(chkSettingsTutorStrong);
		
		JLabel lblPreferredMaximumDaily = new JLabel("Preferred maximum daily tutor allocation:");
		lblPreferredMaximumDaily.setBounds(6, 167, 250, 14);
		panel_9.add(lblPreferredMaximumDaily);
		
		spnSettingsMaxTutorHours = new JSpinner();
		spnSettingsMaxTutorHours.setModel(new SpinnerNumberModel(6, 0, 23, 1));
		spnSettingsMaxTutorHours.setBounds(6, 185, 57, 20);
		panel_9.add(spnSettingsMaxTutorHours);
		
		JLabel label = new JLabel("Hours");
		label.setBounds(73, 188, 46, 14);
		panel_9.add(label);
		
		spnSettingsMaxTutorMins = new JSpinner();
		spnSettingsMaxTutorMins.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spnSettingsMaxTutorMins.setBounds(129, 185, 57, 20);
		panel_9.add(spnSettingsMaxTutorMins);
		
		JLabel label_15 = new JLabel("Minutes");
		label_15.setBounds(198, 188, 58, 14);
		panel_9.add(label_15);
		
		JLabel lblPreferredMaximumDaily_1 = new JLabel("Preferred maximum daily course allocation:");
		lblPreferredMaximumDaily_1.setBounds(6, 216, 250, 14);
		panel_9.add(lblPreferredMaximumDaily_1);
		
		spnSettingsMaxCourseHours = new JSpinner();
		spnSettingsMaxCourseHours.setModel(new SpinnerNumberModel(6, 0, 23, 1));
		spnSettingsMaxCourseHours.setBounds(6, 234, 57, 20);
		panel_9.add(spnSettingsMaxCourseHours);
		
		JLabel label_17 = new JLabel("Hours");
		label_17.setBounds(73, 237, 46, 14);
		panel_9.add(label_17);
		
		spnSettingsMaxCourseMins = new JSpinner();
		spnSettingsMaxCourseMins.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spnSettingsMaxCourseMins.setBounds(129, 234, 57, 20);
		panel_9.add(spnSettingsMaxCourseMins);
		
		JLabel label_18 = new JLabel("Minutes");
		label_18.setBounds(198, 237, 58, 14);
		panel_9.add(label_18);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(349, 10, 304, 84);
		panelSettings.add(panel);
		panel.setLayout(null);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setBounds(10, 11, 63, 14);
		panel.add(lblSettings);
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNumberOfSemesters = new JLabel("Number of Semesters:");
		lblNumberOfSemesters.setBounds(10, 36, 155, 14);
		panel.add(lblNumberOfSemesters);
		
		spnSettingsNumberSemesters = new JSpinner();
		spnSettingsNumberSemesters.setBounds(175, 33, 40, 20);
		panel.add(spnSettingsNumberSemesters);
		spnSettingsNumberSemesters.setModel(new SpinnerNumberModel(2, 1, 4, 1));
		
		spnSettingsLunchStart = new JSpinner();
		spnSettingsLunchStart.setBounds(113, 58, 63, 20);
		spnSettingsLunchStart.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnSettingsLunchStart.getModel()).setCalendarField(Calendar.MINUTE);
		spnSettingsLunchStart.setEditor(new JSpinner.DateEditor(spnSettingsLunchStart, "h:mm a"));
		panel.add(spnSettingsLunchStart);
		
		chkSettingsLunchActive = new JCheckBox("Lunch Time:");
		chkSettingsLunchActive.setBackground(new Color(240, 230, 140));
		chkSettingsLunchActive.setBounds(6, 57, 100, 23);
		panel.add(chkSettingsLunchActive);
		
		JLabel lblTo = new JLabel("until");
		lblTo.setBounds(186, 61, 29, 14);
		panel.add(lblTo);
		
		spnSettingsLunchEnd = new JSpinner();
		spnSettingsLunchEnd.setBounds(214, 58, 63, 20);
		spnSettingsLunchEnd.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnSettingsLunchEnd.getModel()).setCalendarField(Calendar.MINUTE);
		spnSettingsLunchEnd.setEditor(new JSpinner.DateEditor(spnSettingsLunchEnd, "h:mm a"));
		panel.add(spnSettingsLunchEnd);
		
		JLabel lblWelcomeToCollege = new JLabel("Welcome to College Timetabler.");
		lblWelcomeToCollege.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcomeToCollege.setBounds(10, 11, 281, 25);
		panelSettings.add(lblWelcomeToCollege);
		
		JLabel lblNewLabel = new JLabel("To get started please read the instructions below:");
		lblNewLabel.setBounds(10, 47, 318, 14);
		panelSettings.add(lblNewLabel);
		
		JTextPane txtpnFollowTheTabs = new JTextPane();
		txtpnFollowTheTabs.setText("- Choose \"New\" from the menu to begin\r\n- Follow the tabs in order\r\n- Start with the settings on this page\r\n- Complete each page before moving on to the next\r\n- Save regularly\r\n- Save before calculating the timetable");
		txtpnFollowTheTabs.setBounds(10, 64, 318, 340);
		panelSettings.add(txtpnFollowTheTabs);
		
		panelRooms = new JPanel();
		tabbedPane.addTab("Rooms", null, panelRooms, null);
		panelRooms.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 14, 219, 404);
		panelRooms.add(list);
		
		btnRoomsNew = new JButton("New");
		btnRoomsNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doNewRoomAction();
			}
		});
		btnRoomsNew.setBounds(239, 11, 89, 23);
		panelRooms.add(btnRoomsNew);
		
		btnRoomsEdit = new JButton("Edit");
		btnRoomsEdit.setBounds(239, 45, 89, 23);
		panelRooms.add(btnRoomsEdit);
		
		btnRoomsDelete = new JButton("Delete");
		btnRoomsDelete.setBounds(239, 79, 89, 23);
		panelRooms.add(btnRoomsDelete);
		
		Panel panel_3 = new Panel();
		panel_3.setBackground(new Color(176, 224, 230));
		panel_3.setBounds(343, 11, 310, 407);
		panelRooms.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblName = new JLabel("Room Name:");
		lblName.setBounds(10, 11, 81, 14);
		panel_3.add(lblName);
		
		txtRoomName = new JTextField();
		txtRoomName.setBounds(101, 8, 144, 20);
		panel_3.add(txtRoomName);
		txtRoomName.setColumns(10);
		
		JLabel lblAvailability = new JLabel("Availability");
		lblAvailability.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAvailability.setBounds(100, 39, 81, 14);
		panel_3.add(lblAvailability);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(10, 88, 46, 14);
		panel_3.add(lblDay);
		
		cmbRoomsDay = new JComboBox(daysOfWeek);
		cmbRoomsDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRoomAvailability();
			}
		});
		cmbRoomsDay.setBounds(101, 85, 144, 20);
		panel_3.add(cmbRoomsDay);
		
		JLabel lblActive = new JLabel("Active");
		lblActive.setBounds(10, 113, 46, 14);
		panel_3.add(lblActive);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(63, 113, 64, 14);
		panel_3.add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(157, 113, 64, 14);
		panel_3.add(lblEndTime);
		
		chkRoomsActive1 = new JCheckBox("");
		chkRoomsActive1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spnRoomsStartTime1.setEnabled(chkRoomsActive1.isSelected());
				spnRoomsEndTime1.setEnabled(chkRoomsActive1.isSelected());
			}
		});
		chkRoomsActive1.setBackground(new Color(176, 224, 230));
		chkRoomsActive1.setBounds(10, 134, 30, 23);
		panel_3.add(chkRoomsActive1);
		
		chkRoomsActive2 = new JCheckBox("");
		chkRoomsActive2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spnRoomsStartTime2.setEnabled(chkRoomsActive2.isSelected());
				spnRoomsEndTime2.setEnabled(chkRoomsActive2.isSelected());
			}
		});
		chkRoomsActive2.setBackground(new Color(176, 224, 230));
		chkRoomsActive2.setBounds(10, 160, 30, 23);
		panel_3.add(chkRoomsActive2);
		
		chkRoomsActive3 = new JCheckBox("");
		chkRoomsActive3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spnRoomsStartTime3.setEnabled(chkRoomsActive3.isSelected());
				spnRoomsEndTime3.setEnabled(chkRoomsActive3.isSelected());
			}
		});
		chkRoomsActive3.setBackground(new Color(176, 224, 230));
		chkRoomsActive3.setBounds(10, 186, 30, 23);
		panel_3.add(chkRoomsActive3);
		
		chkRoomsActive4 = new JCheckBox("");
		chkRoomsActive4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spnRoomsStartTime4.setEnabled(chkRoomsActive4.isSelected());
				spnRoomsEndTime4.setEnabled(chkRoomsActive4.isSelected());
			}
		});
		chkRoomsActive4.setBackground(new Color(176, 224, 230));
		chkRoomsActive4.setBounds(10, 212, 30, 23);
		panel_3.add(chkRoomsActive4);
		
		chkRoomsActive5 = new JCheckBox("");
		chkRoomsActive5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spnRoomsStartTime5.setEnabled(chkRoomsActive5.isSelected());
				spnRoomsEndTime5.setEnabled(chkRoomsActive5.isSelected());
			}
		});
		chkRoomsActive5.setBackground(new Color(176, 224, 230));
		chkRoomsActive5.setBounds(10, 238, 30, 23);
		panel_3.add(chkRoomsActive5);
		
		chkRoomsActive6 = new JCheckBox("");
		chkRoomsActive6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spnRoomsStartTime6.setEnabled(chkRoomsActive6.isSelected());
				spnRoomsEndTime6.setEnabled(chkRoomsActive6.isSelected());
			}
		});
		chkRoomsActive6.setBackground(new Color(176, 224, 230));
		chkRoomsActive6.setBounds(10, 264, 30, 23);
		panel_3.add(chkRoomsActive6);
		
		spnRoomsStartTime1 = new JSpinner();
		spnRoomsStartTime1.setBounds(63, 134, 78, 20);
		spnRoomsStartTime1.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsStartTime1.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsStartTime1.setEditor(new JSpinner.DateEditor(spnRoomsStartTime1, "h:mm a"));
		panel_3.add(spnRoomsStartTime1);
		
		spnRoomsEndTime1 = new JSpinner();
		spnRoomsEndTime1.setBounds(157, 134, 80, 20);
		spnRoomsEndTime1.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsEndTime1.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsEndTime1.setEditor(new JSpinner.DateEditor(spnRoomsEndTime1, "h:mm a"));
		panel_3.add(spnRoomsEndTime1);
		
		spnRoomsEndTime2 = new JSpinner();
		spnRoomsEndTime2.setBounds(157, 163, 80, 20);
		spnRoomsEndTime2.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsEndTime2.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsEndTime2.setEditor(new JSpinner.DateEditor(spnRoomsEndTime2, "h:mm a"));
		panel_3.add(spnRoomsEndTime2);
		
		spnRoomsStartTime2 = new JSpinner();
		spnRoomsStartTime2.setBounds(63, 163, 78, 20);
		spnRoomsStartTime2.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsStartTime2.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsStartTime2.setEditor(new JSpinner.DateEditor(spnRoomsStartTime2, "h:mm a"));
		panel_3.add(spnRoomsStartTime2);
		
		spnRoomsEndTime3 = new JSpinner();
		spnRoomsEndTime3.setBounds(157, 189, 80, 20);
		spnRoomsEndTime3.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsEndTime3.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsEndTime3.setEditor(new JSpinner.DateEditor(spnRoomsEndTime3, "h:mm a"));
		panel_3.add(spnRoomsEndTime3);
		
		spnRoomsStartTime3 = new JSpinner();
		spnRoomsStartTime3.setBounds(63, 189, 78, 20);
		spnRoomsStartTime3.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsStartTime3.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsStartTime3.setEditor(new JSpinner.DateEditor(spnRoomsStartTime3, "h:mm a"));
		panel_3.add(spnRoomsStartTime3);
		
		spnRoomsEndTime4 = new JSpinner();
		spnRoomsEndTime4.setBounds(157, 215, 80, 20);
		spnRoomsEndTime4.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsEndTime4.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsEndTime4.setEditor(new JSpinner.DateEditor(spnRoomsEndTime4, "h:mm a"));
		panel_3.add(spnRoomsEndTime4);
		
		spnRoomsStartTime4 = new JSpinner();
		spnRoomsStartTime4.setBounds(63, 215, 78, 20);
		spnRoomsStartTime4.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsStartTime4.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsStartTime4.setEditor(new JSpinner.DateEditor(spnRoomsStartTime4, "h:mm a"));
		panel_3.add(spnRoomsStartTime4);
		
		spnRoomsEndTime5 = new JSpinner();
		spnRoomsEndTime5.setBounds(157, 241, 80, 20);
		spnRoomsEndTime5.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsEndTime5.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsEndTime5.setEditor(new JSpinner.DateEditor(spnRoomsEndTime5, "h:mm a"));
		panel_3.add(spnRoomsEndTime5);
		
		spnRoomsStartTime5 = new JSpinner();
		spnRoomsStartTime5.setBounds(63, 241, 78, 20);
		spnRoomsStartTime5.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsStartTime5.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsStartTime5.setEditor(new JSpinner.DateEditor(spnRoomsStartTime5, "h:mm a"));
		panel_3.add(spnRoomsStartTime5);
		
		spnRoomsEndTime6 = new JSpinner();
		spnRoomsEndTime6.setBounds(157, 267, 80, 20);
		spnRoomsEndTime6.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsEndTime6.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsEndTime6.setEditor(new JSpinner.DateEditor(spnRoomsEndTime6, "h:mm a"));
		panel_3.add(spnRoomsEndTime6);
		
		spnRoomsStartTime6 = new JSpinner();
		spnRoomsStartTime6.setBounds(63, 267, 78, 20);
		spnRoomsStartTime6.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnRoomsStartTime6.getModel()).setCalendarField(Calendar.MINUTE);
		spnRoomsStartTime6.setEditor(new JSpinner.DateEditor(spnRoomsStartTime6, "h:mm a"));
		panel_3.add(spnRoomsStartTime6);
		
		JLabel lblSemester = new JLabel("Semester:");
		lblSemester.setBounds(10, 63, 73, 14);
		panel_3.add(lblSemester);
		
		spnRoomsSemester = new JSpinner();
		spnRoomsSemester.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// update the availability display
				updateRoomAvailability();
			}
		});
		spnRoomsSemester.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spnRoomsSemester.setBounds(101, 60, 37, 20);
		panel_3.add(spnRoomsSemester);
		
		btnRoomsCancel = new JButton("Cancel");
		btnRoomsCancel.setBounds(10, 373, 89, 23);
		panel_3.add(btnRoomsCancel);
		
		btnRoomsOK = new JButton("OK");
		btnRoomsOK.setBounds(211, 373, 89, 23);
		panel_3.add(btnRoomsOK);
		
		chkRoomsSameAllSemesters = new JCheckBox("Same all semesters");
		chkRoomsSameAllSemesters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spnRoomsSemester.setEnabled(!chkRoomsSameAllSemesters.isSelected());
			}
		});
		chkRoomsSameAllSemesters.setBackground(new Color(176, 224, 230));
		chkRoomsSameAllSemesters.setBounds(144, 59, 149, 23);
		panel_3.add(chkRoomsSameAllSemesters);
		
		panelTutors = new JPanel();
		panelTutors.setLayout(null);
		tabbedPane.addTab("Tutors", null, panelTutors, null);
		
		JList list_1 = new JList();
		list_1.setBounds(10, 14, 219, 404);
		panelTutors.add(list_1);
		
		btnTutorsNew = new JButton("New");
		btnTutorsNew.setBounds(239, 11, 89, 23);
		panelTutors.add(btnTutorsNew);
		
		btnTutorsEdit = new JButton("Edit");
		btnTutorsEdit.setBounds(239, 45, 89, 23);
		panelTutors.add(btnTutorsEdit);
		
		btnTutorsDel = new JButton("Delete");
		btnTutorsDel.setBounds(239, 79, 89, 23);
		panelTutors.add(btnTutorsDel);
		
		Panel panel_2 = new Panel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(176, 224, 230));
		panel_2.setBounds(344, 14, 309, 404);
		panelTutors.add(panel_2);
		
		JLabel lblTutorName = new JLabel("Tutor Name:");
		lblTutorName.setBounds(10, 11, 80, 14);
		panel_2.add(lblTutorName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(100, 8, 144, 20);
		panel_2.add(textField_1);
		
		JLabel label_2 = new JLabel("Availability");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(100, 38, 103, 14);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("Day:");
		label_3.setBounds(10, 88, 58, 14);
		panel_2.add(label_3);
		
		cmbTutorsDay = new JComboBox(daysOfWeek);
		cmbTutorsDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		cmbTutorsDay.setBounds(100, 85, 144, 20);
		panel_2.add(cmbTutorsDay);
		
		JLabel label_4 = new JLabel("Active");
		label_4.setBounds(10, 113, 47, 14);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("Start Time");
		label_5.setBounds(67, 113, 80, 14);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("End Time");
		label_6.setBounds(161, 113, 72, 14);
		panel_2.add(label_6);
		
		chkTutorsActive1 = new JCheckBox("");
		chkTutorsActive1.setBackground(new Color(176, 224, 230));
		chkTutorsActive1.setBounds(10, 134, 30, 23);
		panel_2.add(chkTutorsActive1);
		
		chkTutorsActive2 = new JCheckBox("");
		chkTutorsActive2.setBackground(new Color(176, 224, 230));
		chkTutorsActive2.setBounds(10, 160, 30, 23);
		panel_2.add(chkTutorsActive2);
		
		chkTutorsActive3 = new JCheckBox("");
		chkTutorsActive3.setBackground(new Color(176, 224, 230));
		chkTutorsActive3.setBounds(10, 186, 30, 23);
		panel_2.add(chkTutorsActive3);
		
		chkTutorsActive4 = new JCheckBox("");
		chkTutorsActive4.setBackground(new Color(176, 224, 230));
		chkTutorsActive4.setBounds(10, 212, 30, 23);
		panel_2.add(chkTutorsActive4);
		
		chkTutorsActive5 = new JCheckBox("");
		chkTutorsActive5.setBackground(new Color(176, 224, 230));
		chkTutorsActive5.setBounds(10, 238, 30, 23);
		panel_2.add(chkTutorsActive5);
		
		chkTutorsActive6 = new JCheckBox("");
		chkTutorsActive6.setBackground(new Color(176, 224, 230));
		chkTutorsActive6.setBounds(10, 264, 30, 23);
		panel_2.add(chkTutorsActive6);
		
		spnTutorsStartTime1 = new JSpinner();
		spnTutorsStartTime1.setBounds(67, 134, 76, 20);
		spnTutorsStartTime1.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsStartTime1.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsStartTime1.setEditor(new JSpinner.DateEditor(spnTutorsStartTime1, "h:mm a"));
		panel_2.add(spnTutorsStartTime1);
		
		spnTutorsEndTime1 = new JSpinner();
		spnTutorsEndTime1.setBounds(161, 134, 77, 20);
		spnTutorsEndTime1.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsEndTime1.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsEndTime1.setEditor(new JSpinner.DateEditor(spnTutorsEndTime1, "h:mm a"));
		panel_2.add(spnTutorsEndTime1);
		
		spnTutorsEndTime2 = new JSpinner();
		spnTutorsEndTime2.setBounds(161, 163, 77, 20);
		spnTutorsEndTime2.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsEndTime2.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsEndTime2.setEditor(new JSpinner.DateEditor(spnTutorsEndTime2, "h:mm a"));
		panel_2.add(spnTutorsEndTime2);
		
		spnTutorsStartTime2 = new JSpinner();
		spnTutorsStartTime2.setBounds(67, 163, 76, 20);
		spnTutorsStartTime2.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsStartTime2.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsStartTime2.setEditor(new JSpinner.DateEditor(spnTutorsStartTime2, "h:mm a"));
		panel_2.add(spnTutorsStartTime2);
		
		spnTutorsEndTime3 = new JSpinner();
		spnTutorsEndTime3.setBounds(161, 189, 77, 20);
		spnTutorsEndTime3.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsEndTime3.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsEndTime3.setEditor(new JSpinner.DateEditor(spnTutorsEndTime3, "h:mm a"));
		panel_2.add(spnTutorsEndTime3);
		
		spnTutorsStartTime3 = new JSpinner();
		spnTutorsStartTime3.setBounds(67, 189, 76, 20);
		spnTutorsStartTime3.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsStartTime3.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsStartTime3.setEditor(new JSpinner.DateEditor(spnTutorsStartTime3, "h:mm a"));
		panel_2.add(spnTutorsStartTime3);
		
		spnTutorsEndTime4 = new JSpinner();
		spnTutorsEndTime4.setBounds(161, 215, 77, 20);
		spnTutorsEndTime4.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsEndTime4.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsEndTime4.setEditor(new JSpinner.DateEditor(spnTutorsEndTime4, "h:mm a"));
		panel_2.add(spnTutorsEndTime4);
		
		spnTutorsStartTime4 = new JSpinner();
		spnTutorsStartTime4.setBounds(67, 215, 76, 20);
		spnTutorsStartTime4.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsStartTime4.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsStartTime4.setEditor(new JSpinner.DateEditor(spnTutorsStartTime4, "h:mm a"));
		panel_2.add(spnTutorsStartTime4);
		
		spnTutorsEndTime5 = new JSpinner();
		spnTutorsEndTime5.setBounds(161, 241, 77, 20);
		spnTutorsEndTime5.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsEndTime5.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsEndTime5.setEditor(new JSpinner.DateEditor(spnTutorsEndTime5, "h:mm a"));
		panel_2.add(spnTutorsEndTime5);
		
		spnTutorsStartTime5 = new JSpinner();
		spnTutorsStartTime5.setBounds(67, 241, 76, 20);
		spnTutorsStartTime5.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsStartTime5.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsStartTime5.setEditor(new JSpinner.DateEditor(spnTutorsStartTime5, "h:mm a"));
		panel_2.add(spnTutorsStartTime5);
		
		spnTutorsEndTime6 = new JSpinner();
		spnTutorsEndTime6.setBounds(161, 267, 77, 20);
		spnTutorsEndTime6.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsEndTime6.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsEndTime6.setEditor(new JSpinner.DateEditor(spnTutorsEndTime6, "h:mm a"));
		panel_2.add(spnTutorsEndTime6);
		
		spnTutorsStartTime6 = new JSpinner();
		spnTutorsStartTime6.setBounds(67, 267, 76, 20);
		spnTutorsStartTime6.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnTutorsStartTime6.getModel()).setCalendarField(Calendar.MINUTE);
		spnTutorsStartTime6.setEditor(new JSpinner.DateEditor(spnTutorsStartTime6, "h:mm a"));
		panel_2.add(spnTutorsStartTime6);
		
		JLabel label_7 = new JLabel("Semester:");
		label_7.setBounds(10, 63, 73, 14);
		panel_2.add(label_7);
		
		spnTutorsSemester = new JSpinner();
		spnTutorsSemester.setBounds(100, 60, 36, 20);
		panel_2.add(spnTutorsSemester);
		
		btnTutorsCancel = new JButton("Cancel");
		btnTutorsCancel.setBounds(10, 370, 89, 23);
		panel_2.add(btnTutorsCancel);
		
		btnTutorsOK = new JButton("OK");
		btnTutorsOK.setBounds(210, 370, 89, 23);
		panel_2.add(btnTutorsOK);
		
		chkTutorsSameAllSemesters = new JCheckBox("Same all semesters");
		chkTutorsSameAllSemesters.setBackground(new Color(176, 224, 230));
		chkTutorsSameAllSemesters.setBounds(142, 59, 144, 23);
		panel_2.add(chkTutorsSameAllSemesters);
		
		panelCourses = new JPanel();
		panelCourses.setLayout(null);
		tabbedPane.addTab("Courses", null, panelCourses, null);
		
		JList list_2 = new JList();
		list_2.setBounds(10, 14, 219, 404);
		panelCourses.add(list_2);
		
		btnCoursesNew = new JButton("New");
		btnCoursesNew.setBounds(239, 11, 89, 23);
		panelCourses.add(btnCoursesNew);
		
		btnCoursesEdit = new JButton("Edit");
		btnCoursesEdit.setBounds(239, 45, 89, 23);
		panelCourses.add(btnCoursesEdit);
		
		btnCoursesDel = new JButton("Delete");
		btnCoursesDel.setBounds(239, 79, 89, 23);
		panelCourses.add(btnCoursesDel);
		
		Panel panel_5 = new Panel();
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(176, 224, 230));
		panel_5.setBounds(342, 11, 311, 407);
		panelCourses.add(panel_5);
		
		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setBounds(10, 11, 84, 14);
		panel_5.add(lblCourseName);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(104, 8, 137, 20);
		panel_5.add(textField_2);
		
		JLabel label_9 = new JLabel("Availability");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(104, 39, 73, 14);
		panel_5.add(label_9);
		
		JLabel label_10 = new JLabel("Day:");
		label_10.setBounds(10, 88, 46, 14);
		panel_5.add(label_10);
		
		cmbCoursesDay = new JComboBox(daysOfWeek);
		cmbCoursesDay.setBounds(104, 85, 137, 20);
		panel_5.add(cmbCoursesDay);
		
		JLabel label_11 = new JLabel("Active");
		label_11.setBounds(10, 113, 46, 14);
		panel_5.add(label_11);
		
		JLabel label_12 = new JLabel("Start Time");
		label_12.setBounds(64, 113, 74, 14);
		panel_5.add(label_12);
		
		JLabel label_13 = new JLabel("End Time");
		label_13.setBounds(158, 113, 74, 14);
		panel_5.add(label_13);
		
		chkCoursesActive1 = new JCheckBox("");
		chkCoursesActive1.setBackground(new Color(176, 224, 230));
		chkCoursesActive1.setBounds(10, 134, 30, 23);
		panel_5.add(chkCoursesActive1);
		
		chkCoursesActive2 = new JCheckBox("");
		chkCoursesActive2.setBackground(new Color(176, 224, 230));
		chkCoursesActive2.setBounds(10, 160, 30, 23);
		panel_5.add(chkCoursesActive2);
		
		chkCoursesActive3 = new JCheckBox("");
		chkCoursesActive3.setBackground(new Color(176, 224, 230));
		chkCoursesActive3.setBounds(10, 186, 30, 23);
		panel_5.add(chkCoursesActive3);
		
		chkCoursesActive4 = new JCheckBox("");
		chkCoursesActive4.setBackground(new Color(176, 224, 230));
		chkCoursesActive4.setBounds(10, 212, 30, 23);
		panel_5.add(chkCoursesActive4);
		
		chkCoursesActive5 = new JCheckBox("");
		chkCoursesActive5.setBackground(new Color(176, 224, 230));
		chkCoursesActive5.setBounds(10, 238, 30, 23);
		panel_5.add(chkCoursesActive5);
		
		chkCoursesActive6 = new JCheckBox("");
		chkCoursesActive6.setBackground(new Color(176, 224, 230));
		chkCoursesActive6.setBounds(10, 264, 30, 23);
		panel_5.add(chkCoursesActive6);
		
		spnCoursesStartTime1 = new JSpinner();
		spnCoursesStartTime1.setBounds(64, 134, 78, 20);
		spnCoursesStartTime1.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesStartTime1.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesStartTime1.setEditor(new JSpinner.DateEditor(spnCoursesStartTime1, "h:mm a"));
		panel_5.add(spnCoursesStartTime1);
		
		spnCoursesEndTime1 = new JSpinner();
		spnCoursesEndTime1.setBounds(158, 134, 78, 20);
		spnCoursesEndTime1.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesEndTime1.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesEndTime1.setEditor(new JSpinner.DateEditor(spnCoursesEndTime1, "h:mm a"));
		panel_5.add(spnCoursesEndTime1);
		
		spnCoursesEndTime2 = new JSpinner();
		spnCoursesEndTime2.setBounds(158, 163, 78, 20);
		spnCoursesEndTime2.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesEndTime2.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesEndTime2.setEditor(new JSpinner.DateEditor(spnCoursesEndTime2, "h:mm a"));
		panel_5.add(spnCoursesEndTime2);
		
		spnCoursesStartTime2 = new JSpinner();
		spnCoursesStartTime2.setBounds(64, 163, 78, 20);
		spnCoursesStartTime2.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesStartTime2.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesStartTime2.setEditor(new JSpinner.DateEditor(spnCoursesStartTime2, "h:mm a"));
		panel_5.add(spnCoursesStartTime2);
		
		spnCoursesEndTime3 = new JSpinner();
		spnCoursesEndTime3.setBounds(158, 189, 78, 20);
		spnCoursesEndTime3.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesEndTime3.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesEndTime3.setEditor(new JSpinner.DateEditor(spnCoursesEndTime3, "h:mm a"));
		panel_5.add(spnCoursesEndTime3);
		
		spnCoursesStartTime3 = new JSpinner();
		spnCoursesStartTime3.setBounds(64, 189, 78, 20);
		spnCoursesStartTime3.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesStartTime3.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesStartTime3.setEditor(new JSpinner.DateEditor(spnCoursesStartTime3, "h:mm a"));
		panel_5.add(spnCoursesStartTime3);
		
		spnCoursesEndTime4 = new JSpinner();
		spnCoursesEndTime4.setBounds(158, 215, 78, 20);
		spnCoursesEndTime4.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesEndTime4.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesEndTime4.setEditor(new JSpinner.DateEditor(spnCoursesEndTime4, "h:mm a"));
		panel_5.add(spnCoursesEndTime4);
		
		spnCoursesStartTime4 = new JSpinner();
		spnCoursesStartTime4.setBounds(64, 215, 78, 20);
		spnCoursesStartTime4.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesStartTime4.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesStartTime4.setEditor(new JSpinner.DateEditor(spnCoursesStartTime4, "h:mm a"));
		panel_5.add(spnCoursesStartTime4);
		
		spnCoursesEndTime5 = new JSpinner();
		spnCoursesEndTime5.setBounds(158, 241, 78, 20);
		spnCoursesEndTime5.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesEndTime5.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesEndTime5.setEditor(new JSpinner.DateEditor(spnCoursesEndTime5, "h:mm a"));
		panel_5.add(spnCoursesEndTime5);
		
		spnCoursesStartTime5 = new JSpinner();
		spnCoursesStartTime5.setBounds(64, 241, 78, 20);
		spnCoursesStartTime5.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesStartTime5.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesStartTime5.setEditor(new JSpinner.DateEditor(spnCoursesStartTime5, "h:mm a"));
		panel_5.add(spnCoursesStartTime5);
		
		spnCoursesEndTime6 = new JSpinner();
		spnCoursesEndTime6.setBounds(158, 267, 78, 20);
		spnCoursesEndTime6.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesEndTime6.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesEndTime6.setEditor(new JSpinner.DateEditor(spnCoursesEndTime6, "h:mm a"));
		panel_5.add(spnCoursesEndTime6);
		
		spnCoursesStartTime6 = new JSpinner();
		spnCoursesStartTime6.setBounds(64, 267, 78, 20);
		spnCoursesStartTime6.setModel(new SpinnerDateModel());
		((SpinnerDateModel) spnCoursesStartTime6.getModel()).setCalendarField(Calendar.MINUTE);
		spnCoursesStartTime6.setEditor(new JSpinner.DateEditor(spnCoursesStartTime6, "h:mm a"));
		panel_5.add(spnCoursesStartTime6);
		
		JLabel label_14 = new JLabel("Semester:");
		label_14.setBounds(10, 63, 73, 14);
		panel_5.add(label_14);
		
		spnCoursesSemester = new JSpinner();
		spnCoursesSemester.setBounds(104, 60, 37, 20);
		panel_5.add(spnCoursesSemester);
		
		btnCoursesCancel = new JButton("Cancel");
		btnCoursesCancel.setBounds(10, 373, 89, 23);
		panel_5.add(btnCoursesCancel);
		
		btnCoursesOK = new JButton("OK");
		btnCoursesOK.setBounds(212, 373, 89, 23);
		panel_5.add(btnCoursesOK);
		
		chkCoursesSameAllSemesters = new JCheckBox("Same all semesters");
		chkCoursesSameAllSemesters.setBackground(new Color(176, 224, 230));
		chkCoursesSameAllSemesters.setBounds(150, 59, 137, 23);
		panel_5.add(chkCoursesSameAllSemesters);
		
		panelSessions = new JPanel();
		tabbedPane.addTab("Sessions", null, panelSessions, null);
		panelSessions.setLayout(null);
		
		JLabel lblCourse_1 = new JLabel("Course:");
		lblCourse_1.setBounds(10, 11, 46, 14);
		panelSessions.add(lblCourse_1);
		
		cmbSessionsCourse = new JComboBox();
		cmbSessionsCourse.setBounds(66, 8, 157, 20);
		panelSessions.add(cmbSessionsCourse);
		
		JList list_3 = new JList();
		list_3.setBounds(10, 36, 213, 383);
		panelSessions.add(list_3);
		
		btnSessionsNew = new JButton("New");
		btnSessionsNew.setBounds(233, 37, 89, 23);
		panelSessions.add(btnSessionsNew);
		
		btnSessionsEdit = new JButton("Edit");
		btnSessionsEdit.setBounds(233, 71, 89, 23);
		panelSessions.add(btnSessionsEdit);
		
		btnSessionsDel = new JButton("Delete");
		btnSessionsDel.setBounds(233, 105, 89, 23);
		panelSessions.add(btnSessionsDel);
		
		Panel panel_8 = new Panel();
		panel_8.setLayout(null);
		panel_8.setBackground(new Color(176, 224, 230));
		panel_8.setBounds(328, 11, 325, 408);
		panelSessions.add(panel_8);
		
		JLabel lblSessionName = new JLabel("Session Name:");
		lblSessionName.setBounds(10, 11, 113, 14);
		panel_8.add(lblSessionName);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(133, 8, 182, 20);
		panel_8.add(textField_3);
		
		btnSessionsCancel = new JButton("Cancel");
		btnSessionsCancel.setBounds(10, 374, 89, 23);
		panel_8.add(btnSessionsCancel);
		
		btnSessionsOK = new JButton("OK");
		btnSessionsOK.setBounds(226, 374, 89, 23);
		panel_8.add(btnSessionsOK);
		
		JLabel lblTutor = new JLabel("Tutor:");
		lblTutor.setBounds(10, 36, 113, 14);
		panel_8.add(lblTutor);
		
		cmbSessionsTutor = new JComboBox();
		cmbSessionsTutor.setBounds(133, 33, 182, 20);
		panel_8.add(cmbSessionsTutor);
		
		JLabel lblRoomOptions = new JLabel("Room Options: (Add rooms that could hold session)");
		lblRoomOptions.setBounds(10, 61, 305, 14);
		panel_8.add(lblRoomOptions);
		
		JList list_4 = new JList();
		list_4.setBounds(10, 76, 152, 103);
		panel_8.add(list_4);
		
		JList list_5 = new JList();
		list_5.setBounds(172, 76, 143, 76);
		panel_8.add(list_5);
		
		btnSessionsRoomsAdd = new JButton("Add");
		btnSessionsRoomsAdd.setBounds(172, 156, 67, 23);
		panel_8.add(btnSessionsRoomsAdd);
		
		btnSessionsRoomsDel = new JButton("Del");
		btnSessionsRoomsDel.setBounds(249, 156, 66, 23);
		panel_8.add(btnSessionsRoomsDel);
		
		JLabel lblSemesterOptions = new JLabel("Semester Options:");
		lblSemesterOptions.setBounds(10, 207, 113, 14);
		panel_8.add(lblSemesterOptions);
		
		JCheckBox checkBox_12 = new JCheckBox("1");
		checkBox_12.setBackground(new Color(176, 224, 230));
		checkBox_12.setBounds(134, 203, 45, 23);
		panel_8.add(checkBox_12);
		
		JCheckBox checkBox_13 = new JCheckBox("2");
		checkBox_13.setBackground(new Color(176, 224, 230));
		checkBox_13.setBounds(181, 203, 45, 23);
		panel_8.add(checkBox_13);
		
		JCheckBox checkBox_14 = new JCheckBox("3");
		checkBox_14.setBackground(new Color(176, 224, 230));
		checkBox_14.setBounds(228, 203, 40, 23);
		panel_8.add(checkBox_14);
		
		JLabel lblcheckSemestersThat = new JLabel("(Check semesters that the session could be in)");
		lblcheckSemestersThat.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblcheckSemestersThat.setBounds(10, 232, 305, 14);
		panel_8.add(lblcheckSemestersThat);
		
		JLabel lblSessionLengthhoursminutes = new JLabel("Session length:");
		lblSessionLengthhoursminutes.setBounds(10, 279, 169, 14);
		panel_8.add(lblSessionLengthhoursminutes);
		
		spnSessionLengthHours = new JSpinner();
		spnSessionLengthHours.setModel(new SpinnerNumberModel(1, 0, 24, 1));
		spnSessionLengthHours.setBounds(10, 301, 57, 20);
		panel_8.add(spnSessionLengthHours);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setBounds(77, 304, 57, 14);
		panel_8.add(lblHours);
		
		spnSessionsLengthMinutes = new JSpinner();
		spnSessionsLengthMinutes.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spnSessionsLengthMinutes.setBounds(144, 301, 57, 20);
		panel_8.add(spnSessionsLengthMinutes);
		
		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setBounds(211, 304, 67, 14);
		panel_8.add(lblMinutes);
		
		JCheckBox checkBox = new JCheckBox("4");
		checkBox.setBackground(new Color(176, 224, 230));
		checkBox.setBounds(270, 203, 45, 23);
		panel_8.add(checkBox);
		
		panelTimetables = new JPanel();
		tabbedPane.addTab("Generate Timetable", null, panelTimetables, null);
		panelTimetables.setLayout(null);
		
		JLabel label_1 = new JLabel("Max versions generated:");
		label_1.setBounds(10, 15, 137, 14);
		panelTimetables.add(label_1);
		
		spnTimetableMaxVersions = new JSpinner();
		spnTimetableMaxVersions.setBounds(176, 12, 59, 20);
		panelTimetables.add(spnTimetableMaxVersions);
		
		JButton button_15 = new JButton("Generate!");
		button_15.setBounds(242, 11, 89, 23);
		panelTimetables.add(button_15);
		
		Panel panel_6 = new Panel();
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(176, 224, 230));
		panel_6.setBounds(10, 47, 643, 372);
		panelTimetables.add(panel_6);
		
		JLabel label_8 = new JLabel("Viewing  timetable version");
		label_8.setBounds(10, 14, 146, 14);
		panel_6.add(label_8);
		
		spnTimetableViewVersion = new JSpinner();
		spnTimetableViewVersion.setBounds(166, 11, 59, 20);
		panel_6.add(spnTimetableViewVersion);
		
		JLabel label_16 = new JLabel("of 0");
		label_16.setBounds(235, 14, 46, 14);
		panel_6.add(label_16);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 39, 177, 20);
		panel_6.add(comboBox);
		
		List list_7 = new List();
		list_7.setBounds(10, 65, 175, 297);
		panel_6.add(list_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(202, 39, 319, 322);
		panel_6.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JButton button_16 = new JButton("Save as...");
		button_16.setBounds(531, 276, 102, 37);
		panel_6.add(button_16);
		
		JButton button_17 = new JButton("Copy");
		button_17.setBounds(531, 324, 102, 37);
		panel_6.add(button_17);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("The buttons below act on the entire timetable version, not just the section currently displayed.");
		textPane.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textPane.setBackground(new Color(176, 224, 230));
		textPane.setBounds(531, 142, 102, 128);
		panel_6.add(textPane);
		
		makeAvailabilityCollection();
	}
	
	private void makeAvailabilityCollection() {
		// spinners
		availabilitySpinners = new ArrayList<>();
		
		ArrayList<JSpinner[]> rooms = new ArrayList<>();
		rooms.add(new JSpinner[] {spnRoomsStartTime1, spnRoomsEndTime1});
		rooms.add(new JSpinner[] {spnRoomsStartTime2, spnRoomsEndTime2});
		rooms.add(new JSpinner[] {spnRoomsStartTime3, spnRoomsEndTime3});
		rooms.add(new JSpinner[] {spnRoomsStartTime4, spnRoomsEndTime4});
		rooms.add(new JSpinner[] {spnRoomsStartTime5, spnRoomsEndTime5});
		rooms.add(new JSpinner[] {spnRoomsStartTime6, spnRoomsEndTime6});
		
		availabilitySpinners.add(rooms);
		
		ArrayList<JSpinner[]> tutors = new ArrayList<>();
		rooms.add(new JSpinner[] {spnTutorsStartTime1, spnTutorsEndTime1});
		rooms.add(new JSpinner[] {spnTutorsStartTime2, spnTutorsEndTime2});
		rooms.add(new JSpinner[] {spnTutorsStartTime3, spnTutorsEndTime3});
		rooms.add(new JSpinner[] {spnTutorsStartTime4, spnTutorsEndTime4});
		rooms.add(new JSpinner[] {spnTutorsStartTime5, spnTutorsEndTime5});
		rooms.add(new JSpinner[] {spnTutorsStartTime6, spnTutorsEndTime6});
		
		availabilitySpinners.add(tutors);
		
		ArrayList<JSpinner[]> courses = new ArrayList<>();
		rooms.add(new JSpinner[] {spnCoursesStartTime1, spnCoursesEndTime1});
		rooms.add(new JSpinner[] {spnCoursesStartTime2, spnCoursesEndTime2});
		rooms.add(new JSpinner[] {spnCoursesStartTime3, spnCoursesEndTime3});
		rooms.add(new JSpinner[] {spnCoursesStartTime4, spnCoursesEndTime4});
		rooms.add(new JSpinner[] {spnCoursesStartTime5, spnCoursesEndTime5});
		rooms.add(new JSpinner[] {spnCoursesStartTime6, spnCoursesEndTime6});
		
		availabilitySpinners.add(courses);
		
		// check boxes
		availabilityCheckBoxes = new ArrayList<>();
		
		ArrayList<JCheckBox> roomsChk = new ArrayList<>();
		roomsChk.add(chkRoomsActive1);
		roomsChk.add(chkRoomsActive2);
		roomsChk.add(chkRoomsActive3);
		roomsChk.add(chkRoomsActive4);
		roomsChk.add(chkRoomsActive5);
		roomsChk.add(chkRoomsActive6);
		
		availabilityCheckBoxes.add(roomsChk);
		
		ArrayList<JCheckBox> tutorsChk = new ArrayList<>();
		roomsChk.add(chkTutorsActive1);
		roomsChk.add(chkTutorsActive2);
		roomsChk.add(chkTutorsActive3);
		roomsChk.add(chkTutorsActive4);
		roomsChk.add(chkTutorsActive5);
		roomsChk.add(chkTutorsActive6);
		
		availabilityCheckBoxes.add(tutorsChk);
		
		ArrayList<JCheckBox> coursesChk = new ArrayList<>();
		roomsChk.add(chkCoursesActive1);
		roomsChk.add(chkCoursesActive2);
		roomsChk.add(chkCoursesActive3);
		roomsChk.add(chkCoursesActive4);
		roomsChk.add(chkCoursesActive5);
		roomsChk.add(chkCoursesActive6);
		
		availabilityCheckBoxes.add(coursesChk);
		
		
		// day combo boxes
		availabilityCombo = new ArrayList<>();
		availabilityCombo.add(cmbRoomsDay);
		availabilityCombo.add(cmbTutorsDay);
		availabilityCombo.add(cmbCoursesDay);
		
		// semester spinners
		availabilitySemester = new ArrayList<>();
		availabilitySemester.add(spnRoomsSemester);
		availabilitySemester.add(spnTutorsSemester);
		availabilitySemester.add(spnCoursesSemester);
		
		// semester check boxes
		availabilitySameAllSemesters = new ArrayList<>();
		availabilitySameAllSemesters.add(chkRoomsSameAllSemesters);
		availabilitySameAllSemesters.add(chkTutorsSameAllSemesters);
		availabilitySameAllSemesters.add(chkCoursesSameAllSemesters);
		
	}
}