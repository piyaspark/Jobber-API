package backend.api.jobber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDetailRepository extends JpaRepository<JobDetail, Long> {
//    List<JobDetail> findAllByTitleAndLocationAndExperience(String title, String location, String experience);

    @Query(
            value = "SELECT * \n" +
                    "FROM job_detail\n" +
                    "WHERE title LIKE :title\n" +
                    "AND location LIKE :location\n " +
                    "AND experience LIKE :experience"

            ,countQuery =
            "SELECT COUNT(*) \n" +
            "FROM job_detail\n" +
            "WHERE title LIKE :title\n" +
            "AND location LIKE :location\n " +
            "AND experience LIKE :experience"

            ,nativeQuery = true

    )
    List<JobDetail> findAllBySearchKeyword(@Param("title") String title, @Param("location") String location, @Param("experience") String experience);
}
