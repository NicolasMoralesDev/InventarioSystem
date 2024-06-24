import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Routing from './Routes'
import Login from '../components/login/Login'
import Page404 from '../components/common/Page404'

export const RoutingLogin = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={ <Login/> } exact />
                <Route path="/*" element={ <Routing/> } exact />            
            </Routes>
        </BrowserRouter>
    )
}
export default RoutingLogin