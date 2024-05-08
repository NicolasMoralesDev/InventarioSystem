import { message } from "antd";

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
 * @params Recime el mensaje a mostrar.
 */
export const successPop = (msg, key) => {
      message.success({ content: msg, key, duration: 2 });
};