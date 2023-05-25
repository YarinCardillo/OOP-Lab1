package university;
import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

	private static final int MATR_START = 10000;
	private static final int COURSE_START = 10;
	private int nMatricola=MATR_START;
	private int codCorso=COURSE_START;
	private static Student[] studentArray = new Student[100];
	private static Course[] courseArray = new Course[50];
	private Student[] studentArrayByScore = new Student[100];
	private int studentArrayIndex=0;
	private int courseArrayIndex=0;
	private String name;
	private String firstRector;
	private String lastRector;

	// R1-------------------------------------------------------------------------------------
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		logger.info("Creating extended university object: " + name);
		this.name = name;
	}

	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		return name;
	}

	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		this.firstRector = first;
		this.lastRector = last;
	}

	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		return firstRector + " " + lastRector;
	}

	// R2-------------------------------------------------------------------------------------------
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		studentArray[studentArrayIndex] = new Student(first, last, nMatricola);
		logger.info("New student enrolled: " + studentArray[studentArrayIndex].getStudentInfo());
		studentArrayByScore[studentArrayIndex] = studentArray[studentArrayIndex++];
		nMatricola++;
		return nMatricola-1;
	}

	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		int index = id-MATR_START;
		return studentArray[index].getStudentInfo(id);
	}

	// R3------------------------------------------------------------------------------------------
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		courseArray[courseArrayIndex] = new Course(title, teacher, codCorso);
		logger.info("New course activated: " + courseArray[courseArrayIndex++].getCourseInfo());
		return codCorso++;
	}

	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		int index = code-COURSE_START;
		return 	courseArray[index].getCourseInfo();
	}

	// R4-------------------------------------------------------------------------------------------
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		int indexS = studentID-MATR_START;
		int indexC = courseCode-COURSE_START;
		studentArray[indexS].registerCourse(courseArray[indexC]);
		courseArray[indexC].registerStudent(studentArray[indexS]);
		logger.info("Student " + studentID + " signed up for course " + courseCode);
	}

	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		int index = courseCode-COURSE_START;
		return courseArray[index].getAttendees(courseCode);
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		int index = studentID-MATR_START;
		return studentArray[index].getStudyPlan(studentID);
	}

	// R5------------------------------------------------------------------------------------------
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentID, int courseID, int grade) {
		if(grade > 0 && grade < 31) {
			int studentIndex = studentID-MATR_START;
			int courseIndex = courseID-COURSE_START;
			studentArray[studentIndex].addExam(studentArray[studentIndex], courseArray[courseIndex], courseIndex, grade);
			courseArray[courseIndex].addExam(studentArray[studentIndex], courseArray[courseIndex], studentIndex, grade);
			logger.info("Student " + studentID + " took an exam in course " + courseID + " with grade " + grade);
			// Sort the score array with bubble sort
			sortByScore();
		}
		else
			System.out.println("Valore del voto non valido.\n");
	}
	// just sorting score array when needed (bubble sort)
	private void sortByScore() {
		Student temp;
		for(int i=0; i<studentArrayIndex-1; i++) {
			for(int j=0; j<studentArrayIndex-1-i; j++) {
				if(studentArrayByScore[j].getScore()<studentArrayByScore[j+1].getScore()) {
					temp = studentArrayByScore[j];
					studentArrayByScore[j] = studentArrayByScore[j+1];
					studentArrayByScore[j+1] = temp;
				}
			}
		}
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentID) {
		int index = studentID-MATR_START;
		float temp = studentArray[index].getAvg();
		if(temp > 0)
			return "Student " + studentID + " " + ":" + " " + temp;
		else
			return "Student " + studentID + " hasn't taken any exams\n";
	}

	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns 
	 * {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		int index = courseId-COURSE_START;
		float temp = courseArray[index].getAvg();
		if(temp > 0)
			return "The average for the course " + courseArray[index].getCourseTitle() + " is" + ": " + temp;
		else
			return "No student has taken the exam in " + courseArray[index].getCourseTitle();
	}

	// R6-----------------------------------------------------------------------------------------------
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * '''''
	 * the number of taken exams divided by the number of courses the student is enrolled to, 
	 * multiplied by 10.
	 * '''''
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the 
	 * highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info on the best three students.
	 */
	public String topThreeStudents() {
		String podium="";
		for(Student student : studentArrayByScore) {
			if(student != null && student.getAvg()!=0) {
				podium += student.getStudentFirst();
				podium += " ";
				podium += student.getStudentLast();
				podium += " : ";
				podium += student.getScore();
				podium += "\n";
			}
		}
		return podium;
	}

	// R7---------------------------------------------------------------------------------------------
	/**
	 * This field points to the logger for the class that can be used
	 * throughout the methods to log the activities.
	 */
	private final static Logger logger = Logger.getLogger("University");
}
