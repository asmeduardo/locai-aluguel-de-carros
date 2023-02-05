package br.com.locaialugueldecarros.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecaptchaUtil {

    public static boolean validateRecaptcha(String recaptchaResponse) {
        try {
            String secret = "sua_chave_secreta_aqui";
            String url = "https://www.google.com/recaptcha/api/siteverify?secret="
                    + secret + "&response=" + recaptchaResponse;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            JSONObject jsonObject = new JSONObject(response.toString());
            return jsonObject.getBoolean("success");
        } catch (IOException | JSONException e) {
            return false;
        }
    }
}