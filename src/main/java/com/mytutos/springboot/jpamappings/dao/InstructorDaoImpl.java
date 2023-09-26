package com.mytutos.springboot.jpamappings.dao;

import com.mytutos.springboot.jpamappings.entity.Course;
import com.mytutos.springboot.jpamappings.entity.Instructor;
import com.mytutos.springboot.jpamappings.entity.InstructorDetail;
import com.mytutos.springboot.jpamappings.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstructorDaoImpl implements AppDao {

    private final EntityManager entityManager;

    public InstructorDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int instructorId) {
        return entityManager.find(Instructor.class, instructorId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int instructorId) {

        // Retrieve instructor
        Instructor tmpInstructor = findInstructorById(instructorId);

        List<Course> courses = tmpInstructor.getCourses();

        //break association
        for (Course tmpCourse : courses) {
            tmpCourse.setInstructor(null);
        }

        // Delete instructor of all courses with the instructor
        entityManager.remove(tmpInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int instructorDetailId) {

        // Retrieve instructor Detail
        InstructorDetail instructorDetail = findInstructorDetailById(instructorDetailId);

        // Delete instructorDetail
        entityManager.remove(instructorDetail);
    }

    @Override
    @Transactional
    public void deleteOnlyInstructorDetailById(int instructorDetailId) {

        // Retrieve instructor Detail
        InstructorDetail instructorDetail = findInstructorDetailById(instructorDetailId);

        // Remove the associated object reference
        // break bidirectional link
        instructorDetail.getInstructor().setInstructorDetail(null);

        // Delete instructorDetail
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {

        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);

        query.setParameter("data", instructorId);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdWithCoursesJoinFetch(int instructorId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "where i.id = :data", Instructor.class);

        query.setParameter("data", instructorId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }


    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int courseId) {

        // get course from db...
        Course tempCourse = findCourseById(courseId);

        // delete..
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }


    @Override
    public Course findCourseWithReviewsByCourseId(int courseId) {

        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id = :data", Course.class);
        query.setParameter("data", courseId);

        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int courseId) {

        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c JOIN FETCH c.students WHERE c.id = :data", Course.class);
        query.setParameter("data", courseId);

        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int studentId) {

        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id = :data", Student.class);
        query.setParameter("data", studentId);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int studentId) {

        Student tempStudent = entityManager.find(Student.class, studentId);

        entityManager.remove(tempStudent);
    }
}
