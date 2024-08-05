import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const URL_BASE = "api/v1/supplier"

/**
 * Obtiene todos los provedores.
 * @returns Devuelve un array con los provedores existentes.
 */
export const obtenerProvedores = async ()  => {

     try {
           const request = await useAxiosConf.get(`${URL_BASE}/getAll`)
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
        const request = await useAxiosConf.post(`${URL_BASE}/create`, provedor)
        return request;
     } catch (error) {
        errorPop("error al intentar conectarse con el servidor." + error);
     }
}

/**
 * Modificacion de provedores.
 * @returns Devuelve un mensaje con el resultado de la operacion.
 */
export const editarProvedor = async (provedor) => {

     try {
        const request = await useAxiosConf.put(`${URL_BASE}/edit`, provedor)
        return request;
     } catch (error) {
        errorPop("error al intentar conectarse con el servidor." + error);
     }
}

/**
 * Realiza borrado multiple de provedores.
 * @returns Devuelve el estado de la operacion.
 */
export const borradoMultipleProvedores = async (provedoresIds) => {

     try {
       const request = await useAxiosConf.post(`${URL_BASE}/delete/bulk`, provedoresIds)
       return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor."+ error);
     } 
}