import { Button, Popconfirm, Tooltip } from "antd"
import logo from "../../assets/Inventarium-SystemP.png"
import { LogoutOutlined } from "@ant-design/icons"
import { onLogOut } from "../../Hooks/util/auth.hook"
import { useGetNombreUsuario } from "../../Hooks/util/localStorage/Auth"

const Nav = () => {

  const nombreUsuario = useGetNombreUsuario()

  return (
    <section className="p-5 flex justify-between items-center xl:flex-row flex-col">
      <nav className="h-1/4" style={ { margin: "auto" } }>
        <img src={ logo } alt="logo" width="290rem" loading="lazy" />
      </nav>
      <h2>{ nombreUsuario }</h2>
      <Tooltip title="Cerrar sesi&#972;n">
        <Popconfirm
           title="Cerrar sesi&#972;n"
           description="¿Esta seguro que desea salir?"
           onConfirm={ onLogOut }
           okText="confirmar"
           cancelText="cancelar"
        >
          <Button icon={ <LogoutOutlined/> } className="h-2/3" type="text">Salir</Button>  
        </Popconfirm>
      </Tooltip>
    </section>
  )
}

export default Nav