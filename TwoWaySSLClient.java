
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class TwoWaySSLClient {
	public static void main(String[] args) {
		
		System.setProperty("javax.net.ssl.keyStoreType", "JKS");
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		System.setProperty("javax.net.debug", "all");
        System.setProperty("javax.net.ssl.keyStore", "/Users/user/Desktop/folders/projs/refs/ca4/root/ca/tomcatclient.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");
        System.setProperty("javax.net.ssl.trustStore", "/Users/user/Desktop/folders/projs/refs/ca4/root/ca/tomcatclienttruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        System.setProperty("deployment.security.SSLv2Hello","false"); 
        System.setProperty("deployment.security.SSLv3","false"); 
        System.setProperty("deployment.security.TLSv1","false");
        System.setProperty("deployment.security.TLSv1.1","false");
        System.setProperty("deployment.security.TLSv1.2","true");
        
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			URL url = new URL("https://localhost:8443/");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});
			conn.setSSLSocketFactory(sslsocketfactory);
			InputStream inputstream = conn.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			String string = null;
			while ((string = bufferedreader.readLine()) != null) {
				System.out.println("Received " + string);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}