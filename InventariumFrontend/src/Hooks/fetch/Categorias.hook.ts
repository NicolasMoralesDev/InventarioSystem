import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const URL_BASE = "api/v1/category"

/**
 * Obtiene todas las categorias.
 * @returns Devuelve un listado de las categorias existentes.
 */
export const obtenerCategorias = async ()  => {

     try {
          const request = await useAxiosConf.get(`${URL_BASE}/getAll`)
          return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     }
}
