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
 * 用户信息表
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-17
 */
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="用户信息表")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户ID")
    private Long id;

    @TableField("login_account")
    @ApiModelProperty(value = "账号")
    private String loginAccount;

    @TableField("name")
    @ApiModelProperty(value = "姓名")
    private String name;

    @TableField("nick_name")
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @TableField("phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @TableField("last_login_ip")
    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;

    @TableField("last_login_date")
    @ApiModelProperty(value = "最后登录时间")
    private String lastLoginDate;

    @TableField("data_status")
    @ApiModelProperty(value = "数据状态")
    private Boolean dataStatus;

    @TableField("email")
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @TableField("sex")
    @ApiModelProperty(value = "用户性别（0未知 1男 2女）")
    private Boolean sex;

    @TableField("avatar")
    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @TableField("post_code")
    @ApiModelProperty(value = "邮编")
    private String postCode;

    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField("address")
    @ApiModelProperty(value = "地址")
    private String address;

    @TableField("source")
    @ApiModelProperty(value = "来源(0 默认)")
    private Boolean source;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Boolean getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Boolean dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSource() {
        return source;
    }

    public void setSource(Boolean source) {
        this.source = source;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
        return this.id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("loginAccount", getLoginAccount())
                .append("name", getName())
                .append("nickName", getNickName())
                .append("phone", getPhone())
                .append("lastLoginIp", getLastLoginIp())
                .append("lastLoginDate", getLastLoginDate())
                .append("dataStatus", getDataStatus())
                .append("email", getEmail())
                .append("sex", getSex())
                .append("avatar", getAvatar())
                .append("postCode", getPostCode())
                .append("password", getPassword())
                .append("address", getAddress())
                .append("source", getSource())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}

