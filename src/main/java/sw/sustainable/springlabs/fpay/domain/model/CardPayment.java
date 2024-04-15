package sw.sustainable.springlabs.fpay.domain.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class CardPayment {
    private String cardNumber;
    private String cardCorpName;
    private String cardOwnerName;
}
