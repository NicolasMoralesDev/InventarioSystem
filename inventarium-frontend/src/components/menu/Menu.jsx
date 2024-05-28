import { FullscreenExitOutlined, FullscreenOutlined, PieChartOutlined, ReconciliationOutlined, UsergroupAddOutlined } from '@ant-design/icons';
import { Tabs } from 'antd'
import { useNavigate } from 'react-router-dom';

const Menu = () => {

    const navigate = useNavigate()
      const items = [
        { key: 'productos', label: "Productos", children: 'Registro de Productos', icon: <PieChartOutlined/>  },
        { key: 'ingresos', label: "Ingresos" , children: 'Registro de Ingresos', icon: <FullscreenExitOutlined/> },
        { key: 'ingresosAlta', label: "Alta ingresos" , children: 'Alta de Ingresos', icon: <FullscreenExitOutlined/> },
        { key: 'egresos', label: "Egresos" , children: 'Registro de Egresos', icon: <FullscreenOutlined/> },
        { key: 'categorias', label: "Configurar ABM" , children: 'Gestion de Categorias', icon: <ReconciliationOutlined/>},
        { key: 'usuarios', label: "Gestion de usuarios" , children: 'Gestion de Usuarios', icon: <UsergroupAddOutlined/> },
      ];

  return (
    <>
    <Tabs
      activeKey={ location.pathname.slice(1) }
      items={ items }
      onChange={ (page) => {navigate(`/${page}`)} }
      indicator={ { size: (origin) => origin + 20, align: "center" } }
    />
  </>
  )
}

export default Menu