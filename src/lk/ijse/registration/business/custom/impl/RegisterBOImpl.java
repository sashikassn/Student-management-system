/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.business.custom.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.registration.business.*;
import lk.ijse.registration.business.custom.RegisterBO;
import lk.ijse.registration.dao.DAOFactory;
import lk.ijse.registration.dao.custom.RegisterDAO;
import lk.ijse.registration.db.HibernateUtil;
import lk.ijse.registration.dto.RegisterDTO;
import lk.ijse.registration.entity.Register;
import lk.ijse.registration.entity.Register_PK;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author SSNLIVE
 */
public class RegisterBOImpl implements RegisterBO {

    private RegisterDAO registerDAO;
    private SessionFactory sessionFactory;

    public RegisterBOImpl(){
        this.registerDAO = (RegisterDAO) DAOFactory.getInstance().getDAO(DAOFactory.dAOTypes.Register);
        sessionFactory = HibernateUtil.getSessionFactory();


    }


    public boolean registerStudent(RegisterDTO register) throws Exception {
       try(Session session = sessionFactory.openSession()) {
           registerDAO.setSession(session);

           session.beginTransaction();

           Register register1 = new Register(register.getStID(),register.getcID(),register.getRegDate(),register.getFee());
           registerDAO.save(register1);

           session.getTransaction().commit();
           return true;
       }catch (HibernateException exp){
           return false;

       }
    }

    @Override
    public boolean updateregistration(RegisterDTO register) throws Exception {
        try(Session session = sessionFactory.openSession()) {
            registerDAO.setSession(session);
            session.beginTransaction();
            Register_PK register_pk = new Register_PK(register.getStID(),register.getcID());
            Register register1 = registerDAO.find(register_pk);
            register1.setCourse(register1.getCourse());
            register1.setRegDate(register1.getRegDate());
            register1.setFee(register1.getFee());
            session.getTransaction().commit();
            return true;


        }catch (HibernateException exp){
            return false;

        }


    }


    public boolean deleteregistration(String stID, String cID) throws Exception {
      try (Session session = sessionFactory.openSession()){
          registerDAO.setSession(session);
          session.beginTransaction();
          Register_PK register_pk = new Register_PK(stID,cID);
          registerDAO.delete(register_pk);
          session.getTransaction().commit();
          return true;
      }catch (HibernateException exp){
          return false;

      }
    }


    public RegisterDTO findById(String stID, String cID) throws Exception {

        try(Session session = sessionFactory.openSession()){
            registerDAO.setSession(session);
            Register_PK register_pk = new Register_PK(stID,cID);
            Register register = registerDAO.find(register_pk);
            RegisterDTO registerDTO = new RegisterDTO(register.getRegister_PK().getStID(),register.getRegister_PK().getcID(),register.getRegDate(),register.getFee());
            return registerDTO;


        }catch (HibernateException exp){
            return null;
        }


    }

    @Override
    public ArrayList<RegisterDTO> getAll() throws Exception {
        try (Session session = sessionFactory.openSession()){

            registerDAO.setSession(session);
            session.beginTransaction();
            List<Register> allregs = registerDAO.getAll();
            session.getTransaction().commit();


           ArrayList<RegisterDTO> dtos = new ArrayList<>();


           for (Register register: allregs){
               RegisterDTO registerDTO = new RegisterDTO(register.getRegister_PK().getStID(),register.getRegister_PK().getcID(),register.getRegDate(),register.getFee());
               dtos.add(registerDTO);
           }
                return dtos;
        }catch (HibernateException exp){
            return null;
        }
        

    }
    
     
    
}
