package com.cumt.dao.impl;

import java.util.List;

import com.cumt.dao.MenuDAO;
import com.cumt.model.Menu;

public class MenuDAOImpl extends BaseDAO implements MenuDAO{

	@Override
	public List<Menu> findNoeById(int parentId) {
		log.info("start findByProperty");
		return this.findByProperty(Menu.class, "menuParentId", parentId);
	}

	@Override
	public List<Menu> findAllLeaf() {
		String hql = "from Menu where menuLeaf = 1";
		return this.findByHQL(hql);
	}

}
