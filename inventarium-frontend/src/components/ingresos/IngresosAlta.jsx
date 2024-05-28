import React, { useEffect, useState } from 'react'
import TablaProductos from "../productos/TablaProductos";
import Registrar from "../ingresos/Registrar"
import { cargarProductosStorage, obtenerProductosStorage } from "../../Hooks/util/localStorage/Abm.registros"
import { Helmet } from 'react-helmet'
import { obtenerCategorias } from '../../Hooks/fetch/Categorias.hook';
import { loadingPop } from '../../Hooks/util/messages/alerts';

const IngresosAlta = () => {

    const [productos, setProductos] = useState([])
    const [categorias, setCategorias] = useState([])

    const onFetch = async () => {
        const productosLocal = obtenerProductosStorage()
        const requestCate = await obtenerCategorias()
        setCategorias(requestCate.data)
        setProductos(productosLocal.productos)
    }

    const onLoadStorage = (productos) => {
        cargarProductosStorage(productos)
    }

    useEffect(() => { onFetch(), loadingPop("Cargando productos...") }, [cargarProductosStorage])

  return (
      <>
          <Helmet>
              <meta charSet="utf-8" />
              <title>Alta de Ingresos</title>
              <link rel="canonical" href="http://mysite.com/example" />
          </Helmet>
          <Registrar 
            onSend={ onLoadStorage }
            categorias={ categorias }
          />
          <TablaProductos 
            dataSourse={ productos }
            categorias={ categorias }
          />
      </>
  )
}

export default IngresosAlta