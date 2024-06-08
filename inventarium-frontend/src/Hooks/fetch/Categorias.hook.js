import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const urlBase = "http://localhost:9002/api/v1/category"

/**
 * Obtiene todas las categorias.
 * @returns Devuelve un listado de las categorias existentes.
 */
export const obtenerCategorias = async ()  => {

     try {
           const request = await useAxiosConf.get(`${urlBase}/getAll`)
           return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     }
}
