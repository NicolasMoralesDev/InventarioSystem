import { useEffect, useState } from "react"
import { obtenerIngresos } from "../../Hooks/fetch/Ingresos.hook"
import TablaIngresos from "./TablaIngresos"
import { Helmet } from "react-helmet"
import ModalEdit from "./ModalEdit"

const Ingresos = () => {

  const [ingresos, setIngresos] = useState([])
  const [visibleEdit, setVisibleEdit] = useState(false)

  const onFetch = async () => {
    const request = await obtenerIngresos()
    setIngresos(request.data)
    console.log(visibleEdit)
  }

  useEffect(() => { onFetch() }, [visibleEdit])

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Listado de Ingresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      { visibleEdit &&
        <ModalEdit
          visible={ visibleEdit }
          setVisible={ setVisibleEdit }
        />
      }
      <TablaIngresos
        setVisibleEdit={ setVisibleEdit }
        dataSourse={ ingresos}
      />
    </>
  )
}

export default Ingresos