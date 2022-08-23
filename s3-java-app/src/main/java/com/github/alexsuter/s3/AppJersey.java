package com.github.alexsuter.s3;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.axonivy.connector.aws.authentication.Aws4AuthenticationRequestFilter;

public class AppJersey {

  public static void main(String[] args) {
    var accessKey = "lr5yr8PXGGZv5Glw";
    var secretKey = "B5jUWaw9V7Fwsr8hfymIZWnG1buy0VEg";

    var client = ClientBuilder.newClient()
            .register(new Aws4AuthenticationRequestFilter())
            .property("accessKey", accessKey)
            .property("secretKey", secretKey)
            .property("regionName", "eu-central-1")
            .property("serviceName", "s3");
    var target = client.target("http://localhost:9000");
             //.path("buckets");

    var response = target
            .request(MediaType.APPLICATION_JSON)
            //.header("Authorization", accessToken + ":" + secretKey)
            .get();
    var content = response.readEntity(String.class);
    System.out.println(content);
  }
}
