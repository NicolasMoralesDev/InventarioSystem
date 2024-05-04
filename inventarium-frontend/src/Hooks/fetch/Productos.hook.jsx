import useAxiosConf from "../util/fetch.hook";

/**
 * Obtine todos los productos paginados
 */
export const obtenerProductos = async ()  => {

     const request = await useAxiosConf.get("/getAll")

     return request;
}
