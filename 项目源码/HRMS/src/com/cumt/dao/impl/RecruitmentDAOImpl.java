package com.cumt.dao.impl;

import java.util.List;

import com.cumt.util.ConditionValidate;
import com.cumt.bean.PageBean;
import com.cumt.bean.RecruitmentBean;
import com.cumt.dao.RecruitmentDAO;
import com.cumt.model.Recruitment;

public class RecruitmentDAOImpl extends BaseDAO implements RecruitmentDAO{

	@Override
	public boolean saveOrUpdate(Recruitment recruitment) {
		return super.saveOrUpdate(recruitment);
	}

	@Override
	public PageBean findAll(String start, String limit) {
		String hql = "FROM Recruitment";
		PageBean pageBean = new PageBean();
		pageBean.setRoot(this.page(hql, Integer.parseInt(start), Integer.parseInt(limit)));
		pageBean.setTotalProperty(this.findTotal(Recruitment.class));
		return pageBean;
	}

	@Override
	public List<Recruitment> findById(int recId) {
		return this.findByProperty(Recruitment.class, "recId", recId);
	}

	@Override
	public boolean deleteByIds(String[] recIds) {
		boolean flag = true;
		for(String recId : recIds){
			if(!this.deleteById(Recruitment.class, Integer.parseInt(recId))){
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public PageBean findAllByCondition(RecruitmentBean recBean, String start, String limit) {
		StringBuffer hql = new StringBuffer("FROM Recruitment r WHERE 1=1 ");
		if(ConditionValidate.isEmpty(recBean.getRecJob())){
			hql.append(" AND r.recJob like '%" + recBean.getRecJob()+"%' ");
		}
		if(ConditionValidate.isEmpty(recBean.getRecTitle())){
			hql.append(" AND r.recTitle like '%" + recBean.getRecTitle() + "%' ");
		}
		if(ConditionValidate.isEmpty(recBean.getStartDate()) &&
				ConditionValidate.isEmpty(recBean.getEndDate())){
			hql.append(" AND r.recDate between '"+recBean.getStartDate() +"' AND '"+recBean.getEndDate()+"'");
		}
		PageBean pageBean = new PageBean();
		pageBean.setRoot(this.page(hql.toString(), Integer.parseInt(start), Integer.parseInt(limit)));
		pageBean.setTotalProperty(this.findByHQL(hql.toString()).size());
		return pageBean;
	}
}