package com.cumt.dao;

import java.util.List;

import com.cumt.model.Menu;

public interface MenuDAO {

	/**
	 * 依据父节点查找子节点
	 * @param parseInt
	 * @return
	 */
	List<Menu> findNoeById(int parentId);

	List<Menu> findAllLeaf();

}
