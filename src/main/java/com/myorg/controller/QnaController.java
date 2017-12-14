package com.myorg.controller;

import com.myorg.model.Qna;
import com.myorg.service.QnaManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@PropertySource(value = {"classpath:application.properties"})
public class QnaController implements ErrorController {
    @Value("${welcome.message:}")
    private String welcomeMessage;
    @Value("${page.title:}")
    private String pageTitle;

    @RequestMapping(value = "/qnalistjsp", method = RequestMethod.GET)
    public ModelAndView qnalistjsp() throws Exception {
        System.out.println("QnaController.qnalistjsp");
        QnaManager qnaManager = new QnaManager();
        ModelAndView modelAndView = new ModelAndView("qnalistjsp");
        modelAndView.getModel().put("welcomeMessage", this.welcomeMessage);
        modelAndView.addObject("qlist", qnaManager.getQnaList());
        modelAndView.setViewName("jsp/qnalistjsp");
        return modelAndView;
    }

    @RequestMapping(value = "/qnalist", method = RequestMethod.GET)
    public @ResponseBody List<Qna> qnalist() throws Exception {
        System.out.println("QnaController.qnalist");
        QnaManager qnaManager = new QnaManager();
        return qnaManager.getQnaList();
    }

    @RequestMapping(value = "/error/5xx", method = RequestMethod.GET)
    public String ServiceNotAvailable(Map<String, Object> model) {
        System.out.println("QnaController.ServiceNotAvailable");
        setModel(model);
        throw new RuntimeException("500");
    }

    @RequestMapping(value = "/public/**", method = RequestMethod.GET)
    public String publicPages(Map<String, Object> model, HttpServletRequest req) {
        System.out.println("QnaController.publicPages");
        setModel(model);
        return "thymeleaf" + req.getServletPath().toString();
    }

    @RequestMapping(value = "/login*", method = RequestMethod.GET)
    public String loginPage(Map<String, Object> model, HttpServletRequest req) {
        System.out.println("QnaController.loginPage");
        setModel(model);
        return "thymeleaf/public" + req.getServletPath().toString();
    }

    @RequestMapping(value = "/user/**", method = RequestMethod.GET)
    public String userPage(Map<String, Object> model, HttpServletRequest req) {
        System.out.println("QnaController.userPage");
        setModel(model);
        return "thymeleaf" + req.getServletPath().toString();
    }

    @RequestMapping(value = "/admin/**", method = RequestMethod.GET)
    public String adminPage(Map<String, Object> model, HttpServletRequest req) {
        System.out.println("QnaController.adminPage");
        setModel(model);
        return "thymeleaf" + req.getServletPath().toString();
    }/*

    @RequestMapping(value = "/error*//**", method = RequestMethod.GET)
    public String errorPage(Map<String, Object> model, HttpServletRequest req) {
        System.out.println("QnaController.errorPage");
        return "thymeleaf" + req.getServletPath().toString();
    }*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultpage(Map<String, Object> model, HttpServletRequest req) {
        System.out.println("QnaController.defaultpage");
        setModel(model);
        return "thymeleaf/public/homepage";
    }

    @Override
    public String getErrorPath() {
        System.out.println("QnaController.getErrorPath");
        return "/error";
    }

    private void setModel(Map<String, Object> model) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.put("title", pageTitle);
        model.put("message", welcomeMessage);
        model.put("role", authorities);
    }
}
