package PrimerTrimestre.Net.Tarea34;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

public class ResultadoWeb {
    private final String url;
    private final long tiempoMs;
    private final int tamanoCaracteres;

    public ResultadoWeb(String url, long tiempoMs, int tamanoCaracteres) {
        this.url = url;
        this.tiempoMs = tiempoMs;
        this.tamanoCaracteres = tamanoCaracteres;
    }

    public static ResultadoWeb analizarURL(String url) {
        try {
            HttpClient cliente = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();

            // Medir tiempo de inicio
            Instant inicio = Instant.now();

            // Enviar petición
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            // Medir tiempo de fin
            Instant fin = Instant.now();

            // Calcular tiempo de respuesta en milisegundos
            long tiempoMs = Duration.between(inicio, fin).toMillis();

            // Obtener tamaño en caracteres del cuerpo de la respuesta
            int tamano = response.body().length();

            return new ResultadoWeb(url, tiempoMs, tamano);

        } catch (Exception e) {
            System.err.println("Error al analizar " + url);
            return new ResultadoWeb(url, Long.MAX_VALUE, -1);
        }
    }

    public String getUrl() { return url; }
    public long getTiempoMs() { return tiempoMs; }
    public int getTamanoCaracteres() { return tamanoCaracteres; }
}