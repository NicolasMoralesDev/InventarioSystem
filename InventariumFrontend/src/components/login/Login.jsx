import React from 'react'
import LoginForm from './LoginForm'
import { loginUsuarios } from '../../Hooks/fetch/Auth.hook'
import { onAuth } from '../../Hooks/util/auth.hook'

const Login = () => {

    const onLogin = async (data) => {
      const request = await loginUsuarios(data)
      onAuth(request.data.jwt);
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