package model;

import java.io.Serializable;
import java.util.Arrays;

public class Answer implements Serializable {
    @Override
    public String toString() {
        return "Answer{" +
                "student=" + student +
                ", answers=" + Arrays.toString(answers) +
                ", isRights=" + Arrays.toString(isRights) +
                ", alreadyRegistration=" + alreadyRegistration +
                '}';
    }

    static final long serialVersionUID = 2L;
    Student student;
    Object[] answers = null;
    boolean[] isRights;
    boolean alreadyRegistration = false;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setAnswers(Object[] answers) {
        this.answers = answers;
    }

    public void setIsRights(boolean[] isRights) {
        this.isRights = isRights;
    }


    public void setAlreadyRegistration(boolean alreadyRegistration) {
        this.alreadyRegistration = alreadyRegistration;
    }

    public static long getSerialVerisonUID() {
        return serialVersionUID;
    }

    public Student getStudent() {
        return student;
    }

    public Object[] getAnswers() {
        return answers;
    }

    public boolean[] getIsRights() {
        return isRights;
    }


    public boolean isAlreadyRegistration() {
        return alreadyRegistration;
    }

    public Answer() {
    }

    public Answer(Student student, Object[] answers, boolean[] isRights, boolean alreadyRegistration) {
        this.student = student;
        this.answers = answers;
        this.isRights = isRights;
        this.alreadyRegistration = alreadyRegistration;
    }
}
