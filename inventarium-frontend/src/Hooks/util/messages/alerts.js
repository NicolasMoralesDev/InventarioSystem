import { message } from "antd";
import Swal from "sweetalert2"

/**
 * PopUp con mensage de info.
 * @params Recime el mensaje a mostrar.
 */
export const infoPop = (msg) => {
    message.info(msg);
};

/**
 * PopUp con mensage de error.
 * @params Recime el mensaje a mostrar.
 */
export const errorPop = (msg) => {
    message.error(msg);
};

/**
 * PopUp con mensage de carga.
 * @params Recime el mensaje a mostrar.
 */
export const loadingPop = (msgCargando, key) => {
    message.loading({ content: msgCargando, key });
};

/**
 * PopUp con mensage de exito.
 * @params Recibe el mensaje a mostrar.
 */
export const successPop = (msg, key) => {
      message.success({ content: msg, key, duration: 2 });
};

/**
 * PopUp con mensage de Confirmacion de Accion.
 * @params Recibe el mensaje a mostrar.
 * @params Recibe el tipo de icono a mostrar.
 * @params Recime la funcion a ejecutar si se confirme la accion.
 */
export const alertPop = (msg, icon, funcion) => {
    Swal.fire({
        text: msg,
        icon: icon,
        showDenyButton: true,
        confirmButtonText: "Confirmar",
        denyButtonText: `Cancelar`
      }).then((result) => {
        if (result.isConfirmed) {
          funcion()
        }
      });
}