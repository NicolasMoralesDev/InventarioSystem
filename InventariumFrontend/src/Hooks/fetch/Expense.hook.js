const urlBase = "expense-service/api/v1/income"

/**
 * Registro de egresos.
 * @returns Devuelve un mensaje con el resultado de la operacion.
 */
export const registrarEgreso = async (egreso) => {

     try {
        const request = await useAxiosConf.post(`${urlBase}/register`, egreso)
        return request;
     } catch (error) {
        errorPop("error al intentar conectarse con el servidor." + error);
     }
}

/**
 * Obtiene todos los registros de egresos
 * @returns Devuelve un listado de registros de egresos.
 */
export const obtenerEgresos = async () => {

     try {
        const request = await useAxiosConf.get(`${urlBase}/getAll`)
        return request;
     } catch (error) {
        errorPop("error al intentar conectarse con el servidor.");
     }
}