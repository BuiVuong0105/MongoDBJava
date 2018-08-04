package vn.com.vuong.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Building implements IEntity<Building> {

	public static final String ID_KEY = "_id";
	private String id;

	public static final String NAME_KEY = "name";
	private String name;

	public Building() {
		super();
	}

	public Building(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Building convertDocumentToEntity(DBObject dbObject) {
		if (dbObject != null) {
			this.id = (String) dbObject.get(ID_KEY);
			this.name = (String) dbObject.get(NAME_KEY);
			return this;
		}
		return null;
	}

	@Override
	public DBObject convertEntityToDocument(){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(NAME_KEY, this.name);
		DBObject dbObject = new BasicDBObject(map);
		return dbObject;
	}
}
