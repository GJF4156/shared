package com.ylgh.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ylgh.pojo.Garbage;
import com.ylgh.service.GarbageService;
import com.ylgh.utils.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 上善若水
 * @since 2020-05-14
 */
@Controller
@RequestMapping("/ylgh/garbage")
public class GarbageController {
    @Autowired
    GarbageService garbageService;

    @GetMapping(value = "/get/{pageNum}",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMessage get(@PathVariable("pageNum") String pageNum) {
        System.out.println(pageNum);
        IPage<Garbage> page = garbageService.page(new Page<Garbage>(Integer.valueOf(pageNum),10));
        return JsonMessage.success()
                .add("pages",page.getPages())
                .add("current",page.getCurrent())
                .add("total",page.getTotal())
                .add("data",page.getRecords());
    }

    @PutMapping(value = "/put/{garbage}",produces="application/json;charset=UTF-8")
    @ResponseBody
    public JsonMessage update(@PathVariable("garbage") String garbage){
        Garbage garbage1 = JSON.parseObject(garbage, Garbage.class);
        System.out.println(garbage1.toString());
        return JsonMessage.success().add("data",garbage);
    }

}

