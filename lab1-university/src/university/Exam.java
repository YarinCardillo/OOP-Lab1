package university;

public class Exam {

	private Student student;
	private Course course;
	private int grade;

	/**
	 * Constructor
	 * @param studentIndex index of the student in the University class students array
	 * @param courseIndex index of the course in the University class courses array
	 * @param grade grade of the exam
	 */
	Exam(Student student, Course course, int grade){
		this.student = student;
		this.course = course;
		this.grade = grade;
	}

	String getExamInfo() {
		return student.getStudentFirst() + " got " + grade + " in " + course.getCourseTitle();
	}

	int getGrade() {
		return grade;
	}
}
