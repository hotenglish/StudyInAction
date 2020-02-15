package org.spring.sprintboot.controller;

import org.apache.ibatis.session.RowBounds;
import org.spring.sprintboot.domain.Role;
import org.spring.sprintboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/api/role/save", method = RequestMethod.GET)
    public Role save() {
        Role role = new Role();
        role.setId(5l);
        role.setRoleName("Waiter");
        role.setNote("Good");
        roleService.saveOrUpdate(role);
        return role;
    }

    @RequestMapping(value = "/api/role/get", method = RequestMethod.GET)
    public Role getRole() {
        Role role = roleService.query(2L);
        System.out.println(role);
        return role;
    }

    @RequestMapping(value = "/api/role/delete", method = RequestMethod.GET)
    public void deleteRole() {
        roleService.deleteRole(1l);
    }

    @RequestMapping(value = "/api/role/findRolesByName", method = RequestMethod.GET)
    public void findRolesByName() {
        String roleName = "admin";
        roleService.findRolesByName(roleName, new RowBounds(0, 5));
    }

    @RequestMapping(value = "/api/role/addRoleForUser", method = RequestMethod.GET)
    public void addRoleForUser() {
        Map<String, Long> params = new HashMap<>();
        params.put("userId", 1L);
        params.put("roleId", 2L);
        roleService.addRoleForUser(params);
    }

    @RequestMapping(value = "/api/role/removeRoleFromUser", method = RequestMethod.GET)
    public void removeRoleFromUser() {
        Map<String, Long> params = new HashMap<>();
        params.put("userId", 1L);
        params.put("roleId", 2L);
        roleService.removeRoleFromUser(params);
    }

}