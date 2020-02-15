package org.spring.sprintboot.controller;

import org.spring.sprintboot.domain.User;
import org.spring.sprintboot.enums.Sex;
import org.spring.sprintboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/save", method = RequestMethod.GET)
    public User save() {
        User user = new User();
        user.setId(4L);
        user.setUserName("Mark");
        LocalDateTime localDateTime
                = LocalDateTime.of(1982, 10, 4, 10, 12, 33);
        Date date = Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());

        user.setBirthday(date);
        user.setCourse("Java");
        user.setMobile("13138654830");
        user.setEmail("hotenglish@163.com");
        user.setNote("Good");
        user.setSex(Sex.FEMALE);

        userService.saveOrUpdate(user);
        return user;
    }

    @RequestMapping(value = "/api/user/get", method = RequestMethod.GET)
    public User getUser() {
        User user = userService.query(1L);
        System.out.println(user.getRoleList().get(0));
        return user;
    }

    @RequestMapping(value = "/api/user/delete", method = RequestMethod.GET)
    public void deleteUser() {
        userService.deleteUser(1l);
    }

    @RequestMapping(value = "/api/user/addRoleForUser", method = RequestMethod.GET)
    public void addRoleForUser() {
        try {
            Map<String,Long> params=new HashMap<>();
            params.put("userId",1L);
            params.put("roleId",2L);
            userService.addRoleForUser(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/api/user/removeUserFromRole", method = RequestMethod.GET)
    public void removeUserFromRole() {
        try {
            Map<String,Long> params=new HashMap<>();
            params.put("userId",1L);
            params.put("roleId",2L);
            userService.removeUserFromRole(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
