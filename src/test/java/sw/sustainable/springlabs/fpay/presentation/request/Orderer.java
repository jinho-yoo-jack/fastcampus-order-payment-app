package sw.sustainable.springlabs.fpay.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Orderer {
    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;
}
