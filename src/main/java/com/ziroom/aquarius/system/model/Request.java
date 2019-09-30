package com.ziroom.aquarius.system.model;

import lombok.Data;

import java.util.Date;

/**
 * 请求信息
 */
@Data
public class Request {
    private Long requestId;

    private Date createTime;

    private Date modifyTime;

    private String status;

    private String reqUrl;

    private String reponse;
}