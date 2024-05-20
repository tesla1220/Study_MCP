package practice.mcp.model.service;

import org.springframework.stereotype.Service;
import practice.mcp.model.dao.BoardMapper;
import practice.mcp.model.dto.BoardDTO;
import practice.mcp.model.dto.CommentDTO;

import java.util.List;

@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<BoardDTO> findAll(int page){
        return boardMapper.findAll(page);
    }

    public Integer totalRecord(){
        return boardMapper.totalRecord();
    }

    public BoardDTO viewByBoardNo(int boardNo){
        return boardMapper.viewByBoardNo(boardNo);
    }

    public List<CommentDTO> viewCommentByBoardNo(int boardNo){
        return boardMapper.viewByCommentByBoardNo(boardNo);
    }

    /* 게시판 글쓰기 기능 */
    public void writeBoard(BoardDTO newWrite) {

        boardMapper.writeBoard(newWrite);
    }

    /* 게시글 수정 기능_게시글 검색 */
    public BoardDTO findBoardByNo(int boardNo){
        return boardMapper.findBoardByNo(boardNo);
    }

    /* 게시글 수정 기능_게시글 수정 */
    public void editBoard(int boardNo, BoardDTO boardDTO) {

        boardMapper.editBoard(boardNo, boardDTO);
    }
}
