package cc.moredo.oa.controller;

import cc.moredo.oa.domain.User;
import cc.moredo.oa.service.UserService;
import cc.moredo.oa.utils.DigestUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Author golden
 * @create 2016-10-27 16:37
 */

@Controller
@RequestMapping(value="login")
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

    //此方法为验证用户登录的方法
    @RequestMapping(value="checkUser")
    public void login(User user, HttpServletRequest request) throws IOException {
        String md5password = DigestUtil.getMD5(user.getPassword());
        user.setPassword(md5password);
        User exitUser = userService.login(user);
        /*User exitUser = new User();
        exitUser.setUsername("aa");*/
        if(exitUser==null)
        {
            write(ajaxReturn(false, "用户名或密码不正确！"));

        }else
        {
            request.getSession().setAttribute("user", exitUser);
            write(ajaxReturn(true, "登陆成功"));
        }
    }


}
