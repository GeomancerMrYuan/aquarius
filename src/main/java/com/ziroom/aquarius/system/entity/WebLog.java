package com.ziroom.aquarius.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuanpeng
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_web_log")
public class WebLog extends Model<WebLog> {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求ip
     */
    private String ip;

    /**
     * 请求参数
     */
    private String parameter;

    /**
     * 请求返回结果
     */
    private String result;

    /**
     * 请求用户
     */
    private String username;

    /**
     * 操作时间
     */
    private LocalDateTime creatTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
