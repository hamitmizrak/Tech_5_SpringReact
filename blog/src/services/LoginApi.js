//rcc => TAB

//axios
import axios from "axios";

//Const URL
const LOGIN_URL = "/login/api/v1.0.0/authentication";

class LoginApi {
    //Login için
    Login(loginObject) {
        // Header için 3 parametre veriliyor.
        // return axios.post(REGISTER_API_BASE_URL, { headers: { 'accept-language': 'tr' }, register });
        return axios.post(
            LOGIN_URL,
            { headers: { "Accept": "application/json", "Content-Type": "application/json" } }  ,
            { auth: loginObject })
    }
}

export default new LoginApi;
