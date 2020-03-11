/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IvyMailer;

import IvyMailer.Response.Base;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author labcisco
 */
public class Http {

    public static String host;
    public static String token;

    public static Base request(String method, String data) throws Exception {

        if (method == null) {
            method = "";
        }

        HttpURLConnection httpClient
                = (HttpURLConnection) new URL(host + method).openConnection();

        httpClient.setRequestProperty("Content-Type", "application/json; utf-8");

        if (token != null && token.length() > 0) {
            httpClient.setRequestProperty("X-Access-Token", token);
        }
        if (data == null) {
            httpClient.setRequestMethod("GET");
        } else {
            httpClient.setDoOutput(true);
            httpClient.setRequestMethod("POST");
            try (OutputStream os = httpClient.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        StringBuilder response = new StringBuilder();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

        }

        return new Base(response.toString());
    }

}
