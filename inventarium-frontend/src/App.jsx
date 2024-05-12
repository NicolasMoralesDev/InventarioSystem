import {
  FullscreenExitOutlined,
  FullscreenOutlined,
  PicCenterOutlined,
  PieChartOutlined,
  UsergroupAddOutlined,
} from '@ant-design/icons';
import { Layout, theme } from 'antd';
import { Content } from 'antd/lib/layout/layout';
import Nav from './components/static/Nav';
import Routing from './routes/Routes';
import Foter from './components/common/Foter';
const { Header } = Layout;
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
            <Nav/>
          </Header>
          <Content
            style={{
              margin: '25px',
              marginBottom: "5%",
            }}
          >
            <Routing />
          </Content>    
          <Foter/>
        </Layout>
      </Layout>
    </>
  );
};
export default App;