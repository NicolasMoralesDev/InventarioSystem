
import { useEffect, useState } from "react"
import { Helmet } from "react-helmet"
import { obtenerProductos } from "../../Hooks/fetch/Productos.hook"
import TablaProductos from "./TablaProductos"

const Productos = () => {

  const [productos, setProductos] = useState([])

  const onFetch = async () => {
    const request = await obtenerProductos()
    setProductos(request.data)
  }

  useEffect(() => { onFetch() }, [])

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Listado Productos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      <TablaProductos
        dataSourse={productos}
      />
    </>
  )
}

export default Productos