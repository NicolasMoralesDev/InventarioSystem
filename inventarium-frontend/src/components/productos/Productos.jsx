
import { useEffect, useState } from "react"
import { Helmet } from "react-helmet"
import { borradoMultipleProductos, obtenerProductos } from "../../Hooks/fetch/Productos.hook"
import TablaProductos from "./TablaProductos"
import { errorPop, loadingPop, successPop } from "../messages/alerts"

const Productos = () => {

  const [productos, setProductos] = useState([])
  const [statusBorrado, setStatusBorrado] = useState("")

  const onFetch = async () => {
    const { data } = await obtenerProductos()
    setProductos(data)
  }

  const onBorrado = async (productosIds) => {
   const request = await borradoMultipleProductos(productosIds)
   setStatusBorrado({error: request.error, msg: request.data.msg, status: request.status})
  }

  useEffect(() => {
    if (statusBorrado.error) {
      errorPop(statusBorrado.status)
    } else if (statusBorrado.status == 200) {
      successPop(statusBorrado.msg)
      onFetch()
    }
  }, [statusBorrado])

  useEffect(() => { onFetch() }, [])
  useEffect(()=> { loadingPop("Obteniendo Productos...")}, [ productos ])

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Listado Productos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      <TablaProductos
        dataSourse={ productos }
        onBorrado={ onBorrado }
      />
    </>
  )
}

export default Productos