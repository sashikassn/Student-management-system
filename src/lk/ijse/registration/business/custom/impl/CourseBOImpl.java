/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.business.custom.impl;

import lk.ijse.registration.business.custom.CourseBO;
import lk.ijse.registration.dao.DAOFactory;
import lk.ijse.registration.dao.custom.CourseDAO;
import lk.ijse.registration.db.HibernateUtil;
import lk.ijse.registration.dto.CourseDTO;
import lk.ijse.registration.entity.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SSNLIVE
 */
public class CourseBOImpl implements CourseBO {

    private CourseDAO courseDAO;
    private SessionFactory sessionFactory;


    public CourseBOImpl() {
        this.courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.dAOTypes.Course);
        sessionFactory = HibernateUtil.getSessionFactory();

    }


    public boolean addCourse(CourseDTO addC) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            courseDAO.setSession(session);
            session.beginTransaction();
            Course course = new Course(addC.getcID(), addC.getName(), addC.getDuration());
            courseDAO.save(course);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exp) {
            return false;

        }
    }

    public boolean deleteCourse(String id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            courseDAO.setSession(session);
            session.beginTransaction();
            courseDAO.delete(id);
            session.getTransaction().commit();
            return true;

        } catch (HibernateException exp) {
            return false;
        }
    }

    public boolean updateCourse(CourseDTO upC) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            courseDAO.setSession(session);
            session.beginTransaction();
            Course course = courseDAO.find(upC.getcID());
            course.setDuration(upC.getDuration());
            course.setName(upC.getName());
            course.setcID(upC.getcID());
            courseDAO.update(course);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException exp) {
            return false;

        }
    }

    public ArrayList<CourseDTO> getAllCourses() throws Exception {

        try (Session session = sessionFactory.openSession()) {
            courseDAO.setSession(session);
            session.beginTransaction();
            List<Course> allCourses = courseDAO.getAll();
            session.getTransaction().commit();

            ArrayList<CourseDTO> dtos = new ArrayList<>();

            for (Course c : allCourses) {
                CourseDTO coursesDTO = new CourseDTO(c.getcID(), c.getName(), c.getDuration());
                dtos.add(coursesDTO);
            }
            return dtos;
        } catch (HibernateException exp) {
            return null;

        }


    }

    public CourseDTO findCourse(String id) throws Exception {

        try (Session session = sessionFactory.openSession()) {
            courseDAO.setSession(session);
            Course course = courseDAO.find(id);
            CourseDTO courseDTO = new CourseDTO(course.getcID(), course.getName(), course.getDuration());
            return courseDTO;
        } catch (HibernateException exp) {
            return null;
        }

//        Course c = courseDAO.find(id);
//        CourseDTO cDTO = new CourseDTO(c.getcID(),c.getName(),c.getDuration());
//        return cDTO;
    }

    public ArrayList<String> getCourseIDs() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            courseDAO.setSession(session);
            session.beginTransaction();
            List<Course> allcourseIds = courseDAO.getAll();
            session.getTransaction().commit();
            ArrayList<String> allids = new ArrayList<>();
            for (Course course : allcourseIds
                    ) {
                allids.add(course.getcID());

            }
            return allids;
        }
    }

}
