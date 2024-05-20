package practice.mcp.model.dto;

public class BoardDTO {

    private int boardNo;
    private String username;
    private String boardTime;
    private String boardTitle;
    private String boardContent;

    public BoardDTO() {
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardNo=" + boardNo +
                ", username='" + username + '\'' +
                ", boardTime='" + boardTime + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                '}';
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBoardTime() {
        return boardTime;
    }

    public void setBoardTime(String boardTime) {
        this.boardTime = boardTime;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public BoardDTO(int boardNo, String username, String boardTime, String boardTitle, String boardContent) {
        this.boardNo = boardNo;
        this.username = username;
        this.boardTime = boardTime;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }
}
