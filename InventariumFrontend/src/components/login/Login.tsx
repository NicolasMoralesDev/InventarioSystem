import React from 'react'
import LoginForm from './LoginForm'
import { loginUsuarios } from '../../Hooks/fetch/Auth.hook'
import { onAuth } from '../../Hooks/util/auth.hook'
import { useNavigate } from 'react-router-dom'
import "./estilos/login.css"
import video from "../../assets/video.mp4"
import { Helmet } from 'react-helmet'

const Login = () => {

    const navigate = useNavigate()

    const onLogin = async (data) => {
      const request = await loginUsuarios(data)
      onAuth(request?.data?.jwt);
      
      if (request?.data?.jwt) {
        navigate("/")
      }
    }

  return (    
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Inventarium System | Login</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
    <div className='login-fondo w overflow-hidden'>
      <video src={ video } loop={ true } autoPlay={ true } >
        Video no soportado.
      </video>
    </div>
    <div>
      <LoginForm
        onLogin={ onLogin }
      />  
    </div>
    </>
  )
}

export default Login