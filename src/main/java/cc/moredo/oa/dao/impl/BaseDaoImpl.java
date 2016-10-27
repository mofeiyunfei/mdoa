package cc.moredo.oa.dao.impl;

import cc.moredo.oa.dao.BaseDao;
import cc.moredo.oa.utils.ReflectGeneric;
import cc.moredo.oa.vo.Condition;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


public /*abstract*/ class BaseDaoImpl<T, ID extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T, ID> {

    public static final String SQLNAME_SEPARATOR = ".";
    public static final String SQL_INSERT = "insert";
    public static final String SQL_INSERTSELECTIVE = "insertSelective";
    public static final String SQL_UPDATEBYSELECTIVE = "updateByPrimaryKeySelective";
    public static final String SQL_UPDATEBYPRIMARYKEY = "updateByPrimaryKey";
    public static final String SQL_UPDATEBYSERIAL = "updateBySerial";
    public static final String SQL_GETBYID = "selectByPrimaryKey";
    public static final String SQL_GETBYSELECTIVE = "selectBySelective";
    public static final String SQL_DELETEBYID = "deleteByPrimaryKey";
    public static final String SQL_DELETEBYIDS = "deleteByPrimaryKeys";
    public static final String SQL_FINDLISTBYPAGE = "selectBySelective";
    public static final String SQL_FINDLISTBYCONDITON = "selectByCondition";
    public static final String SQL_FINDLISTBYENTITY = "selectBySelective";
    public static final String SQL_GETCOUNTBY = "getCountBy";


    /**
     * x
     * 注入SqlSessionFactory
     */
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 获取默认SqlMapping命名空间。
     * 使用泛型参数中业务实体类型的全限定名作为默认的命名空间。
     * 如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则。
     *
     * @return 返回命名空间字符串
     */
    @SuppressWarnings("unchecked")
    protected String getDefaultSqlNamespace() {
        Class<T> clazz = ReflectGeneric.getClassGenricType(this.getClass());
        String nameSpace = clazz.getName();
        //System.out.println(nameSpace);
        return nameSpace;
    }

    private String sqlNamespace = getDefaultSqlNamespace();


    /**
     * 子类可以重写此方法，来改变sqlNamespace
     * 1.默认采用实体类的命名空间，前提是相应的mapper的namespace是实体类的全类名。
     * 此时Dao接口可以精简省略。sqlNamespace=cc.moredo.domain.User
     * 2.如果mapper的namespace是dao接口的全名，那么子类必须实现此方法来改变sqlNamespace的值。
     * 例如：sqlNamespace=cc.moredo.dao.UserDao
     * 3.反正就是一致即可，随便命名，不能重复
     *
     * @param sqlName Sql方法名称名
     * @return 得到完整的sqlName, 包括包名+sqlName
     */
    protected String getSqlName(String sqlName) {
        System.out.println(sqlNamespace + SQLNAME_SEPARATOR + sqlName);
        return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
    }

    /**
     * 生成主键值。
     * 默认情况下什么也不做；
     * 如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值。
     *
     * @param record 要持久化的对象
     */
    protected void generateId(T record) {

    }



    public int deleteByPrimaryKey(ID id) {
        //System.out.println("base实现");
        return this.getSqlSession().delete(getSqlName(SQL_DELETEBYID), id);
    }

    /*@Override*/
    public int deleteByPrimaryKeys(ID[] ids) {
        // TODO Auto-generated method stub
        return 0;
    }

    /*@Override*/
    public int insert(T record) {
        generateId(record);
        return this.getSqlSession().insert(
                getSqlName(SQL_INSERT), record);
    }

    public BaseDaoImpl() {
        super();
    }

    public int insertSelective(T record) {
        return this.getSqlSession().insert(getSqlName(SQL_INSERTSELECTIVE), record);
    }

    public T selectByPrimaryKey(ID id) {
        return this.getSqlSession().selectOne(getSqlName(SQL_GETBYID), id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return this.getSqlSession().update(getSqlName(SQL_UPDATEBYSELECTIVE), record);
    }

    public int updateByPrimaryKey(T record) {
        return this.getSqlSession().update(getSqlName(SQL_UPDATEBYPRIMARYKEY), record);
    }

    public T selectBySelective(T record) {
        return this.getSqlSession().selectOne(getSqlName(SQL_GETBYSELECTIVE), record);
    }

    @SuppressWarnings("unchecked")
    public Page<T> findListByPage(T record, int pageNum, int pageSize, String field, String sort) {
        PageHelper.startPage(pageNum, pageSize);
        Page<T> pageList = (Page<T>) this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBYPAGE), record);
        return pageList;
    }

    public List<T> findListByEntity(T record, String field, String sort) {
        List<T> list = this.getSqlSession().selectList(
                getSqlName(SQL_FINDLISTBYENTITY), record);
        return list;
    }

    public Page<T> findListByCondition(T record, int pageNum, int pageSize, String field, String sort) {
        return null;
    }

    public int updateBySerial(List<String> serials) {

        return this.getSqlSession().update(getSqlName(SQL_UPDATEBYSERIAL), serials);
    }

	/*@SuppressWarnings("unchecked")
    public Page<T> findListByCondition(Condition record, int pageNum, int pageSize,
			String field, String sort) {
		PageHelper.startPage(pageNum, pageSize);
		Page<T> pageList = (Page<T>) this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBYCONDITON), record);
		return pageList;
	}*/


    public List<T> findListByCondition(Condition record, int pageNum, int pageSize,
                                       String field, String sort) {
        //PageHelper.startPage(pageNum, pageSize);
        List<T> pageList = this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBYCONDITON), record);
        return pageList;
    }
	
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByCondition(Condition record, int pageNum, int pageSize,
			String field, String sort) {
		//PageHelper.startPage(pageNum, pageSize);
		List<T> pageList =  this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBYCONDITON), record);
		return pageList;
	}*/

    @SuppressWarnings("unchecked")
    public Page<T> findListByCondition(Condition record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        Page<T> pageList = (Page<T>) this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBYCONDITON), record);
        return pageList;
    }





}
