package backend.api.jobber;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class WebscraperUtil {
    private WebClient client = new WebClient();
    private String MAIN_URL = "https://www.timesjobs.com/candidate/job-search.html?";
    private List<JobDetail> jobDetails;

    public WebscraperUtil() {}

    public List<JobDetail> scrape(String keywordSearch, String locationSearch, String experienceSearch){
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        jobDetails = new ArrayList<>();

        try{
            String searchUrl = MAIN_URL
                    + "searchType=personalizedSearch&from=submit"
                    + "&txtKeywords=" + URLEncoder.encode(keywordSearch, "UTF-8")
                    + "&txtLocation=" + URLEncoder.encode(locationSearch, "UTF-8")
                    + "&cboWorkExp1=" + URLEncoder.encode(experienceSearch, "UTF-8")
                    + "&sequence=1&startPage=1";

            HtmlPage page = client.getPage(searchUrl);
            List<HtmlElement> elements = (List<HtmlElement>) page.getByXPath("//li[@class='clearfix job-bx wht-shd-bx']");

            if (elements.isEmpty()) {
                System.out.println("No elements found");
            } else {
                for (HtmlElement element : elements) {
                    HtmlElement titleElement = element.getFirstByXPath(".//strong[@class='blkclor']");
                    HtmlElement companyElement = element.getFirstByXPath(".//h3[@class='joblist-comp-name']");

                    //get root element of experience and location detail
                    List<?> topJobDetails = element.getByXPath(".//ul[@class='top-jd-dtl clearfix']/li");
                    HtmlElement experienceElement = (HtmlElement) topJobDetails.get(0);
                    HtmlElement locationRoot = (HtmlElement) topJobDetails.get(1);
                    HtmlElement locationElement = (HtmlElement) locationRoot.getLastElementChild();

                    //get root element of description and key skill detail
                    List<?> listJobDetails = element.getByXPath(".//ul[@class='list-job-dtl clearfix']/li");
                    HtmlElement descriptionElement = (HtmlElement) listJobDetails.get(0);
                    HtmlElement keySkillRoot = (HtmlElement) listJobDetails.get(1);
                    HtmlElement keySkillElement = (HtmlElement) keySkillRoot.getLastElementChild();

                    String title = null == titleElement ? "" : titleElement.asText();
                    String company = null == companyElement ? "" : companyElement.asText();
                    String experience = null == experienceElement ? "" : experienceElement.asText();
                    String location = null == locationElement ? "" : locationElement.asText();
                    String description = null == descriptionElement ? "" : descriptionElement.asText();
                    String keySkill = null == keySkillElement ? "" : keySkillElement.asText();

                    System.out.println(String.format("title: %s\ncompany: %s\nexperience: %s\nlocation: %s\ndescription: %s\nkey skills: %s\n\n\n",
                            title, company, experience, location, description, keySkill));

                    JobDetail jobDetail = new JobDetail(title, company, experience, location, description, keySkill);
                    jobDetails.add(jobDetail);
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return jobDetails;
    }

}
