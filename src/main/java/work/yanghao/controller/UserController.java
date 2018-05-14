package work.yanghao.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import work.yanghao.pojo.User;

@Controller
public class UserController {
    @RequestMapping("login")
    public String login(String username,String password){
        //创建令牌
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        //得到subject
        Subject subject = SecurityUtils.getSubject();
        //执行验证

        try {
            subject.login(usernamePasswordToken);
            System.out.println("登录成功");
            String principal= (String)subject.getPrincipal();
            System.out.println("登录成功后可以从subject中获取principal信息,principal代表了当前用户对象:"+principal);
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("登录失败");
        return "redirect:/error.html";
    }


    @RequestMapping("loginOut")
    public String loginOut(){
        //得到subject
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.html";
    }
}
