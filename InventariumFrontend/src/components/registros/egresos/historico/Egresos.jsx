import React from 'react'
import TablaRegistros from '../../TablaRegistros'
import { Helmet } from 'react-helmet'

const Egresos = () => {
  return (
    <>
     <Helmet>
        <meta charSet="utf-8" />
        <title>Historico de egresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
     </Helmet>
    <TablaRegistros/>
    </>
  )
}

export default Egresos