import { FullscreenExitOutlined, FullscreenOutlined, PieChartOutlined, UsergroupAddOutlined, SnippetsOutlined } from '@ant-design/icons';
import { Tabs } from 'antd'
import { useNavigate } from 'react-router-dom';
import { useAdmin } from '../../Hooks/util/auth.hook';

const Menu = () => {

    const navigate = useNavigate()
    const {isAdmin} = useAdmin()

      const items = [
        { key: '', label: "Productos", children: '', icon: <PieChartOutlined/>  },
        { key: 'ingresos', label: "Historial de ingresos" , children: '', icon: <FullscreenExitOutlined/> },
        { key: 'ingresosAlta', label: "Alta ingresos" , children: '', icon: <SnippetsOutlined /> },
        { key: 'egresos', label: "Historial de egresos" , children: '', icon: <FullscreenOutlined/> },
        { key: 'egresosAlta', label: "Alta egresos" , children: '', icon: <SnippetsOutlined /> },
      isAdmin &&  { key: 'usuarios', label: "Gestion de usuarios" , children: '', icon: <UsergroupAddOutlined/> }
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