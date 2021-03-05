package com.ijson.test.simple1.service;

import com.ijson.test.model.RoleEntity;
import com.ijson.test.simple1.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public RoleEntity save(RoleEntity role) {
        return roleDao.save(role);
    }

    public RoleEntity findById(String id) {
        return roleDao.findById(id).get();
    }

    public RoleEntity findByName(String name) {
        RoleEntity role = new RoleEntity();
        role.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match -> match.contains());
        Example<RoleEntity> example = Example.of(role, matcher);
        return roleDao.findOne(example).get();
    }
}
