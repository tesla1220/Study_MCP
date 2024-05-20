package practice.mcp.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.mcp.model.dao.CourseMapper;

@Service
public class CourseService {

    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseMapper courseMapper){
        this.courseMapper = courseMapper;
    }


}
