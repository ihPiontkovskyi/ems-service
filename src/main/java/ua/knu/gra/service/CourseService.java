package ua.knu.gra.service;

import com.nimbusds.jose.util.Base64;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.knu.gra.data.PrefData;
import ua.knu.gra.data.UserData;
import ua.knu.gra.data.course.CourseAddData;
import ua.knu.gra.data.course.CourseData;
import ua.knu.gra.data.course.CourseMainPageData;
import ua.knu.gra.model.CourseModel;
import ua.knu.gra.model.GroupPreferencesModel;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.repository.CourseRepository;
import ua.knu.gra.repository.PrefRepository;
import ua.knu.gra.service.common.MapperUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {

    private final CourseRepository courseRepository;
    private final PrefRepository prefRepository;

    public List<CourseMainPageData> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(MapperUtil::mapToMainPageCourseData)
                .collect(Collectors.toList());
    }

    public CourseData getByUid(String uid, UserModel current) {
        CourseModel course = courseRepository.findByUid(uid).orElseThrow(() -> new RuntimeException("Invalid uid"));
        if (current == null) {
            throw new RuntimeException("Invalid username");
        }
        return MapperUtil.mapToCourseData(course, current);
    }

    public void deleteByUid(String uid) {
        CourseModel courseModel = courseRepository.findByUid(uid).orElseThrow(() -> new RuntimeException("Invalid uid"));
        courseRepository.delete(courseModel);
    }

    public void addCourse(CourseAddData addData, UserModel current) {
        CourseModel model = new CourseModel();
        model.setDescription(addData.getDescription());
        model.setName(addData.getName());
        model.setLecturer(current);
        model.setUid(Base64.encode(UUID.randomUUID().toString()).toString().toUpperCase());
        courseRepository.save(model);
    }

    public void joinCourse(String courseUid, UserModel current) {
        CourseModel course = courseRepository.findByUid(courseUid).orElseThrow(() -> new RuntimeException("Invalid uid"));
        course.getUsers().add(current);
        courseRepository.save(course);
    }

    public List<UserData> getUserListing(String courseUid) {
        CourseModel course = courseRepository.findByUid(courseUid).orElseThrow(() -> new RuntimeException("Invalid uid"));
        return course.getUsers().stream()
                .map(MapperUtil::mapToUserData)
                .collect(Collectors.toList());
    }

    public void addUserPreferences(List<PrefData> data, UserModel current) {
        List<GroupPreferencesModel> prefs = data.stream()
                .map(pref -> createPrefModel(pref, current.getUid()))
                .collect(Collectors.toList());
        prefRepository.saveAll(prefs);
    }

    public GroupPreferencesModel createPrefModel(PrefData data, String currentUid) {
        GroupPreferencesModel model = new GroupPreferencesModel();
        model.setPrefUserUid(data.getPrefUserId());
        model.setUserUid(currentUid);
        model.setPriority(data.getPriority());
        return model;
    }
}
