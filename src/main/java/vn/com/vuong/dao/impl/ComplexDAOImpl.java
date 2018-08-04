package vn.com.vuong.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import vn.com.vuong.constant.Config.CollectionConfig;
import vn.com.vuong.constant.Config.DBConfig;
import vn.com.vuong.dao.ComplexDAO;
import vn.com.vuong.db.DBLoader;
import vn.com.vuong.entity.Complex;

public class ComplexDAOImpl implements ComplexDAO {
	
	private DB homeTagOSDB = DBLoader.mongoClient.getDB(DBConfig.HOMETAG_OS);
	
	private DBCollection complexCollection = homeTagOSDB.getCollection(CollectionConfig.COMPLEX);
	
	public List<Complex> search(String name) {
		List<Complex> complexs = new ArrayList<Complex>();
		BasicDBObject basicDBObject = new BasicDBObject();
		if(name != null && !name.isEmpty()) {
			basicDBObject.put(Complex.NAME_KEY, name);
		}
		DBCursor cursor = complexCollection.find(basicDBObject);
		while(cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			Complex complex = new Complex();
			complex = complex.convertDocumentToEntity(dbObject);
			if(complex != null) {
				complexs.add(complex);
			}
		}
		cursor.close();
		return complexs;
	}

	@Override
	public void save(Complex complex) {
		DBObject complexDBObject = complex.convertEntityToDocument();
		complexCollection.save(complexDBObject);
		System.out.println("Insert Success");
	}

	@Override
	public void update(Complex complex) {
		String id = complex.getId();
		ObjectId objectId = new ObjectId(id);
		
		// Object được truy vấn để thay thế
		DBObject query = new BasicDBObject();
		query.put(Complex.ID_KEY, objectId);
		
		// Object thay thế
		DBObject complexObject = complex.convertEntityToDocument();
		
		// Object set
		DBObject objectUpdate = new BasicDBObject();
		objectUpdate.put("$set", complexObject);
		
		// update
		complexCollection.update(query, objectUpdate, false, true);
		System.out.println("Update Success");
	}

	@Override
	public void delete(String id) {
		ObjectId objectId = new ObjectId(id);
		DBObject query = new BasicDBObject();
		query.put(Complex.ID_KEY, objectId);
		complexCollection.remove(query);
		System.out.println("Delete Success");
	}
}
