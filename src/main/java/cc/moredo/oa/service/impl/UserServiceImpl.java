package cc.moredo.oa.service.impl;


import cc.moredo.oa.dao.UserDao;
import cc.moredo.oa.domain.User;
import cc.moredo.oa.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao<User> userdao;


    public User login(User user) {
        return userdao.selectBySelective(user);
    }

    public User getUserById(Integer id) {
        return userdao.selectByPrimaryKey(id);
    }

    public User getUserBySelective(User user) {
        return userdao.selectBySelective(user);
    }
}
