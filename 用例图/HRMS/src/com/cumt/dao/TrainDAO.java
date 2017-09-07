package com.cumt.dao;

import java.util.List;

import com.cumt.bean.PageBean;
import com.cumt.bean.TrainBean;
import com.cumt.model.Train;

public interface TrainDAO {
	/**
	 * 查询全部
	 * @return
	 */
	List<Train> findAll();
	/**
	 * 按条件查询
	 * @param trainBean
	 * @return
	 */
	PageBean findAllByCondition(TrainBean trainBean, String start, String limit);
	/**
	 * 保存
	 * @param trainBean
	 * @return
	 */
	boolean save(Train train);
	/**
	 * 删除
	 * @param trainIds
	 * @return
	 */
	boolean delete(String[] trainIds);
	/**
	 * 按id查询
	 * @param trainId
	 * @return
	 */
	List<Train> findTrainById(int trainId);
	PageBean findAll(String start, String limit);

}
