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
import { obtenerProvedores, registrarProvedor } from '../../../../Hooks/fetch/Provedores.hook';
import ProvedoresModal from '../../provedores/ProvedoresModal';
import ProvedoresAltaModal from '../../provedores/ProvedoresAltaModal';

const IngresosAlta = () => {

    const [form] = useForm()  

    // fetch inicial del backend
    const [productos, setProductos] = useState([])
    const [provedores, setProvedores] = useState([])
    const [categorias, setCategorias] = useState([])

    // estados para modales
    const [visibleEdit, setVisibleEdit] = useState(false)
    const [visibleProve, setVisibleProve] = useState(false) 
    const [visibleProveReg, setVisibleProveReg] = useState(false) 
    const [productoEditar, setProductoEditar] = useState([])

    // Estados para provedores
    const [provedorCargado, setProvedorCargado] = useState(false)

    // Estados para los productos.
    const [statusReg, setStatusReg] = useState("")
    const [productCargado, setProductCargado] = useState(false)
    const [productEditado, setProductEditado] = useState(false)
    const [productBorrado, setProductBorrado] = useState(false)

    const onFetch = async () => {
        const productosLocal = obtenerProductosStorage("productos")
        const requestProve = await obtenerProvedores()
        const requestCate = await obtenerCategorias()
        setProvedores(requestProve?.data)
        setCategorias(requestCate?.data)
        setProductos(productosLocal?.productos)
    }

    const onRegistrarProvedor = async (data) => {
        const request = await registrarProvedor(data)
        setProvedorCargado(true)
    }

    const onLoadStorage = (productos) => {
        cargarProductosStorage(productos, "productos")
        setProductCargado(true)
    }

    const onEditar = (producto) => {
        editarProductosStorage(producto, "productos")
        setProductEditado(true)
    }

    const onBorrado = (productos) => {
        borrarProductosStorage(productos, "productos")
        setProductBorrado(true)
    }

    const onRegistrar = async () => {
        const registro = obtenerProductosStorage("productos")
        const request = await registrarIngresos(registro)
        setStatusReg(request?.data.msg)
    }

    useEffect(() => { onFetch(), loadingPop("Cargando productos...", "cargandoProductos") }, [obtenerProductosStorage])

    useEffect(() => { if (productCargado) { successPop("Producto Cargado!", "productoAdd"),  onFetch(), setProductCargado(false) } }, [productCargado])
    useEffect(() => { if (productBorrado) { successPop("Producto Borrado!", "productoDelete"),  onFetch(), setProductBorrado(false) } }, [productBorrado])
    useEffect(() => { if (productEditado) { successPop("Producto Editado!", "productoEdit"),  onFetch(), setProductEditado(false) } }, [productEditado])
    useEffect(() => { if (provedorCargado) { successPop("Provedor cargado!", "provedorCargado"),  onFetch(), setProvedorCargado(false) } }, [provedorCargado])
    useEffect(() => { if (statusReg) { successPop(statusReg, "productoReg"), localStorage.removeItem("productos"), onFetch() } }, [statusReg])

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
         setVisibleProve={ setVisibleProve }
         setVisibleProveReg={ setVisibleProveReg }
      />
      {
        visibleProve &&
        <ProvedoresModal
          provedores={ provedores }
          setVisible={ setVisibleProve }
          visible={ visibleProve }
        />
      }
      { 
       visibleProveReg &&
        <ProvedoresAltaModal
          visible={ visibleProveReg }
          setVisible={ setVisibleProveReg }
          onSend={ onRegistrarProvedor }
        />
      }
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