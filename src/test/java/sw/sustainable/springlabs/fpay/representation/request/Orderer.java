package sw.sustainable.springlabs.fpay.representation.request;

import jakarta.validation.constraints.NotBlank;
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
