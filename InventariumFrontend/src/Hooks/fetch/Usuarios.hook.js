import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const urlBase = "api/users"

/**
 * Obtiene todos los usuarios.
 * @returns Devuelve un array con los usuarios existentes.
 */
export const obtenerUsuarios = async ()  => {

     try {
           const request = await useAxiosConf.get(`${urlBase}/getAll`)
           return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     }
}