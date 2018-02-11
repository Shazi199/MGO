package mgo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.IDruidStatViewAuth;

import mgo.model.User;

public class DruidStatViewAuth implements IDruidStatViewAuth {

	@Override
	public boolean isPermitted(HttpServletRequest request) {
		User u = getSessionAttr("currentUser", request);
		if (u != null && PropKit.get("admin_username").equals(u.getUsername())) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private <T> T getSessionAttr(String key, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null ? (T) session.getAttribute(key) : null;
	}
}
