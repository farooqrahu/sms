package com.sms.spring.web.controller;

/**
 * Created by bnv on 10/27/17.
 */

import com.sms.spring.viewmodel.AccountViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Farooq-Rahu
 *
 */

@Controller

public class SetupController {

    @RequestMapping(value = "/form-validation", method = RequestMethod.GET)
    public ModelAndView formValidation() {
        AccountViewModel accountviewModel = new AccountViewModel();
        accountviewModel.setModelAndView(new ModelAndView("form-validation"));
        return accountviewModel.getModelAndView();

    }
    @RequestMapping(value = "/form-teacher", method = RequestMethod.GET)
    public ModelAndView teacher() {
        AccountViewModel accountviewModel = new AccountViewModel();
        accountviewModel.setModelAndView(new ModelAndView("form-validation"));
        return accountviewModel.getModelAndView();

    }

    @RequestMapping(value = "/form-student", method = RequestMethod.GET)
    public ModelAndView student() {
        AccountViewModel accountviewModel = new AccountViewModel();
        accountviewModel.setModelAndView(new ModelAndView("form-validation"));
        return accountviewModel.getModelAndView();

    }

    @RequestMapping(value = "/form-class", method = RequestMethod.GET)
    public ModelAndView stdClass() {
        AccountViewModel accountviewModel = new AccountViewModel();
        accountviewModel.setModelAndView(new ModelAndView("form-validation"));
        return accountviewModel.getModelAndView();

    }
    @RequestMapping(value = "/form-marks", method = RequestMethod.GET)
    public ModelAndView marks() {
        AccountViewModel accountviewModel = new AccountViewModel();
        accountviewModel.setModelAndView(new ModelAndView("form-validation"));
        return accountviewModel.getModelAndView();

    }
    @RequestMapping(value = "/form-fee", method = RequestMethod.GET)
    public ModelAndView fee() {
        AccountViewModel accountviewModel = new AccountViewModel();
        accountviewModel.setModelAndView(new ModelAndView("form-validation"));
        return accountviewModel.getModelAndView();

    }
    @RequestMapping(value = "/form-non-teaching-staff", method = RequestMethod.GET)
    public ModelAndView nonTeachingStaff() {
        AccountViewModel accountviewModel = new AccountViewModel();
        accountviewModel.setModelAndView(new ModelAndView("form-validation"));
        return accountviewModel.getModelAndView();

    }

}
