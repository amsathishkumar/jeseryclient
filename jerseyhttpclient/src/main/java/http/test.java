package http;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class test {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		
		String uriPath = "https://219.65.51.11:9182/vnms/operations/login";
		HttpAuthenticationFeature httpFeature = HttpAuthenticationFeature.basicBuilder().build();
		SSLContext sslContext = null;
        sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, getTrustManager(), null);
		Client client = ClientBuilder.newBuilder().hostnameVerifier((s, session) -> true)
		  .sslContext(sslContext).register(httpFeature).build();
 		 WebTarget target = client.target(uriPath);
 		Response postResponse = target.request()
	              .property(
	                  HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME,
	                  "system")
	              .property(
	                  HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD,
	                  "Tata@123")
	              .post(Entity.entity("", MediaType.APPLICATION_JSON));
	      System.out.println("sathish3");
	      System.out.println(postResponse.getStatus());
	      System.out.println(postResponse.bufferEntity());
		

		
	}
	private static TrustManager[] getTrustManager() {
		  return new TrustManager[] {
		    new X509TrustManager() {
		      public X509Certificate[] getAcceptedIssuers() {
		        return null;
		      }
		      public void checkServerTrusted(X509Certificate[] chain, String authType)
		      throws CertificateException {
		      }
		      public void checkClientTrusted(X509Certificate[] chain, String authType)
		      throws CertificateException {
		      }
		    }
		  };
		}	
		  
}



