package com.mytutos.springboot.jpamappings.dao;

import com.mytutos.springboot.jpamappings.entity.Course;
import com.mytutos.springboot.jpamappings.entity.Instructor;
import com.mytutos.springboot.jpamappings.entity.InstructorDetail;
import com.mytutos.springboot.jpamappings.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppDao {

    void save(Instructor instructor);

    void save(Course theCourse);

    Instructor findInstructorById(int instructorId);

    void deleteInstructorById(int instructorId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int instructorDetailId);

    void deleteOnlyInstructorDetailById(int instructorDetailId);

    List<Course> findCoursesByInstructorId(int instructorId);

    Instructor findInstructorByIdWithCoursesJoinFetch(int instructorId);

    void update(Instructor tempInstructor);

    void updateCourse(Course tempCourse);

    Course findCourseById(int courseId);

    void deleteCourseById(int courseId);

    Course findCourseWithReviewsByCourseId(int courseId);

    Course findCourseAndStudentsByCourseId(int courseId);

    Student findStudentAndCoursesByStudentId(int studentId);

    void update(Student student);

    void deleteStudentById(int studentId);



}
