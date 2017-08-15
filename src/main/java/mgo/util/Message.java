package mgo.util;

import com.jfinal.kit.Kv;

public class Message {
	private static final String STATE_OK = "isOk";
	private static final String STATE_FAIL = "isFail";

	@SuppressWarnings("unchecked")
	public static final Kv ok() {
		Kv kv = new Kv();
		kv.put(STATE_OK, Boolean.TRUE);
		kv.put(STATE_FAIL, Boolean.FALSE);
		return kv;
	}

	@SuppressWarnings("unchecked")
	public static final Kv ok(String msg) {
		Kv kv = ok();
		kv.put("msg", msg);
		return kv;
	}

	@SuppressWarnings("unchecked")
	public static final Kv fail() {
		Kv kv = new Kv();
		kv.put(STATE_FAIL, Boolean.TRUE);
		kv.put(STATE_OK, Boolean.FALSE);
		return kv;
	}

	@SuppressWarnings("unchecked")
	public static final Kv fail(String msg) {
		Kv kv = fail();
		kv.put("msg", msg);
		return kv;
	}
}
