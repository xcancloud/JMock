package cloud.xcan.comp.jmock.core.support.utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SnowIdUtils {

  // 机器ID所占的位数
  private static final long WORKER_ID_BITS = 5L;
  // 数据中心ID所占的位数
  private static final long DATACENTER_ID_BITS = 5L;
  // 序列所占的位数
  private static final long SEQUENCE_BITS = 12L;

  // 机器ID最大值
  private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
  // 数据中心ID最大值
  private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

  // 机器ID和数据中心ID
  private final long workerId;
  private final long datacenterId;

  // 毫秒时间戳
  private long lastTimestamp = -1L;
  // 序列
  private long sequence = 0L;

  // 时间戳偏移量
  private static final long TIMESTAMP_LEFT_SHIFT =
      SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
  // 数据中心ID左移位
  private static final long DATACENTER_ID_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
  // 机器ID左移位
  private static final long WORKER_ID_LEFT_SHIFT = SEQUENCE_BITS;

  private final Lock lock = new ReentrantLock();

  public SnowIdUtils(long workerId, long datacenterId) {
    if (workerId > MAX_WORKER_ID || workerId < 0) {
      throw new IllegalArgumentException(
          "Worker ID can't be greater than " + MAX_WORKER_ID + " or less than 0");
    }
    if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
      throw new IllegalArgumentException(
          "Datacenter ID can't be greater than " + MAX_DATACENTER_ID + " or less than 0");
    }
    this.workerId = workerId;
    this.datacenterId = datacenterId;
  }

  public long nextId() {
    lock.lock(); // 获取锁
    try {
      long timestamp = System.currentTimeMillis();

      // 如果当前时间小于上一次生成ID的时间，说明系统时钟回退过
      if (timestamp < lastTimestamp) {
        throw new RuntimeException(
            "Clock is moving backwards. Rejecting requests until " + lastTimestamp);
      }

      // 如果是同一时间生成的，则序列加1
      if (lastTimestamp == timestamp) {
        sequence = (sequence + 1) & ~(-1L << SEQUENCE_BITS);
        // 如果序列溢出，则等待下一个毫秒
        if (sequence == 0) {
          timestamp = waitNextMillis(lastTimestamp);
        }
      } else {
        sequence = 0L; // 时间戳改变，序列重置
      }

      lastTimestamp = timestamp;

      // 移位并通过或运算拼到一起
      return ((timestamp << TIMESTAMP_LEFT_SHIFT) | (datacenterId << DATACENTER_ID_LEFT_SHIFT) | (
          workerId << WORKER_ID_LEFT_SHIFT) | sequence);
    } finally {
      lock.unlock(); // 释放锁
    }
  }

  private long waitNextMillis(long lastTimestamp) {
    long timestamp;
    do {
      timestamp = System.currentTimeMillis();
    } while (timestamp <= lastTimestamp);
    return timestamp;
  }
}
