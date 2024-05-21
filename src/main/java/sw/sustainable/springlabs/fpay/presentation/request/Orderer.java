package sw.sustainable.springlabs.fpay.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Orderer {
    @NotNull
    @NotBlank
    private final String name;

    @NotNull
    @NotBlank
    private final String phoneNumber;
}
