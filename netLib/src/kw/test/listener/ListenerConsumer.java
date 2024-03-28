package kw.test.listener;


import com.esotericsoftware.kryonet.Connection;

public interface ListenerConsumer<T> {

    void accept(Connection conncetion, T elem);
}
