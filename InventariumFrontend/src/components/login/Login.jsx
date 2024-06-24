import React from 'react'
import LoginForm from './LoginForm'
import { loginUsuarios } from '../../Hooks/fetch/Auth.hook'
import { onAuth } from '../../Hooks/util/auth.hook'
import { useNavigate } from 'react-router-dom'

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
    <div>
      <LoginForm
        onLogin={ onLogin }
      />  
    </div>
  )
}

export default Login