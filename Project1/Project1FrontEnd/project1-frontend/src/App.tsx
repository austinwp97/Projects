import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Login } from './Components/LoginRegister/Login';
import { Register } from './Components/LoginRegister/Register';
import 'bootstrap/dist/css/bootstrap.css';
import { ReimbursementContainer } from './Components/Reimbursement/ReimbursementContainer';
import { User } from './Components/User/User';
import { UserReimbursements } from './Components/User/UserReimbursements';
import { PendingReimbursements } from './Components/User/PendingReimbursements';
import { Container } from 'react-bootstrap';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>
          <Route path="/reimbursements" element={<ReimbursementContainer/>}/>
          <Route path="/users" element={<User/>}/>
          <Route path="/users/reimbursements" element={<UserReimbursements/>}/>
          <Route path="/users/reimbursements/status/pending" element={<PendingReimbursements/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
