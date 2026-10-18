package task15;

import model.Course;
import model.Student;

public record EnrollmentRequest(Student student, Course course) {}
