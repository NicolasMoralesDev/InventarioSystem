import React, { useState } from 'react'
import { obtenerProductosStorage } from '../../../../Hooks/util/localStorage/Abm.registros'
import { obtenerCategorias } from '../../../../Hooks/fetch/Categorias.hook'
import useForm from "antd/lib/form/hooks/useForm"
import { Helmet } from 'react-helmet'
import TablaProductos from '../../../productos/TablaProductos'

const EgresosAlta = () => {

    const [productos, setProductos] = useState([])

    const [visibleEdit, setVisibleEdit] = useState(false)
    const [productoEditar, setProductoEditar] = useState([])

    const [statusReg, setStatusReg] = useState("")
    const [productCargado, setProductCargado] = useState(false)
    const [productEditado, setProductEditado] = useState(false)
    const [productBorrado, setProductBorrado] = useState(false)

    const onFetch = async () => {
        const productosLocal = obtenerProductosStorage()
        setProductos(productosLocal?.productos)
    }

    const onLoadStorage = (productos) => {
        cargarProductosStorage(productos)
        setProductCargado(true)
    }

  return (
    <>
     <Helmet>
        <meta charSet="utf-8" />
        <title>Alta de egresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
     </Helmet>
     <TablaProductos/>
    </>
  )
}

export default EgresosAlta