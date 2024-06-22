import {
    BrowserRouter,
    Route,
    Routes,
} from "react-router-dom";
import TablaProductos from "../components/productos/TablaProductos";
import Productos from "../components/productos/Productos";
import Ingresos from "../components/registros/ingresos/historico/Ingresos";
import IngresosAlta from "../components/registros/ingresos/alta/IngresosAlta";
import Egresos from "../components/registros/egresos/historico/Egresos";
import EgresosAlta from "../components/registros/egresos/alta/EgresosAlta";
import Login from "../components/login/Login";
import ProtectedAuth from "../routes/rutasProtegidas/ProtectedAuth"


const Routing = () => {
    return (
        <BrowserRouter>
            <Routes>
         <Route element={ <ProtectedAuth/> } >
                <Route path="/productos" element={ <Productos/> } exact />
                <Route path="/categorias" element={ <TablaProductos/> } exact />
                <Route path="/ingresos" element={ <Ingresos/> } exact />
                <Route path="/ingresosAlta" element={ <IngresosAlta/> } exact />
                <Route path="/egresos" element={ <Egresos/> } exact />
                <Route path="/egresosAlta" element={ <EgresosAlta/> } exact />
                <Route path="/usuarios" element={ <Ingresos/> } exact />
         </Route>
                <Route path="/login" element={ <Login/> } exact />
            </Routes>
        </BrowserRouter>
    )
}

export default Routing