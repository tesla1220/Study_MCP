package practice.mcp.model.dto;

public class SectionDTO {

    private int startTime;
    private int endTime;
    private String days = "";

    public SectionDTO() {
    }

    public SectionDTO(int startTime, int endTime, String days) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "SectionDTO{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", days='" + days + '\'' +
                '}';
    }
}
