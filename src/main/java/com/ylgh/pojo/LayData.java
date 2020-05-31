package com.ylgh.pojo;

import java.util.List;

/**
 * @ 作者：上善若水
 * @ 时间：2020-05-30 19:39
 * @ 修改：于2020年05月30日更改
 * @ 描述：layui返回数据格式类
 * @ 版本:
 */
public class LayData {

    private String msg;
    private  List data;
    private  int code;
    private  int count;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LayData(String msg, List data, int code, int count) {
        this.msg = msg;
        this.data = data;
        this.code = code;
        this.count = count;
    }

    public LayData() {
    }
}
