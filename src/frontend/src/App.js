import React, { useState } from 'react';
import './App.css';
import LoginPage from './pages/LoginPage';
import ForgotPassword from './pages/ForgotPassword';
import OtpPage from './pages/OtpPage';
import RegisterPage from './pages/RegisterPage';
import ResetPassword from './pages/ResetPassword';
import Delight from './pages/Delight';

function App() {
  const [route, setRoute] = useState({ name: 'login', context: {} });

  const onNavigate = (name, context = {}) => setRoute({ name, context });

  return (
    <div className="App">
      {route.name === 'login' && <LoginPage onNavigate={onNavigate} context={route.context} />}
      {route.name === 'forgot' && <ForgotPassword onNavigate={onNavigate} />}
      {route.name === 'otp' && <OtpPage onNavigate={onNavigate} context={route.context} />}
      {route.name === 'reset' && <ResetPassword onNavigate={onNavigate} context={route.context} />}
      {route.name === 'delight' && <Delight onNavigate={onNavigate} />}
      {route.name === 'register' && <RegisterPage onNavigate={onNavigate} />}
    </div>
  );
}

export default App;
