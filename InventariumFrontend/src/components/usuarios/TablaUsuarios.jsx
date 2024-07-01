import { useState } from "react";
import { Button, Table, Tooltip } from "antd";
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { EditOutlined, FileExcelFilled, FilePdfFilled } from "@ant-design/icons";
import Menu from "../menu/Menu"

const TablaUsuarios = (props) => {

  const { setIngresoEdit, dataSourse, setVisibleEdit } = props

  const [ingresosSeleccionados, setIngresosSeleccionados] = useState([])
  const onSelectIngresos = (ingresosSelected) => {
    setIngresosSeleccionados(ingresosSelected)
  }

  const onEdit = (income) => {
    setIngresoEdit(income)
    setVisibleEdit(true)
  }

  const columns = [
    {
      title: 'Usuario',
      dataIndex: 'username',
      width: "13%",
      sorter: (a, b) => a.usuario + b.usuario,
      key: 'username',
    },
    {
      title: 'Nombre',
      dataIndex: 'nombreCompleto',
      width: "13%",
      key: 'nombreCompleto',
    },
    { 
      title: 'DNI',
      dataIndex: 'dni',
      width: "10%",
      key: 'dni',
    }
    ,
    {
      title: 'Roles',
      dataIndex: 'roles',
      width: "15%",
      key: 'roles',
      render: (rol) => rol.map(role => <p key={ role.id }>{ role.role }</p>) 
    },
    {
      title: 'Generar Informe',
      width: "13%",
      key: 'generarInforme',
      render: () => 
          <div className="p-0 text-center">
              <Tooltip title="Generar PDF"> <Button title="Generar PDF" className="bg-red-700 btn-rojo-custom text-white xl:w-1/2 sm:w-full"><FilePdfFilled />PDF</Button></Tooltip> 
              <Tooltip title="Generar EXCEL"> <Button title="Generar EXCEL" className="bg-green-700 btn-verde-custom text-white xl:w-1/2 sm:w-full"><FileExcelFilled />EXCEL</Button></Tooltip>
          </div>
    },
    {
      title: 'Acciones',
      width: "10%",
      key: 'acciones',
      render: (ingreso) => <>
          <Button title="Editar Registro"  onClick={ () => onEdit(ingreso) } className="bg-cyan-950 btn-cyan-custom text-white">Editar <EditOutlined/></Button>
      </>
  },
  ];

  return (
    <div className="p-5 bg-slate-200" style={{
      marginBottom: "5%",
    }}>  
     <Menu/>
      <div className="w-full flex justify-end">
      </div>
      <Table
        scroll="small"
        className="overflow-x-scroll"
        rowKey={ (usuario) => usuario.id }
        dataSource={ dataSourse }
        columns={ columns }
        pagination={ defaultPagination(dataSourse, 10) }
        rowSelection={ {
          selectedRowKeys: ingresosSeleccionados,
          onChange: onSelectIngresos,
        } }
        locale={ {
          emptyText: "No se encontraron usuarios registrados"
        } }
      />
    </div>
  )
}

export default TablaUsuarios