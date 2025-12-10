package PrimerTrimestre.Net.Tarea35;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class CryptoAPI {

    public static void consultarCriptomoneda(String entrada) {
        try {
            String apiUrl = "https://api.coinlore.net/api/tickers/";

            URL url = new URL(apiUrl);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setConnectTimeout(10000);
            conexion.setReadTimeout(10000);

            int codigoRespuesta = conexion.getResponseCode();
            if (codigoRespuesta != 200) {
                System.out.println("Error al conectar con la API. Codigo: " + codigoRespuesta);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }
            reader.close();
            conexion.disconnect();

            procesarRespuesta(respuesta.toString(), entrada);

        } catch (java.net.SocketTimeoutException e) {
            System.out.println("Tiempo de espera agotado. Verifica tu conexion a internet.");
        } catch (java.io.IOException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al consultar la API: " + e.getMessage());
        }
    }

    private static void procesarRespuesta(String respuestaJSON, String entradaUsuario) {
        try {
            JSONObject jsonRespuesta = new JSONObject(respuestaJSON);
            JSONArray criptomonedas = jsonRespuesta.getJSONArray("data");

            String entradaMin = entradaUsuario.toLowerCase();
            boolean encontrada = false;

            // Buscar primero por simbolo exacto
            for (int i = 0; i < criptomonedas.length(); i++) {
                JSONObject moneda = criptomonedas.getJSONObject(i);
                String simbolo = moneda.getString("symbol").toLowerCase();

                if (simbolo.equals(entradaMin)) {
                    mostrarInformacion(moneda);
                    encontrada = true;
                    break;
                }
            }

            // Si no se encuentra por simbolo, buscar por nombre
            if (!encontrada) {
                for (int i = 0; i < criptomonedas.length(); i++) {
                    JSONObject moneda = criptomonedas.getJSONObject(i);
                    String nombre = moneda.getString("name").toLowerCase();

                    if (nombre.contains(entradaMin)) {
                        mostrarInformacion(moneda);
                        encontrada = true;
                        break;
                    }
                }
            }

            if (!encontrada) {
                System.out.println("\nMoneda no encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Error al procesar los datos: " + e.getMessage());
        }
    }

    private static void mostrarInformacion(JSONObject moneda) {
        String nombre = moneda.getString("name");
        String simbolo = moneda.getString("symbol");
        double precio = moneda.getDouble("price_usd");
        int rank = moneda.getInt("rank");
        double cambio24h = moneda.getDouble("percent_change_24h");


        System.out.printf("Nombre: %s (%s)%n", nombre, simbolo);
        System.out.printf("Precio: $%,.4f USD%n", precio);
        System.out.printf("Ranking: %dº%n", rank);

        if (cambio24h > 0) {
            System.out.printf("Variacion 24h: +%.2f%% (Subiendo)%n", cambio24h);
        } else if (cambio24h < 0) {
            System.out.printf("Variacion 24h: %.2f%% (Bajando)%n", cambio24h);
        } else {
            System.out.printf("Variacion 24h: %.2f%% (Estable)%n", cambio24h);
        }

    }
}
