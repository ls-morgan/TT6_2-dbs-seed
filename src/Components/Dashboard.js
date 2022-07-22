import React from 'react';

import Button from './UI elements/Button';
import Title from './UI elements/Title';
import { Link } from 'react-router-dom';
import ExchangeTable from './ExchangeTable';



import '../css/Dashboard.css';
const Dashboard = ({ name }) => {
    return (
      <div>
        <h2>This is the dashboard page</h2>
        <Title title="Welcome" user={name} />
        <div className='exchangeTable'>
          <ExchangeTable />
        </div>
        <div className="item-container">

          <Link to="/view-wallets">
            <Button text="View Wallets" />
          </Link>
          <Link to="/exchange-currency">
            <Button text="Exchange Currency" />
          </Link>
          <Link to="/wallet-config">
            <Button text="Add or Remove Wallets" />
          </Link>
        </div>
      </div>
    );
}

export default Dashboard;