package vn.com.vuong.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Complex implements IEntity<Complex> {
	public static final String ID_KEY = "_id";
	private String id;
	public static final String NAME_KEY = "name";
	private String name;
	public static final String ADDRESS_KEY = "address";
	private String address;
	public static final String ROOMS_KEY = "rooms";
	private Integer rooms;
	public static final String BUILDINGS_KEY = "buildings";
	private List<Building> buildings = new ArrayList<Building>();

	public Complex() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public Complex convertDocumentToEntity(DBObject dbObject) {
		if (dbObject != null) {
			ObjectId objectId = (ObjectId) dbObject.get(ID_KEY);
			this.id = objectId.toString();
			this.name = (String) dbObject.get(NAME_KEY);
			this.rooms = dbObject.get(ROOMS_KEY) == null ? null : ((Number) dbObject.get(ROOMS_KEY)).intValue();
			this.address = (String) dbObject.get(ADDRESS_KEY);
			if (dbObject.get(BUILDINGS_KEY) != null) {
				BasicDBList basicDBList = (BasicDBList) dbObject.get(BUILDINGS_KEY);
				basicDBList.forEach(dbObjectBulding -> {
					Building building = new Building();
					building = building.convertDocumentToEntity((DBObject) dbObjectBulding);
					if (building != null) {
						buildings.add(building);
					}
				});
			}
			return this;
		}
		return null;
	}

	@Override
	public DBObject convertEntityToDocument() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(NAME_KEY, this.name);
		map.put(ADDRESS_KEY, this.address);
		map.put(ROOMS_KEY, this.rooms);
		if (this.getBuildings() != null && !this.getBuildings().isEmpty()) {
			BasicDBList dbListObject = new BasicDBList();
			List<Building> listBuilding = this.getBuildings();
			for (Building building : listBuilding) {
				DBObject dbObjectBuilding = building.convertEntityToDocument();
				dbListObject.add(dbObjectBuilding);
			}
			map.put(BUILDINGS_KEY, dbListObject);
		}else {
			map.put(BUILDINGS_KEY, null);
		}
		DBObject dbObject = new BasicDBObject(map);
		return dbObject;
	}
}
