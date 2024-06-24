

export const useLoadLocal = (user, permissions) => {
    localStorage.setItem("usuario", JSON.stringify(user))
    localStorage.setItem("permisos", JSON.stringify(permissions))
}

export const useGetNombreUsuario = () => {
    return JSON.parse(localStorage.getItem("usuario"))
}