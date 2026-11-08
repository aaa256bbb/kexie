package org.example.kexie.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.example.kexie.common.Result;
import org.example.kexie.entity.User;
import org.example.kexie.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/kexie")
public class KexieController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result<User> add(@RequestBody @Valid User user){
        userService.add(user);
        return Result.ok("新增成功",user);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id){
        userService.remove(id);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result<Void> update(@RequestBody @Valid User user) {
        userService.update(user);
        return Result.ok();
    }

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        return Result.ok(userService.getById(id));
    }

    @GetMapping("list")
    public Result<List<User>> list(){
        return Result.ok(userService.list());
    }
}
