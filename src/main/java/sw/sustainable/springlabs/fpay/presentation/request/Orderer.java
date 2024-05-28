package sw.sustainable.springlabs.fpay.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
