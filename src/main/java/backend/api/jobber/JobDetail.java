package backend.api.jobber;

import lombok.Data;

@Data
public class JobDetail {
    private String title;
    private String description;
    private String company;
    private String experience;
    private String location;
    private String keySkill;

    public JobDetail(String title, String company, String experience, String location, String description, String keySkill) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.experience = experience;
        this.location = location;
        this.keySkill = keySkill;
    }
}
