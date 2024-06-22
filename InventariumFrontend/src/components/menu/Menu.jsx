import { FullscreenExitOutlined, FullscreenOutlined, PieChartOutlined, ReconciliationOutlined, UsergroupAddOutlined, SnippetsOutlined } from '@ant-design/icons';
import { Tabs } from 'antd'
import { useNavigate } from 'react-router-dom';

const Menu = () => {

    const navigate = useNavigate()
      const items = [
        { key: '', label: "Productos", children: '', icon: <PieChartOutlined/>  },
        { key: 'ingresos', label: "Historial de ingresos" , children: '', icon: <FullscreenExitOutlined/> },
        { key: 'ingresosAlta', label: "Alta ingresos" , children: '', icon: <SnippetsOutlined /> },
        { key: 'egresos', label: "Historial de egresos" , children: '', icon: <FullscreenOutlined/> },
        { key: 'egresosAlta', label: "Alta egresos" , children: '', icon: <SnippetsOutlined /> },
        { key: 'usuarios', label: "Gestion de usuarios" , children: '', icon: <UsergroupAddOutlined/> },
      ]

  return (
    <>
    <Tabs
      activeKey={ location.pathname.slice(1) }
      items={ items }
      className='bg-slate-100	p-5'
      onChange={ (page) => {navigate(`/${page}`)} }
      indicator={ { size: (origin) => origin + 20, align: "center" } }
    />
  </>
  )
}

export default Menu