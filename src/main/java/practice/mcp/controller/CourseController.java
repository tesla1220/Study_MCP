package practice.mcp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import practice.mcp.model.dto.CourseDTO;
import practice.mcp.model.dto.SectionDTO;
import practice.mcp.model.service.CourseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;


    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/add")
    public ModelAndView addCourse(HttpSession session, ModelAndView mv) {
        // 메소드 이름은 addCourse야. 두 개의 정보를 받아: 세션(session)과 ModelAndView(mv).
        // session은 사용자에 대한 정보를 담고 있고, ModelAndView는 웹 페이지를 만들 때 필요한 도구야.

        mv.addObject("username", session.getAttribute("username"));
            // 여기서는 ModelAndView에 "username"이라는 정보를 추가해.
            // 이 정보는 세션에서 가져온 "username"에 해당해.

        mv.setViewName("/course/add");
            // 페이지의 보여줄 뷰를 설정해줘. "/course/add"라는 뷰를 보여줄 거야.
            // 이 뷰는 코스를 추가할 수 있는 폼을 보여주는 페이지야.

        return mv;
            //  ModelAndView 객체를 반환해. 이 객체에는 사용자의 이름이 포함된 새로운 정보가 담겨져 있어.
            //  페이지에서 이 정보를 사용해서 원하는 대로 보여줄 수 있어.

    }

    @PostMapping("/add")
    public String addCourse(String courseName, HttpSession httpSession, HttpServletRequest request){

        CourseDTO courseDTO = new CourseDTO();
        // 새로운 코스 정보를 저장하기 위한 새로운 종이를 만들어. 이 종이에는 코스의 이름이 적히겠지.

        courseDTO.setCourseName(courseName);
        // 새로운 종이에 코스의 이름을 적어. 이 이름은 사용자가 입력한 코스 이름이겠지.

        List<SectionDTO> sectionDTOList = new ArrayList<>();
        // 여기서는 섹션들의 정보를 저장하기 위한 목록을 만들어. 이 목록에는 새로운 섹션들의 정보를 추가할 수 있어.

        /*  List 는 데이터를 순서대로 저장하는 방법을 정의한 인터페이스이고,
            ArrayList 는 그 인터페이스를 구현한 클래스 중 하나입니다.
            따라서 ArrayList 는 List 의 규칙을 따르며, List 로 선언된 변수에 할당할 수 있습니다.

            여기서 ArrayList 를 사용하는 이유: 가변 크기
            -> ArrayList 는 배열을 기반으로 하며, 내부적으로 크기를 동적으로 조절할 수 있어요.
            즉, 필요에 따라 요소를 추가하거나 제거할 수 있어요. */

        Map<String, String[]> parameters = request.getParameterMap();
        //  HTTP 요청에서 받은 파라미터들을 정리한 목록을 만들어. 이 목록에는 사용자가 입력한 정보들이 있을 거야.

        /*  'Map<String, String[]>' 은 파라미터의 이름을 키로 하고,
            해당 파라미터의 값들을 문자열 배열로 담고 있는 맵을 나타내는 것이에요.
            이를 통해 여러 개의 값이 전달된 경우에도 모두 처리할 수 있게 됩니다. */

        SectionDTO sectionDTO = new SectionDTO();
        // 새로운 섹션 정보를 저장하기 위한 새로운 종이를 만들어. 이 종이에는 새로운 섹션에 대한 정보를 입력할 수 있겠지.

        for(String key : parameters.keySet()){
            // 맵(Map) 안에 있는 모든 키(key)들을 가져와서 반복
            // parameters.keySet()는 맵(Map)에 있는 모든 키(key)들의 집합을 가져와요.
            // 이 키(key)들을 하나씩 반복하면서 가져오는 것

            if (key.startsWith("startTime-0")){

                sectionDTO = new SectionDTO();
                // 새로운 섹션을 생성하고 그 정보를 저장할 준비

                String startTime = Arrays.toString(parameters.get(key)).replace(":","")
                        .replace("[","").replace("]","");

                // parameters.get(key)는 특정 키에 해당하는 값(value)을 가져오는 것
                // ':' 및 문자열의 시작과 끝에 있는 [] 제거
                // 즉, String startTime은 시간을 나타내는 문자열을 ":"와 대괄호 "[" "]"를 없앤 형태로 저장한 것

                sectionDTO.setStartTime(Integer.parseInt(startTime));
                // 문자열로 된 시작 시간을 정수로 변환. startTime은 문자열로 된 시작 시간을 나타내는 변수
                // 변환된 정수값을 setStartTime 메서드를 통해 섹션 정보 객체에 설정해 줘요.
                // 이렇게 하면 섹션 정보 객체에는 지정된 시작 시간이 저장되게 되는 거에요.

            }
            else if (key.startsWith("startTime-")){
                //  키(key)가 "startTime-"으로 시작하는 경우
                sectionDTOList.add(sectionDTO);
                //  섹션 정보를 담고 있는 객체(sectionDTO)를 섹션 정보 목록(sectionDTOList)에 추가
                sectionDTO = new SectionDTO();
                //  새로운 섹션 정보를 나타내는 객체를 생성
                String startTime = Arrays.toString(parameters.get(key)).replace(":","")
                        .replace("[","").replace("]","");
                sectionDTO.setStartTime(Integer.parseInt(startTime));
            }
            else if (key.startsWith("endTime-")) {

                String endTime = Arrays.toString(parameters.get(key)).replace(";","")
                        .replace("[","").replace("]","");
                sectionDTO.setEndTime(Integer.parseInt(endTime));
            }
            else if (key.startsWith("dayM-")) {
                sectionDTO.setDays(sectionDTO.getDays()+"M");
                //  섹션 정보 객체에 있는 요일 정보를 설정
                // setDays() 메서드는 섹션 정보 객체에 있는 요일 정보를 설정하는 메서드입니다.
                // 여기서는 기존에 설정된 요일 정보에 "M"을 추가하는 것으로 보입니다.
                // 따라서 이 코드는 "dayM-"으로 시작하는 키(key)에 대한 처리를 통해
                // 섹션 정보 객체에 월요일을 추가하는 것입니다.
            }
            else if (key.startsWith("DayT-")) {
                sectionDTO.setDays(sectionDTO.getDays()+"T");
            }

            else if (key.startsWith("DayW-")){
                sectionDTO.setDays(sectionDTO.getDays()+"W");
            }
            else if (key.startsWith("DayTh-")){
                sectionDTO.setDays(sectionDTO.getDays()+"X");
            }
            else if (key.startsWith("DayF-")) {
                sectionDTO.setDays(sectionDTO.getDays()+"F");
            }
        }

        sectionDTOList.add(sectionDTO);
        // 새로운 섹션 정보(sectionDTO)가 생성되었을 때,
        // 이를 섹션 정보 목록에 추가하여 모든 섹션을 한 곳에 모아둡니다.

        courseDTO.setSectionDTOList(sectionDTOList);
        // 코스 정보 객체(courseDTO)에 섹션 정보 목록(sectionDTOList)을 설정합니다.
        // 이렇게 함으로써 코스에 속한 모든 섹션 정보를 코스 정보에 담을 수 있습니다.

        int memberCode = (int) httpSession.getAttribute("memberCode");
        // HTTP 세션에서 회원 코드를 가져옵니다. HTTP 세션은 웹 애플리케이션에서 사용자의 정보를 저장하는데 사용됩니다.
        // 여기서는 회원의 고유한 식별 코드인 회원 코드를 가져옵니다.

        courseDTO.setMemberCode(memberCode);
        //  코스 정보 객체(courseDTO)에 회원 코드를 설정합니다.
        //  이를 통해 어떤 회원이 해당 코스를 생성했는지를 알 수 있게 됩니다.

        courseService.addCourse(courseDTO);

        return "/course/course";
    }

    @GetMapping("/course")
    public ModelAndView viewCourse(HttpSession session, ModelAndView mv) {

        // ModelAndView는 컨트롤러가 처리한 데이터와 사용자에게 보여질 화면을 함께 담아서 전달하는 컨테이너

        int memberCode = (int) session.getAttribute("memberCode");
        //  HttpSession에서 "memberCode"라는 이름으로 저장된 값(회원 코드)을 가져와요.
        //  이를 통해 현재 로그인한 회원의 코드를 알 수 있어요.

        List<CourseDTO> courseDTOList = courseService.viewAllCourse(memberCode);
        // CourseService를 사용해서 현재 로그인한 회원의 모든 코스를 조회해요.
        // 조회된 코스들은 CourseDTO 객체의 목록(=courseDTOList)으로 반환돼요.

        mv.addObject("courseDTOList", courseDTOList);
        // ModelAndView 객체(=mv)에 "courseDTOList"라는 이름으로 조회된 코스 목록을 추가해요.
        // 이렇게 함으로써 웹 페이지에서 이 코스 목록을 사용할 수 있게 돼요.

        mv.setViewName("/course/course");
        //  ModelAndView 객체가 보여줄 뷰(view)의 이름을 설정

        return mv;
        //  처리된 ModelAndView 객체를 반환.
        //  이를 통해 코스 목록이 담긴 ModelAndView 객체가 웹 페이지로 전송되고, 화면에 표시

    }

}

