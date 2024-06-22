import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import RoutingLogin from './routes/RoutingLogin.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
    <RoutingLogin>
       <App/>  
    </RoutingLogin>
)
