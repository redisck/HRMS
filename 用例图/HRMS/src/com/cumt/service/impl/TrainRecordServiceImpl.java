package com.cumt.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.cumt.util.CurrentDate;
import com.cumt.bean.PageBean;
import com.cumt.bean.TrainRecordBean;
import com.cumt.dao.TrainRecordDAO;
import com.cumt.enums.StaticValue;
import com.cumt.model.TrainRecord;
import com.cumt.service.TrainRecordService;

public class TrainRecordServiceImpl implements TrainRecordService{
	private TrainRecordDAO tRecordDAO;

	@Override
	public String list(String start, String limit) {
		PageBean pageBean = tRecordDAO.findAll(start, limit);
		String jsonTRecord = this.replace(pageBean.getRoot());
		return "{totalProperty:"+pageBean.getTotalProperty()+",root:"+jsonTRecord+"}";
	}
	
	@Override
	public String queryList(TrainRecordBean tRecordBean, String start, String limit) {
		PageBean pageBean = tRecordDAO.findAllByCondition(tRecordBean, start, limit);
		String tRecordJson = this.replace(pageBean.getRoot());
		return "{totalProperty:"+pageBean.getTotalProperty()+",root:"+tRecordJson+"}";
	}
	
	@Override
	public String delete(String ids) {
		String[] tRecordIds = ids.split(",");
		if(tRecordDAO.delete(tRecordIds)) 
			return StaticValue.DELETE_SUCCESS;
		return StaticValue.DELETE_FAILURE;
	}
	
	public String replace(List<TrainRecord> tRecords){
		List<TrainRecordBean> tRecordBeans= new ArrayList<TrainRecordBean>();
		for(TrainRecord tRecord : tRecords){
			TrainRecordBean tRecordBean = new TrainRecordBean();
			tRecordBean.settRecordId(tRecord.getTrecordId());
			tRecordBean.setEmpName(tRecord.getEmployee().getEmpName());
			tRecordBean.setTrainTitle(tRecord.getTrain().getTrainTitle());
			tRecordBean.setTrainDate(CurrentDate.getStringDate(tRecord.getTrain().getTrainDate()));
			tRecordBean.setTrainPerson(tRecord.getTrain().getTrainPerson());
			tRecordBean.setTrainPlace(tRecord.getTrain().getTrainPlace());
			tRecordBean.setTrainResult(tRecord.getTrecordResult());
			tRecordBeans.add(tRecordBean);
		}
		String jsonTRecord = JSONArray.fromObject(tRecordBeans).toString();
		return jsonTRecord;
	}
	
	@Override
	public String save(TrainRecord tRecord) {
		if(tRecordDAO.saveOrUpdate(tRecord)){
			return StaticValue.SAVE_SUCCESS;
		}
		return StaticValue.SAVE_FAILURE;
	}
	
	@Override
	public String getTRecordById(String tRecordId) {
		TrainRecord trainRecord = tRecordDAO.findById(Integer.parseInt(tRecordId));
		return JSONArray.fromObject(trainRecord).toString();
	}
	
	@Override
	public String update(TrainRecord trainRecord) {
		if(tRecordDAO.update(trainRecord)){
			return StaticValue.UPDATE_SUCCESS;
		}
		return StaticValue.UPDATE_FAILURE;
	}
	
	public TrainRecordDAO gettRecordDAO() {
		return tRecordDAO;
	}

	public void settRecordDAO(TrainRecordDAO tRecordDAO) {
		this.tRecordDAO = tRecordDAO;
	}

}
