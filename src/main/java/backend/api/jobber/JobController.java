package backend.api.jobber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {
//    public static void main(String[] args) {
//        WebscraperUtil webscraperUtil = new WebscraperUtil();
//        List<JobDetail> jobDetailList = webscraperUtil.scrape("programmer", "", "");
//        System.out.println(jobDetailList);
//    }

    @Autowired
    JobDetailService jobDetailService;

    @RequestMapping(value = "job", method = RequestMethod.GET)
    public List<JobDetail> getJobDetailList(@RequestParam("title") String title, @RequestParam String location, @RequestParam String experience) {
        return jobDetailService.findJobs(title, location, experience);
    }

}
