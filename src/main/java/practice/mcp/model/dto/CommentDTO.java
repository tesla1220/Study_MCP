package practice.mcp.model.dto;

public class CommentDTO {
    private int commentNo;
    private String username;
    private String commentTime;
    private String commentContent;
    private int boardNo;

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentNo=" + commentNo +
                ", username='" + username + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", boardNo=" + boardNo +
                '}';
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public CommentDTO(int commentNo, String username, String commentTime, String commentContent, int boardNo) {
        this.commentNo = commentNo;
        this.username = username;
        this.commentTime = commentTime;
        this.commentContent = commentContent;
        this.boardNo = boardNo;
    }

    public CommentDTO() {
    }

}
