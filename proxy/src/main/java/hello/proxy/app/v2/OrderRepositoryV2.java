package hello.proxy.app.v2;

public class OrderRepositoryV2 {
    public void save(String itemId) {
        //저장로직
        if(itemId.equals("ex")){
            throw  new IllegalStateException("예오발생");
        }
        sleep(1000);
    }

    private void sleep(int i) {
        try{
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
