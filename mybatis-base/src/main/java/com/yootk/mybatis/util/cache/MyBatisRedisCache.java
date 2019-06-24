package com.yootk.mybatis.util.cache;

import com.yootk.mybatis.util.RedisConnectionUtil;
import io.lettuce.core.api.StatefulRedisConnection;
import org.apache.ibatis.cache.Cache;

import java.io.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyBatisRedisCache implements Cache {
    // 进行序列化对象管理的时候，一定要使用字节数组的形式完成所有的数据操作
    private StatefulRedisConnection<byte[], byte[]> connection = RedisConnectionUtil.getConnection();
    // 在缓存的时候有一个阻塞队列的配置，防止修改问题
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private String id; // 设置缓存数据保存的id名称，由Mybatis自行设计

    public MyBatisRedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        this.connection.sync().set(SerializableUtil.serialize(key), SerializableUtil.serialize(value));
    }

    @Override
    public Object getObject(Object key) {
        byte data[] = this.connection.sync().get(SerializableUtil.serialize(key));
        if (data == null) { // 现在没有数据存储
            return null;
        }
        return SerializableUtil.dserialize(data);
    }

    @Override
    public Object removeObject(Object key) {
        return this.connection.sync().del(SerializableUtil.serialize(key));
    }

    @Override
    public void clear() {
        this.connection.sync().flushdb();
    }

    @Override
    public int getSize() {
        return this.connection.sync().dbsize().intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.lock;
    }

    private static class SerializableUtil { // 实现一个序列化与反序列化的处理
        public static byte[] serialize(Object object) {
            byte result[] = null; // 保存最终的序列化处理结果
            ObjectOutputStream oos = null;
            ByteArrayOutputStream bos = null;
            try {
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(object); // 对象写入内存输出流
                result = bos.toByteArray(); // 获取二进制数据内容
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }

        public static Object dserialize(byte[] data) {
            Object result = null;
            ByteArrayInputStream bis = null;
            ObjectInputStream ois = null;
            try {
                bis = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bis);
                result = ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }
    }
}
