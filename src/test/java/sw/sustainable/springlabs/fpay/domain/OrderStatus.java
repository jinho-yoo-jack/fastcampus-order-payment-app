package sw.sustainable.springlabs.fpay.domain;

public enum OrderStatus {
    FULLFILL, // 결제 완료
    CANCEL,   // 결제 취소
    SETTLE;   // 정산
}
