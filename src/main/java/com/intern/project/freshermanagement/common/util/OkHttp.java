package com.intern.project.freshermanagement.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.project.freshermanagement.common.exception.NetworkApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.Document;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class OkHttp {

    private static final int BAD_REQUEST = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int TOO_MANY_REQUESTS = 429;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final Set<Integer> FAILURE_STATUS_CODE = new HashSet<>(Arrays.asList(BAD_REQUEST, UNAUTHORIZED, FORBIDDEN, NOT_FOUND, TOO_MANY_REQUESTS, INTERNAL_SERVER_ERROR, SERVICE_UNAVAILABLE));
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public String get(String endpoint, Map<String, String> params, Map<String, String> headers) throws NetworkApiException {
        if (headers == null) {
            headers = new HashMap<>();
        }
        String url = buildUrl(endpoint, params);
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).get().build();
        Call call = client.newCall(request);
        ResponseBody body = null;
        String res = "";
        try (Response response = call.execute()) {

            body = response.body();
            if (body != null) {
                res = body.string();
            }
            if (FAILURE_STATUS_CODE.contains(response.code())) {
                log.error("{}: {}", LocalDateTime.now(), res);
                handleApiException(response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (body != null) {
                body.close();
            }
        }
        return res;
    }


    public String postWithJsonBody(String endpoint, Map<String, String> params, Map<String, String> headers, Object body) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        String url = buildUrl(endpoint, params);
        ResponseBody responseBody = null;
        String res = "";
        try {
            RequestBody requestBody = RequestBody.create(mapper.writeValueAsString(body), MEDIA_TYPE_JSON);
            Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(requestBody).build();
            Call call = client.newCall(request);
            try (Response response = call.execute()) {
                responseBody = response.body();
                if (responseBody != null) {
                    res = responseBody.string();
                }
                if (FAILURE_STATUS_CODE.contains(response.code())) {
                    handleApiException(response.code());
                }
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }

    public String postWithFormData(String endpoint, Map<String, String> params, Map<String, String> headers, Map<String, Object> data) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        String url = buildUrl(endpoint, params);
        ResponseBody responseBody = null;
        String res = "";
        try {
            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
            data.forEach((key, value) -> {
                if (value instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) value;
                    MediaType type = fromFileType(FileUtils.getFileType(file));
                    try {
                        requestBody.addFormDataPart(key, FileUtils.getFileName(file), RequestBody.create(type, file.getBytes())).build();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    try {
                        String stringValue = mapper.writeValueAsString(value);
                        requestBody.addFormDataPart(key, stringValue);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
            Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(requestBody.build()).build();
            Call call = client.newCall(request);
            Response response = call.execute();
            responseBody = response.body();
            if (responseBody != null) {
                res = responseBody.string();
            }
            if (FAILURE_STATUS_CODE.contains(response.code())) {
                log.error("{} - request: {} - response: {}", LocalDateTime.now(), data, res);
                handleApiException(response.code());
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }

    public String postWithStringFormData(String endpoint, Map<String, String> params, Map<String, String> headers, Map<String, String> data) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        String url = buildUrl(endpoint, params);
        ResponseBody responseBody = null;
        String res = "";
        try {
            MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
            data.forEach(requestBody::addFormDataPart);
            Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(requestBody.build()).build();
            Call call = client.newCall(request);
            Response response = call.execute();
            responseBody = response.body();
            if (responseBody != null) {
                res = responseBody.string();
            }
            if (FAILURE_STATUS_CODE.contains(response.code())) {
                log.error("{} - request: {} - response: {}", LocalDateTime.now(), data, res);
                handleApiException(response.code());
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }

    public String putWithJsonBody(String endpoint, Map<String, String> params, Map<String, String> headers, Object body) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        String url = buildUrl(endpoint, params);
        ResponseBody responseBody = null;
        String res = "";
        try {
            RequestBody requestBody = RequestBody.create(mapper.writeValueAsString(body), MEDIA_TYPE_JSON);
            Request request = new Request.Builder().url(url).headers(Headers.of(headers)).put(requestBody).build();
            Call call = client.newCall(request);
            Response response = call.execute();
            responseBody = response.body();
            if (responseBody != null) {
                res = responseBody.string();
            }
            if (FAILURE_STATUS_CODE.contains(response.code())) {
                handleApiException(response.code());
            }
            return  res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }

    public String delete(String endpoint, Map<String, String> params, Map<String, String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        String url = buildUrl(endpoint, params);
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).delete().build();
        Call call = client.newCall(request);
        ResponseBody responseBody = null;
        String res = "";
        try {
            Response response = call.execute();
            responseBody = response.body();
            if (responseBody != null) {
                res = responseBody.string();
            }
            if (FAILURE_STATUS_CODE.contains(response.code())) {
                log.error("{}: {}", LocalDateTime.now(), res);
                handleApiException(response.code());
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }

    public String patchWithJsonBody(String endpoint, Map<String, String> params, Map<String, String> headers, Object body) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        String url = buildUrl(endpoint, params);
        ResponseBody responseBody = null;
        String res = "";
        try {
            RequestBody requestBody = RequestBody.create(mapper.writeValueAsString(body), MEDIA_TYPE_JSON);
            Request request = new Request.Builder().url(url).headers(Headers.of(headers)).patch(requestBody).build();
            Call call = client.newCall(request);
            Response response = call.execute();
            responseBody = response.body();
            if (responseBody != null) {
                res = responseBody.string();
            }
            if (FAILURE_STATUS_CODE.contains(response.code())) {
                handleApiException(response.code());
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
    }

    private String buildUrl(String endpoint, Map<String, String> params) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(endpoint)).newBuilder();
        if (params != null) {
            params.forEach(urlBuilder::addQueryParameter);
        }
        return urlBuilder.build().toString();
    }

    private void handleApiException(int code) {
        if (!FAILURE_STATUS_CODE.contains(code)) {
            return;
        }
        switch (code) {
            case BAD_REQUEST:
                throw new NetworkApiException(HttpStatus.BAD_REQUEST, "Invalid information send to network!");
            case UNAUTHORIZED:
                throw new NetworkApiException(HttpStatus.BAD_REQUEST, "Authentication failed to network!");
            case FORBIDDEN:
                throw new NetworkApiException(HttpStatus.BAD_REQUEST, "Not authorized to network!");
            case NOT_FOUND:
                throw new NetworkApiException(HttpStatus.BAD_REQUEST, "Endpoint is not found in network!");
            case TOO_MANY_REQUESTS:
                throw new NetworkApiException(HttpStatus.BAD_REQUEST, "Too many request call to network!");
            case INTERNAL_SERVER_ERROR:
                throw new NetworkApiException(HttpStatus.BAD_REQUEST, "Platform errored when solve this request!");
            case SERVICE_UNAVAILABLE:
                throw new NetworkApiException(HttpStatus.BAD_REQUEST, "Platform service unavailable!");
            default:
        }
    }

    private MediaType fromFileType(String type) {
        Set<String> imageTypes = Set.of("png", "jpg", "jpeg");
        Set<String> videoTypes = Set.of("mp4");
        if (imageTypes.contains(type)) {
            return MediaType.parse("image/" + type);
        }
        if (videoTypes.contains(type)) {
            return MediaType.parse("video/" + type);
        }
        return null;
    }
}