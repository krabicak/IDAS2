package view.gui.libs;

import model.*;

import java.util.List;

public class Data {
    private List<Teacher> teachers;
    private List<Workplace> workplaces;
    private List<Subject> subjects;
    private List<FieldOfStudy> fieldOfStudies;
    private List<LearningAction> learningActions;

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Workplace> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<Workplace> workplaces) {
        this.workplaces = workplaces;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<FieldOfStudy> getFieldOfStudies() { return fieldOfStudies; }

    public void setFieldOfStudies(List<FieldOfStudy> fieldOfStudies) { this.fieldOfStudies = fieldOfStudies; }

    public List<LearningAction> getLearningActions() { return learningActions; }

    public void setLearningActions(List<LearningAction> learningActions) { this.learningActions = learningActions; }
}
