package com.ziroom.aquarius.recoupjob.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通用业务补偿表
 * </p>
 *
 * @author yuanpeng
 * @since 2020-08-28
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_recoup_job")
public class RecoupJob extends Model<RecoupJob> {

    private static final long serialVersionUID = 1L;

    /**
     * 补偿作业表ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 执行补偿业务逻辑的类,全类名,必须实现IRecoup接口
     */
    private String jobClass;

    /**
     * 补偿任务名称,全英文
     */
    private String jobName;

    /**
     * 补偿任务描述
     */
    private String jobDesc;

    /**
     * 补偿任务执行类的json参数
     */
    private String jobJsonParam;

    /**
     * 补偿job状态,execute待执行，executing执行中，successed执行成功，failed执行失败, paused暂停
     */
    private String jobStatus;

    /**
     * 补偿job开始时间,到秒绝对时间如20160720121212
     */
    private Long startTime;

    /**
     * 补偿job完成时间,到秒绝对时间如20160720121212
     */
    private Long completeTime;

    /**
     * 补偿job创建时间,到秒绝对时间如20160720121212
     */
    private Long createTime;

    /**
     * 补偿截止时间,到秒绝对时间如20160720121212，超过该时间没成功放弃补偿
     */
    private Long effectTime;

    /**
     * 当前重试次数
     */
    private Integer retryCurTimes;

    /**
     * 重试总次数,超过该次数还没有补偿成功自动放弃补偿
     */
    private Integer retryTotalTimes;

    /**
     * 执行失败原因
     */
    private String failReason;

    /**
     * 是否开启异步执行,0同步执行 1异步执行
     */
    private Integer async;

    /**
     * 业务编码
     */
    private String busCode;

    /**
     * 执行失败报警标志，0未报警 1已报警
     */
    private Integer failAlarmFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Getter
    public enum JobStatusEnum {

        EXECUTE("execute", "待执行"),
        EXECUTING("executing", "执行中"),
        SUCCESSED("successed", "执行成功"),
        FAILED("failed", "执行失败"),
        PAUSED("paused", "暂停");

        private final String code;
        private final String codeName;

        JobStatusEnum(String code, String codeName) {
            this.code = code;
            this.codeName = codeName;
        }
    }

}
