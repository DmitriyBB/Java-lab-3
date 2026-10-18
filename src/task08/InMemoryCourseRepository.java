package task08;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import model.Course;

public class InMemoryCourseRepository implements CourseRepository {

    private final Map<String, Course> byCode = new LinkedHashMap<>();

    @Override
    public void save(Course course) {
        byCode.put(course.code(), course);
    }

    @Override
    public Optional<Course> findByCode(String code) {
        return Optional.ofNullable(byCode.get(code));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(byCode.values());
    }
}
