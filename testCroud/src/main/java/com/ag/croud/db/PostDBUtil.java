package com.ag.croud.db;

public class PostDBUtil {

	public static boolean insertPost(String data) throws Exception {
		return DBUtil.insert("Posts", data);
	}

	public static String fetchPost(String id) throws Exception {
		return DBUtil.fetch("Posts", id);
	}

	public static boolean updatePost(String id, String data) throws Exception {
		String[] payLoad = new String[2];
		payLoad[0] = id;
		payLoad[1] = data;

		return DBUtil.update("Posts", payLoad);
	}

	public static boolean deletePost(String id) throws Exception {
		return DBUtil.delete("Posts", id);
	}
}
