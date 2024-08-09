import dayjs from "dayjs";

export const columnsIngresos = [
    { 
      title: 'Fecha y Hora de ingreso',
      dataIndex: 'fechaIngreso',
      width: "13%",
      key: 'fechaIngreso',
      render: (fechaIngreso) =>  dayjs(fechaIngreso).format('DD/MM/YYYY HH:mm') 
    },
    { 
      title: 'Proveedor',
      dataIndex: 'provedor',
      width: "10%",
      key: 'provedor',
      render: (provedor) => provedor ? provedor : "-"  
    },
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
      render: (productos) =>  productos.map((producto) => `<p className="text-justify" key=${ producto.codigo }> ${ producto.codigo } - ${ producto.nombre }</p>`)
    },
    {
      title: 'Observacion',
      dataIndex: 'observacion',
      width: "13%",
      key: 'observacion',
    },
  ];

export const columnsEgresos = [
    { 
      title: 'Fecha y Hora de egreso',
      dataIndex: 'fechaEgreso',
      width: "13%",
      key: 'fechaEgreso',
      render: (fechaEgreso) =>  dayjs(fechaEgreso).format('DD/MM/YYYY HH:mm') 
    },
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
      render: (productos) => productos.map((producto) => `<p className="text-justify" key=${ producto.codigo }> ${ producto.codigo } - ${ producto.nombre }</p>`)
    },
    {
      title: 'Observacion',
      dataIndex: 'observacion',
      width: "13%",
      key: 'observacion',
    },
/*     { 
      title: 'Acciones',
      width: "10%",
      key: 'acciones',
      render: (ingreso) => `<Button title="Editar Registro"  ``onClick={ () => onEdit(ingreso) } className="bg-cyan-950 btn-cyan-custom text-white">Editar <EditOutlined/></Button>`
    } */
  ];