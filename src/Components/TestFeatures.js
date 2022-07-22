import React from 'react';
import Button from './UI elements/Button';
import axios from 'axios';
import ExchangeTable from './ExchangeTable';

const TestFeatures = () => {
    const headers = {
        'Content-Type': 'application/json',
        "Access-Control-Allow-Origin": "*"
    };

    const handleLogin = (e) => {
        e.preventDefault();

        const loginPayLoad = {
            "userName": "User",
            "password":"password123",
            "name": "Morgan"
        };
        
        axios.post("https://fa7b-116-15-113-158.ap.ngrok.io/api/v1/users",loginPayLoad,
        {headers}
        )
        .then(response => {
            console.log(response);
        })
        .catch(err => console.log(err));
    }

    return ( 
        <div>
            <h2>THIS IS A TEST COMPONENT TO TEST INTEGRATION WITH BACKEND</h2>

            <div>
                <p>Login button</p>
                <Button text="login" onClick={handleLogin}/>
            </div>

            <div className='container'>
            <ExchangeTable />
            </div>

        </div>
     );
}

export default TestFeatures;