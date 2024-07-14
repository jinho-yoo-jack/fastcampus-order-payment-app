package sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.method;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  "card": {
    "issuerCode": "71",
    "acquirerCode": "71",
    "number": "12345678****000*",
    "installmentPlanMonths": 0,
    "isInterestFree": false,
    "interestPayer": null,
    "approveNo": "00000000",
    "useCardPoint": false,
    "cardType": "신용",
    "ownerType": "개인",
    "acquireStatus": "READY",
    "receiptUrl": "https://dashboard.tosspayments.com/receipt/redirection?transactionId=tviva20240213121757MvuS8&ref=PX",
    "amount": 1000
  },
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String cardType;
    private String acquireStatus;
    private String approveNo;
    private int amount;
    private boolean isInterestFree;
    private String receiptUrl;

}
