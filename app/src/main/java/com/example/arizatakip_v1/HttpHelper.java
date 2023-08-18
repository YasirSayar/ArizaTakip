package com.example.arizatakip_v1;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpHelper {

    public static void sendGeneralNotification() {
        String apiKey = "AIzaSyCvxqCvN_9G-gUjDyauyeZ72WFg3DTD_KM";
        String fcmUrl = "https://fcm.googleapis.com/fcm/send";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(fcmUrl);

            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "key=" + apiKey);

            String notificationPayload = "{\n" +
                    "  \"to\": \"/topics/all\",\n" +
                    "  \"notification\": {\n" +
                    "    \"title\": \"Genel Bildirim\",\n" +
                    "    \"body\": \"Ariza kaydı\"\n" +
                    "  }\n" +
                    "}";

            StringEntity entity = new StringEntity(notificationPayload);
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("HTTP Status Code: " + statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
