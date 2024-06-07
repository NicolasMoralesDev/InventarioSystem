import { useState } from "react";
import { Button, Table, Tooltip } from "antd";
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { EditOutlined, FileExcelFilled, FilePdfFilled } from "@ant-design/icons";
import dayjs from "dayjs";
import Menu from "../Menu/Menu";

const TablaIngresos = (props) => {

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
      title: 'Fecha y Hora de Ingreso',
      dataIndex: 'fechaIngreso',
      width: "13%",
      sorter: (a, b) => a.fechaIngreso + b.fechaIngreso,
      key: 'fechaIngreso',
      render: (fechaIngreso) => <p>{ dayjs(fechaIngreso).format('DD/MM/YYYY HH:mm') }</p>
    },
     {
      title: 'Proveedor',
      dataIndex: 'provedor',
      width: "10%",
      key: 'provedor',
      render: (provedor) => provedor ? <p> { provedor } </p> : <p>-</p> 
    },
    {
      title: 'Usuario que Registro',
      dataIndex: 'observacio',
      width: "15%",
      key: 'descripciones',
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
      <div className="w-full flex justify-end tabla_botonera">
      </div>
      <Table
        scroll="small"
        className="overflow-x-scroll"
        rowKey={ (ingreso) => ingreso.id }
        dataSource={ dataSourse }
        columns={ columns }
        pagination={ defaultPagination(dataSourse, 10) }
        rowSelection={ {
          selectedRowKeys: ingresosSeleccionados,
          onChange: onSelectIngresos,
        } }
        locale={ {
          emptyText: "No se encontraron Registros"
        } }
      />
    </div>
  )
}

export default TablaIngresos