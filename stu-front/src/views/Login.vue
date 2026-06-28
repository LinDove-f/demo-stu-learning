<template>
  <div class="login-page">
    <div class="login-card">
      <h2>学生管理系统</h2>
      <p class="sub-title">用户登录</p>

      <div class="form-item">
        <label>用户名</label>
        <input v-model="username" placeholder="请输入用户名" />
      </div>

      <div class="form-item">
        <label>密码</label>
        <input v-model="password" type="password" placeholder="请输入密码" />
      </div>

      <button @click="login">登录</button>

      <pre class="result">{{ result }}</pre>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",

  data() {
    return {
      username: "admin",
      password: "admin",
      result: ""
    };
  },

  methods: {
    login() {
      axios.post("http://localhost:8081/login", {
        username: this.username,
        password: this.password
      }).then(res => {
        this.result = JSON.stringify(res.data, null, 2);

        if (res.data.code === 200 && res.data.data && res.data.data.token) {
          localStorage.setItem("token", res.data.data.token);
          alert("登录成功，token 已保存");
        }
      }).catch(err => {
        this.result = JSON.stringify(
          err.response ? err.response.data : err.message,
          null,
          2
        );
      });
    }
  }
};
</script>

<style scoped>
.login-page {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #eef4ff;
}

.login-card {
  width: 360px;
  padding: 32px;
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
}

.login-card h2 {
  text-align: center;
  margin-bottom: 8px;
  color: #1f2d3d;
}

.sub-title {
  text-align: center;
  color: #666;
  margin-bottom: 24px;
}

.form-item {
  margin-bottom: 16px;
}

.form-item label {
  display: block;
  margin-bottom: 6px;
  color: #333;
}

.form-item input {
  width: 100%;
  box-sizing: border-box;
  padding: 10px 12px;
  border: 1px solid #dcdfe6;
  border-radius: 6px;
  outline: none;
}

.form-item input:focus {
  border-color: #409eff;
}

button {
  width: 100%;
  padding: 11px;
  border: none;
  border-radius: 6px;
  background: #409eff;
  color: white;
  cursor: pointer;
  font-size: 15px;
}

button:hover {
  background: #337ecc;
}

.result {
  margin-top: 20px;
  padding: 12px;
  background: #f6f8fa;
  border-radius: 6px;
  font-size: 12px;
  max-height: 220px;
  overflow: auto;
}
</style>