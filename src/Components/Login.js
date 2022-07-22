import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import '../css/Login.css';
const Login = ({ updateAuthorization }) => {
  const [pw, setPw] = useState("");
  const history = useNavigate();
  const headers = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  };
  // API call to backend for authentication
  const handleLogin = (e) => {
    e.preventDefault();

    const loginPayLoad = {
      userName: "User",
      password: "password123",
      name: "Morgan",
    };

    axios
      .post(
        "https://fa7b-116-15-113-158.ap.ngrok.io/api/v1/users",
        loginPayLoad,
        { headers }
      )
      .then((response) => {
        console.log(response);
      })
      .catch((err) => console.log(err));
  };

  //Hardcoded login details in front-end
  const onSubmit = (e) => {
    e.preventDefault();
    console.log(pw);
    if (pw === "password123") {
      console.log(pw);
      updateAuthorization(true);
      history("/dashboard");
      return;
    } else window.alert("Wrong user/password!");
    setPw("");
  };

  return (
    <section>
      <div className="login">
        <form onSubmit={onSubmit}>
          <div className="login-form">
            <h3>User Login</h3>
            <br />
            <div className="login-details">
              <label>Username: </label>
              <input
                type="text"
                placeholder="Username"
                // onInput={(e) => updateName(e.target.value)}
              />
              <label>Password: </label>
              <input
                type="password"
                placeholder="Password"
                value={pw}
                onInput={(e) => setPw(e.target.value)}
              />
            </div>
            <button type="submit">LOGIN</button>
          </div>
        </form>
      </div>
    </section>
  );
}

export default Login;