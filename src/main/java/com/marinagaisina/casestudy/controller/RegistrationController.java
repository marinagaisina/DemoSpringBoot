package com.marinagaisina.casestudy.controller;

import com.marinagaisina.casestudy.database.dao.UserDAO;
import com.marinagaisina.casestudy.database.entity.User;
import com.marinagaisina.casestudy.form.RegisterFormBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration-url-path")
public class RegistrationController {

    @Autowired
    private UserDAO userDao;

//    @RequestMapping(value = "/userList", method = RequestMethod.GET)
//    public ModelAndView userList(@RequestParam(required = false) String search) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("registration/userList");
//
//        if ( ! StringUtils.isEmpty(search)) {
//            List<User> users = userDao.findByFirstName(search);
//            response.addObject("userListKey", users);
//        }
//        return response;
//    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register");

        RegisterFormBean form = new RegisterFormBean();
        response.addObject("formBeanKey", form);

        return response;
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.GET)
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                System.out.println("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("formBeanKey", form);
            response.setViewName("registration/register");

        } else {
            // there are no errors on the form submission lets redirect to the login page
            // right here that you would save the new user registration to the database
            // however we will get to this later in the week when spring JPA
            User user = new User();

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setPassword(form.getPassword());
            user.setUsername(form.getUsername());

            userDao.save(user);

            response.setViewName("redirect:/login");
        }

        return response;
    }
}
