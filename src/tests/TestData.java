package tests;

import java.util.Set;

import data.Course;
import data.Room;
import data.Settings;
import data.Tutor;

public interface TestData {
	
	public Set<Course> getCourses();
	
	public Settings getSettings();
	
}