/*
HttpSession: 웹 애플리케이션에서는 사용자의 상태를 유지하기 위해 세션을 사용해요.
'HttpSession httpSession' 은 현재 사용자의 세션을 가져오는 데 사용돼요.
이를 통해 로그인한 사용자를 식별하고, 사용자에 대한 정보를 저장하거나 가져올 수 있어요.
예를 들어, 로그인한 사용자의 아이디나 권한 등을 세션에 저장해두고 이를 활용할 수 있어요.

HttpServletRequest: 웹 애플리케이션에서 클라이언트의 요청을 처리하기 위해 'HttpServletRequest' 를 사용해요.
이 객체를 사용하면 클라이언트가 보낸 요청에 대한 다양한 정보를 가져올 수 있어요.
예를 들어, 요청된 URL, HTTP 메소드(GET 또는 POST), 요청 파라미터 등을 가져와서 처리할 수 있어요.
이를 통해 클라이언트의 요청을 이해하고 적절히 처리할 수 있어요.
따라서, 이 코드에서 'HttpSession httpSession' 과 'HttpServletRequest request' 를 사용하는 이유는
사용자의 세션 정보와 클라이언트의 요청 정보를 가져와서 처리하기 위해서에요.
*/

/*
HttpServletRequest 를 서버에 무언가를 요청할 때 쓰는 특별한 종이처럼 생각해봐.
이 종이에는 너가 요청한 것에 대한 정보가 적혀 있어.
예를 들어, 너가 컴퓨터로 인터넷에서 웹페이지를 열면, 그 순간에 서버에 이 종이가 전달되고,
그 종이에는 너가 어떤  페이지를 요청했는지, 무슨 정보를 보냈는지 등이 적혀 있어.
그러면 서버는 이 종이를 보고 너의 요청을 이해하고, 적절한 답변을 보내주는 거야!
*/
