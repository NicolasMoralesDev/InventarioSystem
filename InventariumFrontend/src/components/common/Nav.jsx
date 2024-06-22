import { Button, Tooltip } from "antd"
import logo from "../../assets/Inventarium-SystemP.png"
import { LogoutOutlined } from "@ant-design/icons"
import { onLogOut } from "../../Hooks/util/auth.hook"

const Nav = () => {
  return (
    <section className="p-5 flex justify-between items-center xl:flex-row flex-col">
      <nav className="h-1/4" style={ { margin: "auto" } }>
        <img src={ logo } alt="logo" width="290rem" />
      </nav>
      <Tooltip title="cerrar sesi&#972;n">
        <Button icon={ <LogoutOutlined/> } className="h-2/3" type="text" onClick={ () => onLogOut() }>Salir</Button>
      </Tooltip>
    </section>
  )
}

export default Nav