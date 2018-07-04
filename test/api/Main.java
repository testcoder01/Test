package test.api;

//import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
//import com.sun.net.httpserver.HttpServer;
import org.glassfish.grizzly.http.server.HttpServer;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class Main {
	public static void main(String[] args) throws Exception {

		ResourceConfig config = new ResourceConfig().packages("test.api");

		URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();

		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config, true);
		System.out.println("Server started. Hit enter to stop.");
		System.in.read();
		server.shutdown();
		System.out.println("Server stopped.");
	}
}

