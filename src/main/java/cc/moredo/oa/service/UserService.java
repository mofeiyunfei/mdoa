package cc.moredo.oa.service;


import cc.moredo.oa.domain.User;
import cc.moredo.oa.vo.Condition;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 1.登录
 * 2.注册
 * 3。登出
 * 4.token取值
 * 5.查看个人信息
 * 6.修改密码
 * 7.激活账户：验证手机号，并赠送一张打印券
 * @author MHoney
 */
public interface UserService {
	
	/**
	 * 登录方法 已加用户必须状态 0正常 1 不正常
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	public User getUserById(Integer id);
	
	public User getUserBySelective(User user);
	

 
}
