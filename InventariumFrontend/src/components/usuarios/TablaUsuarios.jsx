import { Button, Table } from "antd";
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { EditOutlined } from "@ant-design/icons";
import Menu from "../menu/Menu"

const TablaUsuarios = (props) => {

  const { setIngresoEdit, dataSourse, setVisibleEdit } = props

  const onEdit = (income) => {
    setIngresoEdit(income)
    setVisibleEdit(true)
  }

  const columns = [
    {
      title: 'Usuario',
      dataIndex: 'username',
      width: "13%",
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
      <Table
        scroll="small"
        className="overflow-x-scroll"
        rowKey={ (usuario) => usuario.id }
        dataSource={ dataSourse }
        columns={ columns }
        pagination={ defaultPagination(dataSourse, 10) }
        locale={ {
          emptyText: "No se encontraron usuarios registrados"
        } }
      />
    </div>
  )
}

export default TablaUsuarios