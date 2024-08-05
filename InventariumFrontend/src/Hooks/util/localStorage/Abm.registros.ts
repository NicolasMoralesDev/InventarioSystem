
/**
 * Carga el LocalStorage con productos.
 * @param {*} productos datos de los productos a cargar.
 * @param {*} itemNombre nombre del item a cargar. 
 */
export const cargarProductosStorage = (productos, itemNombre) => {
    const produPrev = obtenerProductosStorage(itemNombre)
    if (produPrev != null) {

        if (produPrev.observacion != productos.observacion) {
            produPrev.observacion = productos.observacion
        }

        if (produPrev.provedor != productos.provedor) {
            produPrev.provedor = productos.provedor
        }

        delete productos.usuario
        produPrev.productos.push(productos)
        localStorage.setItem(itemNombre, JSON.stringify(produPrev))
    } else {
        const usuario = productos.usuario
        const observacion = productos.observacion
        const provedor = productos.provedor
        delete productos.observacion
        delete productos.usuario

        localStorage.setItem(itemNombre, JSON.stringify({observacion:observacion, provedor:provedor, usuario:usuario, productos:[productos]}))
    }
}

/**
 * Carga el LocalStorage con productos para registrar egresos.
 * @param {*} productos datos de los productos a cargar.
 * @param {*} itemNombre nombre del item a cargar. 
 */
export const cargarProductosEgresoStorage = (productos, itemNombre) => {
    const produPrev = obtenerProductosStorage(itemNombre)
    if (produPrev != null) {

        if (produPrev.observacion != productos.observacion) {
            produPrev.observacion = productos.observacion
        }

        delete productos.usuario
        produPrev.productos.push(productos)
        localStorage.setItem(itemNombre, JSON.stringify(produPrev))
    } else {
        const usuario = productos.usuario
        const observacion = productos.observacion
        delete productos.observacion
        delete productos.usuario
        localStorage.setItem(itemNombre, JSON.stringify({observacion:observacion, usuario:usuario, productos:[productos]}))
    }
}

/**
 * Obtiene los productos del LocalStorage.
 * @param {*} itemNombre nombre del item de datos a obtener.
 * @returns un Array de productos.
 */
export const obtenerProductosStorage = (itemNombre) => {
    const productos : any = JSON.parse(localStorage.getItem(itemNombre))
    return productos 
}

/**
 * Edita los productos del LocalStorage.
 * @param {*} itemNombre nombre del item de datos a editar.
 */
export const editarProductosStorage = (producto, itemNombre) => {
    let storage = obtenerProductosStorage(itemNombre)
    
    storage.productos.forEach((produt, index) => {
        if (produt.codigo == producto.codigo) {
            storage.productos.splice(index, 1)
        }
    });

    localStorage.removeItem(itemNombre)
    storage.productos.push(producto)
    localStorage.setItem(itemNombre, JSON.stringify(storage))
}

/**
 * Borra los productos del LocalStorage.
 * @param {*} itemNombre nombre del item de datos a borrar.
 */
export const borrarProductosStorage = (productos, itemNombre) => {
    let storage = obtenerProductosStorage(itemNombre)
    
    productos.forEach(function(elemento) {
    
    storage.productos.forEach((produt, index) => {

        if (produt.codigo == elemento) {
            storage.productos.splice(index, 1)
        }
    })})

    localStorage.removeItem(itemNombre)
    localStorage.setItem(itemNombre, JSON.stringify(storage))
}