package sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Configuration
public class TossApiClientConfig {
    private static final String BASE_URL = "https://api.tosspayments.com/v1/";
    private static final String SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

    @Bean
    public OkHttpClient okHttpClient() {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedKey = encoder.encode((SECRET_KEY + ":").getBytes(StandardCharsets.UTF_8));
        String authorization = "Basic " + new String(encodedKey);

        return new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(chain -> {
                Request original = chain.request().newBuilder().header("Authorization", authorization).build();
                return chain.proceed(original);
            })
            .build();
    }

    @Bean
    public Retrofit retrofit(OkHttpClient okHttpClient) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(okHttpClient)
            .build();
    }

    @Bean
    public TossPaymentAPIs createApiClient(Retrofit retrofit) {
        return retrofit.create(TossPaymentAPIs.class);
    }
}
