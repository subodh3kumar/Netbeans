package workshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import workshop.model.Payload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileDownloadClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Value("${local.directory}")
    private String localDirectory;

    @GetMapping("/v1/download")
    public String largeFileDownloadV1() throws IOException {
        log.info("largeFileDownloadV1() method called");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

        Payload payload = new Payload();
        payload.setFileDirectory("C:/Development/Files/Input/algs4-data/");
        payload.setFileName("largeEWD.txt");

        HttpEntity<?> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                "http://localhost:8081/file/v5/download",
                HttpMethod.POST,
                entity,
                byte[].class
        );

        String fileName = response.getHeaders().getContentDisposition().getFilename();
        String absolutePath = localDirectory + fileName;
        Files.write(Paths.get(absolutePath), response.getBody());
        return Files.exists(Paths.get(absolutePath)) == true ? "file downloaded" : "file not downloaded";
    }

    @GetMapping("/v2/download")
    public String largeFileDownloadV2() {
        log.info("largeFileDownloadV2() method called");

        Payload payload = new Payload();
        payload.setFileDirectory("C:/Development/Files/Input/algs4-data/");
        payload.setFileName("largeEWD.txt");

        Mono<HttpHeaders> headersMono = webClient.post()
                .uri("http://localhost:8081/file/v5/download")
                .body(Mono.just(payload), Payload.class)
                .exchange()
                .map(clientResponse -> clientResponse.headers().asHttpHeaders());

        HttpHeaders httpHeaders = getHttpHeaders(headersMono);

        String fileName = httpHeaders.getContentDisposition().getFilename();
        log.info("file name: " + fileName);

        Flux<DataBuffer> dataBufferFlux = webClient.post()
                .uri("http://localhost:8081/file/v5/download")
                .body(Mono.just(payload), Payload.class)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        String absolutePath = localDirectory + fileName;
        final Path path = Paths.get(absolutePath);
        DataBufferUtils.write(dataBufferFlux, path).block();

        return Files.exists(Paths.get(absolutePath)) == true ? "file downloaded" : "file not downloaded";
    }

    private HttpHeaders getHttpHeaders(Mono<HttpHeaders> headersMono) {
        return headersMono.block();
    }

    @GetMapping("/v3/download")
    public String largeFileDownloadV3() {
        log.info("largeFileDownloadV3() method called");

        String url = "http://localhost:8081/file/v5/download";

        Payload payload = new Payload();
        payload.setFileDirectory("C:/Development/Files/Input/algs4-data/");
        payload.setFileName("largeEWD.txt");

        RequestCallback requestCallback = restTemplate.httpEntityCallback(payload);

        Path path = restTemplate.execute(url, HttpMethod.POST, requestCallback, this::extractData);

        String fileName = null;
        if (path != null) {
            fileName = path.getFileName().toString();
        }
        log.info("File name: {}", fileName);
        return Files.exists(path) == true ? "file downloaded" : "file not downloaded";
    }

    private Path extractData(ClientHttpResponse response) throws IOException {
        String fileName = response.getHeaders().getContentDisposition().getFilename();
        log.info("file name: " + fileName);
        Path path = Paths.get(localDirectory + fileName);
        Files.deleteIfExists(path);
        Files.copy(response.getBody(), path);
        return path;
    }
}
