import React from 'react';
import Button from './UI elements/Button';
import Title from './UI elements/Title';

import '../css/Dashboard.css';
const Dashboard = () => {
    return (
        <div> 
            <h2>This is the dashboard page</h2>
                <Title title="Welcome" user="Username"/>
                <div className="item-container">
                
                <Button text="View Wallets" />
                <Button text="Exchange Currency" />
                <Button text="Add or remove Wallets" />
            </div>
        </div>
     );
}

export default Dashboard;