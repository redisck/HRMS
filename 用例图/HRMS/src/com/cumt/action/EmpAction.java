package com.cumt.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cumt.util.ConditionValidate;
import com.cumt.util.CurrentDate;
import com.cumt.util.FileExport;
import com.cumt.util.SequenceBuilder;
import com.cumt.bean.EmployeeBean;
import com.cumt.model.Employee;
import com.cumt.model.User;
import com.cumt.service.EmpService;
import com.cumt.service.JobChangeService;
import com.opensymphony.xwork2.ActionContext;

public class EmpAction extends BaseAction{
	private EmpService empService;
	private Employee emp;
	private List<EmployeeBean> empBeans;
	private JobChangeService jobChangeService;
	/**
	 * 由于dept和job常用，故单独成一js文件
	 * 但在与struts整合时不便将属�?�名绑定到name�?
	 * 故此单独定义deptId和jobId属�??
	 */
	private String deptId = null;
	private String jobId  = null;
	private String empPhoto = null;
	/**
	 * 配置文件中的参数会�?�过setter方法注入
	 * rePath获取savePath的�??
	 */
	private String rePath = null;
	/**
	 * 查询条目
	 */
	private String condition;
	/**
	 * 查询内容
	 */
	private String conditionValue;
	/**
	 * 保存的路�?
	 */
	private String savePath; 
	/**
	 * 上传的文件内�? 
	 */
	private File upload;
	/**
	 * 保存的文件名
	 */
	private String uploadFileName;
	/**
	 * 上传的文件种�?
	 */
	private String uploadContentType;
	private String empId;
	private String ids;
	private String start;
	private String limit;
	
	/************方法**********************************************/
	/**
	 * 清单
	 */
	public void list(){
		String json = null;
		json = empService.getByHQL(deptId, condition, conditionValue, start, limit);
		this.setStart(null);
		this.setLimit(null);
		this.out(json);
	}
	/**
	 * 保存员工信息
	 */
	public void save(){
		log.info("save start....");
		log.info(this.getEmpPhoto());
		String msg = "保存失败";
		HttpServletResponse response = this.getResponse();
		User user = (User)ActionContext.getContext().getSession().get("user");
		emp.setEmpPhoto(this.getEmpPhoto());
		emp.setEmpAddDate(CurrentDate.getDate());
		emp.setEmpAddPerson(user.getUserName());
		msg = empService.save(emp);
		this.out("{success: true, msg: '"+msg+"'}");
	}
	/**
	 * 员工头像上传
	 */
	public void upload(){
		log.info("upload start...");
		log.info("uploadFileName="+this.getUploadFileName());
		//重命�?
		String fileName = SequenceBuilder.getSequence()+this.getUploadFileName().substring(this.getUploadFileName().indexOf(".")); 
		String msg = empService.uploadPhoto(this.getSavePath()+"\\"+fileName, this.getUpload());
		this.out("{success: true, msg: '"+msg+"', path: '"+this.rePath+"/"+fileName+"'}");
	}
	/**
	 * 根据工号判断是否存在此员�?
	 */
	public void isExist(){
		String empName = empService.isExistByEmpId(empId);
		this.out(empName);
	}
	
	public void unique(){
		String emp = empService.unique(empId);
		this.out(emp);
	}
	
	public void delete(){
		String filePath = ServletActionContext.getRequest().getRealPath(savePath);
		String msg = empService.delete(ids, filePath);
		this.out("{success: true, msg: '"+msg+"'}");
	}
	
	public void intoUpdate(){
		String empJson = empService.listByEmpId(empId);
		this.out(empJson);
	}
	/**
	 * 详细员工pdf报表预览
	 */
	public String detailPdfReport(){
		empBeans = empService.getEmpList(empId);
		return "detailPdf";
	}
	public String simplePdfReport(){
		empBeans = empService.getEmpList(empId);
		return "simplePdf";
	}
	/**
	 * 导出详细报表pdf
	 */
	public void detailPdfExport(){
		empService.pdfExport(empId, this.getResponse(),"员工详细信息.pdf","detailEmp.jasper");
	}
	/**
	 * 导出员工�?单信息pdf
	 */
	public void simplePdfExport(){
		empService.pdfExport(empId, this.getResponse(),"员工�?单信�?.pdf", "simpleEmp.jasper");
	}
	/**
	 * 导出员工�?单信息Excel
	 */
	public void detailXlsExport(){
		empService.xlsExport(this.getResponse(), "员工信息.xls");
	}
	/*********getter and setter ***********/
	public EmpService getEmpService() {
		return empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getSavePath() {
		//struts.xml中配置savePath参数,且获取文件夹的真实地�?
		return ServletActionContext.getRequest().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.rePath = savePath;
		this.savePath = savePath;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getEmpPhoto() {
		return empPhoto;
	}
	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}
	public JobChangeService getJobChangeService() {
		return jobChangeService;
	}
	public void setJobChangeService(JobChangeService jobChangeService) {
		this.jobChangeService = jobChangeService;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public List<EmployeeBean> getEmpBeans() {
		return empBeans;
	}
	public void setEmpBeans(List<EmployeeBean> empBeans) {
		this.empBeans = empBeans;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
}
