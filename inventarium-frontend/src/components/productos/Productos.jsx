
import { useEffect, useState } from "react"
import { Helmet } from "react-helmet"
import { borradoMultipleProductos, crearProducto, editarProducto, obtenerProductoByCodigo, obtenerProductos } from "../../Hooks/fetch/Productos.hook"
import TablaProductos from "./TablaProductos"
import { errorPop, loadingPop, successPop } from "../../Hooks/util/messages/alerts"
import { obtenerCategorias } from "../../Hooks/fetch/Categorias.hook"
import useForm  from "antd/lib/form/hooks/useForm"
import { obtenerSubCategorias } from "../../Hooks/fetch/SubCategorias.hook"
import FormBusqueda from "./formBusqueda/FormBusqueda"
import ProductosModal from "./ProductosModal"

const Productos = () => {

  const [form] = useForm()
  const [productos, setProductos] = useState([])
  const [productoCode, setProductoCode] = useState([])
  const [productoEdit, setProductoEdit] = useState([])
  const [visibleAdd, setVisibleAdd] = useState(false)
  const [visibleEdit, setVisibleEdit] = useState(false)
  const [categorias, setCategorias] = useState([])
  const [subCategorias, setSubCategorias] = useState([])
  const [statusBorrado, setStatusBorrado] = useState("")
  const [statusEdit, setStatusEdit] = useState("")

  const onFetch = async () => {
     const resProdu = await obtenerProductos()
     const resCate = await obtenerCategorias()
     const resSubCate = await obtenerSubCategorias()
     setSubCategorias(resSubCate.data)
     setCategorias(resCate.data)
     setProductos(resProdu.data)
  }

  const onGetByCode = async (code) => {
     const request = await obtenerProductoByCodigo(code)
     setProductoCode([request.data])
  }

  const onBorrado = async (productosIds) => {
     const request = await borradoMultipleProductos(productosIds)
     setStatusBorrado({error: request.error, msg: request.data.msg, status: request.status})
  }

  const onEdit = async (productoEdit) => {
     const request = await editarProducto(productoEdit)
     setStatusEdit(request.data.msg)
  }

  const onAdd = async (productoAdd) => {
     const request = await crearProducto(productoAdd)
     setStatusEdit(request.data.msg)
  }

  useEffect(() => {
    if (statusBorrado.error) {
      errorPop(statusBorrado.status)
    } else if (statusBorrado.status == 200) {
      successPop(statusBorrado.msg)
      onFetch()
    } 
  }, [ statusBorrado ])

  useEffect(() => { onFetch() }, [])
/*   useEffect(() => { editarProducto ? loadingPop("Editando Producto...") : ""}, [editarProducto]) */
  useEffect(() => { if (statusEdit) { successPop(statusEdit),  onFetch(), setVisibleEdit(false) } }, [statusEdit])
  useEffect(() => { statusBorrado ? loadingPop("Borrando Productos...")  : "" }, [ statusBorrado ])
  useEffect(() => { productos.length < 1 || statusBorrado || statusEdit ? loadingPop("Obteniendo Productos...") : "" }, [ productos ])

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Listado Productos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      <FormBusqueda
         form={ form }
         onGetByCode={ onGetByCode }
      />
      {
        visibleEdit &&
        <ProductosModal 
         form={ form }
         categorias={ categorias }
         subCategorias={ subCategorias }
         productoEdit={ productoEdit }
         visible={ visibleEdit }
         setVisible={ setVisibleEdit }
         onEdit={ onEdit }
         edit={ true }
        />
      }
      {
        visibleAdd &&
        <ProductosModal 
         form={ form }
         categorias={ categorias }
         subCategorias={ subCategorias }
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
      />
    </>
  )
}

export default Productos