package hello.proxy.app.v1;

public class OrderControllerV1Impl implements OrderControllerV1{

    private final OrderServiceV1 orderServce;

    public OrderControllerV1Impl(OrderServiceV1 orderServce) {
        this.orderServce = orderServce;
    }

    @Override
    public String request(String itemId) {
        orderServce.orderItem(itemId);
        return "ok1";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
