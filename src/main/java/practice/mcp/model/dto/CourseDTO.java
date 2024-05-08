package practice.mcp.model.dto;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseDTO {

    private int memberCode;
    private String courseName;
    private List<SectionDTO> sectionDTOList;

    public CourseDTO() {
    }

    public CourseDTO(int memberCode, String courseName, List<SectionDTO> sectionDTOList) {
        this.memberCode = memberCode;
        this.courseName = courseName;
        this.sectionDTOList = sectionDTOList;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<SectionDTO> getSectionDTOList() {
        return sectionDTOList;
    }

    public void setSectionDTOList(List<SectionDTO> sectionDTOList) {
        this.sectionDTOList = sectionDTOList;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "memberCode=" + memberCode +
                ", courseName='" + courseName + '\'' +
                ", sectionDTOList=" + sectionDTOList +
                '}';
    }
}
