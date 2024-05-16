import { FullscreenExitOutlined, FullscreenOutlined, PieChartOutlined, UsergroupAddOutlined } from '@ant-design/icons';
import { Tabs } from 'antd'
import { useNavigate, useParams, useLocation } from 'react-router-dom';

const Menu = () => {

    const navigate = useNavigate()

      const items = [
        { key: 'productos', label: "Productos", children: 'Registro de Productos', icon: <PieChartOutlined/>  },
        { key: 'ingresos', label: "Ingresos" , children: 'Registro de Ingresos', icon: <FullscreenExitOutlined/> },
        { key: 'egresos', label: "Egresos" , children: 'Registro de Egresos', icon: <FullscreenOutlined/> },
        { key: 'usuarios', label: "Usuarios" , children: 'Gestion de Usuarios', icon: <UsergroupAddOutlined/> },
      ];

  return (
    <>
    <Tabs
      activeKey={ (page) => page.key }
      items={ items }
      onChange={ (page) => {navigate(`/${page}`), setPath(page)} }
      indicator={ { size: (origin) => origin - 20, align: "center" } }
    />
  </>
  )
}

export default Menu