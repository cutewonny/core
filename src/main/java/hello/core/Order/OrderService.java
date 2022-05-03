package hello.core.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);//클라이언트에서 주문을 생성함
}
