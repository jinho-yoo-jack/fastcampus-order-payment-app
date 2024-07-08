package sw.sustainable.springlabs.fpay.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sw.sustainable.springlabs.fpay.domain.order.Order;
import sw.sustainable.springlabs.fpay.representation.request.order.Orderer;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrder;
import sw.sustainable.springlabs.fpay.representation.request.order.PurchaseOrderItem;

import java.util.*;

/**
 * PurchaseOrder 객체
 * define: 주문 도메인의 Aggregate
 * description: 주문 업무의 모든 업무 규칙 기능(비즈니스 로직) 제공하는 클래스
 */
public class OrderTests {

    /**
     * 신규 상품 주문(Purchase Order) 관련 단위 테스트
     * - 상품 주문은 최소 1개 이상 주문해야 한다.
     * [TEST CASE#1] 1개 일 때, return true;
     * [TEST CASE#2] n개 일 때, return true;
     * [Exception] 0개 일 때, 오류 처리
     */
    @Test
    public void verifyHaveAtLeastOneItem_False_ListSizeBiggerThanOne() {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order order = newOrder.toEntity();

        Assertions.assertFalse(Order.verifyHaveAtLeastOneItem(order.getItems()));
    }

    @Test
    public void verifyHaveAtLeastOneItem_True_ListSizeZeroOrLess() {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                Collections.emptyList());
        Order order = newOrder.toEntity();

        Assertions.assertTrue(Order.verifyHaveAtLeastOneItem(order.getItems()));
    }

    /**
     * 신규 상품 주문(Purchase Order) 관련 단위 테스트
     * - 상품 주문 시, product_id는 중복될 수 없다.
     * [TEST CASE#1] 주문하는 상품의 모든 product_id가 유니크한 경우, return true;
     * [TEST CASE#2] 주문하는 상품의 모든 product_id가 유니크 하지 않을 경우, return false;
     * [Exception] NULL 경우, 오류 처리
     */
    @Test
    public void verifyDuplicateOrderItemId_True_NotDuplicateProductId() {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));
        Order order = newOrder.toEntity();

        Assertions.assertTrue(order.verifyDuplicateOrderItemId());
    }

    public void verifyDuplicateOrderItemId_ThrowException_DuplicateProductId() {
        UUID productId = UUID.randomUUID();
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, productId, "농심 짜파게티 4봉", 4500, 1, 4500),
                        new PurchaseOrderItem(1, productId, "농심 짜파게티 4봉", 4500, 1, 4500)
                ));

        Order order = newOrder.toEntity();
        Assertions.assertThrows(IllegalArgumentException.class, order::verifyDuplicateOrderItemId);
    }
}
