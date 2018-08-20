/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.registration.view.util.tblmodel;

/**
 *
 * @author SSNLIVE
 */
public class CourseTM {
    private String courseID;
    private String name;
    private String duration;

    public CourseTM() {
    }

    public CourseTM(String courseID, String name, String duration) {
        this.courseID = courseID;
        this.name = name;
        this.duration = duration;
    }

    /**
     * @return the cID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * @param cID the cID to set
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    


}