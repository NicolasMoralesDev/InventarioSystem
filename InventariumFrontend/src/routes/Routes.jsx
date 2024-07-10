import {
    Route,
    Routes,
} from "react-router-dom";
import TablaProductos from "../components/productos/TablaProductos";
import Productos from "../components/productos/Productos";
import Ingresos from "../components/registros/ingresos/historico/Ingresos";
import IngresosAlta from "../components/registros/ingresos/alta/IngresosAlta";
import Egresos from "../components/registros/egresos/historico/Egresos";
import EgresosAlta from "../components/registros/egresos/alta/EgresosAlta";
import ProtectedAuth from "../routes/rutasProtegidas/ProtectedAuth"
import App from "../App";
import '../index.css'
import Page404 from "../components/common/Page404";
import Usuarios from "../components/usuarios/Usuarios";
import ProtectedPermission from "./rutasProtegidas/ProtectedPermission";
import { ROLE_DUENIO, ROLE_ENCARGADO } from "../constants/permisos";
import { usePermission } from "../Hooks/util/auth.hook";

const Routing = () => {

    const isDuenio = usePermission(ROLE_DUENIO)

    return (
        <>
        <App>
            <Routes>
             <Route element={ <ProtectedAuth/> } >
                <Route path="/" element={ <Productos/> } />
                <Route element={  <ProtectedPermission permission={ isDuenio ? ROLE_DUENIO : ROLE_ENCARGADO } /> } >
                   <Route path="ingresos" element={ <Ingresos/> } exact />
                   <Route path="ingresosAlta" element={ <IngresosAlta/> } exact />
                   <Route path="egresos" element={ <Egresos/> } exact />
                   <Route path="egresosAlta" element={ <EgresosAlta/> } exact />
                </Route>
                <Route element={ <ProtectedPermission permission={ ROLE_DUENIO } /> }>
                  <Route path="usuarios" element={ <Usuarios/> } exact />
                </Route>
                <Route path="*" element={ <Page404/> } exact />
              </Route>
            </Routes>
        </App>
        </>
    )
}

export default Routing