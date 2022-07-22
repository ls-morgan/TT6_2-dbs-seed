import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

import '../css/Login.css';
const Login = ({ updateAuthorization, updateName }) => {
  const [ user, setUser ] = useState("");
  const [ pw, setPw ] = useState("");
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
      password: "password123"
    };
    console.log(user,pw);
    axios
      .post(
        "https://fa7b-116-15-113-158.ap.ngrok.io/api/v1/users/login",
        { 
          userName: user,
          password: pw,
        },
        { headers }
      )
      .then((response) => {
        console.log(response);
        if (response.data.status === "SUCCESS") {
          updateAuthorization(true);
          updateName(response.data.name);
          history("/dashboard");
          return;
        } else window.alert("Wrong user/password!");
        setPw("");
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
        <form onSubmit={handleLogin}>
          <div className="login-form">
            <h3>User Login</h3>
            <br />
            <div className="login-details">
              <label>Username: </label>
              <input
                type="text"
                placeholder="Username"
                value={user}
                onInput={(e) => setUser(e.target.value)}
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