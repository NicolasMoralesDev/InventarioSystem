import React, { useEffect, useState } from "react"
import { modificarIngresos, obtenerIngresos } from "../../../../Hooks/fetch/Ingresos.hook"
import { Helmet } from "react-helmet"
import ModalEdit from "./ModalEdit"
import TablaRegistros from "../../TablaRegistros"
import { loadingPop, successPop } from "../../../../Hooks/util/messages/alerts"
import useForm from "antd/lib/form/hooks/useForm"
import { columnsIngresos } from "../../registros.constants.table"

const Ingresos = () => {

  const [form] = useForm()
  const [ingresos, setIngresos] = useState([])
  const [ingresoEdit, setIngresoEdit] = useState([])
  const [visibleEdit, setVisibleEdit] = useState(false)

  const [statusEdit, setStatusEdit] = useState("")

  const onFetch = async () => {
    const request = await obtenerIngresos()
    setIngresos(request.data)
  }

  useEffect(() => { onFetch() }, [ visibleEdit ])
  useEffect(() => { loadingPop("Obteniendo Registros....") }, [obtenerIngresos])
  useEffect(() => { if (statusEdit) { successPop(statusEdit) } }, [statusEdit])

  const onEdit = async (ingreso) => {
    const request = await modificarIngresos(ingreso)
    setVisibleEdit(false)
    setStatusEdit(request.data.msg)
  }

  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Historico de ingresos</title>
        <link rel="canonical" href="http://mysite.com/example" />
      </Helmet>
      {visibleEdit &&
        <ModalEdit
          form={ form }
          ingresoEdit={ ingresoEdit }
          visible={ visibleEdit }
          setVisible={ setVisibleEdit }
          onSend={ onEdit }
        />
      }
      <TablaRegistros
        setIngresoEdit={ setIngresoEdit }
        setVisibleEdit={ setVisibleEdit }
        dataSourse={ ingresos }
        columnas={ columnsIngresos }
      />
    </>
  )
}

export default Ingresos