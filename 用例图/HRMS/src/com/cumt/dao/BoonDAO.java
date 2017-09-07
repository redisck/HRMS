package com.cumt.dao;

import java.util.List;

import com.cumt.bean.PageBean;
import com.cumt.model.Boon;

public interface BoonDAO {

	PageBean findAll(String start, String limit);

	boolean delete(String[] reIds);

	boolean saveOrUpdate(Boon boon);

	List<Boon> findAll();

}
