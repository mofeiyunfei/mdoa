package cc.moredo.oa.dao;


public interface UserDao<User> extends BaseDao<User, Integer> {
	
	public Integer getMaxUserNo();
	
}
