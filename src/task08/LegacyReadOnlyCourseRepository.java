package task08;

import java.util.List;
import model.Course;

public class LegacyReadOnlyCourseRepository extends LegacyCourseRepository {

    public LegacyReadOnlyCourseRepository(List<Course> snapshot) {
        courses.addAll(snapshot);
    }

    @Override
    public void save(Course course) {
        throw new UnsupportedOperationException("read-only");
    }
}
