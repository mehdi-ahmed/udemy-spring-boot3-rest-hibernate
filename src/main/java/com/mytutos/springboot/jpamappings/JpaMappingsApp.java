package com.mytutos.springboot.jpamappings;

import com.mytutos.springboot.jpamappings.dao.AppDao;
import com.mytutos.springboot.jpamappings.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaMappingsApp {

	public static void main(String[] args) {
		SpringApplication.run(JpaMappingsApp.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao) {


		// return runner -> createInstructor(appDao);
		// return runner -> findInstructor(appDao);
		// return runner -> deleteInstructor(appDao);
		// return runner -> findInstructorDetail(appDao);
		// return runner -> deleteInstructorDetail(appDao);
		// return runner -> deleteOnlyInstructionDetail(appDao);
		// return runner -> createInstructorWithCourses(appDao);
		// return runner -> findInstructorWithCourses(appDao); ==> Exception
		// return runner -> findCoursesForInstructor(appDao);
		// return runner -> findInstructorJoinFetch(appDao);
		// return runner -> updateInstructor(appDao);
		// return runner -> updateCourse(appDao);
		// return runner -> deleteInstructor(appDao);
		// return runner -> deleteCourse(appDao);

		// return runner -> createCourseAndReviews(appDao);
		// return runner -> findCourseWithReviews(appDao);
		// return runner -> deleteCourseWithReviews(appDao);

		// Many to Many
		// return runner -> createCourseAndStudents(appDao);
		// return runner -> findCourseWithStudents(appDao);
		// return runner -> findStudentAndCoursesHeFollows(appDao);
		// return runner -> addCoursesForStudent(appDao);
		// return runner -> deleteCourse(appDao);
		return runner -> deleteStudent(appDao);

	}

	private void deleteStudent(AppDao appDao) {

		int studentId = 1;

		System.out.println("Deleting Student with ID = " + studentId);
		appDao.deleteStudentById(studentId);

		System.out.println("Done ! ");
	}

	void test(AppDao appDao) {

	}

	private void addCoursesForStudent(AppDao appDao) {
		int studentId = 1;

		Student tempStudent = appDao.findStudentAndCoursesByStudentId(studentId);
		tempStudent.addCourse(new Course("AWS DEVOPS PRO"));
		tempStudent.addCourse(new Course("Machine Learning"));
		tempStudent.addCourse(new Course("ChatGPT"));

		System.out.println("Updating Student = " + tempStudent);
		System.out.println("New Courses Added = " + tempStudent.getCourses());

		// Update
		appDao.update(tempStudent);

		System.out.println("Done ! ");
	}

	private void findStudentAndCoursesHeFollows(AppDao appDao) {

		int studentId = 1;
		Student tempStudent = appDao.findStudentAndCoursesByStudentId(studentId);
		System.out.println("Student = " + tempStudent);
		System.out.println("Courses he follows = " + tempStudent.getCourses());

		System.out.println("Done ! ");
	}

	private void findCourseWithStudents(AppDao appDao) {

		int courseId = 10;
		Course tempCourse = appDao.findCourseAndStudentsByCourseId(courseId);
		System.out.println("Course = " + tempCourse);
		System.out.println("Students attending = " + tempCourse.getStudents());

		System.out.println("Done ! ");
	}

	private void createCourseAndStudents(AppDao appDao) {

		// create Course
		Course theCourse = new Course("Python for Noobs");

		// Create the students
		Student s1 = new Student("Marcus", "Ammar", "marcus@awsfanboy.com");
		Student s2 = new Student("Sophie", "Coucke", "sophie@awsfanboy.com");
		Student s3 = new Student("Ilona", "Ammar", "ilona@awsfanboy.com");

		// Add students to the course
		theCourse.addStudent(s1);
		theCourse.addStudent(s2);
		theCourse.addStudent(s3);

		// Save the course and associate it with the students
		System.out.println("Saving Course  " + theCourse);
		System.out.println("Students attending are : " + theCourse.getStudents());
		appDao.save(theCourse);    // Cascade Persist will also save the students

		System.out.println("Done ! ");
	}

	private void deleteCourseWithReviews(AppDao appDao) {

		int courseId = 10;
		System.out.println("Deleting course with ID: " + courseId);
		appDao.deleteCourseById(courseId);
		System.out.println("Done ! ");
	}

	private void findCourseWithReviews(AppDao appDao) {

		int courseId = 10;
		Course tempCourse = appDao.findCourseWithReviewsByCourseId(courseId);
		System.out.println("tempCourse = " + tempCourse);
		System.out.println("Reviews Associated = " + tempCourse.getReviews());

		System.out.println("Done ! ");

	}

	private void createCourseAndReviews(AppDao appDao) {

		Course tempCourse = new Course("AWS Architect Pro - Cantrill");
		tempCourse.addReview(new Review("Amazing and Clear"));
		tempCourse.addReview(new Review("Average. Could do better"));
		tempCourse.addReview(new Review("Must buy now !!!"));

		// Save course and ... Review thanks to Cascade All
		System.out.println("Saving Course...");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		appDao.save(tempCourse);

		System.out.println("Done ! ");
	}

	private void deleteCourse(AppDao appDao) {

		int courseId = 13;
		System.out.println("Deleting course with ID: " + courseId);
		appDao.deleteCourseById(courseId);
		System.out.println("Done ! ");
	}

	private void updateCourse(AppDao appDao) {

		int courseId = 24;

		// find course
		Course tempCourse = appDao.findCourseById(courseId);

		// update course
		tempCourse.setTitle("Docker with Kubernetes");
		appDao.updateCourse(tempCourse);

		System.out.println("Done ! ");
	}

	private void updateInstructor(AppDao appDao) {

		int theId = 5;
		System.out.println("Finding instructor with ID: " + theId);
		Instructor tempInstructor = appDao.findInstructorByIdWithCoursesJoinFetch(theId);

		// update instructor
		tempInstructor.setLastName("Ammar");
		appDao.update(tempInstructor);

		System.out.println("Done ! ");
	}

	private void findInstructorJoinFetch(AppDao appDao) {
		int theId = 5;
		System.out.println("Finding instructor with ID: " + theId);
		Instructor tempInstructor = appDao.findInstructorByIdWithCoursesJoinFetch(theId);

		System.out.println("Instructor = " + tempInstructor);

		// Find courses
		System.out.println("Finding courses for instructor with ID = " + tempInstructor.getCourses());
		System.out.println("Done ! ");
	}

	private void findCoursesForInstructor(AppDao appDao) {

		int theId = 5;
		System.out.println("Finding instructor with ID: " + theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("Instructor = " + tempInstructor);

		// Find courses
		System.out.println("Finding courses for instructor with ID = " + tempInstructor.getId());
		List<Course> courses = appDao.findCoursesByInstructorId(tempInstructor.getId());

		// Do the association
		tempInstructor.setCourses(courses);

		System.out.println("courses associated are = " + courses);
		System.out.println("Done ! ");
	}

	private void findInstructorWithCourses(AppDao appDao) {

		int theId = 5;
		System.out.println("Finding instructor with ID: " + theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("Instructor = " + tempInstructor);
		System.out.println("The associated courses are  = " + tempInstructor.getCourses());
		System.out.println("Done ! ");
	}

	private void createInstructorWithCourses(AppDao appDao) {

		// Instructor
		Instructor tempInstructor =
				new Instructor("Marcus", "Ahmed", "marcus@awsfanboy.com");

		// InstructorDetail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("www.youtube.com/awsfanboy", "Destiny 2");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Courses
		Course course1 = new Course("Spring Boot 3", tempInstructor);
		Course course2 = new Course("Java 17", tempInstructor);
		Course course3 = new Course("Docker", tempInstructor);

		// Add course to instructor
		tempInstructor.add(course1);
		tempInstructor.add(course2);
		tempInstructor.add(course3);

		// save
		// This will also persist courses in Course Table because of Cascade PERSIST
		System.out.println("Saving Instructor...");
		System.out.println("courses = " + tempInstructor.getCourses());
		appDao.save(tempInstructor);

		System.out.println("Done ! ");
	}

	private void deleteOnlyInstructionDetail(AppDao appDao) {

		int theId = 3;
		System.out.println("Deleting Only Instructor Detail with ID = " + theId);
		appDao.deleteOnlyInstructorDetailById(theId);

		System.out.println("Done ! ");
	}

	private void deleteInstructorDetail(AppDao appDao) {

		// Deleting instructor Detail
		int theId = 4;
		System.out.println("Deleting Instructor Detail with ID = " + theId);
		appDao.deleteInstructorDetailById(theId);

		System.out.println("Done ! ");
	}

	private void findInstructorDetail(AppDao appDao) {

		int theId = 3;
		System.out.println("finding Instructor Details with ID = " + theId);

		InstructorDetail instructorDetail = appDao.findInstructorDetailById(theId);
		System.out.println("instructorDetail = " + instructorDetail);
		System.out.println("instructor associated = " + instructorDetail.getInstructor());

		System.out.println("Done ! ");
	}

	private void deleteInstructor(AppDao appDao) {

		// Deleting instructor
		int theId = 6;
		System.out.println("Deleting Instructor with ID = " + theId);
		appDao.deleteInstructorById(theId);

		System.out.println("Done ! ");
	}

	private void findInstructor(AppDao appDao) {

		int theId = 1;
		System.out.println("finding Instructor with ID = " + theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("Instructor  = " + tempInstructor);
		System.out.println("Instructor Details = " + tempInstructor.getInstructorDetail());

		System.out.println("Done ! ");
	}

	private void createInstructor(AppDao appDao) {

		// Instructor
		Instructor tempInstructor =
				new Instructor("Marcus", "Ahmed", "marcus@awsfanboy.com");
		Instructor tempInstructor2 =
				new Instructor("Sophie", "Coucke", "sophie@meditation.com");

		// InstructorDetail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("www.youtube.com/awsfanboy", "Destiny 2");

		InstructorDetail tempInstructorDetail2 =
				new InstructorDetail("www.youtube.com/meditation", "Meditation");

		tempInstructor.setInstructorDetail(tempInstructorDetail);
		tempInstructor2.setInstructorDetail(tempInstructorDetail2);

		System.out.println("Saving Instructor in the database...");
		appDao.save(tempInstructor);
		appDao.save(tempInstructor2);

		System.out.println("Done ! ");
	}
}
