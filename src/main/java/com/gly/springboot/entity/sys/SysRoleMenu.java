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
 * 角色和菜单关联表
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
@TableName("sys_role_menu")
@ApiModel(value = "SysRoleMenu对象", description = "角色和菜单关联表")
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @TableField("menu_id")
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }


    @Override
    public Serializable pkVal() {
        return this.roleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("roleId", getRoleId())
        .append("menuId", getMenuId())
        .toString();
    }
}
