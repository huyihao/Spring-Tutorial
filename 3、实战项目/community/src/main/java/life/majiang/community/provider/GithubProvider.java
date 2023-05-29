package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.provider.dto.AccessTokenDTO;
import life.majiang.community.provider.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class GithubProvider {

    // 获取accessToken
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediumType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediumType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            log.info("response: " + string);
            String token = string.split("&")[0].split("=")[1];
            return token;
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
            log.info("response: " + string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }

}
