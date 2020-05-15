package com.ziroom.aquarius.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yuanpeng
 * @since 2020-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dept")
@ApiModel("部门实体")
public class Dept extends Model<Dept> {

    private static final long serialVersionUID=1L;

    /**
     * 部门ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级部门ID
     */
    @NotNull(message = "上级部门Id不能为空")
    private Long parentId;

    /**
     * 部门名称
     */
    @NotNull(message = "部门名称不能为空")
    private String deptName;

    /**
     * 排序
     */
    private Integer deptOrder;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人id
     */
    private Long creater;

    /**
     * 更新人id
     */
    private Long updater;

    /**
     * 是否删除;0-启用,1-删除
     */
    private Boolean isDel;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
