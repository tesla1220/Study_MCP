package practice.mcp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import practice.mcp.model.dto.CourseDTO;
import practice.mcp.model.dto.SectionDTO;
import practice.mcp.model.service.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController_2 {

    private final CourseService CourseServie;

    @Autowired
    public CourseController_2(CourseService courseService){
        this.CourseServie = courseService;
    }

    @GetMapping("/add")
    public ModelAndView addCourse(HttpSession httpSession, ModelAndView mv){
        mv.addObject("username", httpSession.getAttribute("username"));
        mv.setViewName("/course/add");
        return mv;
    }

    @PostMapping("/add")
    public String addCourse(String courseName, HttpSession httpSession, HttpServletRequest request){

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName(courseName);
        List<SectionDTO> sectionDTOList = new ArrayList<>();
        Map<String, String[]> paratemers = request.getParameterMap();


    }

}
