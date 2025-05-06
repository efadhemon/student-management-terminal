package model;

public class AdvisedCourse {
    private int studentId;
    private String courseCode;

    public AdvisedCourse(int studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String toDBString() {
        return studentId + ","+courseCode+ "\n";
    }
}
