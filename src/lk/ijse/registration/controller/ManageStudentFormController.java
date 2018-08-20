/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.registration.business.BOFactory;
import lk.ijse.registration.business.custom.StudentBO;
import lk.ijse.registration.dto.StudentDTO;
import lk.ijse.registration.main.IjseRegistration;
import lk.ijse.registration.view.util.tblmodel.StudentTM;

/**
 * FXML Controller class
 *
 * @author SSNLIVE
 */
public class ManageStudentFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtStudentID;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXTextField txtStudentAdr;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private TableView<StudentTM> tblStudent;
    @FXML
    private JFXButton btnAdd1;
    @FXML
    private JFXButton btnAdd11;
    
    
    StudentBO studentBO = (StudentBO)BOFactory.getInstance().getBO(BOFactory.BOType.Student);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("stID"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        loadAllStudents();
    }    
    
    private void loadAllStudents(){
        try {
            ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();
            ObservableList<StudentTM> students = tblStudent.getItems();
            students.removeAll(students);
            
            for (StudentDTO student : allStudents) {
                StudentTM st = new StudentTM(student.getStID(), student.getName(), student.getAddress());
                students.add(st);
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(ManageStudentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void AddStudent(ActionEvent event) {
        try {
            String stID = txtStudentID.getText();
            String name = txtStudentName.getText();
            String address = txtStudentAdr.getText();
            StudentDTO studentDTO = new StudentDTO(stID, name, address);
            
            Boolean result = studentBO.addStudent(studentDTO);
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Student had been added successfully", ButtonType.OK).show();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.INFORMATION, " Something went wrong", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageStudentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void navigateToHome(ActionEvent event) {
        IjseRegistration.navigateToHome(root, (Stage)this.root.getScene().getWindow());
    }

    @FXML
    private void removeStudent(ActionEvent event) {
        
        try {
            String stId = tblStudent.getSelectionModel().getSelectedItem().getStID();
            
            Boolean result = studentBO.deleteStudent(stId);
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Student had been deleted successfully", ButtonType.OK).show();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.INFORMATION, " error", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageStudentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateStudent(ActionEvent event) {
        
        try {
            String stID = txtStudentID.getText();
            String name = txtStudentName.getText();
            String address = txtStudentAdr.getText();
            StudentDTO studentDTO = new StudentDTO(stID, name, address);
            
            Boolean result = studentBO.updateStudent(studentDTO);
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION, "Student had been Updated successfully", ButtonType.OK).show();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Update error", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageStudentFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
