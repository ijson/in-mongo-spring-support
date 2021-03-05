package com.ijson.test.simple1;

import java.util.Date;

import com.ijson.test.model.RoleEntity;
import com.ijson.test.model.UserEntity;
import com.ijson.test.simple1.service.RoleService;
import com.ijson.test.simple1.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mongo.xml")
public class AppT1Test {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Test
    public void save() {
        RoleEntity role = new RoleEntity();
        role.setName("superadmin");
        role.setCreateTime(System.currentTimeMillis());
        role.setLastModifiedTime(System.currentTimeMillis());
        RoleEntity save = roleService.save(role);
        Assert.assertEquals("superadmin", save.getName());
    }

    @Test
    public void query() {
        RoleEntity role = roleService.findByName("admin");
        System.out.println(role);
        //Assert.assertEquals("admin", role.getName());
        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setMobile("15011186302");
        role.setCreateTime(System.currentTimeMillis());
        role.setLastModifiedTime(System.currentTimeMillis());
        user.setRole(role);
        UserEntity tmp = userService.save(user);
        Assert.assertEquals("admin", tmp.getUsername());
    }

    @Test
    public void query1() {
        RoleEntity role = roleService.findByName("admin");
        UserEntity tmp = userService.findByRole(role);
        System.out.println(tmp);
        Assert.assertEquals("admin", tmp.getUsername());
    }
}
