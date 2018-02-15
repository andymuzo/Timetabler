package ui;

import tests.SimpleTest04;
import tests.SpareTimeTest01;
import tests.TestData;
import dao.TimetablerDAO;
import data.Timetable;

/**
 * extremely basic (lack of a) UI, enters hard-coded values and outputs the
 * results as a basic text representative. Used for testing.
 * 
 * @author ajrog_000
 * 
 */
public class TextUI {

	Timetable timetable;
	TestData testData;
	TimetablerDAO timetablerDAO;

	public TextUI() {
		this.timetable = null;
		timetablerDAO = new TimetablerDAO();
		populateTestData();
		sendTestDataToDAO();
		calculateTimetable();
		displayResults();
	}

	/**
	 * change the data type in here when testing different timetables
	 */
	private void populateTestData() {
		testData = new SpareTimeTest01();
	}
	
	/**
	 * get each page of information in turn and pass it to the dao layer
	 */
	private void sendTestDataToDAO() {
		timetablerDAO.defineSettings(testData.getSettings());
		timetablerDAO.addCourses(testData.getCourses());
	}

	/**
	 * launch the timetable calculator
	 */
	private void calculateTimetable() {
		timetable = timetablerDAO.calculateTimetable();
	}

	/**
	 * display the results
	 */
	private void displayResults() {
		System.out.print(timetable.toString());
	}

}
