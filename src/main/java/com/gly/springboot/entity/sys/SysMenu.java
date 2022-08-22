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
 * 菜单权限表
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-22
 */
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "菜单权限表")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @TableField("menu_name")
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @TableField("parent_id")
    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @TableField("order_num")
    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @TableField("path")
    @ApiModelProperty(value = "路由地址")
    private String path;

    @TableField("component")
    @ApiModelProperty(value = "组件路径")
    private String component;

    @TableField("query")
    @ApiModelProperty(value = "路由参数")
    private String query;

    @TableField("is_frame")
    @ApiModelProperty(value = "是否为外链（0是 1否）")
    private Integer isFrame;

    @TableField("is_cache")
    @ApiModelProperty(value = "是否缓存（0缓存 1不缓存）")
    private Integer isCache;

    @TableField("menu_type")
    @ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    @TableField("visible")
    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    private String visible;

    @TableField("status")
    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    private String status;

    @TableField("perms")
    @ApiModelProperty(value = "权限标识")
    private String perms;

    @TableField("icon")
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @TableField("create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @TableField("update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @TableField("update_time")
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(Integer isFrame) {
        this.isFrame = isFrame;
    }

    public Integer getIsCache() {
        return isCache;
    }

    public void setIsCache(Integer isCache) {
        this.isCache = isCache;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("menuId", getMenuId())
                .append("menuName", getMenuName())
                .append("parentId", getParentId())
                .append("orderNum", getOrderNum())
                .append("path", getPath())
                .append("component", getComponent())
                .append("query", getQuery())
                .append("isFrame", getIsFrame())
                .append("isCache", getIsCache())
                .append("menuType", getMenuType())
                .append("visible", getVisible())
                .append("status", getStatus())
                .append("perms", getPerms())
                .append("icon", getIcon())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}

