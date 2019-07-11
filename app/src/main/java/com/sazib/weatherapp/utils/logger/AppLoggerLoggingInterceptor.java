package com.sazib.weatherapp.utils.logger;

import androidx.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AppLoggerLoggingInterceptor implements Interceptor {

  @Override
  public Response intercept(@NonNull Chain chain) throws IOException {
    Request request = chain.request();
    long t1 = System.nanoTime();
    Response response = chain.proceed(request);

    ResponseBody responseBody = response.body();
    String responseBodyString = null;
    if (response.body() != null) {
      responseBodyString = response.body().string();
    }

    Response newResponse = null;
    if (responseBody != null) {
      if (responseBodyString != null) {
        newResponse = response.newBuilder()
            .body(ResponseBody.create(responseBody.contentType(), responseBodyString.getBytes()))
            .build();
      }
    }

    long t2 = System.nanoTime();
    AppLogger.i("Received response for %s in %.1fms%n%s", response.request().url(),
        (t2 - t1) / 1e6d, response.headers());
    AppLogger.v("REQUEST BODY BEGIN");
    AppLogger.json(responseBodyString);
    AppLogger.v("REQUEST BODY END");

    return newResponse;
  }
}
