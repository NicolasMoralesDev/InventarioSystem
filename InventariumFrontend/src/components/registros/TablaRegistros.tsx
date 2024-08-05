import React from "react";
import { Button, Table } from "antd";
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { EditOutlined } from "@ant-design/icons";
import dayjs from "dayjs";
import Menu from "../menu/Menu"

const TablaRegistros = (props) => {

  const { setIngresoEdit, dataSourse, setVisibleEdit, isEgreso } = props

  /* const [ingresosSeleccionados, setIngresosSeleccionados] = useState([])
  const onSelectIngresos = (ingresosSelected) => {
    setIngresosSeleccionados(ingresosSelected)
  } */

  const onEdit = (income) => {
    setIngresoEdit(income)
    setVisibleEdit(true)
  }

  const columns = [
    !isEgreso ? { 
      title: 'Fecha y Hora de ingreso',
      dataIndex: 'fechaIngreso',
      width: "13%",
      key: 'fechaIngreso',
      render: (fechaIngreso) => <p>{ dayjs(fechaIngreso).format('DD/MM/YYYY HH:mm') }</p>
    }
    :
    { 
      title: 'Fecha y Hora de egreso',
      dataIndex: 'fechaEgreso',
      width: "13%",
      key: 'fechaEgreso',
      render: (fechaEgreso) => <p>{ dayjs(fechaEgreso).format('DD/MM/YYYY HH:mm') }</p>
    }
    ,
    !isEgreso ? { 
      title: 'Proveedor',
      dataIndex: 'provedor',
      width: "10%",
      key: 'provedor',
      render: (provedor) => provedor ? <p> { provedor } </p> : <p>-</p> 
    } : <></>
    ,
    {
      title: 'Usuario que registro',
      dataIndex: 'usuario',
      width: "15%",
      key: 'usuario',
    },
    {
      title: 'Codigo y producto registrado',
      dataIndex: 'productos',
      width: "15%",
      key: 'productos',
      render: (productos) => productos ? productos.map((producto) => <p className="text-justify" key={ producto.codigo }>{ producto.codigo } - { producto.nombre }</p>) : ""
    },
    {
      title: 'Observacion',
      dataIndex: 'observacion',
      width: "13%",
      key: 'observacion',
    },
    !isEgreso ?
    { 
      title: 'Acciones',
      width: "10%",
      key: 'acciones',
      render: (ingreso) => <>
          <Button title="Editar Registro"  onClick={ () => onEdit(ingreso) } className="bg-cyan-950 btn-cyan-custom text-white">Editar <EditOutlined/></Button>
      </>
    } : <></>,
  ];

  return (
    <div className="p-5 bg-slate-200" style={{
      marginBottom: "5%",
    }}>  
     <Menu/>
      <Table
        scroll="small"
        className="overflow-x-scroll"
        rowKey={ (ingreso) => ingreso.id }
        dataSource={ dataSourse }
        columns={ columns }
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