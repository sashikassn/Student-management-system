/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.business;

import lk.ijse.registration.business.custom.impl.CourseBOImpl;
import lk.ijse.registration.business.custom.impl.RegisterBOImpl;
import lk.ijse.registration.business.custom.impl.StudentBOImpl;

/**
 *
 * @author SSNLIVE
 */
public class BOFactory {
    
    private static BOFactory boFactory;

    private  BOFactory() {
    }
    
    public static BOFactory getInstance(){
        if(boFactory == null){
           boFactory =  new BOFactory();
        }
        return boFactory;
        
    }
    
    
    public enum BOType{
        Student , Course , Register
    }
    
    public SuperBO getBO(BOType boType){
        switch(boType){
            case Student:
                return new StudentBOImpl();
            case Course:
                return new CourseBOImpl();
            case Register:
                return new RegisterBOImpl();
            default:
                return null;
        }
    }
    
    
    
    
}
