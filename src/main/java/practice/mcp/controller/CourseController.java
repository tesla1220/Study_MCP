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
        // 메소드 이름은 addCourse 야. 두 개의 정보를 받아: 세션(session)과 ModelAndView(mv).
        // session 은 사용자에 대한 정보를 담고 있고, ModelAndView 는 웹 페이지를 만들 때 필요한 도구야.

        mv.addObject("username", session.getAttribute("username"));
            // 여기서는 ModelAndView 에 "username"이라는 정보를 추가해.
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

        /*  new ArrayList<>()는 새로운 ArrayList 객체를 생성하는 것이에요.
        이 코드에서 ArrayList 를 새로 만드는 이유는 새로운 섹션 정보 목록을 만들기 위해서에요.
        여기서 sectionDTOList 는 섹션 정보를 담는 리스트인데,
        새로운 코스가 추가될 때마다 해당 코스에 대한 섹션 정보를 담는 새로운 리스트를 생성해야 해요.
        그래서 new ArrayList<>()를 사용해서 새로운 비어있는 리스트를 만들어요.
        이렇게 하면 새로운 코스에 대한 섹션 정보를 새로운 리스트에 담을 수 있고,
        기존의 코스와 섹션 정보들과 구분하여 관리할 수 있게 되요. */

        Map<String, String[]> parameters = request.getParameterMap();
        //  HTTP 요청에서 받은 파라미터들을 정리한 목록을 만들어. 이 목록에는 사용자가 입력한 정보들이 있을 거야.

            /* request.getParameterMap()을 사용하는 이유는
            클라이언트가 POST 요청을 통해 전달한 파라미터들을 가져오기 위해서에요.

            HTTP POST 요청은 클라이언트가 서버로 데이터를 전송할 때 사용되는 방식 중 하나에요.
            이때 클라이언트가 전송한 데이터는 URL 에 직접 붙어서 전송되는 것이 아니라,
            HTTP 요청의 본문(body)에 담겨서 전송돼요. 이 본문에는 여러 가지 데이터들이 포함될 수 있고,
            이를 파라미터(parameter)라고 해요.

            request.getParameterMap()은 이런 파라미터들을 맵(Map) 형태로 가져오는 메서드에요.
            여기서 맵의 키(key)는 파라미터의 이름이 되고, 값(value)은 파라미터의 값들을 문자열 배열로 담고 있어요.
            이렇게 하면 하나의 파라미터 이름에 여러 개의 값이 전달된 경우에도 모두 처리할 수 있게 되는 거에요.

            따라서, Map<String, String[]> parameters = request.getParameterMap();은
            클라이언트가 전달한 파라미터들을 맵으로 가져와서 변수에 담는 것이에요.
            이를 통해 후속 처리에서 이 파라미터들을 사용할 수 있게 되는 거죠. */

        SectionDTO sectionDTO = new SectionDTO();
        // 새로운 섹션 정보를 저장하기 위한 새로운 종이를 만들어. 이 종이에는 새로운 섹션에 대한 정보를 입력할 수 있겠지.

        for(String key : parameters.keySet()){
            // 맵(Map) 안에 있는 모든 키(key)들을 가져와서 반복
            // parameters.keySet()는 맵(Map)에 있는 모든 키(key)들의 집합을 가져와요.
            // 이 키(key)들을 하나씩 반복하면서 가져오는 것

            if (key.startsWith("startTime-0")){

                sectionDTO = new SectionDTO();
                // 새로운 섹션을 생성하고 그 정보를 저장할 준비

                    /*
                    SectionDTO sectionDTO = new SectionDTO();은 새로운 SectionDTO 객체를 생성하고,
                    그것을 sectionDTO 라는 변수에 할당하는 것이에요.
                    이것은 새로운 객체를 생성하고 그것을 변수에 처음으로 할당하는 과정이에요.

                    sectionDTO = new SectionDTO();는
                    이미 생성된 sectionDTO 변수에 새로운 SectionDTO 객체를 할당하는 것이에요.
                    이 경우, 기존에 sectionDTO 변수에 다른 객체가 할당되어 있었다면
                    그 객체는 더 이상 참조되지 않게 되어 메모리에서 삭제될 수 있어요.
                    이것은 기존에 할당된 객체를 더 이상 사용하지 않고 새로운 객체로 교체하는 과정이에요. */


                String startTime = Arrays.toString(parameters.get(key)).replace(":","")
                        .replace("[","").replace("]","");

                // parameters.get(key)는 특정 키에 해당하는 값(value)을 가져오는 것
                // ':' 및 문자열의 시작과 끝에 있는 [] 제거
                // 즉, String startTime 은 시간을 나타내는 문자열을 ":"와 대괄호 "[" "]"를 없앤 형태로 저장한 것

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
