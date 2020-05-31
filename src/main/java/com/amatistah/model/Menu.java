package com.amatistah.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.amatistah.persistence.MongoDAO;

@Document(collection = "Transacitions")
public class Menu {

	@Id
	private Integer menuID;
	private String descript;
	private String url;
	private Integer parentMenuID;

	public Menu() {
	}

	public Menu(Menu menu) {
		this(menu.menuID, menu.descript, menu.url);
	}

	public Menu(Integer menuID, String descript, String url) {
		super();
		this.menuID = menuID;
		this.descript = descript;
		this.url = url;
	}

	public Integer getMenuID() {
		return menuID;
	}

	public String getDescript() {
		return descript;
	}

	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getParentMenuID() {
		return parentMenuID;
	}

	public void setParentMenuID(Integer parentMenuID) {
		this.parentMenuID = parentMenuID;
	}

	public List<Menu> getChildrenMenus() {
		return MongoDAO.getMenusByParent(this.getMenuID());
	}

}
