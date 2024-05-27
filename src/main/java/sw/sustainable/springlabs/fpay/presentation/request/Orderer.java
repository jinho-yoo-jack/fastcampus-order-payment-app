package sw.sustainable.springlabs.fpay.presentation.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@RequiredArgsConstructor
public class Orderer {
    @NotBlank(message = "The orderer.name is required.")
    private final String name;

    @NotBlank
    private final String phoneNumber;
}
