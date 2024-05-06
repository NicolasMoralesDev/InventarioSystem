import useAxiosConf from "../util/fetch.hook";

const urlBase = "http://localhost:9002/api/v1/product"

/**
 * Obtiene todos los productos paginados
 * @returns Devuelve un listado de productos.
 */
export const obtenerProductos = async ()  => {

     const request = await useAxiosConf.get(`${urlBase}/getAll`)

     return request;
}
