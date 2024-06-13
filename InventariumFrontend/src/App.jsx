import { Layout, theme } from 'antd';
import { Content } from 'antd/lib/layout/layout';
import Nav from './components/common/Nav';
import Routing from './routes/Routes';
import Foter from './components/common/Foter';

const { Header } = Layout;

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
              margin: '1%'
            }}
          >
            <Routing/>
          </Content>    
          <Foter/>
        </Layout>
      </Layout>
    </>
  );
};
export default App;