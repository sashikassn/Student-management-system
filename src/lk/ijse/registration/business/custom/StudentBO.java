/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.business.custom;

import java.util.ArrayList;
import lk.ijse.registration.business.SuperBO;
import lk.ijse.registration.dto.StudentDTO;

/**
 *
 * @author SSNLIVE
 */
public interface StudentBO extends SuperBO{
    
    public boolean addStudent(StudentDTO student)throws Exception;
    
    public ArrayList<StudentDTO> getAllStudents ()throws Exception;
    
    public boolean deleteStudent(String id)throws Exception;
    
    public boolean updateStudent(StudentDTO student)throws Exception;
    
    public StudentDTO findStudent(String id)throws Exception;
    
}
