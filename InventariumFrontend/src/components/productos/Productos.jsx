
import { useEffect, useState } from 'react'
import { Helmet } from "react-helmet"
import { obtenerCategorias } from "../../Hooks/fetch/Categorias.hook"
import { borradoMultipleProductos, crearProducto, editarProducto, genearReportePDFproductos, obtenerProductoByCodigo, obtenerProductos } from "../../Hooks/fetch/Productos.hook"
import TablaProductos from "./TablaProductos"
import { errorPop, loadingPop, successPop } from "../../Hooks/util/messages/alerts"
import useForm  from "antd/lib/form/hooks/useForm"
import FormBusqueda from "./formBusqueda/FormBusqueda"
import ProductosModal from "./ProductosModal"

const Productos = () => {

  const [form] = useForm()
  const [productos, setProductos] = useState([])
  const [categorias, setCategorias] = useState([])
  const [productoCode, setProductoCode] = useState([])
  const [productoEdit, setProductoEdit] = useState([])
  const [visibleAdd, setVisibleAdd] = useState(false)
  const [visibleEdit, setVisibleEdit] = useState(false)
  const [loading, setLoading] = useState(false)
  
  const [statusBorrado, setStatusBorrado] = useState("")
  const [statusAdd, setStatusAdd] = useState("")
  const [statusEdit, setStatusEdit] = useState("")

  const [borrando, setBorrando] = useState(false)
  const [add, setAdd] = useState(false)
  const [editando, setEditando] = useState(false)

  const onFetch = async () => {
     const resProdu = await obtenerProductos(setLoading)
     const resCate = await obtenerCategorias()
     setCategorias(resCate?.data)
     setProductos(resProdu?.data)
  }

  const onGetByCode = async (code) => {
     const request = await obtenerProductoByCodigo(code)
     setProductoCode([request?.data])
  }

  const onBorrado = async (productosIds) => {
     const request = await borradoMultipleProductos(productosIds)
     setLoading(true)
     setStatusBorrado({error: request.error, msg: request.data.msg, status: request.status})
  }

  const onGeneratePdf = async (productosIds) => {
     await genearReportePDFproductos(productosIds)
  }

  const onEdit = async (productoEdit) => {
     setEditando(true)
     const request = await editarProducto(productoEdit, setLoading)
     setStatusEdit(request?.data.msg)
  }

  const onAdd = async (productoAdd) => {
     const request = await crearProducto(productoAdd)
     setStatusAdd(request?.data.msg)
  }

  useEffect(() => {
    if (statusBorrado.error) {
      errorPop(statusBorrado.status)
    } else if (statusBorrado.status == 200) {
      successPop(statusBorrado.msg)
      onFetch()
    } 
  }, [ statusBorrado ])

  useEffect(() => { onFetch(), loadingPop("Obteniendo Productos...", "productosLoad") }, [])
/*   useEffect(() => { editarProducto ? loadingPop("Editando Producto...") : ""}, [editarProducto]) */
  useEffect(() => { 
    if (statusEdit) { 
      successPop(statusEdit, "statusEdit")
      onFetch()
      setVisibleEdit(false)
      setEditando(false)
    } }, [statusEdit])

  useEffect(() => { if (statusAdd) { successPop(statusAdd, "statusAdd"),  onFetch(), setVisibleAdd(false) } }, [statusAdd])
  useEffect(() => { if (statusBorrado) { loadingPop("Borrando Productos...", "borrando") } }, [ statusBorrado ])
  
    useEffect(() => { 
    if (statusBorrado || statusEdit || statusAdd) { 
      loadingPop("Obteniendo Productos...", "productosLoad") 
      setStatusAdd("")
      setBorrando("")
      setStatusEdit("")
  } }, [ productos ])

  useEffect(() => { if (add || editando || borrando) { setLoading(true)  } }, [add, borrando, editando])

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Listado productos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      <FormBusqueda
         onGetByCode={ onGetByCode }
      />
      {
        visibleEdit &&
        <ProductosModal 
         form={ form }
         categorias={ categorias }
         productoEdit={ productoEdit }
         visible={ visibleEdit }
         setVisible={ setVisibleEdit }
         onSend={ onEdit }
         edit={ true }
        />
      }
      {
        visibleAdd &&
        <ProductosModal 
         form={ form }
         categorias={ categorias }
         productoEdit={ productoEdit }
         visible={ visibleAdd }
         setVisible={ setVisibleAdd }
         onSend={ onAdd }
         edit={ false }
        />
      }
      <TablaProductos
        setVisibleAdd={ setVisibleAdd }
        setVisibleEdit={ setVisibleEdit }
        setProductoEdit={ setProductoEdit }
        categorias={ categorias }
        dataSourse={ Object.keys(productoCode).length != 0 ? productoCode :  productos }
        onBorrado={ onBorrado }
        onGeneratePdf={ onGeneratePdf }
        loading={ loading }
        isList={ true }
      />
    </>
  )
}

export default Productos