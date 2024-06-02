
/**
 * Carga el LocalStorage con productos.
 * @param {*} productos 
 */
export const cargarProductosStorage = (productos) => {
    const produPrev = obtenerProductosStorage()
    if (produPrev != null) {

        if (produPrev.observacion != productos.observacion) {
            produPrev.observacion = productos.observacion
        }

        if (produPrev.provedor != productos.provedor) {
            produPrev.provedor = productos.provedor
        }

        produPrev.productos.push(productos)
        localStorage.setItem("productos", JSON.stringify(produPrev))
    } else {
        const observacion = productos.observacion
        const provedor = productos.provedor
        delete productos.observacion
        localStorage.setItem("productos", JSON.stringify({observacion:observacion, provedor:provedor, productos:[productos]}))
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

/**
 * Edita los productos del LocalStorage.
 */
export const editarProductosStorage = (producto) => {
    let storage = obtenerProductosStorage()
    
    storage.productos.forEach((produt, index) => {
        if (produt.codigo == producto.codigo) {
            storage.productos.splice(index, 1)
        }
    });

    localStorage.removeItem("productos")
    storage.productos.push(producto)
    localStorage.setItem("productos", JSON.stringify(storage))
}

/**
 * Borra los productos del LocalStorage.
 */
export const borrarProductosStorage = (productos) => {
    let storage = obtenerProductosStorage()
    
    productos.forEach(function(elemento) {
    
    storage.productos.forEach((produt, index) => {

        if (produt.codigo == elemento) {
            storage.productos.splice(index, 1)
        }
    })})

    localStorage.removeItem("productos")
    localStorage.setItem("productos", JSON.stringify(storage))
}