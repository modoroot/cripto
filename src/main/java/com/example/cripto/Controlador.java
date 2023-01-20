package com.example.cripto;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class Controlador {
    @GetMapping("/bitcoin")
    public String
    bitcoin(@RequestParam(value = "tipo", defaultValue = "EUR") String tipo) {
        String url = "https://blockchain.info/ticker";
        String respuesta = null;
        try {
            respuesta = peticionHttpGet(url, tipo).toString();
            System.out.println("La respuesta es:\n" + respuesta);
            return respuesta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public static JSONObject peticionHttpGet(String urlParaVisitar, String tipo) throws Exception {
        StringBuilder resultado = new StringBuilder();
        URL url = new URL(urlParaVisitar);
        HttpURLConnection conexion = (HttpURLConnection)
                url.openConnection();
        conexion.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new
                InputStreamReader(conexion.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        rd.close();
        return parsearJSON(resultado.toString(), tipo);
    }

    public static JSONObject parsearJSON(String json, String tipo) {

        JSONObject objetoJson = new JSONObject(json);
        JSONObject divisa = objetoJson.getJSONObject(tipo);

        double precio = divisa.getDouble("15m");
        double last = divisa.getDouble("last");
        double buy = divisa.getDouble("buy");
        double sell = divisa.getDouble("sell");
        String symbol = divisa.getString("symbol");

        Bitcoin bitcoin = new Bitcoin(precio, last, sell, symbol);
        JSONObject jsonO = new JSONObject(bitcoin);
        System.out.println(jsonO);
        return jsonO;
    }
}
