import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const urlBase = "http://localhost:9002/api/v1/subCategory"

/**
 * Obtiene todas las sub categorias.
 * @returns Devuelve un listado de las sub categorias existentes.
 */
export const obtenerSubCategorias = async ()  => {

     try {
           const request = await useAxiosConf.get(`${urlBase}/getAll`)
           return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     }
}