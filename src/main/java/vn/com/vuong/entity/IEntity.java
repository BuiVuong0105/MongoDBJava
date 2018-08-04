package vn.com.vuong.entity;

import com.mongodb.DBObject;

public interface IEntity<T> {
	T convertDocumentToEntity(DBObject dbObject);
	DBObject convertEntityToDocument();
}
