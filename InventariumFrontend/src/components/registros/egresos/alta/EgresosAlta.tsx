import React, { useEffect, useState } from 'react'
import { obtenerProductosStorage, borrarProductosStorage, cargarProductosEgresoStorage } from '../../../../Hooks/util/localStorage/Abm.registros'
import { Helmet } from 'react-helmet'
import TablaProductosEgresos from './TablaProductosEgresos'
import { obtenerProductoByCodigo } from "../../../../Hooks/fetch/Productos.hook"
import { obtenerCategorias } from '../../../../Hooks/fetch/Categorias.hook'
import FormRegistrar from '../../FormRegistrar'
import { registrarEgreso } from '../../../../Hooks/fetch/Expense.hook'
import { successPop } from '../../../../Hooks/util/messages/alerts'
import { Producto } from '../../../../classes/Producto'

const EgresosAlta = () => {

    const [productos, setProductos] = useState([])
    const [categorias, setCategorias] = useState([])

    const [loading, setLoading] = useState(false)

    const [statusReg, setStatusReg] = useState("")
    const [productCargado, setProductCargado] = useState(false)
    const [productBorrado, setProductBorrado] = useState(false)

    const onFetch = async () => {
      const productosLocal = obtenerProductosStorage("productosEgresos")
      const requesyCate = await obtenerCategorias()
      setCategorias(requesyCate?.data)
      setProductos(productosLocal?.productos)
    }

    const onLoadStorage = (productos) => {
      cargarProductosEgresoStorage(productos, "productosEgresos")
      setProductCargado(true)
    }

    const onRegister = async () => {
       const data = obtenerProductosStorage("productosEgresos")
       const request : any = await registrarEgreso(data)
       setStatusReg(request.data.msg)
    }

    const onBorrado = (productos : Producto) => {
      borrarProductosStorage(productos, "productosEgresos")
      setProductBorrado(true)
    }

    const onGetByCode = async (code) => {
      setLoading(true)
      const request = await obtenerProductoByCodigo(code)
      onLoadStorage(request?.data)
    }

    useEffect(() => { onFetch() }, [productCargado, productBorrado, onRegister])
    useEffect(() => { if(productos?.length > 0 ) { setLoading(false) } }, [productCargado, productBorrado])
    useEffect(() => { if(statusReg.length > 0) { successPop("Egreso registrado correctamente!", "egresoReg"), localStorage.removeItem("productosEgresos") } }, [statusReg])

  return (
    <>
     <Helmet>
        <meta charSet="utf-8" />
        <title>Alta de egresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
     </Helmet>
     <FormRegistrar
        onSend={onLoadStorage}
        categorias={categorias}
        onRegister={onRegister}
        isEgreso={true}
      />
     <TablaProductosEgresos
        onSend={ onRegister }
        dataSourse={ productos }
        onBorrado={ onBorrado }
        categorias={ categorias }
        loading={ loading }
     />
    </>
  )
}

export default EgresosAlta