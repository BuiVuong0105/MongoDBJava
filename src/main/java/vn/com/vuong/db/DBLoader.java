package vn.com.vuong.db;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import vn.com.vuong.constant.Config.CollectionConfig;
import vn.com.vuong.constant.Config.ConfigServer;
import vn.com.vuong.constant.Config.DBConfig;
import vn.com.vuong.constant.Config.UserDB;

public class DBLoader {
	// Dùng để kết nối tới MongoServer, nó Thread Safe, 
	// mọi connection sẽ được lấy trong pool để request đến DB
	// Nên chỉ cần 1 instance của Mongo Client
	public static MongoClient mongoClient;

	public static void init() {
		try {
			ServerAddress sa = new ServerAddress(ConfigServer.SERVER_IP, ConfigServer.SERVER_PORT);
			MongoCredential credential = MongoCredential.createCredential(UserDB.USERNAME, UserDB.DATABASE_NAME, UserDB.PASSWORD.toCharArray());
			MongoClientOptions mongoOptions = new MongoClientOptions.Builder().connectionsPerHost(ConfigServer.CONNECTION_PER_HOST).build();
			mongoClient = new MongoClient(sa, Arrays.asList(credential), mongoOptions);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		init();
		// Tạo 1 connecton tới DB
		MongoDatabase dbTestDB = mongoClient.getDatabase(DBConfig.HOMETAG_OS);
		MongoCollection<Document> moviewCollection = dbTestDB.getCollection(CollectionConfig.COMPLEX);
		FindIterable<Document> dbCursor = moviewCollection.find();
		Iterator<Document> iterator = dbCursor.iterator();
		while (iterator.hasNext()) {
			Document document = iterator.next();
			if(document.get("buildings")!= null) {
				List<Document> buldings = (List) document.get("buildings");
				for (Document documentBuilding : buldings) {
					System.out.println(documentBuilding);
				}
			}
		}
	}
}
