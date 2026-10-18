package task08;

import java.util.List;
import java.util.Optional;
import model.Course;

public class ReadOnlyCourseCatalog implements CourseReader {

    private final List<Course> courses;

    public ReadOnlyCourseCatalog(List<Course> courses) {
        this.courses = List.copyOf(courses);
    }

    @Override
    public Optional<Course> findByCode(String code) {
        return courses.stream().filter(c -> c.code().equals(code)).findFirst();
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }
}
