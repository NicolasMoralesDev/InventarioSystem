import React, { useEffect } from 'react'
import { Outlet, useNavigate } from 'react-router-dom';
import { useUser } from '../../Hooks/util/auth.hook';

/**
 * Configuracion de rutas protegidas, verifica si el usuario esta logueado.
 * @param {*} param0 
 * @returns conjunto de rutas.
 */
const ProtectedAuth = ({ children }) => {

    const { isAuthenticated } = useUser()
    const navigate = useNavigate();

    useEffect(() => {
        if (!isAuthenticated) {
            navigate("/login")
        }

    }, [navigate]);
  
    return (children ? children : <Outlet/>);
}

export default ProtectedAuth