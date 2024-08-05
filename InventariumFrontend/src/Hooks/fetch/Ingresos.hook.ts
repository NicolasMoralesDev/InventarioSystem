import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const URL_BASE = "api/v1/income"

/**
 * Registro de Ingresos.
 * @returns Devuelve un mensaje con el resultado de la operacion.
 */
export const registrarIngresos = async (ingreso) => {

     try {
          const request = await useAxiosConf.post(`${URL_BASE}/register`, ingreso)
          return request;
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor." + error);
     }
}

/**
 * Obtiene todos los registros de ingresos
 * @returns Devuelve un listado de registros de ingresos.
 */
export const obtenerIngresos = async () => {

     try {
          const request = await useAxiosConf.get(`${URL_BASE}/getAll`)
          return request;
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     }
}

/**
 * Edita registros de ingresos
 * @returns Devuelve un mensaje con el resultado de la operacion.
 */
export const modificarIngresos = async (ingreso) => {

     try {
          const request = await useAxiosConf.put(`${URL_BASE}/modify`, ingreso)
          return request;
     } catch (error) {
          errorPop(error);
     }
}