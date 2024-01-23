package life.majiang.community.controller;

import life.majiang.community.cache.TagCache;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.service.QuestionService;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request,
                          Model model) {
        if (request.getSession() != null && request.getSession().getAttribute("user") == null) {
            return "redirect:/";
        }
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title") String title,
                            @RequestParam(value = "description") String description,
                            @RequestParam(value = "tag") String tag,
                            @RequestParam(value = "id", required = false) Long id,
                            HttpServletRequest request,
                            Model model) {
        // 方便页面回显，这样页面已经填了的信息不用再重填一次
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("id", id);
        model.addAttribute("tags", TagCache.get());

        if (StringUtils.isEmpty(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        String invalidTag = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalidTag)) {
            model.addAttribute("error", "非法标签[" + invalidTag + "]");
            return "publish";
        }

        // 如果cookie中不存在token，或根据token查无用户，则返回报错
        if (request.getSession() == null || request.getSession().getAttribute("user") == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        // 记录提交问题带数据库
        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        question.setTag(tag);
        questionService.createOrUpdate(question);

        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO questionDTO = questionService.getById(id);

        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", questionDTO.getId());
        model.addAttribute("tags", TagCache.get());

        return "publish";
    }

}
