import { useState } from "react";
import { Button, Table, Tooltip } from "antd";
import { defaultPagination } from "../../Hooks/util/DefaultPagination";


const TablaIngresos = (props) => {
  
// eslint-disable-next-line react/prop-types
  const { dataSourse, setVisibleEdit } = props
  const [ingresosSeleccionados, setIngresosSeleccionados] = useState([])

  const onSelectIngresos = (ingresosSelected) => {
    console.log(ingresosSelected);
    setIngresosSeleccionados(ingresosSelected)
  }

  const onEdit = () => {
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
      dataIndex: '',
      width: "10%",
      key: 'generarInforme',
      render: () => <>
        <Button title="Editar" className="bg-green-600 text-white">Pdf</Button>
        <Button title="Editar" className="bg-slate-800 text-white">Excel</Button>
      </>
    },
    {
      title: 'Acciones',
      dataIndex: '',
      width: "10%",
      key: 'Acciones',
      render: () => 
      <>
        <Button title="Editar" onClick={()=> onEdit()} className="bg-green-600 text-white">Editar</Button>
        <Button title="Editar" className="bg-slate-800 text-white">Ver</Button>
      </>
    },
  ];

  return (
    <>
    <Tooltip align={"center"} title={"Borrado Multiple"}>
      <Button>Borrado Multiple</Button>
    </Tooltip>
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
    </>
  )
}

export default TablaIngresos