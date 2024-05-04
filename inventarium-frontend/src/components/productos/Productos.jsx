
import { useEffect, useState } from "react"
import { obtenerProductos } from "../../Hooks/fetch/Productos.hook"
import TablaProductos from "./TablaProductos"

const Productos = () => {

  const [productos, setProductos] = useState([])


  const onFetch = async () => {
    const request = await obtenerProductos()
    setProductos(request.data)
  } 

   useEffect( () => { onFetch() }, [])

  return (
    <>
    <TablaProductos 
    dataSourse={productos}
    
    />
    </>
  )
}

export default Productos