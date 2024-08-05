import React from 'react';
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import RoutingLogin from './routes/RoutingLogin.tsx'

const root = document.getElementById('root');
const container = root ? ReactDOM.createRoot(root) : null;

if (container) {
  container.render(
    <RoutingLogin>
      <App children={undefined} />
    </RoutingLogin>
  );
}

