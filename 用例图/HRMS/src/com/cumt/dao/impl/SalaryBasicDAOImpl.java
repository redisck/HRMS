package com.cumt.dao.impl;

import java.util.List;

import com.cumt.util.ConditionValidate;
import com.cumt.bean.PageBean;
import com.cumt.bean.SalaryBasicBean;
import com.cumt.dao.SalaryBasicDAO;
import com.cumt.model.Employee;
import com.cumt.model.SalaryBasic;

public class SalaryBasicDAOImpl extends BaseDAO implements SalaryBasicDAO{

	@Override
	public List<SalaryBasic> findAll() {
		return super.findAll(SalaryBasic.class);
	}

	@Override
	public SalaryBasic findByEmpId(String empId) {
		Employee emp = new Employee();
		emp.setEmpId(empId);
		List<SalaryBasic> salaryBasics = super.findByProperty(SalaryBasic.class, "employee", emp);
		return salaryBasics.get(0);
	}

	@Override
	public PageBean findByCondition(SalaryBasicBean salBasicBean, String start, String limit) {
		StringBuffer hql = new StringBuffer("FROM SalaryBasic WHERE 1 = 1 ");
		if(ConditionValidate.isEmpty(salBasicBean.getEmpId())){
			hql.append("AND employee.empId='"+salBasicBean.getEmpId()+"'");
		}
		if(ConditionValidate.isEmpty(salBasicBean.getEmpName())){
			hql.append("AND employee.empName = '"+salBasicBean.getEmpName()+"'");
		}
		PageBean pageBean = new PageBean();
		pageBean.setRoot(this.page(hql.toString(), Integer.parseInt(start), Integer.parseInt(limit)));
		pageBean.setTotalProperty(this.findByHQL(hql.toString()).size());
		return pageBean;
	}

	@Override
	public boolean deleteById(String[] sbIds) {
		boolean flag = true;
		for(String sbId : sbIds){
			if(!super.deleteById(SalaryBasic.class, Integer.parseInt(sbId))){
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean saveOrUpdate(SalaryBasic salBasic) {
		return super.saveOrUpdate(salBasic);
	}

	@Override
	public PageBean findAll(String start, String limit) {
		String hql = "FROM SalaryBasic";
		PageBean pageBean = new PageBean();
		pageBean.setRoot(this.page(hql, Integer.parseInt(start), Integer.parseInt(limit)));
		pageBean.setTotalProperty(this.findTotal(SalaryBasic.class));
		return pageBean;
	}

	@Override
	public boolean save(SalaryBasic salBasic) {
		return super.save(salBasic);
	}

	@Override
	public boolean update(SalaryBasic salBasic) {
		return super.update(salBasic);
	}

	@Override
	public boolean uniqueEmp(String empId) {
		String hql = "FROM SalaryBasic WHERE employee.empId = ?";
		List<SalaryBasic> salBasics = this.findByHQLAndValue(hql, empId);
		if(salBasics.size() > 0){
			return false;
		}
		return true;
	}
	
}
