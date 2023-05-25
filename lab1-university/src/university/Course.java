package university;

public class Course {

	private static final int MAX_STUDENTS = 100;
	private String title;
	private String teacher;
	private int courseCode;
	private Student[] attendingStudentsArray = new Student[MAX_STUDENTS];
	private int attendingStudentsArrayIndex = 0;
	private Exam[] examsArray = new Exam[MAX_STUDENTS];
	private int examsCounter = 0;

	/**
	 * Constructor
	 * @param first first of the student
	 * @param last last name of the student
	 * @param id id of the student
	 */
	Course(String title, String teacher, int codCorso){
		this.title = title;
		this.teacher = teacher;
		this.courseCode = codCorso;
	}

	// Course info getters
	String getCourseInfo() {
		return this.courseCode + "," + this.title + "," + this.teacher;
	}
	
	int getCourseCode(){
		return courseCode;
	}
	
	String getCourseTitle() {
		return title;
	}
	
	String getCourseTeacher() {
		return teacher;
	}

	// Student registration method with overflow check
	void registerStudent(Student student) {
		if(attendingStudentsArrayIndex<MAX_STUDENTS) {
			attendingStudentsArray[attendingStudentsArrayIndex++] = student;
		}
		else
			System.out.println("Unable to add another student, there are too many for this course!\n");
	}

	// Get attendees method
	String getAttendees(int courseCode) {
		String attendingStudentsList=attendingStudentsArray[0].getStudentInfo() + "\n";
		for(int i=1; i<attendingStudentsArrayIndex; i++) {
			attendingStudentsList+=attendingStudentsArray[i].getStudentInfo() + "\n";
		}
		return attendingStudentsList;
	}

	// Add exam and get average
	void addExam(Student student, Course course, int studentIndex, int grade) {
		examsArray[studentIndex] = new Exam(student, course, grade);
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
}
