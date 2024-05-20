package practice.mcp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import practice.mcp.model.dto.BoardDTO;
import practice.mcp.model.dto.CommentDTO;
import practice.mcp.model.service.BoardService;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;
    // 이 필드는 BoardService 타입의 객체를 참조합니다. final 키워드로 인해 한 번 초기화되면 변경할 수 없습니다.

    @Autowired
    public BoardController(BoardService boardService){
        // 클래스의 생성자로, BoardService 객체를 매개변수로 받아 필드에 할당합니다.
        // 이 생성자는 스프링이 BoardService 객체를 자동으로 주입하도록 합니다.

        this.boardService = boardService;
        // 전달받은 boardService 객체를 클래스의 필드에 저장합니다.
    }


    /* 게시판 목록 조회 */
    @GetMapping("/board")
    public String board(Model model, @RequestParam(defaultValue = "0")int page, HttpSession session){

        //Model model: 뷰에 데이터를 전달하기 위해 사용합니다.
        //@RequestParam(defaultValue = "0") int page: 요청 파라미터로 페이지 번호를 받으며, 기본값은 0입니다.
        //HttpSession session: 현재 세션 정보를 담고 있는 객체입니다

        Integer totalRecord = boardService.totalRecord();
        // 총 레코드 수를 boardService를 통해 조회합니다.

        int totalPage = 1;
        // 총 페이지 수를 초기화합니다. 실제로는 총 레코드 수에 따라 계산되어야 합니다. 현재는 하드코딩된 값

        String username = (String) session.getAttribute("username");
        // 세션에서 사용자 이름을 가져옵니다.

        int memberCode = (int) session.getAttribute("memeberCode");
        // 세션에서 회원 코드를 가져옵니다.

        List<BoardDTO> boardDTOList = boardService.findAll(page);
        // 현재 페이지의 게시글 목록을 조회합니다.

        model.addAttribute("boardPage", boardDTOList);
        // 모델에 게시글 목록을 추가합니다.

        model.addAttribute("username", username);
        model.addAttribute("memberCode", memberCode);
        model.addAttribute("totalPage", totalPage); // 모델에 총 페이지 수 추가
        model.addAttribute("boardNo", page);    // 모델에 현재 페이지 번호를 추가합니다

        return "board/board";
    }

    /* 게시글 상세 조회 */
    public String viewBoard(int boardNo, Model model, HttpSession session){
        // int boardNo: 조회할 게시글 번호입니다.
        // Model model: 뷰에 데이터를 전달하기 위해 사용합니다.
        // HttpSession session: 현재 세션 정보를 담고 있는 객체입니다.

        /* 게시글 조회 */
        BoardDTO boardDTO = boardService.viewByBoardNo(boardNo);
        // 주어진 게시글 번호(boardNo)로 게시글 정보를 조회합니다.
        // boardService의 viewByBoardNo 메서드를 호출하여 BoardDTO 객체를 반환받습니다.

        /* 댓글 조회 */
       List<CommentDTO> commentDTO = boardService.viewCommentByBoardNo(boardNo);
        // 주어진 게시글 번호(boardNo)로 해당 게시글의 댓글 목록을 조회합니다.
        // boardService의 viewCommentByBoardNo 메서드를 호출하여 댓글 목록(List<CommentDTO>)을 반환받습니다.

        /* 모델에 데이터 추가 */
        model.addAttribute("boardDTO", boardDTO);
        // 조회한 게시글 정보를 모델에 추가하여 뷰에 전달합니다.
        model.addAttribute("commentDTO", commentDTO);
        // 조회한 댓글 목록을 모델에 추가하여 뷰에 전달합니다.

        /* 세션에서 사용자 정보 가져오기 */
        String username = (String) session.getAttribute("username");
        int memberCode = (int) session.getAttribute("memberCode");

        /* 모델에 사용자 정보 추가 */
        model.addAttribute("username", username);
        model.addAttribute("memberCode", memberCode);
        /* 게시글 번호 추가 */
        model.addAttribute("boardNo", boardNo);

        return "board/view";

    }


}
