import {
    BrowserRouter,
    Route,
    Routes,
} from "react-router-dom";
import TablaProductos from "../components/productos/TablaProductos";
import TablaIngresos from "../components/ingresos/TablaIngresos";
import Productos from "../components/productos/Productos";

const Routing = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/productos" element={ <Productos/> } exact />
                <Route path="/categorias" element={ <TablaProductos/> } exact />
                <Route path="/ingresos" element={ <TablaIngresos/> } exact />
                <Route path="/egresos" element={ <TablaProductos/> } exact />
                <Route path="/usuarios" element={ <TablaProductos/> } exact />
                <Route path="/login" element={ <TablaProductos/> } exact />
            </Routes>
        </BrowserRouter>
    )
}

export default Routing