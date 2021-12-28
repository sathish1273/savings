package com.stackroute.keepnote.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Test
{
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String message = "Junk characters? method sendMultipartTextMessage only send text message. If you want to send non text message, you should look to method sendDataMessage. Below is the code excerpt from android cts. It has example on how to send long messages.";      
		String phone = "8801140530";
		String username = "abcd";
		String password = "1234";
		String address = "http://localhost";
		String port = "8090";

		URL url = new URL(
		        address+":"+port+"/SendSMS?username="+username+"&password="+password+
		        "&phone="+phone+"&message="+URLEncoder.encode(message,"UTF-8"));
      
		URLConnection connection = url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while((inputLine = bufferedReader.readLine()) !=null){
		    System.out.println(inputLine);
		}
		bufferedReader.close();
		}
		}
//	
//	  public static String Token;
//	    Form sms;
//	    UserAgent agent;
//
//	    /**
//	     * Used to login at http://www.160by2.com bu using username and password
//	     * @param username
//	     * @param Password
//	     * @throws ResponseException
//	     * @throws NotFound
//	     */
//	    public void login(String username,String Password) throws ResponseException, NotFound {
//
//	        agent=new UserAgent();
//	        agent.visit("http://www.160by2.com/Index");
//	        Form form=agent.doc.getForm(0);
//	        form.setTextField("username",username);
//	        form.setPassword("password",Password);
//	        form.submit();
//
//	        Token=agent.getLocation().substring(agent.getLocation().indexOf("?id=")+4);
//	        agent.visit("http://www.160by2.com/SendSMS?id="+Token);
//	        sms=agent.doc.getForm(0);
//	    }
//
//	    /**
//	     * Used to send msg to specified phone number.
//	     * @param message
//	     * @param Phone_No
//	     * @throws NotFound
//	     * @throws ResponseException
//	     */
//	    public void sendSMS(String message,String Phone_No) throws NotFound, ResponseException {
//
//	        sms.setTextField(sms.getElement().findFirst("<input type=\"text\" placeholder=\"Enter Mobile Number or Name\"").getAt("name"),Phone_No);
//	        sms.setTextArea("sendSMSMsg",message);
//	        sms.setHidden("maxwellapps",Token);
//	        sms.setHidden("hid_exists","no");
//	        sms.setAction("http://www.160by2.com/"+sms.getElement().findFirst("<input type=\"hidden\" id=\"fkapps\"").getAt("value"));
//	        sms.submit();
//	        System.out.println(agent.doc.innerHTML());
//	    }
//
//}
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//
//import java.util.List;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.protocol.ClientContext;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HttpContext;
//import org.apache.http.util.EntityUtils;
//
//
//public class Test {
//    HttpClient httpclient = new DefaultHttpClient();
//    CookieStore cookieStore = new BasicCookieStore();
//    HttpContext localContext = new BasicHttpContext();
//    HttpGet httpget;
//    HttpResponse response;
//    HttpEntity entity;
//    List<Cookie> cookies;
//    Cookie cookie = null;
//    private String action1;
//    final String sendSMSPage = "http://160by2.com/SendSMS?";
//    final String sendSMSActionPage =
//        "http://160by2.com/SendSMSAction?hid_exists=yes";
//    final String loginPage = "http://160by2.com/re-login?";
//
//    public Test() {
//    }
//
//    public boolean getLoggedIn(String uname, String pwd) throws IOException,
//                                                                ClientProtocolException {
//        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
//        URI uri = null;
//        uri = URI.create(loginPage + "username=" + uname + "&password=" + pwd);
//        HttpGet httpget = new HttpGet(uri);
//        response = httpclient.execute(httpget, localContext);
//        entity = response.getEntity();
//        cookies = cookieStore.getCookies();
//        Cookie cookie = cookies.get(0);
//        String cookieSet = cookie.getName() + "=" + cookie.getValue();
//        EntityUtils.consume(entity);
//
//        fetchAction(cookieSet);
//
//        if (isLoggedIn())
//            return true;
//        else
//            return false;
//    }
//
//    private void fetchAction(String cookieSet) throws IOException {
//
//        URLConnection conn = new URL(sendSMSPage).openConnection();
//        conn.setRequestProperty("Cookie", cookieSet);
//        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
//        BufferedReader br = new BufferedReader(isr);
//        String content;
//        StringBuilder sBuilder = new StringBuilder();
//        while (true) {
//            content = br.readLine();
//            if (content == null)
//                break;
//            else if (content.contains("id=\"action1\"")) {
//                sBuilder.append(content);
//                break;
//            }
//        }
//        String action[] = content.split("value=\"");
//        String action1[] = action[1].split("\"");
//        setAction1(action1[0]);
//    }
//
//    public void sendSms(String to, String msg) throws URISyntaxException,
//                                                      UnsupportedEncodingException,
//                                                      IOException,
//                                                      ClientProtocolException {
//
//        URI uri = null;
//        msg = URLEncoder.encode(msg, "utf-8");
//        String params =
//            "&action1=" + getAction1() + "&mobile1=" + to + "&msg1=" + msg;
//        uri = new URI(sendSMSActionPage + params);
//        httpget = new HttpGet(uri);
//        httpclient.execute(httpget, localContext);
//    }
//
//    public boolean isLoggedIn() {
//        try {
//            if (cookies.size() == 2)
//                return true;
//            else
//                return false;
//
//        } catch (NullPointerException e) {
//            return false;
//        }
//    }
//
//    public void setAction1(String action1) {
//        this.action1 = action1;
//    }
//
//    public String getAction1() {
//        return action1;
//    }
//
//
//    public static void main(String[] args) throws IOException,
//                                                  ClientProtocolException {
//        Test proxy = new Test();
//        String username = "your 160b2 ten digit mobile number username";
//        String password = "your password";
//        String to = "ten digit mobile number";
//        String msg = "your message as string";
//        try {
//            proxy.getLoggedIn(username, password);
//            proxy.sendSms(to, msg);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
