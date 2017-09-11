package com.cumt.service;

import com.cumt.bean.TrainRecordBean;
import com.cumt.model.TrainRecord;

public interface TrainRecordService {
	/**
	 * 查询列表
	 * @return json
	 */
	String list(String start, String limit);
	/**
	 * 删除所选
	 * @param ids
	 * @return 结果
	 */
	String delete(String ids);
	/**
	 * 按条件查询
	 * @param tRecordBean
	 * @return json
	 */
	String queryList(TrainRecordBean tRecordBean, String start, String limit);
	/**
	 * 保存
	 * @param tRecord
	 * @return
	 */
	String save(TrainRecord tRecord);
	/**
	 * 按id查询
	 * @param tRecordId
	 * @return
	 */
	String getTRecordById(String tRecordId);
	/**
	 * 修改
	 * @param trainRecord
	 * @return
	 */
	String update(TrainRecord trainRecord);

}
