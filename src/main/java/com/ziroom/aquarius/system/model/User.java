package com.ziroom.aquarius.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class User {

    private Long userId;

    private String username;

    private String password;

    private Long deptId;

    private String email;

    private String mobile;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;

    private String sex;

    private String theme;

    private String avatar;

    private String description;

}