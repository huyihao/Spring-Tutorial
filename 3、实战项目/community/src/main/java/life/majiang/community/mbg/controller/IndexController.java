package life.majiang.community.mbg.controller;

import life.majiang.community.mbg.dto.PaginationDTO;
import life.majiang.community.mbg.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {
        PaginationDTO questionDTOS = questionService.list(page, size);
        model.addAttribute("pagination", questionDTOS);

        return "index";
    }

}
