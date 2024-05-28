
/**
 * Carga el LocalStorage con productos.
 * @param {*} productos 
 */
export const cargarProductosStorage = (productos) => {
    const produPrev = obtenerProductosStorage()
    if (produPrev != null) {
        produPrev?.productos.push(productos)
        localStorage.setItem("productos", JSON.stringify(produPrev))
    } else {
        localStorage.setItem("productos", JSON.stringify({productos:[productos]}))
    }
}

/**
 * Obtiene los productos del LocalStorage.
 * @returns un Array de productos.
 */
export const obtenerProductosStorage = () => {
    const productos = JSON.parse(localStorage.getItem("productos"))
    return productos 
}