import React from "react";
import { Table } from "antd";
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { EditOutlined } from "@ant-design/icons";
import Menu from "../menu/Menu"

const TablaRegistros = (props) => {

  const { setIngresoEdit, dataSourse, setVisibleEdit, columnas } = props

  /* const [ingresosSeleccionados, setIngresosSeleccionados] = useState([])
  const onSelectIngresos = (ingresosSelected) => {
    setIngresosSeleccionados(ingresosSelected)
  } */

  const onEdit = (income) => {
    setIngresoEdit(income)
    setVisibleEdit(true)
  }

  return (
    <div className="p-5 bg-slate-200" style={{
      marginBottom: "5%",
    }}>  
     <Menu/>
      <Table
        className="overflow-x-scroll"
        rowKey={ (ingreso) => ingreso.id }
        dataSource={ dataSourse }
        columns={ columnas }
        pagination={ defaultPagination(dataSourse, 15) }
      /*   rowSelection={ {
          selectedRowKeys: ingresosSeleccionados,
          onChange: onSelectIngresos,
        } } */
        locale={ {
          emptyText: "No se encontraron registros"
        } }
      />
    </div>
  )
}

export default TablaRegistros