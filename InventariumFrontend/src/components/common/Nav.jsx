import { Button } from "antd"
import logo from "../../assets/Inventarium-SystemP.png"
import { LogoutOutlined } from "@ant-design/icons"
import { onLogOut } from "../../Hooks/util/auth.hook"

const Nav = () => {
  return (
    <section className="p-5 flex justify-between items-center">
      <nav className="h-1/4" style={{ margin: "auto" }}>
        <img src={ logo } alt="logo" width="290rem" />
      </nav>
        <Button icon={ <LogoutOutlined/> } type="text" onClick={ () => onLogOut() }>Salir</Button>
    </section>
  )
}

export default Nav