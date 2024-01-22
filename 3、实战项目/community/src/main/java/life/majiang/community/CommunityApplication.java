package life.majiang.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

// 测试MyBatis Generator
//@SpringBootApplication(scanBasePackages = {"life.majiang.community.mbg"})
//@MapperScan({"life.majiang.community.mbg.mapper"})
@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "life.majiang.community.mbg.*")})
public class CommunityApplication {

    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");
        SpringApplication.run(CommunityApplication.class, args);
    }

}
