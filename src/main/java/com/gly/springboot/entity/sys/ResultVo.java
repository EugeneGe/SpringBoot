package com.gly.springboot.entity.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回信息
 *
 * @author x
 * @date 2019/05/21
 */
@Data
@ApiModel(value = "统一返回类型", description = "统一返回类型")
public class ResultVo<T> implements Serializable {

    /**
     * 返回统一值
     */
    @ApiModelProperty(value = "请求成功失败标识 true 成功 false 失败", name = "success", example = "true")
    public boolean success = true;
    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息 当success错误时 返回错误信息", name = "Msg", example = "请输入密码！")
    public String msg = "success";

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据", name = "data")
    public T data;

    /**
     * 分页总数
     */
    @ApiModelProperty(value = "分页总数 默认0", name = "total", example = "100")
    public int total = 0;

    public void resultFail(String msg) {
        this.success = false;
        this.msg = msg;
    }

    public void resultSuccess(T obj) {
        this.success = true;
        this.data = obj;
    }

    /**
     * 自定义flag返回方法
     */
    public void resultFlag(ResultVo<Integer> result, Integer flag, String trueDesc, String falseDesc) {
        if (flag > 0) {
            result.setSuccess(true);
            result.setMsg(trueDesc);
            result.setTotal(flag);
        } else {
            result.setSuccess(false);
            result.setMsg(falseDesc);
            result.setTotal(flag);
        }
    }

}
