package practice.mcp.model.dao;

import practice.mcp.model.dto.BoardDTO;
import practice.mcp.model.dto.CommentDTO;

import java.util.List;

public interface BoardMapper {

    List<BoardDTO> findAll();

    Integer totalRecord();


    BoardDTO viewByBoardNo(int boardNo);

    List<CommentDTO> viewByCommentByBoardNo(int boardNo);

    void writeBoard(BoardDTO newWrite);

    BoardDTO findBoardByNo(int boardNo);

    void editBoard(int boardNo, BoardDTO boardDTO);


}
