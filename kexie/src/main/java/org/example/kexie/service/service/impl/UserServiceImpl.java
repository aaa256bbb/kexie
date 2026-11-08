package org.example.kexie.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.example.kexie.common.BusinessException;
import org.example.kexie.entity.User;
import org.example.kexie.mapper.UserMapper;
import org.example.kexie.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean add(User user) {
        Long smaeNmae = userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (smaeNmae != null && smaeNmae > 0)
            throw new BusinessException("用户名已存在");


        if (user.getStatus() == null) {
            user.setStatus(1);
        }

        user.setId(null);
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean remove(Long id) {
        if (userMapper.selectById(id) == null) {
            throw new BusinessException(404, "用户不存在，无法删除");
        }
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public boolean update(User user) {
        if (user.getId() == null) {
            throw new BusinessException("修改时必须携带 id");
        }
        if (userMapper.selectById(user.getId()) == null) {
            throw new BusinessException(404, "用户不存在，无法修改");
        }
        Long sameName = userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
                .ne(User::getId, user.getId()));
        if (sameName != null && sameName > 0) {
            throw new BusinessException("用户名已被其他用户占用");
        }
        return userMapper.updateById(user) > 0;
    }

    @Override
    public User getById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(404, "用户不存在");
        }
        return user;
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(
                new LambdaQueryWrapper<User>().orderByDesc(User::getId));
    }
}
