package cc.moredo.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author golden
 * @create 2016-10-28 10:56
 * 这个是部门管理,部门的增删改查都是在这里了
 */
@Controller
@RequestMapping(value="dep")
public class DepController extends BaseController{

    //跳转到部门列表页面的方法
    @RequestMapping(value="dep")
    public String toDepPage(){

            return "dep/dep";
    }
}
