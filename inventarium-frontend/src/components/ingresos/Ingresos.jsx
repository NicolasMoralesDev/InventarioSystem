import { useEffect, useState } from "react"
import { obtenerIngresos } from "../../Hooks/fetch/Ingresos.hook"
import TablaIngresos from "./TablaIngresos"
import { Helmet } from "react-helmet"
import ModalEdit from "./ModalEdit"
import { errorPop, loadingPop } from "../messages/alerts"
import { borradoMultipleProductos } from "../../Hooks/fetch/Productos.hook"

const Ingresos = () => {

  const [ingresos, setIngresos] = useState([])
  const [statusBorrado, setStatusBorrado] = useState("")
  const [visibleEdit, setVisibleEdit] = useState(false)

  const onFetch = async () => {
    const request = await obtenerIngresos()
    setIngresos(request.data)
  }

  const onBorrado = async (ingresosSeleccionados) => {
    const request = await borradoMultipleProductos(ingresosSeleccionados)
    setStatusBorrado({ msg: request.msg, status: request.status, error: request.error })
  }

  useEffect(() => {
    if (statusBorrado.error) {
      errorPop(statusBorrado.status)
    }
  }, [statusBorrado])

  useEffect(() => { onFetch() }, [visibleEdit])
  useEffect(() => { loadingPop("Obteniendo Registros...") }, [ingresos])
  useEffect(() => { loadingPop("Eliminando Registros...") }, [borradoMultipleProductos])

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Listado de Ingresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      {visibleEdit &&
        <ModalEdit
          visible={ visibleEdit }
          setVisible={ setVisibleEdit }
        />
      }
      <TablaIngresos
        setVisibleEdit={ setVisibleEdit }
        dataSourse={ ingresos }
        onDelete={ onBorrado }
      />
    </>
  )
}

export default Ingresos