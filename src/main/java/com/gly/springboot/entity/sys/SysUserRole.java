package com.gly.springboot.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
@TableName("sys_user_role")
@ApiModel(value = "SysUserRole对象", description = "用户和角色关联表")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @TableField("role_id")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    @Override
    public Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("userId", getUserId())
        .append("roleId", getRoleId())
        .toString();
    }
}
