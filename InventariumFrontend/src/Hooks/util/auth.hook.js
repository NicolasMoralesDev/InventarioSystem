import { jwtDecode } from "jwt-decode";
import Cookies from "universal-cookie";
import { popUp } from "./messages/alerts";

const cookies = new Cookies()

/**
 * Funcion de logIng recibe y verifica datos del servidor.
 * @param {*} token Recibe el token de la session.
 */
export const onAuth = (token) => {
    const date = new Date()
    date.setTime(date.getTime() + (1000 * 60 * 60 * 10))
    const user = jwtDecode(token) 

    cookies.set("token", token, { path: '/', expires: date })
    popUp("¡Bienvenido "+ user.nombreCompleto + "!", "success")
    
}

/**
 * Funcion verificadora de session de usuario.
 * @returns valor si el usuario esta o no logueado.
 */
export const useUser = () => {
        let isAuthenticated = null

    if (cookies.get("token")) {
        isAuthenticated = true
    } else {
        isAuthenticated = false
    }
    return  {
        isAuthenticated
    }
}

/**
 * Funcion de logOut, quita la cookie y el token.
 */
export const onLogOut = () => {

    popUp("Gracias por visitarnos!", "success")
    location.replace("/login")
    cookies.remove("token")
}