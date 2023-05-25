package university;

public class Student {

	private static final int MAX_COURSES = 25;
	private String first;
	private String last;
	private int id;
	private Course[] attendingCoursesArray = new Course[MAX_COURSES];
	private Exam[] examsArray = new Exam[MAX_COURSES];
	private int attendingCoursesArrayIndex = 0;
	private int examsCounter = 0;

	/**
	 * Constructor
	 * @param first first of the student
	 * @param last last name of the student
	 * @param id id of the student
	 */
	Student(String first, String last, int id){
		this.first = first;
		this.last = last;
		this.id = id;
	}

	// Student info getters
	String getStudentInfo(int id) {
		return id + " " + first + " " + last;
	}
	
	String getStudentInfo() {
		return id + " " + first + " " + last;
	}
	
	int getStudentId() {
		return id;
	}
	
	String getStudentFirst() {
		return first;
	}
	
	String getStudentLast() {
		return last;
	}

	// Course registration method with overflow check
	void registerCourse(Course course) {
		if(attendingCoursesArrayIndex<MAX_COURSES) {
			attendingCoursesArray[attendingCoursesArrayIndex++] = course;
		}
		else
			System.out.println("Unable to add another course, there are too many for this student!\n");
	}

	// Get Study plan method
	String getStudyPlan(int courseCode) {
		String attendingCoursesList=attendingCoursesArray[0].getCourseInfo() + "\n";
		for(int i=1; i<attendingCoursesArrayIndex; i++) {
			attendingCoursesList+=attendingCoursesArray[i].getCourseInfo() + "\n";
		}
		return attendingCoursesList;
	}

	// Add exam and get average
	void addExam(Student student, Course course, int courseIndex, int grade) {
		examsArray[courseIndex] = new Exam(student, course, grade);
		examsCounter++;
	}

	float getAvg() {
		float average=0;
		if(examsCounter != 0) {
			for(int i=0; i<examsArray.length; i++) {
				if(examsArray[i] != null)
					average+=examsArray[i].getGrade();
			}
			return average/(examsCounter);
		}
		else
			return 0;
	}

	float getScore() {
		if(attendingCoursesArrayIndex>0)
			return (float)getAvg()+((float)10*((float)examsCounter/(float)attendingCoursesArrayIndex));
		else
			return -1;
	}
}
