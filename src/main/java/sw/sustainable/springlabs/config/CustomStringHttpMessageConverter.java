package sw.sustainable.springlabs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import sw.sustainable.springlabs.common.ApiResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomStringHttpMessageConverter extends AbstractHttpMessageConverter<ApiResponse<Object>> {
    private final ObjectMapper objectMapper;

    public CustomStringHttpMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.APPLICATION_JSON);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return String.class == clazz;
    }

    @Override
    protected ApiResponse<Object> readInternal(Class<? extends ApiResponse<Object>> clazz, HttpInputMessage inputMessage)
        throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException("This converter can only support writing operation.");
    }

    @Override
    protected void writeInternal(ApiResponse<Object> s, HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
        String str = this.objectMapper.writeValueAsString(s);
        StreamUtils.copy(str.getBytes(StandardCharsets.UTF_8), outputMessage.getBody());
    }
}
