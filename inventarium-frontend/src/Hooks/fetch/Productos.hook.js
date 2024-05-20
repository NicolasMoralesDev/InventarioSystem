import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const urlBase = "http://localhost:9002/api/v1/product"

/**
 * Obtiene todos los productos.
 * @returns Devuelve un listado de productos.
 */
export const obtenerProductos = async () => {
     try {
          const request = await useAxiosConf.get(`${urlBase}/getAll`)
          return request;
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     }
}

/**
 * Busca producto por codigo de barras.
 * @returns Devuelve el producto relacionado con el codigo.
 */
export const obtenerProductoByCodigo = async (code) => {
     try {
          const request = await useAxiosConf.get(`${urlBase}/get/code/${code}`)
          return request;
     } catch (error) {
          errorPop(error.response.data);
     }
}

/**
 * Realiza Borrado multiple de productos.
 * @returns Devuelve un listado de productos.
 */
export const borradoMultipleProductos = async (productosIds) => {
     try {
       const request = await useAxiosConf.post(`${urlBase}/delete/bulk`, productosIds)
       return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     } 
}

/**
 * Realiza Editado de productos.
 * @returns Devuelve un mensaje con el estado de la operacion.
 */
export const editarProducto = async (producto) => {
     try {
       const request = await useAxiosConf.put(`${urlBase}/put`, producto)
       return request;   
     } catch (error) {
          errorPop("error al intentar editar el Producto.");
     } 
}

/**
 * Realiza la carga de productos.
 * @returns Devuelve un mensaje con el estado de la operacion.
 */
export const crearProducto = async (producto) => {
     try {
       const request = await useAxiosConf.post(`${urlBase}/post`, producto)
       return request;   
     } catch (error) {
          errorPop(`Error ${error}`);
     } 
}