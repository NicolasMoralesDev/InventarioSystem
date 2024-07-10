/**
 * Guarda informacion al Local Storage del usuario logueado.
 */
export const useLoadLocal = (user, permissions) => {
    localStorage.setItem("usuario", JSON.stringify(user))
    localStorage.setItem("permisos", JSON.stringify(permissions))
}

/**
 * Obtiene informacion desde el Local Storage del usuario logueado.
 * @returns Informacion del usuario actual logueado.
 */
export const useGetNombreUsuario = () => {
    return JSON.parse(localStorage.getItem("usuario"))
}