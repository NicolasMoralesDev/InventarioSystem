import React from 'react'
import Menu from '../menu/Menu'
import { Helmet } from 'react-helmet'
import error404 from "../../assets/enchufe.webp"
import { Button } from 'antd'
import { useNavigate } from 'react-router-dom'

const Page404 = () => {

  const navigate = useNavigate()

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Inventarium System | Error 404</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
    <Menu/>
    <section className='text-center flex gap-3 justify-center items-center flex-col'>
      <img src={ error404 } alt="error404 icono" srcset="" width="15%"/>
      <h1 className='font-mono'>ERROR 404</h1>
      <h3 className='font-mono'>Pagina no encontrada</h3>
      <Button type='text' className='bg-cyan-950 text-white' onClick={ () => navigate("/") }>volver a inicio</Button>
    </section>
    </>
  )
}

export default Page404