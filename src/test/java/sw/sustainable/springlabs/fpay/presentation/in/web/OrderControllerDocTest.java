package sw.sustainable.springlabs.fpay.presentation.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import sw.sustainable.springlabs.fpay.application.port.in.CreateNewOrderUseCase;
import sw.sustainable.springlabs.fpay.domain.repository.OrderRepository;
import sw.sustainable.springlabs.fpay.presentation.request.Orderer;
import sw.sustainable.springlabs.fpay.presentation.request.PurchaseOrder;
import sw.sustainable.springlabs.fpay.presentation.request.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs // Use Default Options - http://localhost:8080
@ExtendWith(RestDocumentationExtension.class)
public class OrderControllerDocTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CreateNewOrderUseCase createNewOrderUseCase;

    @Test
    public void newOrderTest() throws Exception {
        PurchaseOrder newOrder = new PurchaseOrder(new Orderer("유진호", "010-1234-1234"),
                List.of(new PurchaseOrderItem(1, UUID.randomUUID(), "농심 짜파게티 4봉", 4500, 1, 4500)));

        String requestJson = objectMapper.writeValueAsString(newOrder);
        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/order/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andDo(document("new-order",
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("orderer.name").description("주문자명")
                                        .attributes((key("constraint").value("주문자명을 입력 해주세요."))),
                                PayloadDocumentation.fieldWithPath("orderer.phoneNumber").description("주문자 휴대전화 번호")
                                        .attributes((key("constraint").value("주문자명을 입력 해주세요."))),
                                PayloadDocumentation.fieldWithPath("newlyOrderedItem.itemIdx").description("")
                                        .attributes((key("constraint").value("상세 주문 번호"))),
                                PayloadDocumentation.fieldWithPath("newlyOrderedItem.productId").description("")
                                        .attributes((key("constraint").value("상품 ID"))),
                                PayloadDocumentation.fieldWithPath("newlyOrderedItem.productName").description("")
                                        .attributes((key("constraint").value("상품명"))),
                                PayloadDocumentation.fieldWithPath("newlyOrderedItem.price").description("")
                                        .attributes((key("constraint").value("상품 단일 가격"))),
                                PayloadDocumentation.fieldWithPath("newlyOrderedItem.quantity").description("")
                                        .attributes((key("constraint").value("주문 수량"))),
                                PayloadDocumentation.fieldWithPath("newlyOrderedItem.amounts").description("")
                                        .attributes((key("constraint").value("주문 상품 총 가격(상품 단일 가격 * 주문 수량)")))
                        ))
                );
    }

}
