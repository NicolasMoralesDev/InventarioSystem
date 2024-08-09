import React, { useEffect, useState } from 'react'
import TablaRegistros from '../../TablaRegistros'
import { Helmet } from 'react-helmet'
import { obtenerEgresos } from '../../../../Hooks/fetch/Expense.hook'
import { columnsEgresos } from '../../registros.constants.table'

const Egresos = () => {

  const [loading, setLoading] = useState(false)
  
  const [egresos, setEgresos] = useState([])

  const onFetch = async () => {
    setLoading(true)
    const request = await obtenerEgresos(setLoading)
    setEgresos(request?.data)
  }

  useEffect(() => { onFetch() }, [])
  
  return (
    <>
     <Helmet>
        <meta charSet="utf-8" />
        <title>Historico de egresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
     </Helmet>
    <TablaRegistros
     dataSourse={ egresos }
     loading={ loading }
     columnas={ columnsEgresos }
    />
    </>
  )
}

export default Egresos