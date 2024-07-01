import React from 'react'
import { Button, Form, Input } from 'antd';
import useForm from 'antd/lib/form/hooks/useForm';

const LoginForm = ( { onLogin } ) => {

    const [form] = useForm();
  
    const handleSubmit = (values) => {
        // Enviar datos de inicio de sesión al servidor
         onLogin(values)
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };
  
    return (
    <div className='w-full pt-16 flex justify-center items-center'>
    <div className="sm:w-1/2 md:w-1/4 p-3 bg-slate-200 rounded-2xl">
      <h1 className="text-center p-3 font-sans text-black">Inicio de Sesi&#972;n</h1>
      <Form 
        form={ form } 
        name='basic'
        layout="vertical" 
        onFinish={ handleSubmit }
        onFinishFailed={ onFinishFailed }
        initialValues={ {
            remember: true,
        } }
        >
        <Form.Item label="Nombre de usuario"    
            rules={ [
                    {
                      required: true,
                      message: 'El nombre de usuario es obligatorio!',
                      transform: (value) => value.trim()
                    },
                  ] }
            name="username"
            >
          <Input placeholder="Ingrese su nombre de usuario" />
        </Form.Item>
        <Form.Item label="Contraseña"
            rules={ [
                    {
                      required: true,
                      message: 'La contraseña es obligatoria!',
                      transform: (value) => value.trim()
                    },
                  ] }
            name="password"
            >
          <Input.Password placeholder="Ingrese su contraseña" />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit">Iniciar sesi&#972;n</Button>
        </Form.Item>
      </Form>
      </div>
      </div>
    );
  };
  
  export default LoginForm;