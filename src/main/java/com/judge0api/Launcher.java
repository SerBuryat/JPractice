package com.judge0api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Artem Anosov
 */
public class Launcher {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String JUDGE0_API_HTTPS = "https://judge0-ce.p.rapidapi.com";
    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final boolean BASE_64_ENCODED = false;
    private static final String CONTENT_TYPE = "application/json";
    private static final String X_RAPIDAPI_HOST = "judge0-ce.p.rapidapi.com";
    private static final String X_RAPIDAPI_KEY = "aef6e0e391mshacbc9b668a210b3p13a25ejsna71dba04aa7c";
    private static final CreateJavaSubmission CREATE_JAVA_SUBMISSION;

    static {
        CREATE_JAVA_SUBMISSION = CreateJavaSubmission.builder()
            .sourceCode("import java.time.LocalDateTime; "
                + "public class Main { "
                    + "public static void main(String[] args) { "
                        + "System.out.println(\"Current time: \" + LocalDateTime.now());"
                    + "} "
                + "}")
            .build();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CreateSubmissionToken token = post(CREATE_JAVA_SUBMISSION);

        GetJavaSubmission get = get(token.getToken());

        System.out.println(get.getConsoleOut());
    }

    public static GetJavaSubmission get(String token) throws IOException, InterruptedException {
        //All fields = "*"
        String fields = "stdout";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(getSubmissionJudge0ApiUri(token, fields))
            .header("x-rapidapi-host", X_RAPIDAPI_HOST)
            .header("x-rapidapi-key", X_RAPIDAPI_KEY)
            .method(GET, HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response =
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), GetJavaSubmission.class);
    }

    /** @return json "token: 'token'"*/
    public static CreateSubmissionToken post(CreateJavaSubmission submission) throws IOException, InterruptedException {
        String fields = "*";

        String post = mapper.writeValueAsString(submission);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(postSubmissionJudge0ApiUri(fields))
            .header("content-type", CONTENT_TYPE)
            .header("x-rapidapi-host", X_RAPIDAPI_HOST)
            .header("x-rapidapi-key", X_RAPIDAPI_KEY)
            .method(POST, HttpRequest.BodyPublishers.ofString(post))
            .build();
        HttpResponse<String> response =
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), CreateSubmissionToken.class);
    }

    private static URI postSubmissionJudge0ApiUri(String fields) {
        return URI.create(JUDGE0_API_HTTPS + "/submissions?" +
                "base64_encoded=" + BASE_64_ENCODED +
                "&fields=" + fields
        );
    }

    private static URI getSubmissionJudge0ApiUri(String token, String fields) {
        return URI.create(JUDGE0_API_HTTPS + "/submissions/" +
            token +"?base64_encoded=" + BASE_64_ENCODED +
            "&fields=" + fields
        );
    }
}
