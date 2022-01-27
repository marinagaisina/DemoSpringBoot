package com.marinagaisina.casestudy.controller;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView response = new ModelAndView();
        response.setViewName("HelloWorld!");
        return response;
    }
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public ModelAndView greeting(HttpServletRequest request) {
        ModelAndView response = new ModelAndView();

        String userFromURL = request.getParameter("user");
        System.out.println("userFromURL: "+userFromURL);
        response.addObject("user", userFromURL);
        response.setViewName("greeting");
        return response;
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("index");

        //if there is a value in the session print it
        Object usersession = session.getAttribute("username");
        System.out.println("usersession: "+usersession);
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("Cookie : " + name + " = " + value);
            }
        } else {
            System.out.println("No cookies on request");
        }
        return response;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public ModelAndView indexSubmit(HttpServletRequest request, HttpSession session) throws Exception {

        // these 3 are the same name as on the html form
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String dropdown = request.getParameter("dropdown");

        System.out.println("username = " + username);
        System.out.println("firstname = " + firstname);
        System.out.println("dropdown = " + dropdown);

        //put a value in the session
        System.out.println("/indexSubmit - adding user to session = " + username);
        session.setAttribute("username", username);

        session.setAttribute("firstname", "Putting firstname in session "+firstname);


        // this is going to the JSP page
        ModelAndView response = new ModelAndView();
        response.addObject("username", username);   //adding params to the output
        response.addObject("firstname",firstname);
        response.addObject("dropdown", dropdown);

        response.setViewName("indexSubmit");

        return response;
    }

}

