export const defaultPagination = (datasource, pageSize = 5) => ({
    pageSize,
    total: datasource?.length,
    showSizeChanger: false,
    showTotal: (total, range) => `Mostrando ${ range[0] }-${ range[1] } de un total de ${ datasource?.length } registros`
})