package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import life.majiang.community.provider.dto.AccessTokenDTO;
import life.majiang.community.provider.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class GithubProvider {

    @Value("${github.personal.token}")
    private String personalToken;

    public String getAccessToken(AccessTokenDTO accessToken) {
        // 驼峰标识POJO转下划线JSON
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String json = JSON.toJSONString(accessToken, config);
        log.info("json = " + json);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                                     .url("https://github.com/login/oauth/access_token")
                                     .post(body)
                                     .build();
        try (Response response = client.newCall(request).execute()) {
            String respStr = response.body().string();
            log.info("response: " + respStr);
            String token = respStr.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            log.error("Get access token exception: {}", e);
        }
        return personalToken;
    }

    public String getAccessTokenByHttpClient(AccessTokenDTO accessToken) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String json = JSON.toJSONString(accessToken, config);
        log.info("json = " + json);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://github.com/login/oauth/access_token");
        httpPost.setHeader("Content-Type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String respStr  = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info("response: " + respStr);
                return respStr;
            }
        } catch (IOException e) {
            log.error("Get access token exception: {}", e);
        }
        return null;
    }

    public GithubUser getGithubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                                     .url("https://api.github.com/user")
                                     .addHeader("Authorization", "Bearer " + accessToken)
                                     .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            log.info("Get github user response: " + string);
            // 下划线JSON转驼峰POJO
            ParserConfig.getGlobalInstance().propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            log.error("Get github user exception: {}", e);
        }
        return null;
    }

}
