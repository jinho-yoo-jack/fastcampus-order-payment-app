package sw.sustainable.springlabs.fpay.infrastructure.out.pg.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.TossPaymentAPIs;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Configuration
public class MockApiClientConfig {
    private static final String BASE_URL = "https://6cf3c32b-3908-4eaf-954f-b24a79d319a0.mock.pstmn.io/";
    private static final String SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

    @Bean
    public OkHttpClient mockOkHttpClient() {
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
    public Retrofit mockRetrofit(OkHttpClient mockOkHttpClient) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(mockOkHttpClient)
            .build();
    }

    @Bean
    public MockPaymentAPIs mockCreateApiClient(Retrofit mockRetrofit) {
        return mockRetrofit.create(MockPaymentAPIs.class);
    }
}
