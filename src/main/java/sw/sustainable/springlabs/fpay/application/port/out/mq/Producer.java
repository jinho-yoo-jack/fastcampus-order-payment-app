package sw.sustainable.springlabs.fpay.application.port.out.mq;

public interface Producer<T> {
    boolean send(String topic, T record);
}
