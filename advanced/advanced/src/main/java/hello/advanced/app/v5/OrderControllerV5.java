package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
    private final OrderServiceV5 orderservice;

    private final TraceTemplate template;

    @Autowired
    public OrderControllerV5(OrderServiceV5 orderservice, LogTrace trace) {
        this.orderservice = orderservice;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId){
        return template.execute("OrderControllet.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderservice.orderItem(itemId);
                return "ok";
            }
        });
    }

}
