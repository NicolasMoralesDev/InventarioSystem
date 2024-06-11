import React, { useEffect, useState } from 'react';
import { obtenerCategorias } from '../../../../Hooks/fetch/Categorias.hook';
import { borrarProductosStorage, cargarProductosStorage, editarProductosStorage, obtenerProductosStorage } from "../../../../Hooks/util/localStorage/Abm.registros";
import { Helmet } from 'react-helmet';
import TablaProductos from "../../../productos/TablaProductos";
import FormRegistrar from '../../FormRegistrar';
import { loadingPop, successPop } from '../../../../Hooks/util/messages/alerts';
import useForm from "antd/lib/form/hooks/useForm"
import ProductosModal from '../../../productos/ProductosModal';
import { registrarIngresos } from '../../../../Hooks/fetch/Ingresos.hook';
import { obtenerProvedores } from '../../../../Hooks/fetch/Provedores.hook';

const IngresosAlta = () => {

    const [form] = useForm()  

    const [productos, setProductos] = useState([])
    const [provedores, setProvedores] = useState([])
    const [categorias, setCategorias] = useState([])

    const [visibleEdit, setVisibleEdit] = useState(false)
    const [productoEditar, setProductoEditar] = useState([])

    const [statusReg, setStatusReg] = useState("")
    const [productCargado, setProductCargado] = useState(false)
    const [productEditado, setProductEditado] = useState(false)
    const [productBorrado, setProductBorrado] = useState(false)

    const onFetch = async () => {
        const productosLocal = obtenerProductosStorage()
        const requestProve = await obtenerProvedores()
        const requestCate = await obtenerCategorias()
        setProvedores(requestProve.data)
        setCategorias(requestCate.data)
        setProductos(productosLocal?.productos)
    }

    const onLoadStorage = (productos) => {
        cargarProductosStorage(productos)
        setProductCargado(true)
    }

    const onEditar = (producto) => {
        editarProductosStorage(producto)
        setProductEditado(true)
    }

    const onBorrado = (productos) => {
        borrarProductosStorage(productos)
        setProductBorrado(true)
    }

    const onRegistrar = async () => {
        const registro = obtenerProductosStorage()
        const request = await registrarIngresos(registro)
        setStatusReg(request.data.msg)
    }

    useEffect(() => { onFetch(), loadingPop("Cargando productos...") }, [obtenerProductosStorage])

    useEffect(() => { if (productCargado) { successPop("Producto Cargado!"),  onFetch(), setProductCargado(false)} }, [productCargado])
    useEffect(() => { if (productBorrado) { successPop("Producto Borrado!"),  onFetch(), setProductBorrado(false)} }, [productBorrado])
    useEffect(() => { if (productEditado) { successPop("Producto Editado!"),  onFetch(), setProductEditado(false)} }, [productEditado])
    useEffect(() => { if (statusReg) { successPop(statusReg), localStorage.removeItem("productos"), onFetch() } }, [statusReg])

  return (
      <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Alta de ingresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      <FormRegistrar
        onSend={ onLoadStorage }
        onRegister={ onRegistrar }
        provedores={ provedores }
        categorias={ categorias }
      />
      {
        visibleEdit &&
        <ProductosModal
          form={ form }
          categorias={ categorias }
          productoEdit={ productoEditar }
          visible={ visibleEdit }
          setVisible={ setVisibleEdit }
          onSend={ onEditar }
          edit={ true }
          altaReg={ true }
        />
      }
      <TablaProductos
        onBorrado={ onBorrado }
        dataSourse={ productos }
        setVisibleEdit={ setVisibleEdit }
        setProductoEdit={ setProductoEditar }
      />
    </>
  )
}

export default IngresosAlta