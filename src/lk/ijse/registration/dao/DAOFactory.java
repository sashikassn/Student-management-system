/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.dao;

import lk.ijse.registration.dao.custom.impl.CourseDAOImpl;
import lk.ijse.registration.dao.custom.impl.QueryDAOImpl;
import lk.ijse.registration.dao.custom.impl.RegisterDAOImpl;
import lk.ijse.registration.dao.custom.impl.StudentDAOImpl;
import lk.ijse.registration.entity.Course;
import lk.ijse.registration.entity.Register;

/**
 *
 * @author SSNLIVE
 */
public class DAOFactory {
    private static DAOFactory dAOFactory;
    
    public enum dAOTypes{
        Student ,Course , Register, Query;
    }
    
    private DAOFactory(){
        
    }
    public static DAOFactory getInstance(){
        if(dAOFactory == null){
            dAOFactory = new DAOFactory();
            
        }
        return  dAOFactory;
    }
    
    public SuperDAO getDAO(dAOTypes daoType) {
        switch (daoType) {
            case Student:
                return new StudentDAOImpl();
            case Course:
                return new CourseDAOImpl();
            case Register:
                return new RegisterDAOImpl();
            case Query:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
    
}
