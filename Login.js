import logo from './logo.svg';
import './App.css';
import { useState } from 'react';
import store from './store.js'

const Login = () => {

    const [ username, setUsername ] = useState("Type username...");
    const [ password, setPassword ] = useState("Type password...");  

    const [ showErrorMsg, setShowErrorMsg ] = useState(false);
    const [ rememberMe, setRememberMe ] = useState(false);

    const TryLogin = (event) => {
        event.preventDefault();

        if (username == "test" && password == "test")
        {
            // Input API code to check if username and password is correct
            store.dispatch({ type: 'Login Success'});
        }
        else
        {
            setShowErrorMsg(true);
        }

    }

    const EditUsername = (event) => {
        setUsername(event.target.value);
    }

    const EditPassword = (event) => {
        setPassword(event.target.value);
    }

    return (
      <div>
        <form onSubmit={ TryLogin }>
          <div><pre>Username   <input value={ username } onChange={ EditUsername }></input></pre></div>
          <div><pre>Password   <input value={ password } onChange={ EditPassword }></input></pre></div>
          <input type="checkbox" name="RememberMe" value={ rememberMe }></input>Remember me?
          { showErrorMsg ? <div>Incorrect username or password</div> : <div></div> }
          <p></p>
          <button className="Button" type="submit">Login</button>
        </form>
      </div>
    );
  }

  export default Login;