package backend.api.jobber;

import java.util.List;

public class JobController {
    public static void main(String[] args) {
        WebscraperUtil webscraperUtil = new WebscraperUtil();
        List<JobDetail> jobDetails = webscraperUtil.scrape("programmer", "", "", 1);
        System.out.println(jobDetails);
    }
}
