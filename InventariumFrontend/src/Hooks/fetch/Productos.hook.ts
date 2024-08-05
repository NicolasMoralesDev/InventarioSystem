import { errorPop } from "../util/messages/alerts";
import useAxiosConf from "../util/fetch.hook";

const URL_BASE = "api/v1/product"

/**
 * Obtiene todos los productos.
 * @returns Devuelve un listado de productos.
 */
export const obtenerProductos = async (setLoading) => {
     try {
          const request = await useAxiosConf.get(`${URL_BASE}/getAll`)
          return request;
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     } finally {
          setLoading(false)
     }
}

/**
 * Busca productos por su codigo de barras.
 * @returns Devuelve el producto relacionado con el codigo.
 */
export const obtenerProductoByCodigo = async (code) => {
     try {
          const request = await useAxiosConf.get(`${URL_BASE}/get/code/${code}`)
          return request;
     } catch (error) {
          errorPop(error.response.data);
     }
}

/**
 * Realiza borrado multiple de productos.
 * @returns Devuelve un listado de productos.
 */
export const borradoMultipleProductos = async (productosIds) => {
     try {
       const request = await useAxiosConf.post(`${URL_BASE}/delete/bulk`, productosIds)
       return request;   
     } catch (error) {
          errorPop("error al intentar conectarse con el servidor.");
     } 
}

/**
 * Realiza editado de productos.
 * @returns Devuelve un mensaje con el estado de la operacion.
 */
export const editarProducto = async (producto, setLoading) => {
     try {
       const request = await useAxiosConf.put(`${URL_BASE}/put`, producto)
       return request;   
     } catch (error) {
          errorPop("error al intentar editar el Producto.");
     } finally {
       setLoading(false)
     }
}

/**
 * Realiza la carga de productos.
 * @returns Devuelve un mensaje con el estado de la operacion.
 */
export const crearProducto = async (producto) => {
     try {
       const request = await useAxiosConf.post(`${URL_BASE}/post`, producto)
       return request;   
     } catch (error) {
          errorPop(`Error ${error}`);
     } 
}

/**
 * Genera un reporte PDF con los productos selccionados.
 * @returns Devuelve un mensaje con el estado de la operacion y el reporte PDF.
 */
export const genearReportePDFproductos = async (productosIds) => {
     try {
       const request = await useAxiosConf.post(`${URL_BASE}/generate/pdf`, productosIds)
       location.replace(request.data.url)
     } catch (error) {
          errorPop(`Error ${error}`);
     } 
}