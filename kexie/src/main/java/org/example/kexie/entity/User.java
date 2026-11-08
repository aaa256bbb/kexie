package org.example.kexie.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank(message="用户名不能为空")
    @Size(min=3,max=20,message="用户名长度必须是3-20位")
    private String username;

    @Size(min=6,max=20,message = "密码长度必须是6-20位")
    private String password;
    public String getPassword() {
        if (password == null){
            password ="123456";
        }
        return password;
    }

    @Size(max=50,message = "昵称长度最大为50位")
    private String nickname;

    @Min(value = 0,message = "年龄不能小于0")
    @Max(value = 150,message = "别跟我说你能活到150多岁")
    private Integer age;

    private Integer gender;//性别1男2女0未知

    @Email(message = "邮箱没写对噢")
    private String email;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    private Integer status;//状态1正常0禁用

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;//更新时间

}
