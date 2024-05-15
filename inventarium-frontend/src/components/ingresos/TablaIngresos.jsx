import { useState } from "react";
import { Button, Table, Tooltip } from "antd";
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { FileExcelFilled, FilePdfFilled } from "@ant-design/icons";
import Menu from "../menu/Menu";

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
      title: 'Fecha de Ingreso',
      dataIndex: 'dateIncome',
      width: "13%",
      sorter: (a, b) => a.dateIncome - b.dateIncome,
      key: 'dateIncome',
    },
    {
      title: 'Provedores',
      dataIndex: 'suppliers',
      width: "10%",
      key: 'suppliers',
    },
    {
      title: 'Usuario que Registro',
      dataIndex: 'descripcion',
      width: "15%",
      key: 'descripcion',
    },
    {
      title: 'Observacion',
      dataIndex: 'description',
      width: "13%",
      key: 'description',
    },
    {
      title: 'Generar Informe',
      width: "14%",
      key: 'generarInforme',
      render: () =>
        <div className="p-0 text-center">
          <Button title="Generar PDF" className="bg-red-600 btn-rojo-custom text-white xl:w-1/2 sm:w-full "><FilePdfFilled />PDF</Button>
          <Button title="Generar EXCEL" className="bg-green-600 btn-verde-custom text-white xl:w-1/2 sm:w-full  "><FileExcelFilled />EXCEL</Button>
        </div>
    },
    {
      title: 'Acciones',
      dataIndex: '',
      width: "10%",
      key: 'Acciones',
      render: (income) =>
        <>
          <Button title="Editar Registro" onClick={() => onEdit(income)} className="bg-slate-800 text-white">Editar</Button>
        </>
    },
  ];

  return (
    <div className="p-5 bg-slate-200" style={{
      marginBottom: "5%",
/*    boxShadow: "-1px -1px 90px -16px ", */
    }}>  
     <Menu/>
      <div className="w-full flex justify-end tabla_botonera">
      </div>
      <Table
        scroll="small"
        className="overflow-x-scroll"
        rowKey={(ingreso) => ingreso.id}
        dataSource={dataSourse}
        columns={columns}
        pagination={defaultPagination(dataSourse, 10)}
        rowSelection={{
          selectedRowKeys: ingresosSeleccionados,
          onChange: onSelectIngresos,
        }}
        locale={{
          emptyText: "No se encontraron Registros"
        }}
      />
    </div>
  )
}

export default TablaIngresos