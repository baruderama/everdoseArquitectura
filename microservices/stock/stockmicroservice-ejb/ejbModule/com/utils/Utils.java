package com.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class Utils {
	
	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	
	public static boolean sendJson(String url,String json) {
		

		try {

		    HttpPost request = new HttpPost(url);
		    StringEntity params =new StringEntity(json);
		    request.addHeader("content-type", "application/x-www-form-urlencoded");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);

		    if(response.getStatusLine().getStatusCode()>=400) {
		    	return false;
		    }

		}catch (Exception ex) {
		    return false;
		} finally {
		}
		return true;
	}
}
