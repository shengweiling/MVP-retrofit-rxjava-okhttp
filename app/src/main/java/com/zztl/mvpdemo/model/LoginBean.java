package com.zztl.mvpdemo.model;

/**
 * @desc
 * @auth ${user}
 * @time 2018/6/4  12:01
 */
public class LoginBean extends BaseResponseBean <LoginBean.DataBean>{
    public static class DataBean {
        /**
         * nickname : 冯建荣
         * phone : 18873812961
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MzQyMTkxOTkzMDgsInBheWxvYWQiOiJ7XCJ1c2VySWRcIjpcIjIyMjIyMjIyMjIyMjIyMjIyXCJ9In0.7dp0LUAkOFZxWAEWD3l5o_r813hy90alIJzXhSdi5P0
         */

        private String nickname;
        private String phone;
        private String token;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
