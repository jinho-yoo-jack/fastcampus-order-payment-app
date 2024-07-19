package sw.sustainable.springlabs.fpay.infrastructure.out.pg.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MockTossApiClientConfig {
    private static final String BASE_URL =  "https://58d05ef3-499f-497c-a08c-33e520c88989.mock.pstmn.io/";
    private static final String SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6:";

    @PostConstruct
    public void init(){
        log.info("MockApiClient Base URL: {}", BASE_URL);
        log.info("MockApiClient Private Key: {}", SECRET_KEY);
    }

    @Bean
    public OkHttpClient mockOkHttpClient() {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((SECRET_KEY + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);
        log.info("key: {}", authorizations);

        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder().addHeader("Authorization", authorizations).build();
                    return chain.proceed(request);
                })
                .build();
    }

    @Bean
    public Retrofit mockRetrofit(OkHttpClient mockOkHttpClient) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(mockOkHttpClient)
                .build();
    }

    @Bean
    public MockTossPaymentAPIs createMockApiClient(Retrofit mockRetrofit) {
        return mockRetrofit.create(MockTossPaymentAPIs.class);
    }
}
