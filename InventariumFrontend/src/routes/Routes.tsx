import React from 'react';
import {
    Route,
    Routes,
} from "react-router-dom";
import Productos from "../components/productos/Productos";
import Ingresos from "../components/registros/ingresos/historico/Ingresos";
import IngresosAlta from "../components/registros/ingresos/alta/IngresosAlta";
import Egresos from "../components/registros/egresos/historico/Egresos";
import EgresosAlta from "../components/registros/egresos/alta/EgresosAlta";
import ProtectedAuth from "./rutasProtegidas/ProtectedAuth"
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
             <Route element={ <ProtectedAuth children={ undefined }/> } >
                <Route path="/" element={ <Productos/> } />
                <Route element={ <ProtectedPermission permission={ isDuenio ? ROLE_DUENIO : ROLE_ENCARGADO } children={ undefined } /> } >
                   <Route path="ingresos" element={ <Ingresos/> } />
                   <Route path="ingresosAlta" element={ <IngresosAlta/> } />
                   <Route path="egresos" element={ <Egresos/> } />
                   <Route path="egresosAlta" element={ <EgresosAlta/> } />
                </Route>
                <Route element={ <ProtectedPermission permission={ ROLE_DUENIO } children={ undefined } /> }>
                  <Route path="usuarios" element={ <Usuarios/> } />
                </Route>
                <Route path="*" element={ <Page404/> }/>
              </Route>
            </Routes>
        </App>
        </>
    )
}

export default Routing