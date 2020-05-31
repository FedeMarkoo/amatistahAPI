package com.amatistah.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.amatistah.model.Menu;
import com.amatistah.model.Role;

public class MongoDAO {

	@Autowired
	private static MongoTemplate managerDAO;

	public static List<Menu> getMenusByParent(Integer parentMenuID) {
		return managerDAO.find(new Query(Criteria.where("parentMenuID").is(parentMenuID)), Menu.class);
	}

	public static List<Menu> getParentMenus() {
		return managerDAO.find(new Query(Criteria.where("parentMenuID").exists(false)), Menu.class);
	}

	public static List<Role> getRolesList() {
		return managerDAO.findAll(Role.class);
	}

}
