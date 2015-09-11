package base;

/**
 * 回调函数通用接口
 */
@FunctionalInterface
public interface Callback<T> {
    void run(T data);
}