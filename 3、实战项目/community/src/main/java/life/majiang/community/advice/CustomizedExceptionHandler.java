package life.majiang.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizedExceptionHandler {

    // 要返回的是一个渲染数据的页面
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable ex, Model model) {
        //HttpStatus status = getStatus(request);
        model.addAttribute("message", "服务太热啦，要不然稍等下再来试试~");
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
