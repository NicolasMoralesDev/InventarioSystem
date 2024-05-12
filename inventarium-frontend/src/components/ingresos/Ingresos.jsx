import { useEffect, useState } from "react"
import { obtenerIngresos } from "../../Hooks/fetch/Ingresos.hook"
import TablaIngresos from "./TablaIngresos"
import { Helmet } from "react-helmet"
import ModalEdit from "./ModalEdit"
import { errorPop, loadingPop } from "../../Hooks/util/messages/alerts"
import useForm  from "antd/lib/form/hooks/useForm"


const Ingresos = () => {

  const [form] = useForm()

  const [ingresos, setIngresos] = useState([])
  const [ingresoEdit, setIngresoEdit] = useState([])
  const [visibleEdit, setVisibleEdit] = useState(false)

  const onFetch = async () => {
    const request = await obtenerIngresos()
    setIngresos(request.data)
  }

  useEffect(() => { onFetch() }, [ visibleEdit ])
  useEffect(() => { loadingPop("Obteniendo Registros...") }, [obtenerIngresos])

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Listado de Ingresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      {visibleEdit &&
        <ModalEdit
          form={ form }
          ingresoEdit={ ingresoEdit }
          visible={ visibleEdit }
          setVisible={ setVisibleEdit }
        />
      }
      <TablaIngresos
        setIngresoEdit={ setIngresoEdit }
        setVisibleEdit={ setVisibleEdit }
        dataSourse={ ingresos }
      />
    </>
  )
}

export default Ingresos