package sw.sustainable.springlabs.fpay.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.domain.order.OrderStatus;
import sw.sustainable.springlabs.fpay.representation.request.order.CancelOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * PurchaseOrder 객체
 * define: 주문 도메인의 Aggregate
 * description: 주문 업무의 모든 업무 규칙 기능(비즈니스 로직) 제공하는 클래스
 */
public class OrderTests {

    /**
     * 신규 상품 주문(Purchase Order) 관련 단위 테스트
     * - 상품 주문은 최소 1개 이상 주문해야 한다.
     * [TEST CASE#1] 1개 일 때, return false;
     * [TEST CASE#2] n개 일 때, return false;
     * [Exception] 0개 일 때, return true;
     */
    @Test
    public void verifyHaveAtLeastOneItem_False_ListSizeBiggerThanOne() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(
            new Orderer("유진호", "010-1234-1234"),
            List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500),
                new PurchaseOrderItem(2, UUID.randomUUID(), "속이 편한 우유", 3800, 1, 3800)
            )
        );

        Order order = newOrder.toEntity();
        assertFalse(order.verifyHaveAtLeastOneItem());
    }

    @Test
    public void verifyHaveAtLeastOneItem_False_ListSizeZeroOrLess() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(
            new Orderer("유진호", "010-1234-1234"),
            Collections.emptyList()
        );

        Order order = newOrder.toEntity();
        assertTrue(order.verifyHaveAtLeastOneItem());
    }


    /**
     * 신규 상품 주문(Purchase Order) 관련 단위 테스트
     * - 상품 주문 시, product_id는 중복될 수 없다.
     * [TEST CASE#1] 주문하는 상품의 모든 product_id가 유니크한 경우, return true;
     * [TEST CASE#2] 주문하는 상품의 모든 product_id가 유니크 하지 않을 경우, return false;
     * [Exception] NULL 경우, 오류 처리
     */
    @Test
    public void verifyDuplicateOrderItemId_True_NotDuplicateProductId() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
            List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order order = newOrder.toEntity();

        assertTrue(order.verifyDuplicateOrderItemId());
    }

    @Test
    public void verifyDuplicateOrderItemId_ThrowException_DuplicateProductId() throws Exception {
        UUID productId = UUID.randomUUID();
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
            List.of(new PurchaseOrderItem(1, productId, "농심 짜파게티 4봉", 4500, 1, 4500),
                new PurchaseOrderItem(1, productId, "농심 짜파게티 4봉", 4500, 1, 4500)
            ));

        Order order = newOrder.toEntity();
        Assertions.assertThrows(IllegalArgumentException.class, order::verifyDuplicateOrderItemId);
    }


    /**
     * 결제 완료된 주문(Purchase Order)건에 대한 단위 테스트
     * - "구매 완료" 상태가 아닌 주문 건에 대해서만 취소가 가능하다.
     * [TEST CASE#1] "구매 완료" 상태가 아닌 경우, return true;
     * [TEST CASE#2] "구매 완료" 상태인 경우, return false;
     */
    @DisplayName("[TEST CASE#1] \"구매 완료\" 상태가 아닌 경우, return true;")
    @Test
    public void isNotOrderStatusPurchaseDecision_true_OrderStatusIsNotPurchaseDecision() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
            List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));

        Order order = newOrder.toEntity();
        assertTrue(order.isNotOrderStatusPurchaseDecision());
    }

    @DisplayName("[TEST CASE#1] \"구매 완료\" 상태가 아닌 경우, return true;")
    @Test
    public void isNotOrderStatusPurchaseDecision_true_OrderStatusIsPurchaseDecision() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
            List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));

        Order order = newOrder.toEntity();
        order.setStatus(OrderStatus.PURCHASE_DECISION);
        assertFalse(order.isNotOrderStatusPurchaseDecision());
    }

    /**
     * 주문 취소 단위 테스트
     * - 상품 정보(itemIdx)가 있는 경우, 부분 취소; 그렇지 않은 전체 취소;
     * [TEST CASE#1] "상품 상세 정보"가 Not Empty 경우, return true;
     * [TEST CASE#2] "상품 상세 정보"가 Empty 경우, return false;
     */
    @DisplayName("[TEST CASE#1] \"상품 상세 정보\"가 Not Empty 경우, return true;")
    @Test
    public void hasItemIdx_true_ItemIdIsNotEmpty() throws Exception {
        UUID orderId = UUID.randomUUID();
        CancelOrder cancelOrder = new CancelOrder(orderId, new int[]{1}, "Cancel Reason", "tgen_20240605132741Jtkz1", 3400);
        assertTrue(cancelOrder.hasItemIdx());
    }

    @DisplayName("[TEST CASE#2] \"상품 상세 정보\"가 Empty 경우, return false;")
    @Test
    public void hasItemIdx_false_ItemIdIsNotEmpty() throws Exception {
        UUID orderId = UUID.randomUUID();
        CancelOrder cancelOrder = new CancelOrder(orderId, new int[]{}, "Cancel Reason", "tgen_20240605132741Jtkz1", 3400);
        assertFalse(cancelOrder.hasItemIdx());
    }
}
