import { jwtDecode } from "jwt-decode";
import Cookies from "universal-cookie";

const cookies = new Cookies();

export const onAuth = (token) => {

    const date = new Date();
    date.setTime(date.getTime() + (1000 * 60 * 60 * 10));
    console.log(jwtDecode(token));
    cookies.set("token", token, { path: '/', expires: date });
}