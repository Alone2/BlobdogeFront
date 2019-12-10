package ch.blobber.blobdogefront.connection;

import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

public class BlobdogeConnection {
	String output;
	String web_url;
	String web_port;
	String token;
	public String balance;
	public String address;
	public JSONArray codes;

	public BlobdogeConnection(String token) {
		PropertiesConnection pcon = new PropertiesConnection();
		web_url = pcon.getParameter("serverUrl");
		web_port = pcon.getParameter("serverPort");
		this.token = token;
	}

	private String get(String url_extention, JSONArray args) throws Exception {
	// stolen from : 
	// https://stackoverflow.com/questions/1201048/allowing-java-to-use-an-untrusted-certificate-for-ssl-https-connection#1201102
	TrustManager[] trustAllCerts = new TrustManager[]{
		    new X509TrustManager() {
		        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		            return null;
		        }
		        public void checkClientTrusted(
		            java.security.cert.X509Certificate[] certs, String authType) {
		        }
		        public void checkServerTrusted(
		            java.security.cert.X509Certificate[] certs, String authType) {
		        }
		    }
		};
		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	
			
		URL url = new URL(web_url + url_extention + "?" +  toParameter(args));
		
		HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
		
		StringBuilder content;
		try (BufferedReader in = new BufferedReader(new InputStreamReader(https.getInputStream()))) {

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
	
	public JSONObject sendToURLAddress(String amount, String address) throws Exception {
		JSONArray a = new JSONArray();
		a.put("token");
		a.put(token);

		a.put("amount");
		a.put(amount);
		
		a.put("address");
		a.put(amount);

		String out;
		out = this.get("sendURLAddress", a);
		JSONObject o = new JSONObject(out);

		return o;
	}

}
