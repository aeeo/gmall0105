package com.atguigui.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.bean.UmsMemberReceiveAddress;
import com.atguigu.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    //Idea会失误报错，在setting中取消报错
    @Reference
    UserService userService;

    //查询所有用户，返回所有用户的List集合
    @RequestMapping("getAllUser")
    @ResponseBody
    public List<UmsMember> getAllUser() {
        List<UmsMember> umsMembers = userService.getAllUser();
        return umsMembers;
    }

    //根据id查询单个用户信息
    @RequestMapping("getReveiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReveiveAddressByMemberId(String memberId) {
        List<UmsMemberReceiveAddress> msMemberReceiveAddress = userService.getReveiveAddressByMemberId(memberId);
        return msMemberReceiveAddress;
    }

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello user";
    }
}
