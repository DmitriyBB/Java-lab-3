package task08;

import java.util.List;
import java.util.Optional;
import model.Course;

public interface CourseReader {

    Optional<Course> findByCode(String code);

    List<Course> findAll();
}
