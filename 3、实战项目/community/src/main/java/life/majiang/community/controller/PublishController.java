package life.majiang.community.controller;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping()
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        if (request.getSession() == null || request.getSession().getAttribute("user") == null) {
            return "redirect:/";
        }

        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title") String title,
                            @RequestParam(value = "description") String description,
                            @RequestParam(value = "tag") String tag,
                            HttpServletRequest request,
                            Model model) {
        // 方便页面回显，这样页面已经填了的信息不用再重填一次
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

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


        // 如果cookie中不存在token，或根据token查无用户，则返回报错
        if (request.getSession() == null || request.getSession().getAttribute("user") == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        // 记录提交问题带数据库
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        question.setTag(tag);

        questionService.createOrUpdate(question);
        //questionMapper.insert(question);

        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        QuestionDTO questionDTO = questionService.getById(id);

        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", questionDTO.getId());

        return "publish";
    }

}
