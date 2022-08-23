package com.github.alexsuter.s3;

import java.util.Map;

import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

public class AppMinIO {

  public static void main(String[] args) throws Exception {
    var accessKey = "lr5yr8PXGGZv5Glw";
    var secretKey = "B5jUWaw9V7Fwsr8hfymIZWnG1buy0VEg";

    var client = MinioClient.builder()
            .endpoint("http://localhost:9000")
            .credentials(accessKey, secretKey)
            .build();

    //upload(client);
    //list(client);
    //search(client);
    //random(client);
    //download(client);
  }

  private static void list(MinioClient client) throws Exception {
    for (var bucket : client.listBuckets()) {
      System.out.println("Bucket: " + bucket.name());

      var args = ListObjectsArgs.builder()
              .bucket(bucket.name())
              .prefix("folder/")
              //.startAfter("sample.pdf")
              .build();
      for (var object : client.listObjects(args)) {
        var item = object.get();
        System.out.println("Object: " + item.objectName() + " size=" + item.size());
      }
    }
  }

  public static void upload(MinioClient client) throws Exception {
    var args = UploadObjectArgs.builder()
            .bucket("ivy")
            .object("folder/abc.pdf")
            .filename("/home/alex/Downloads/sample.pdf")
            .userMetadata(Map.of("type", "case"))
            //.contentType("text/plain")
            .build();
    client.uploadObject(args);
  }

  public static void download(MinioClient client) throws Exception {
    var args = DownloadObjectArgs.builder()
            .bucket("ivy")
            .object("folder/abc.pdf")
            .filename("/home/alex/Downloads/sampleggggg.pdf")
            .build();
    client.downloadObject(args);
  }

  public static void stream(MinioClient client) throws Exception {
    var args = GetObjectArgs.builder()
            .bucket("ivy")
            .object("folder/abc.pdf")
            .build();
    var response = client.getObject(args);
  }

  public static void random(MinioClient client) throws Exception {
    for (var i = 1; i < 1000; i++) {
      var args = UploadObjectArgs.builder()
              .bucket("ivy")
              .object("case-" + i)
              .filename("/home/alex/Downloads/sample.pdf")
              .userMetadata(Map.of("type", "case", "caseId", i + ""))
              .build();
      client.uploadObject(args);
    }
  }
}
