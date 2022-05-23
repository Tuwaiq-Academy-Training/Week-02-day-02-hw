package com.example.springday2ex1.model;

public class CustomerApi {



        private String message;

        public CustomerApi(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}
