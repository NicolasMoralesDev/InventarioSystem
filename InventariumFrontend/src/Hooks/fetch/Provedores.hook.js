import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const urlBase = "api/v1/supplier"

/**
 * Obtiene todos los provedores.
 * @returns Devuelve un array con los provedores existentes.
 */
export const obtenerProvedores = async ()  => {

     try {
           const request = await useAxiosConf.get(`${urlBase}/getAll`)
           return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     }
}

/**
 * Registro de provedores.
 * @returns Devuelve un mensaje con el resultado de la operacion.
 */
export const registrarProvedor = async (provedor) => {

     try {
        const request = await useAxiosConf.post(`${urlBase}/create`, provedor)
        return request;
     } catch (error) {
        errorPop("error al intentar conectarse con el servidor." + error);
     }
}