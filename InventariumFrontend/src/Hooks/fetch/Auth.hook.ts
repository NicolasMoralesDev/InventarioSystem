import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const URL_BASE = "api/auth"

/**
 * Login de usuarios.
 * @returns Devuelve el token (si el usuario es valido) o sino un error.
 */
export const loginUsuarios = async (data)  => {

     try {
           const request = await useAxiosConf.post(`${URL_BASE}/login`, data)
           return request;  
     } catch (error) {
           errorPop("La contraseña o el usuario son invalidos");
     }
}