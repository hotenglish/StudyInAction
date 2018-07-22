package com.learn.chapter8.controller;

import com.learn.chapter8.pojo.RoleBean;
import com.learn.chapter8.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role/getRole")
    @ResponseBody
    public RoleBean getRole(@RequestParam("id") int id) {
        long start = System.currentTimeMillis();
        RoleBean role = this.roleService.getRole(id);
        long end = System.currentTimeMillis();
        System.err.println(end - start);
        return role;
    }

    @RequestMapping("/role/deleteRole")
    @ResponseBody
    public int deleteRole(@RequestParam("id") int id) {
        long start = System.currentTimeMillis();
        int rows = this.roleService.deleteRole(id);
        long end = System.currentTimeMillis();
        System.err.println(end - start);
        return rows;
    }

    @RequestMapping("/role/findRoles")
    @ResponseBody
    public List<RoleBean> findRoles(@RequestParam("roleName") String roleName) {
        long start = System.currentTimeMillis();
        List<RoleBean> roleBeans= this.roleService.findRoles(roleName,0, 2);
        long end = System.currentTimeMillis();
        System.err.println(end - start);
        return roleBeans;
    }
}
