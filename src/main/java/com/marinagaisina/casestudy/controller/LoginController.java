package com.marinagaisina.casestudy.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = GET)
    public ModelAndView login(HttpServletRequest request, HttpSession session) {
        // this method is checking to see if the user is logged in by looking at the session
        // if logged in ( user is in the session ) then show the success page.
        // if not logged in ( user is not in the session ) then show the login page
        ModelAndView response = new ModelAndView();

        String username = (String) session.getAttribute("usernameSessionKey");
        if (StringUtils.equals(username, "tom")) {
            // when using redirect you will use the URL of the controller method
            // that you want to display.  In this case the /success RequestMapping
            response.setViewName("redirect:/success");

        } else {
            // when using the name of a view we use the path to the JSP page
            // within the jsp folder.
            response.setViewName("login/login");
        }

        return response;
    }
    @RequestMapping(value = "/loginFormSubmit", method = RequestMethod.GET)
    public ModelAndView loginSubmit(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();

        String username = request.getParameter("usernameFromForm");
        String password = request.getParameter("passwordFromForm");

        // if ("tom".equals(username) && "jerry".equals(password) ){
        if (StringUtils.equals(username, "tom") && StringUtils.equals(password, "jerry")) {
            session.setAttribute("usernameSessionKey", username);
            response.setViewName("redirect:/success");
        } else {
            // invalid login
            session.setAttribute("username", null);
            response.setViewName("redirect:/login");
        }

        return response;
    }
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ModelAndView success(HttpSession session) throws Exception {
        // this method is checking to see if the user is logged in by looking at the session
        // if logged in ( user is in the session ) then show the success page.
        // if not logged in ( user is not in the session ) then show the login page
        ModelAndView response = new ModelAndView();

        String username = (String) session.getAttribute("usernameSessionKey");
        if (StringUtils.equals(username, "tom")) {
            response.addObject("loggedInUser", username);
            response.setViewName("/login/success");
        } else {
            // need to implement here to redirect back to login page
            // because it means the user has requested the /success url
            // but is not in the session
            response.setViewName("redirect:/login");
        }

        return response;
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) throws Exception {
        // this is how to destroy the current user session
        // always implement a logout method this way.
        session.invalidate();
        ModelAndView response = new ModelAndView();
        // this is how to do a redirect in spring mvc usin the model
        // this will change the url to be localhost:8080/login
        // which is preferable because the URL is the same as the page you are on
        response.setViewName("redirect:/login");

        // doing it this way will work but is not best practice
        //response.setViewName("login/login");

        return response;
    }

}
