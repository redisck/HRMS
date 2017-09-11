package com.cumt.service;

import com.cumt.bean.EncouragePunishBean;
import com.cumt.model.EncouragePunish;

public interface EncouragePunishService {
	/**
	 * 查询全部
	 * @return
	 */
	String list( String start, String limit);
	/**
	 * 按条件查询
	 * @return
	 */
	String listByCondition(EncouragePunishBean ePunishBean, String start, String limit);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	String delete(String ids);
	/**
	 * 保存
	 * @param ePunish
	 * @return
	 */
	String save(EncouragePunish ePunish);
	/**
	 * 按id查询
	 * @param epId
	 * @return
	 */
	String getEPunishById(String epId);
	/**
	 * 修改
	 * @param ePunish
	 * @return
	 */
	String update(EncouragePunish ePunish);

}
