package com.ag.croud.db;

public class UserDBUtil {

	public static boolean insertUser(String data) throws Exception {
		return DBUtil.insert("Users", data);
	}

	public static String fetchUser(String id) throws Exception {
		return DBUtil.fetch("Users", id);
	}

	public static boolean updateUser(String id, String data) throws Exception {
		String[] payLoad = new String[2];
		payLoad[0] = id;
		payLoad[1] = data;

		return DBUtil.update("Users", payLoad);
	}

	public static boolean deleteUser(String id) throws Exception {
		return DBUtil.delete("Users", id);
	}
}
