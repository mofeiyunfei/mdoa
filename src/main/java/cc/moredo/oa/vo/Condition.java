package cc.moredo.oa.vo;

import java.util.List;

public class Condition {
	/*
	 * user 的条件值接收
	 */
	private Byte isActive;

	private Integer agencyid;

	private String startDate;

	private String endDate;

	private Integer pageNum = 1;
	// 默认分页大小为10 条
	private Integer pageSize = 10;

	private String realname;

	private String username;

	private String enrollTime;

	private String billname;

	private Integer appid;

	// 这个是作为按照激活时间倒序排列的条件
	private String active_time_cc;
	
	private String grade;
	
	private String classes;
	
	private Integer billStatus;
	
	private Integer userid;
	
	private Integer couponId;
	
	private Integer isStore;
	
	private Integer statusid;
	
	private Integer parentId;
	
	private Integer isSchool;

    private String flag;
    
    private Integer agencyClassId;
    
    private Double price;
    
    private String orderSerial;
    
    private Integer paywayId;
    
    private List  statusId;
    
    private String agencyname;
    
    private List agencyIds;
    
    private Integer orderBy;
    
    private Integer userno;
    
    //身份
    private Integer extra;

	public Integer getExtra() {
		return extra;
	}

	public void setExtra(Integer extra) {
		this.extra = extra;
	}

	public Integer getUserno() {
		return userno;
	}

	public void setUserno(Integer userno) {
		this.userno = userno;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public List getAgencyIds() {
		return agencyIds;
	}

	public void setAgencyIds(List agencyIds) {
		this.agencyIds = agencyIds;
	}

	public String getAgencyname() {
		return agencyname;
	}

	public void setAgencyname(String agencyname) {
		this.agencyname = agencyname;
	}

	public String getOrderSerial() {
		return orderSerial;
	}

	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}

	public Integer getPaywayId() {
		return paywayId;
	}

	public void setPaywayId(Integer paywayId) {
		this.paywayId = paywayId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAgencyClassId() {
		return agencyClassId;
	}

	public void setAgencyClassId(Integer agencyClassId) {
		this.agencyClassId = agencyClassId;
	}

	public Integer getIsSchool() {
		return isSchool;
	}

	public void setIsSchool(Integer isSchool) {
		this.isSchool = isSchool;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getIsStore() {
		return isStore;
	}

	public void setIsStore(Integer isStore) {
		this.isStore = isStore;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade.equals("") ? null : grade;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes.equals("") ? null : classes;
	}

	public String getActive_time_cc() {
		return active_time_cc;
	}

	public void setActive_time_cc(String active_time_cc) {
		this.active_time_cc = active_time_cc;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public String getBillname() {
		return billname;
	}

	public void setBillname(String billname) {
		this.billname = billname;
	}

	public String getEnrollTime() {
		return enrollTime;
	}

	public void setEnrollTime(String enrollTime) {
		this.enrollTime = enrollTime.equals("") ? null : enrollTime;
		// this.enrollTime = enrollTime;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	public Integer getAgencyid() {
		return agencyid;
	}

	public void setAgencyid(Integer agencyid) {
		this.agencyid = agencyid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate.equals("") ? null : startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate.equals("") ? null : endDate;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname.equals("") ? null : realname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.equals("") ? null : username;
	}

	public List getStatusId() {
		return statusId;
	}

	public void setStatusId(List statusId) {
		this.statusId = statusId;
	}



}
