package com.koke.koke_backend.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderStatus {

    ORDERED("주문 완료", "1"), CANCEL_REQUEST("주문 취소 요청", "CR"),
    DELIVERY_READY("배송 준비중", "2"), DELIVERING("배송중", "3"),
    DELIVERED("배송 완료", "4"), COMPLETE("구매 확정", "5"),
    REFUNDING("환불중", "R"), REFUNDED("환불 완료", "RC");

    private String description;
    private String orderCode;

    public static Boolean isStepStatus(OrderStatus orderStatus) {
        return isNumeric(orderStatus.getOrderCode());
    }

}
