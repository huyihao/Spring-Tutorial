package life.majiang.community.controller;

import life.majiang.community.dto.NotificationDTO;
import life.majiang.community.enums.NotificationTypeEnum;
import life.majiang.community.model.User;
import life.majiang.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String question(HttpServletRequest request,
                           @PathVariable(name = "id") Long id,
                           Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (notificationDTO != null && (notificationDTO.getType() == NotificationTypeEnum.REPLY_COMMENT.getType() || notificationDTO.getType() == NotificationTypeEnum.REPLY_QUESTION.getType())) {
            Integer unreadCount = (Integer) request.getSession().getAttribute("unreadCount");
            if (unreadCount > 0) {
                unreadCount--;
            }
            request.getSession().setAttribute("unreadCount", unreadCount);
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }
    }

}
