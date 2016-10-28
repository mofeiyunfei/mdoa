package cc.moredo.oa.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author golden
 * @create 2016-10-27 16:59
 */
@Controller
public class BaseController<T> {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * ajax提交后回调的json字符串
     * @return
     */
    protected String ajaxReturn(boolean success,String message)
    {
        Map map=new HashMap();
        map.put("success", success);//是否成功
        map.put("message", message);//文本消息
        String json= JSON.toJSONString(map);
        return json;
    }

    /**
     * 输出字符串
     * @param jsonString
     */
    protected void write(String jsonString)
    {
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
