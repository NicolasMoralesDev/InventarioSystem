import { FullscreenExitOutlined, FullscreenOutlined, PieChartOutlined, UsergroupAddOutlined } from '@ant-design/icons';
import { Tabs } from 'antd'
import { useState } from 'react';
import { useNavigate, useParams, useLocation } from 'react-router-dom';

const Menu = () => {

    const navigate = useNavigate()

    const [path, setPath] = useState("")

      const items = [
        { key: 'productos', label: <PieChartOutlined/> , children: 'Registro de Productos' },
        { key: 'ingresos', label: <FullscreenExitOutlined/> , children: 'Registro de Ingresos' },
        { key: 'egresos', label: <FullscreenOutlined/> , children: 'Registro de Egresos' },
        { key: 'usuarios', label:  <UsergroupAddOutlined />, children: 'Gestion de Usuarios' },
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