package sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response;

/*[
    {
    "mId": "tosspayments",
    "paymentKey": "xLpgeoO7410238740297423RBKEzMjPJyG",
    "transactionKey": "497BF239847238947B0491D84B4",
    "orderId": "EjBNtZK7j8q2TlGFLJ-9T",
    "currency": "KRW",
    "method": "카드",
    "fees": [
    {
    "type": "BASE",
    "fee": -2250
    },
    {
    "type": "ETC",
    "fee": 0
    }
    ],
    "approvedAt": "2023-11-25T13:03:39+09:00",
    "soldDate": "2023-11-25",
    "paidOutDate": "2023-11-30",
    "card": {
    "issuerCode": "11",
    "acquirerCode": "11",
    "number": "55704251****800*",
    "installmentPlanMonths": 3,
    "isInterestFree": true,
    "interestPayer": "CARD_COMPANY",
    "approveNo": "30024234",
    "useCardPoint": false,
    "cardType": "신용",
    "ownerType": "개인",
    "acquireStatus": "READY",
    "amount": 99800
    },
    "virtualAccount": null,
    "transfer": null,
    "mobilePhone": null,
    "giftCertificate": null,
    "easyPay": null,
    "cancel": {
        "transactionKey": "497BF239847238947B0491D84B4",
        "cancelReason": "주문취소",
        "taxExemptionAmount": 0,
        "canceledAt": "2023-11-25T13:40:03+09:00",
        "easyPayDiscountAmount": 0,
        "receiptKey": null,
        "cancelAmount": 99800,
        "taxFreeAmount": 0,
        "refundableAmount": 0,
        "cancelStatus": "DONE",
        "cancelRequestId": null
    },
    "amount": -99800,
    "interestFee": 0,
    "fee": -2250,
    "supplyAmount": -2045,
    "vat": -205,
    "payOutAmount": -97550
    },
]*/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.ResponsePaymentCommon;
import sw.sustainable.springlabs.fpay.infrastructure.out.pg.toss.response.payment.method.Card;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class ResponsePaymentSettlements {
    private String orderId;
    private String paymentKey;
    private String method;
    @JsonProperty("amount")
    private int totalAmount;
    private Card card;
    private int payOutAmount; // 지급 금액입니다. 결제 금액 amount에서 수수료인 fee를 제외한 금액입니다.
    private String soldDate;
    private String paidOutDate;


}
