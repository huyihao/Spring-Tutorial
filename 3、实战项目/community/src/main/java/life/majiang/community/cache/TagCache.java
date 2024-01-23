package life.majiang.community.cache;

import life.majiang.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TagCache {
    private static final CopyOnWriteArrayList<TagDTO> tagDTOS = new CopyOnWriteArrayList<>();

    public static List<TagDTO> get() {
        if (tagDTOS.isEmpty()) {
            TagDTO program = new TagDTO();
            program.setCategoryName("开发语言");
            program.setCategoryId("program");
            program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
            tagDTOS.add(program);

            TagDTO framework = new TagDTO();
            framework.setCategoryName("平台框架");
            framework.setCategoryId("framework");
            framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
            tagDTOS.add(framework);

            TagDTO server = new TagDTO();
            server.setCategoryName("服务器");
            server.setCategoryId("server");
            server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
            tagDTOS.add(server);

            TagDTO db = new TagDTO();
            db.setCategoryName("数据库");
            db.setCategoryId("db");
            db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
            tagDTOS.add(db);

            TagDTO tool = new TagDTO();
            tool.setCategoryName("开发工具");
            tool.setCategoryId("tool");
            tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
            tagDTOS.add(tool);
        }

        return tagDTOS;
    }

    // 过滤出非法标签
    public static String filterInvalid(String tags) {
        String[] splits = StringUtils.split(tags, ",");
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(splits).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
