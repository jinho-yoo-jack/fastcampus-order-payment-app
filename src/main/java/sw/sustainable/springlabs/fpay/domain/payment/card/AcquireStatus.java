package sw.sustainable.springlabs.fpay.domain.payment.card;


/**
 * acquireStatus string
 * 카드 결제의 매입 상태입니다. 아래와 같은 상태 값을 가질 수 있습니다.
 * - READY: 아직 매입 요청이 안 된 상태입니다.
 * - REQUESTED: 매입이 요청된 상태입니다.
 * - COMPLETED: 요청된 매입이 완료된 상태입니다.
 * - CANCEL_REQUESTED: 매입 취소가 요청된 상태입니다.
 * - CANCELED: 요청된 매입 취소가 완료된 상태입니다.
 */
public enum AcquireStatus {
    READY, REQUESTED, COMPLETED, CANCEL_REQUESTED, CANCELLED;
}
