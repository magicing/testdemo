package ipconfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringEscapeUtils;

import net.sf.json.JSONObject;

/**
 * 
 * @GetAreaByIp 根据ip获取地址
 *
 * @date 2017年12月29日
 *
 * @author Mr.dong
 *
 */
public class GetAreaByIp {

	public static void main(String[] args)  {  
		  
	    System.out.println(getIpArea("124.65.50.138"));  
	  
	}
	
	public static String getIpArea(String ip) {

		String path = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
		String inputline = "";
		String info = "";
		 try {  
		        URL url = new URL(path);  
		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
		        conn.setReadTimeout(10*1000);  
		        conn.setRequestMethod("GET");  
		          
		        InputStreamReader  inStream = new InputStreamReader(conn.getInputStream(),"UTF-8");  
		        BufferedReader buffer=new BufferedReader(inStream);  
		           
		        while((inputline=buffer.readLine())!=null){  
		            info+=inputline;  
		        }  
		          
		    } catch (MalformedURLException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  
		  
		    JSONObject jsonob = JSONObject.fromObject((JSONObject.fromObject(info).getString("data")));  
		    String city = StringEscapeUtils.escapeSql(jsonob.getString("city"));  
		      
		    return city;
	}

}
