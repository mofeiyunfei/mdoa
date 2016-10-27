package cc.moredo.oa.dao.impl;

import cc.moredo.oa.dao.UserDao;
import cc.moredo.oa.domain.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao<User> {


	public Integer getMaxUserNo() {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(this.getSqlName("selectmaxuserno"));
	}

	
	
	/**
	 * 此处只要是和mapper的namespace 相同即可，而且namespace随便命名
	 * 所有的mapper的namespace不能重复 就行
	 * 1.如果不采用省略dao接口实现的方式来开发的话，那么mapper的namespace的作用只有一个，就是起着区分的作用。
	 * 2.如果采用，那么还有另外一个作用 就是必须指向dao接口所在的全类名
	 * 结论：所以实不实现下面的方法没有任何意思，唯一就是可以形象的表达展示
	 */
	/*String nameSpace="cc.moredo.dao.UserDao333";
	protected String getSqlName(String sqlName) {
		//System.out.println("sqlNamespace:"+sqlNamespace);
		return nameSpace + SQLNAME_SEPARATOR + sqlName;
	}*/
	
}
