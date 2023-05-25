import university.*;

public class ExampleApp {

	public static void main(String[] args) {
// R1-----------------------------------------------------------------------------------------------
		String universityName = "Politecnico di Torino";
		
		University poli = new University(universityName);
		
		poli.setRector("Guido", "Saracco");
		System.out.println("R1------------------------------------------------");
		System.out.println("Rector of " + poli.getName() + " : " + poli.getRector());
		System.out.println("\n");
// R2-----------------------------------------------------------------------------------------------
		System.out.println("R2------------------------------------------------");
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		int s3 = poli.enroll("Marco","Codegone");
		System.out.println("Enrolled students " + s1 + ", " + s2 + ", " + s3); // 10000, 10001, 10002
		System.out.println("s1 = " + poli.student(s1)); // 10000 Mario Rossi
		System.out.println("s2 = " + poli.student(s2)); 
		System.out.println("s3 = " + poli.student(s3));
		System.out.println("\n");

// R3----------------------------------------------------------------------------------------------
		System.out.println("R3------------------------------------------------");
		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "James Gosling");
		int philo = poli.activate("Logic and Analytical Methods", "Ludwig Wittgenstein");
		System.out.println("Activated courses " + macro + " and " + oop + " and " + philo); // 10 and 11
		System.out.println("c1 = " + poli.course(10));
		System.out.println("c2 = " + poli.course(11));
		System.out.println("c3 = " + poli.course(12));
		System.out.println("\n");
// R4-----------------------------------------------------------------------------------------------
		System.out.println("R4------------------------------------------------");
		
		poli.register(s1, macro);
		poli.register(s1, oop);
		poli.register(s1, philo);
		
		poli.register(s2, macro);
		poli.register(s2, oop);
		poli.register(s2, philo);
		
		poli.register(s3, macro);
		poli.register(s3, oop);
		poli.register(s3, philo);
		
		System.out.println("Attendees of macro:\n" + poli.listAttendees(macro));
		// 10000 Mario Rossi
		// 10001 Giuseppe Verdi
		
		System.out.println("s2 study plan:\n" + poli.studyPlan(s2));
		// 10,Macro Economics,Paul Krugman
		// 11,Object Oriented Programming,James Gosling
		// 12,Logic and Analytical Methods,Ludwig Wittgenstein

// R5------------------------------------------------------------------------------------------------
		System.out.println("R5------------------------------------------------");
		poli.exam(s1, macro, 27);
		poli.exam(s1, oop, 27);
		poli.exam(s2, oop, 28);
		poli.exam(s3, macro, 29);
		System.out.println("StudentsAvgs:-----------------");
		System.out.println(poli.studentAvg(s1));
		System.out.println(poli.studentAvg(s2)); // 29.0
		System.out.println(poli.studentAvg(s3));
		System.out.println("CoursesAvgs:-----------------");
		System.out.println(poli.courseAvg(macro)); // 28.0
// R6------------------------------------------------------------------------------------------------
		System.out.println("R6------------------------------------------------");
		System.out.println("\n");
		System.out.println("Best students:\n" + poli.topThreeStudents());
	}
}
