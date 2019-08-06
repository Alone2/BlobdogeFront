package ch.blobber.blobdogefront.connection;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class BlobdogeConnection {
	String output;
	final String web_url = "http://localhost:8080/Blobdoge/";
	String token;
	public String balance;
	public String address;
	public JSONArray codes;

	public BlobdogeConnection(String token) {
		this.token = token;
	}

	private String get(String url_extention, JSONArray args) throws Exception {
		URL url = new URL(web_url + url_extention);

		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection) con;

		http.setRequestMethod("POST");
		http.setDoOutput(true);

		String outStr = toParameter(args);
		byte[] out = outStr.getBytes(StandardCharsets.UTF_8);

		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		http.connect();
		try (OutputStream os = http.getOutputStream()) {
			os.write(out);
		}
		StringBuilder content;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

			String line;
			content = new StringBuilder();

			while ((line = in.readLine()) != null) {
				content.append(line);
				content.append(System.lineSeparator());
			}
			this.output = content.toString();
		}

		return this.output;
	}

	private String toParameter(JSONArray a) throws UnsupportedEncodingException, JSONException {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < a.length(); i = i + 2) {
			String encoded = URLEncoder.encode(a.getString(i + 1), StandardCharsets.UTF_8.name());
			output.append("&" + a.getString(i) + "=" + encoded);
		}
		return output.toString();
	}

	public JSONObject login(String uname, String passwd) throws Exception {
		JSONArray a = new JSONArray();
		a.put("uname");
		a.put(uname);

		a.put("passwd");
		a.put(passwd);

		a.put("register");
		a.put("false");

		String out;
		out = this.get("auth", a);
		JSONObject o = new JSONObject(out);
		return o;

	}

	public JSONObject register(String uname, String passwd) throws Exception {
		JSONArray a = new JSONArray();
		a.put("uname");
		a.put(uname);

		a.put("passwd");
		a.put(passwd);

		a.put("register");
		a.put("true");

		String out;
		out = this.get("auth", a);
		JSONObject o = new JSONObject(out);
		return o;
	}

	public boolean getInfo() {
		// returns False if error;
		JSONArray a = new JSONArray();
		a.put("token");
		a.put(token);
		String o;
		try {
			o = this.get("infoAddress", a);
		} catch (Exception e) {
			e.printStackTrace();
			balance = "error";
			address = "error";
			return false;
		}
		JSONObject out = new JSONObject(o);

		if (!out.getString("error").equals("none")) {
			balance = out.getString("error");
			address = out.getString("error");
			return false;
		}
		try {
			balance = String.valueOf(out.getFloat("balance"));
			address = out.getString("address");
			codes = out.getJSONArray("codes");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean getCodeInfo(String code) {
		JSONArray a = new JSONArray();
		a.put("code");
		a.put(code);

		String out;
		try {
			out = this.get("infoURL", a);
		} catch (Exception e) {
			return false;
		}
		JSONObject o = new JSONObject(out);
		try {
			balance = String.valueOf(o.getFloat("balance"));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public JSONObject sendToAddress(String code, String address) throws Exception {
		JSONArray a = new JSONArray();
		a.put("address");
		a.put(address);

		a.put("code");
		a.put(code);

		String out;
		out = this.get("sendAddress", a);
		JSONObject o = new JSONObject(out);

		return o;
	}

	public JSONObject sendToMyself(String code) throws Exception {
		JSONArray a = new JSONArray();
		a.put("token");
		a.put(token);

		a.put("code");
		a.put(code);

		String out;
		out = this.get("sendMyself", a);
		JSONObject o = new JSONObject(out);

		return o;
	}
	
	public JSONObject sendToURL(String amount) throws Exception {
		JSONArray a = new JSONArray();
		a.put("token");
		a.put(token);

		a.put("amount");
		a.put(amount);

		String out;
		out = this.get("sendURL", a);
		JSONObject o = new JSONObject(out);

		return o;
	}

}
