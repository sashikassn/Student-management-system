/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.business.custom;

import java.util.ArrayList;
import lk.ijse.registration.business.SuperBO;
import lk.ijse.registration.dto.CourseDTO;

/**
 *
 * @author SSNLIVE
 */
public interface CourseBO extends SuperBO{
    
    public boolean addCourse(CourseDTO addC)throws Exception;
    
    public boolean deleteCourse(String id)throws Exception;
    
    public boolean updateCourse(CourseDTO upC)throws Exception;
    
    public ArrayList<CourseDTO> getAllCourses()throws Exception;
    
    public CourseDTO findCourse(String id)throws Exception;
    
    public ArrayList<String> getCourseIDs()throws Exception;
}
