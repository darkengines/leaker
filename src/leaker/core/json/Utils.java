package leaker.core.json;

import leaker.core.exception.ManagedException;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
	public static JSONObject generateError(ManagedException me) throws JSONException {
		JSONObject json = new JSONObject();
		return json.put("code", me.getCode()).put("message", me.getMessage());
	}
	public static JSONObject generateError(Exception me) throws JSONException {
		JSONObject json = new JSONObject();
		return json.put("code", "666").put("message", me.getMessage());
	}
}
