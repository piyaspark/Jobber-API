package backend.api.jobber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDetailService {
    @Autowired
    private JobDetailRepository jobDetailRepository;
    private WebscraperUtil webscraperUtil;
    private List<JobDetail> jobDetailList;

    public List<JobDetail> findJobs(String title, String location, String experience) {
        webscraperUtil = new WebscraperUtil();

        List<JobDetail> jobDetailFromDb = jobDetailRepository.findAllBySearchKeyword("%"+title, "%"+location, "%"+experience);

        if (jobDetailFromDb.isEmpty()) {
            jobDetailList = webscraperUtil.scrape(title, location, experience);
            jobDetailRepository.saveAll(jobDetailList);
        } else {
            return jobDetailFromDb;
        }

        return jobDetailList;
    }
}
