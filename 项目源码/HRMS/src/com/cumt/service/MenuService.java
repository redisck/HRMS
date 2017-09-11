package com.cumt.service;

import java.util.List;

import com.cumt.model.Menu;
import com.cumt.model.User;

public interface MenuService {
	/**
	 * 依据父节点获取子节点,并进行权限判断
	 * @param nodeId
	 * @return
	 */
	String getNodeById(String node, User user);
	/**
	 * 查询所有
	 * @return
	 */
	List<Menu> getAllLeaf();
}
