package info.gladen.springssrf;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.Buffer;

@RestController
@SpringBootApplication
public class SpringssrfApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringssrfApplication.class, args);
	}

	@RequestMapping("/")
	public ResponseEntity<String> handleMain() {
		return ResponseEntity.ok("SSRF vulnerable web application");
	}

	@RequestMapping("/intraVulnerability")
	public ResponseEntity<String> handleIntraVulnerability(@RequestParam(required = true) String uri) {
		HttpRequest httpRequest = null;
		try {
			httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
        return ResponseEntity.ok(httpResponse.body());
	}

	@RequestMapping("/interVulnerability")
	public ResponseEntity<String> handleInterVulnerability(@RequestParam(required = true) String uri) {
		return ResponseEntity.ok(SSRFVulnerabilities.get(SSRFVulnerabilities::getContentsOfUri, uri));
	}

	@RequestMapping("/imageVulnerability")
	public ResponseEntity<byte[]> handleImageVulnerability(@RequestParam(required = true) String uri) throws IOException {
		BufferedImage img = (BufferedImage) SSRFVulnerabilities.get(SSRFVulnerabilities::getImageOfUri, uri);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "jpeg", baos);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(baos.toByteArray());
	}
}
