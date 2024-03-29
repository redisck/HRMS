package com.cumt.service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import com.cumt.bean.PageBean;
import com.cumt.bean.RecruitmentBean;
import com.cumt.dao.RecruitmentDAO;
import com.cumt.enums.StaticValue;
import com.cumt.model.Recruitment;
import com.cumt.service.RecruitmentService;

public class RecruitmentServiceImpl implements RecruitmentService{
	private RecruitmentDAO recruitmentDAO;

	@Override
	public String save(Recruitment recruitment) {
		if(recruitmentDAO.saveOrUpdate(recruitment)){
			return StaticValue.SAVE_SUCCESS;
		}
		return StaticValue.SAVE_FAILURE;
	}

	@Override
	public String list(String start, String limit) {
		PageBean pageBean = recruitmentDAO.findAll(start, limit);
		String json = JSONArray.fromObject(pageBean.getRoot()).toString();
		return "{totalProperty:"+pageBean.getTotalProperty()+",root:"+json+"}";
	}
	
	@Override
	public String getListByRecId(String recId) {
		List<Recruitment> recs = recruitmentDAO.findById(Integer.parseInt(recId));
		return JSONArray.fromObject(recs).toString();
	}
	
	@Override
	public String delete(String ids) {
		String[] recId = ids.split(",");
		if(recruitmentDAO.deleteByIds(recId)){
			return StaticValue.DELETE_SUCCESS;
		}
		return StaticValue.DELETE_FAILURE;
	}

	@Override
	public String getByCondition(RecruitmentBean recBean, String start, String limit) {
		PageBean pageBean = recruitmentDAO.findAllByCondition(recBean, start, limit);
		String json = JSONArray.fromObject(pageBean.getRoot()).toString();
		return "{totalProperty:"+pageBean.getTotalProperty()+",root:"+json+"}";
	}
	
	public RecruitmentDAO getRecruitmentDAO() {
		return recruitmentDAO;
	}

	public void setRecruitmentDAO(RecruitmentDAO recruitmentDAO) {
		this.recruitmentDAO = recruitmentDAO;
	}
}
