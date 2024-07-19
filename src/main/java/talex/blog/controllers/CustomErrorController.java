package talex.blog.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class CustomErrorController implements ErrorController  {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = null;

        if (status != null) {
            statusCode = Integer.valueOf(status.toString());            
        }
        model.addAttribute("statusCode", statusCode);
        
        return "error";
    }
}
