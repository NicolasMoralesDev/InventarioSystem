import React, { useEffect, useState } from 'react'
import TablaUsuarios from './TablaUsuarios'
import { Helmet }  from 'react-helmet'
import { obtenerUsuarios } from '../../Hooks/fetch/Usuarios.hook'

const Usuarios =  () => {
    
    const [ usuarios, setUsuarios ] = useState([])

    const onFetch = async () => {
        const request : any = await obtenerUsuarios()
        setUsuarios(request.data)
    }

    useEffect(() => { onFetch() }, [])

  return (
    <div>
    <Helmet>
        <meta charSet="utf-8" />
        <title>Gestion de usuarios</title>
        <link rel="canonical" href="http://mysite.com/example" />
    </Helmet>
    <TablaUsuarios
       dataSourse={ usuarios }
    />
    </div>
  )
}

export default Usuarios