package com.yootk.mybatis.util;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class RedisConnectionUtil {
    public static final String REDIS_ADDRESS = "redis://hellolee@redis-server:6379/0";
    private static final int MAX_IDLE = 10 ; // 最大的维持连接数量
    private static final int MIN_IDLE = 1 ; // 最小维持的可用数量
    private static final int MAX_TOTAL = 1 ; // 最大的可用数量
    private static final boolean TEST_ON_BORROW = true ;
    private static GenericObjectPool<StatefulRedisConnection<byte[], byte[]>> pool ;
    private static final RedisURI REDIS_URI = RedisURI.create(REDIS_ADDRESS) ;
    private static final RedisClient REDIS_CLIENT = RedisClient.create(REDIS_URI) ; // 构建RedisClient实例
    private static final ThreadLocal<StatefulRedisConnection<byte[], byte[]>> REDIS_CONNECTION_THREAD_LOCAL = new ThreadLocal<>()  ;
    static {
        // 1、如果要进行连接池的操作，则肯定要进行一些连接池的基本配置
        GenericObjectPoolConfig config = new GenericObjectPoolConfig() ; // 配置对象
        config.setMaxIdle(MAX_IDLE); // 设置最大维持连接数量
        config.setMinIdle(MIN_IDLE); // 设置最小维持连接数量
        config.setMaxTotal(MAX_TOTAL); // 连接池总共的可用连接数量
        config.setTestOnBorrow(TEST_ON_BORROW); // 连接测试后返回
        // 2、连接池的创建需要依赖于连接的配置类实例
        pool = ConnectionPoolSupport.createGenericObjectPool(() -> REDIS_CLIENT.connect(new ByteArrayCodec()), config);
    }
    public static StatefulRedisConnection getConnection() {
        StatefulRedisConnection<byte[], byte[]> connection = REDIS_CONNECTION_THREAD_LOCAL.get() ;
        if (connection == null) {
            connection = build() ;
            REDIS_CONNECTION_THREAD_LOCAL.set(connection);
        }
        return connection ;
    }
    public static void close() {
        StatefulRedisConnection<byte[], byte[]> connection = REDIS_CONNECTION_THREAD_LOCAL.get() ;
        if (connection != null) {
            connection.close();
            REDIS_CONNECTION_THREAD_LOCAL.remove();
        }
    }
    private static StatefulRedisConnection build() {
        try {
            return pool.borrowObject() ;
        } catch (Exception e) {
            return null ;
        }
    }
}