package com.elina.railwayApp.controller;

import com.elina.railwayApp.configuration.common.URLs;
import com.elina.railwayApp.configuration.common.Views;
import com.elina.railwayApp.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j
@Controller
public class ViewController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = URLs.WELCOME)
    public String home(Model model) {
        model.addAttribute("startPage", "Hello beach!");
        model.addAttribute("message", "this is project from Elinaaas");
        return Views.WELCOME;
    }

    @RequestMapping(value = URLs.REGISTRATION)
    public String registration() {
        return Views.REGISTRATION;
    }

    @RequestMapping(value = URLs.LOGIN)
    public String login() {
        return Views.LOGIN;
    }

    @RequestMapping(value = URLs.PROFILE)
    public String getProfile() {
        return Views.PROFILE;
    }

    @RequestMapping(value = URLs.SCHEDULE_CREATE)
    public String createSchedule() {
        return Views.CREATE_SCHEDULE;
    }

    @RequestMapping(value = URLs.SCHEDULE)
    public String test() {
        return Views.HEADER_SCHEDULE;
    }

    @RequestMapping(value = URLs.SEAT)
    public String seat() {
        return Views.SEAT;
    }

    @RequestMapping(value = URLs.ADD_ITEM_RAILWAY)
    public String addItemsRailway() {
        return Views.ADD_ITEMS_RAILWAY;
    }

    @RequestMapping(value = URLs.SHOW_ITEMS_RAILWAY)
    public String showItemsRailway() {
        return Views.MAP;
    }

    @RequestMapping(value = URLs.EDIT_ITEMS_RAILWAY)
    public String editItemsRailway() {
        return Views.EDIT_ITEMS_RAILWAY;
    }

    @RequestMapping(value = URLs.USERS_MAP)
    public String showUsersMap() {
        return Views.USERS_MAP;
    }

    @RequestMapping(value = URLs.CHART)
    public String showChart() {
        return Views.CHART;
    }

    @RequestMapping(value = URLs.SHOW_DELETED_ITEMS_RAILWAY)
    public String showDeletedItemsRailway() {
        return Views.DELETED_ITEMS_RAILWAY;
    }

    @RequestMapping(value = URLs.TRIPS)
    public String showTrips() {
        return Views.TRIPS;
    }

    @RequestMapping(value = URLs.LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        return Views.LOGIN;
    }
}
