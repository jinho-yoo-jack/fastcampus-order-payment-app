package sw.sustainable.springlabs.fpay.infrastructure.out.pg;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class ApiClientConfig {
    private static final String BASE_URL =  "https://api.tosspayments.com/";
    private static final String PRIVATE_KEY = "Basic test_sk_0RnYX2w532257LzwRYBK3NeyqApQ";

    @PostConstruct
    public void init(){
        log.info("ApiClient Base URL: {}", BASE_URL);
        log.info("ApiClient Private Key: {}", BASE_URL);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder().addHeader("Authorization", PRIVATE_KEY).build();
                    return chain.proceed(request);
                })
                .build();
    }

    @Bean
    public Retrofit retrofit(OkHttpClient client) {
        String baseURL = BASE_URL + "v1/";
        return new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
