import { useState } from 'react';
import {
  FullscreenExitOutlined,
  FullscreenOutlined,
  PicCenterOutlined,
  PieChartOutlined,
  UsergroupAddOutlined,
} from '@ant-design/icons';
import { Layout, Menu, theme } from 'antd';
import { Content } from 'antd/lib/layout/layout';
import Nav from './components/static/Nav';
import Routing from './routes/Routes';
import Foter from './components/common/Foter';
const { Header, Sider } = Layout;
function getItem(label, key, icon, children) {
  return {
    key,
    icon,
    children,
    label,
  };
}
const items = [
  getItem('Productos', 'productos', <PieChartOutlined />),
  getItem('Categorias', 'categrias', <PicCenterOutlined />),
  getItem('Ingresos', 'ingresos', <FullscreenExitOutlined />),
  getItem('Egresos', 'egresos', <FullscreenOutlined />),
  getItem('Usuarios', 'usuarios', <UsergroupAddOutlined />),
];

const App = () => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  return (
    <>
      <Layout
        style={{
          minHeight: '100vh',
        }}
      >
         <Sider collapsible collapsed={ collapsed } onCollapse={(value) => setCollapsed(value)}>
          <div className="demo-logo-vertical p-5" />
          <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" onClick={(item) => location.replace(item.key)} items={ items } />
        </Sider> 
        <Layout
        >
          <Header
            style={{
              padding: 0,
              background: colorBgContainer,
              textAlign: 'center',
              height: "10%"
            }}
          >
            <Nav />
          </Header>
          <Content
            style={{
              margin: '20px',
            }}
          >
            <Routing />
          </Content>
        </Layout>
      </Layout>
      <Foter/>
    </>
  );
};
export default App;