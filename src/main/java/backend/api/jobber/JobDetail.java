package backend.api.jobber;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class JobDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String company;
    private String experience;
    private String location;
    private String keySkill;

    public JobDetail() { }

    public JobDetail(String title, String company, String experience, String location, String description, String keySkill) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.experience = experience;
        this.location = location;
        this.keySkill = keySkill;
    }

}
