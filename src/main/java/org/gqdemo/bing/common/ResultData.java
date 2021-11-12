package org.gqdemo.bing.common;

/**
 * @version v1.0
 * @ClassName ResultData
 * @Description TODO
 * @Author Q
 */
public class ResultData<T> {

    //定义返回状态码
    private int status;

    //定义返回消息
    private String message;

    //定义返回数据
    private T data;

    //定义当前时间戳
    private long timestamp;

    public ResultData(Builder<T> builder) {
        this.message = builder.message;
        this.data = builder.data;
    }

    public final static class Builder<T> {
        //定义返回状态码
        private int status;

        //定义返回消息
        private String message;

        //定义返回数据
        private T data;

        //定义当前时间戳
        private long timestamp;

        public Builder(int status) {
            this.status = status;
            this.timestamp = System.currentTimeMillis();
        }

        public Builder<T> message(String val) {
            this.message = val;
            return this;
        }

        public Builder<T> data(T val) {
            this.data = val;
            return this;
        }

        public ResultData<T> build() {
            return new ResultData<T>(this);
        }
    }

    public static <T> ResultData<T> ok() {
        return ResultData.ok(ReturnCode.RC100.getCode(), null);
    }


    public static <T> ResultData<T> ok(int status, T data) {
        return ResultData.ok(status, data, null);
    }

    public static <T> ResultData<T> ok(int status, T data, String message) {
        return new Builder<T>(status).data(data).message(message).build();
    }

    public static <T> ResultData<T> fail() {
        return ResultData.ok(ReturnCode.RC999.getCode(), null);
    }


    public static <T> ResultData<T> fail(int status, T data) {
        return ResultData.ok(status, data, null);
    }

    public static <T> ResultData<T> fail(int status, T data, String message) {
        return new Builder<T>(status).data(data).message(message).build();
    }


}
