package com.ag.croud.db;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class DBUtil {
	static Mongo m;

	static {
		connect();
	}

	@SuppressWarnings("finally")
	private static boolean connect() {
		boolean result = true;
		if (m != null) {
			return result;
		} else {
			try {
				m = new Mongo("localhost", 27017);
				// m = new
				// Mongo(java.net.InetAddress.getLocalHost().getHostAddress(),
				// 27017);
			} catch (UnknownHostException ex) {
				result = false;
				System.err.println(ex.getMessage());
			} catch (MongoException ex) {
				result = false;
				System.err.println(ex.getMessage());
			} catch (Exception ex) {
				result = false;
				System.err.println(ex.getMessage());
			} finally {
				return result;
			}
		}
	}

	static boolean insert(String collectionName, String data) throws Exception {
		boolean result = true;
		// connect();
		DB db = m.getDB("test");
		DBCollection coll = db.getCollection(collectionName);
		// coll.insert(makeUser(1, "James", "male", "06081970", "usa",
		// "abc@xyz.com"));

		DBObject dbObject = (DBObject) JSON.parse(data);
		coll.insert(dbObject);
		return result;
	}

	// private static BasicDBObject makeUser(int id, String name, String gender,
	// String dob, String country, String emailId) {
	// BasicDBObject doc = new BasicDBObject();
	// doc.put("id", id);
	// doc.put("name", name);
	// doc.put("gender", gender);
	// doc.put("dob", dob);
	// doc.put("country", country);
	// doc.put("emailId", emailId);
	// return doc;
	// }

	static String fetch(String collectionName, String payLoad) throws Exception {
		String result = null;
		// String dbName = "test"; // TODO should come from XML
		// try {
		DB db = m.getDB("test");

		if (db.collectionExists(collectionName)) {
			DBCollection coll = db.getCollection(collectionName);
			DBObject myDoc = coll.findOne(new BasicDBObject("_id",
					new ObjectId(payLoad)));
			// obj.get("_id").toString();
			result = myDoc.toString();
		}
		return result;
	}

	static boolean update(String collectionName, String payLoad[])
			throws Exception {
		boolean result = false;
		// TODO should come from XML
		DB db = m.getDB("test");

		if (db.collectionExists(collectionName)) {
			DBCollection coll = db.getCollection(collectionName);
			DBObject dbObjectOld = coll.findOne(new BasicDBObject("_id",
					new ObjectId(payLoad[0])));
			// obj.get("_id").toString();
			DBObject dbObjectNew = (DBObject) JSON.parse(payLoad[1]);
			coll.update(dbObjectOld, dbObjectNew);
			result = true;
		}
		// TODO else throw internal server error
		return result;
	}

	static boolean delete(String collectionName, String payLoad)
			throws Exception {
		boolean result = false;
		// String dbName = "test"; // TODO should come from XML

		DB db = m.getDB("test");

		if (db.collectionExists(collectionName)) {
			DBCollection coll = db.getCollection(collectionName);
			DBObject doc = coll.findOne(new BasicDBObject("_id", new ObjectId(
					payLoad)));
			coll.remove(doc);
			result = true;
		}

		return result;
	}

	// Post Methods

	// User Methods
	// public static boolean insertUser(String data) throws Exception {
	// return insert("Users", data);
	// }
	//
	// public static String fetchUser(String id) throws Exception {
	// return fetch("Users", id);
	// }
	//
	// public static boolean updateUser(String id, String data) throws Exception
	// {
	// String[] payLoad = new String[2];
	// payLoad[0] = id;
	// payLoad[1] = data;
	//
	// return update("Users", payLoad);
	// }
	//
	// public static boolean deleteUser(String id) throws Exception {
	// return delete("Users", id);
	// }
}
