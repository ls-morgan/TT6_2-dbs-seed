import './index.css';
import { createSlice, createStore } from '@reduxjs/toolkit'

// The store used to keep track of the information within the application
const initialState = {
    LoginSuccess : false, 
    LoginDetails: {
      Username: "",
      Password: ""
    }
  };
  
  const appReducer = (state = initialState, action) => {

    switch (action.type)
    {
      case 'Login Success':
        return { ...initialState,
                 LoginSuccess : true };
    }
  
    return initialState;
  };
  
  const store = createStore(appReducer);

  export default store;

  