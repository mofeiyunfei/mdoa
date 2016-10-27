package cc.moredo.oa.dao;

import java.io.Serializable;
import java.util.List;

import cc.moredo.oa.vo.Condition;
import com.github.pagehelper.Page;


/**
 * 基于Mybatis的基础DAO接口
 * @author MHoney
 *
 * @param <T> 业务实体类型
 * @param <ID> ID类型 ，如：String、Long、Integer 等
 */
public interface BaseDao<T, ID extends Serializable> {
	/**
	 * 删除指定的唯一标识符对应的持久化对象
	 * @param id  指定的唯一标识符
	 * @return 删除的对象数量
	 */
	public int deleteByPrimaryKey(ID id);

	/**
	 * 删除指定的唯一标识符数组对应的持久化对象
	 * @param ids 指定的唯一标识符数组
	 * @return 删除的对象数量
	 */
	public int deleteByPrimaryKeys(ID[] ids);

	/**
	 * 保存（持久化）对象
	 * @param record 要持久化的对象
	 * @return 执行成功的记录个数
	 */
	public int insert(T record);
	/**
	 * 有选择的保存对象
	 * @param record 要持久化的对象
	 * @return 执行成功的记录个数
	 * 备注：要对比这两种方法的异同点
	 */
	public int insertSelective(T record);

	/**
	 * 根据主键查询对应的持久化对象
	 * @param id 指定的唯一标识符
	 * @return 指定的唯一标识符对应的持久化对象，如果没有对应的持久化对象，则返回null
	 */
	public T selectByPrimaryKey(ID id);
	/**
	 * 有选择的查询某个对象
	 * @param record 要查询的条件
	 * @return 返回要查询的对象
	 */
	public T selectBySelective(T record);
	/**
	 * 更新（持久化）对象 有选择性的更新
	 * @param record  要持久化的对象
	 * @return 执行成功的记录个数
	 */
	public int updateByPrimaryKeySelective(T record);

	/**
	 * 更新（持久化）对象
	 * @param record  要持久化的对象
	 * @return 执行成功的记录个数
	 * 备注：此方法不常用。
	 */
	public int updateByPrimaryKey(T record);

	/**
	 * 分页查询 采用PageHelper插件
	 * @param record 查询参数
	 * @param pageNum 要查询的页号
	 * @param pageSize 每页显示数量
	 * @param field 排序字段名
	 * @param sort 排序方式（升序(asc)或降序(desc)
	 * @return 查询结果分页数据
	 */
	public Page<T> findListByPage(T record, int pageNum, int pageSize,
                                  String field, String sort);

	/**
	 * 不分页查询
	 * @param record 查询参数
	 * @param field 排序字段名
	 * @param sort 排序方式（升序(asc)或降序(desc)
	 * @return 查询结果列表
	 */
	public List<T> findListByEntity(T record, String field, String sort);

	/**
	 * 
	 * @param record
	 * @param pageNum
	 * @param pageSize
	 * @param field
	 * @param sort
	 * @return
	 */
	public Page<T> findListByCondition(T record, int pageNum, int pageSize,
			String field, String sort);

	public List<T> findListByCondition(Condition record, int pageNum, int pageSize,
									   String field, String sort);
	

	
	/**
	 * 
	 * @param record 条件查询
	 * @return
	 */
	public Page<T> findListByCondition(Condition record);

}
