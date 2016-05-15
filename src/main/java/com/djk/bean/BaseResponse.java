package com.djk.bean;

/**
 * Created by dujinkai on 2016/5/15.
 * <p>
 * 响应基础类
 */
public final class BaseResponse<T> {

    /**
     * http消息 是否接收到返回
     */
    private final boolean ok;

    /**
     * 操作结果提示语: 可选
     * - 支持国际化
     * - 可直接展示给用户
     */
    private final String message;

    /**
     * 返回的信息  用户自定义
     */
    private final T data;

    public BaseResponse(boolean ok, String message, T data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    /**
     * 获得建造者
     *
     * @return 返回建造者
     */
    public static Builder builder() {
        return new Builder();
    }


    public static class Builder<T> {

        private boolean ok;

        private String message;

        private T data;

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Builder success() {
            this.ok = true;

            return Builder.this;
        }

        public Builder addMessage(String message) {
            this.message = message;
            return Builder.this;
        }

        public Builder addDate(T t) {
            this.data = t;
            return Builder.this;
        }

        public BaseResponse<T> build() {
            return new BaseResponse(this.ok, this.message, this.data);
        }

    }

}
