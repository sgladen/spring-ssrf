package info.gladen.springssrf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

public class SSRFVulnerabilities {
    public static String getContentsOfUri(URI uri) {
        HttpRequest httpRequest = null;
        try {
            httpRequest = HttpRequest.newBuilder().uri(uri).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return httpResponse.body();
    }

    public static Image getImageOfUri(URI uri) {
        try {
            return ImageIO.read(uri.toURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <O> O get(Function<URI, O> funcToCall, String uri) {
        return funcToCall.apply(URI.create(uri));
    }
}
