package org.example.kexie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.kexie.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
