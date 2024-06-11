/**
 * Configuracion de la paginacion de tablas.
 * @param {*} datasource Recibe los datos a paginar.
 * @param {*} pageSize Recibe el tamaño de cada pagina.
 * @returns El componente paginador.
 */
export const defaultPagination = (datasource, pageSize = 5) => ({
    pageSize,
    total: datasource?.length,
    showSizeChanger: false,
    showTotal: (total, range) => `Mostrando ${ range[0] }-${ range[1] } de un total de ${ datasource?.length } registros`
})