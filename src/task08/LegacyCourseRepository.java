package task08;

import java.util.ArrayList;
import java.util.List;
import model.Course;

public class LegacyCourseRepository {

    protected final List<Course> courses = new ArrayList<>();

    public void save(Course course) {
        courses.add(course);
    }

    public List<Course> findAll() {
        return List.copyOf(courses);
    }
}
