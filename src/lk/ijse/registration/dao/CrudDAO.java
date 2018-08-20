/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.dao;

import java.util.List;

/**
 *
 * @author SSNLIVE
 */
public interface CrudDAO<T,ID> extends SuperDAO {

    public void save(T entity)throws Exception;
    
    public void delete(ID id)throws Exception;

    public void update(T entity) throws Exception;
    
    public List<T> getAll()throws Exception;
    
    public T find(ID id)throws Exception;
    

}
