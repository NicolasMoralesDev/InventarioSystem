import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const URL_BASE = "api/users"

/**
 * Obtiene todos los usuarios.
 * @returns Devuelve un array con los usuarios existentes.
 */
export const obtenerUsuarios = async ()  => {

     try {
           const request = await useAxiosConf.get(`${URL_BASE}/getAll`)
           return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     }
}