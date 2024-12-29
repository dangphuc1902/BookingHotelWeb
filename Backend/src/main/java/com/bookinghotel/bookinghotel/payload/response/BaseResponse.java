package com.bookinghotel.bookinghotel.payload.response;

public class BaseResponse {
        private int status = 220;
        private String message = "";
        private Object data;

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public Object getData() {
                return data;
        }

        public void setData(Object data) {
                this.data = data;
        }
}
