/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.business.custom;

import java.util.ArrayList;
import lk.ijse.registration.business.SuperBO;
import lk.ijse.registration.dto.RegisterDTO;

/**
 *
 * @author SSNLIVE
 */
public interface RegisterBO extends SuperBO{
    
    public boolean registerStudent(RegisterDTO register)throws Exception;
    
    public boolean updateregistration(RegisterDTO register)throws Exception;
    
    public boolean deleteregistration(String stID,String cID )throws Exception;
    
    public ArrayList<RegisterDTO> getAll()throws Exception;
    
    public RegisterDTO findById(String stID,String cID)throws Exception;
    
    
}
