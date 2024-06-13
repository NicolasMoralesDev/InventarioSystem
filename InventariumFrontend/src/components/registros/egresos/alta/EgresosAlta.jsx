import React, { useEffect, useState } from 'react'
import { obtenerProductosStorage, cargarProductosStorage, borrarProductosStorage } from '../../../../Hooks/util/localStorage/Abm.registros'
import FormBusqueda from "../../../productos/formBusqueda/FormBusqueda"
import { Helmet } from 'react-helmet'
import TablaProductosEgresos from '../alta/TablaProductosEgresos'
import { obtenerProductoByCodigo } from "../../../../Hooks/fetch/Productos.hook"
import { obtenerCategorias } from '../../../../Hooks/fetch/Categorias.hook'

const EgresosAlta = () => {

    const [productos, setProductos] = useState([])
    const [categorias, setCategorias] = useState([])

    const [loading, setLoading] = useState(false)
    const [productoEditar, setProductoEditar] = useState([])

    const [statusReg, setStatusReg] = useState("")
    const [productCargado, setProductCargado] = useState(false)
    const [productEditado, setProductEditado] = useState(false)
    const [productBorrado, setProductBorrado] = useState(false)

    const onFetch = async () => {
        const productosLocal = obtenerProductosStorage()
        const requesyCate = await obtenerCategorias()
        setCategorias(requesyCate?.data)
        setProductos(productosLocal?.productos)
    }

    const onLoadStorage = (productos) => {
        cargarProductosStorage(productos)
        setProductCargado(true)
    }

    const onBorrado = (productos) => {
      borrarProductosStorage(productos)
      setProductBorrado(true)
  }

    const onGetByCode = async (code) => {
      setLoading(true)
      const request = await obtenerProductoByCodigo(code, setLoading)
      onLoadStorage(request.data)
   }

   useEffect(() => { onFetch() }, [productCargado, productBorrado])

  return (
    <>
     <Helmet>
        <meta charSet="utf-8" />
        <title>Alta de egresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
     </Helmet>
     <FormBusqueda
        onGetByCode={ onGetByCode }
     />
     <TablaProductosEgresos
        dataSourse={ productos }
        onBorrado={ onBorrado }
        categorias={ categorias }
        loading={ loading }
     />
    </>
  )
}

export default EgresosAlta