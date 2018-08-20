/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.registration.business.BOFactory;
import lk.ijse.registration.business.custom.CourseBO;
import lk.ijse.registration.dto.CourseDTO;
import lk.ijse.registration.main.IjseRegistration;
import lk.ijse.registration.view.util.tblmodel.CourseTM;

/**
 * FXML Controller class
 *
 * @author SSNLIVE
 */
public class ManageCourseFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtCourseName;
    @FXML
    private JFXTextField txtCourseID;
    @FXML
    private TableView<CourseTM> tblCourse;
    @FXML
    private JFXTextField txtDuration;
    
    private Boolean btn;
    
    
    CourseBO courseBO = (CourseBO)BOFactory.getInstance().getBO(BOFactory.BOType.Course);

    /**
     * Initializes the controller class.
     */
    
    
    private void loadAllCustomers(){
        
        try {
            ArrayList<CourseDTO> allCourses = courseBO.getAllCourses();
            
            ObservableList<CourseTM> olCourses = tblCourse.getItems();
            olCourses.removeAll(olCourses);
            
            for (CourseDTO course : allCourses) {
                CourseTM courseTM = new CourseTM(course.getcID(),course.getName(), course.getDuration());
                olCourses.add(courseTM);
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCourseFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseID"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        loadAllCustomers();
    }    

    @FXML
    private void addCourseBtn(ActionEvent event) {
        String cID = txtCourseID.getText();
        String name = txtCourseName.getText();
        String duration = txtDuration.getText();
        
        CourseDTO course = new CourseDTO(cID, name, duration);
        
        try {
            Boolean result = courseBO.addCourse(course);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Course has been successfully added.", ButtonType.OK).show();
                loadAllCustomers();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Error.", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCourseFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void removeCourseBtn(ActionEvent event) {
        try {
            String cID = tblCourse.getSelectionModel().getSelectedItem().getCourseID();
            if(cID != null){
                Boolean result = courseBO.deleteCourse(cID);
                if(result){
                    new Alert(Alert.AlertType.INFORMATION, "Course had been successfully Deleted.", ButtonType.OK).show();
                    loadAllCustomers();
                }else{
                    new Alert(Alert.AlertType.INFORMATION, "Error.", ButtonType.OK).show();
                }
            }else{
                    
            
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManageCourseFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    private void clear(){
        txtCourseID.setText("");
        txtCourseName.setText("");
        txtDuration.setText("");
    }

    @FXML
    private void navigateToHome(MouseEvent event) {
        IjseRegistration.navigateToHome(root, (Stage)this.root.getScene().getWindow());
    }

    @FXML
    private void updateCourseBtn(ActionEvent event) {
        String cID = txtCourseID.getText();
        String name = txtCourseName.getText();
        String duration  = txtDuration.getText();
        
        CourseDTO courseDTO = new CourseDTO(cID, name, duration);
        
        try {
            Boolean result = courseBO.updateCourse(courseDTO);
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Course has been updated Successfully..",ButtonType.OK).show();
                loadAllCustomers();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Error when updating..",ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCourseFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
