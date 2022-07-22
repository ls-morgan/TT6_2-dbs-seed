import React from 'react';
import { Link } from 'react-router-dom';

import "../../css/Topbar.css";

const Topbar = ({ authorized }) => {
    
    return (
      <header className="">
        <div className="top-bar">
          <div className="home">
            <Link to="/dashboard">Home</Link>
          </div>

          <div className="login-btn">
            {authorized ? (
              <Link to="/dashboard">Logout</Link>
            ) : (
              <Link to="/login">Login</Link>
            )}
          </div>
        </div>
      </header>
    );
}

export default Topbar;