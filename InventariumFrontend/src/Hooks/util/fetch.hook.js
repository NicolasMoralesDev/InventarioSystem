// axios configuracion
import axios from 'axios';
import Cookies from 'universal-cookie';

const url = ["http://localhost:8080/"]
const cookies = new Cookies();

const baseUrl = import.meta.env.VITE_BACKEND_HOST || url[0];
 
const useAxiosConf = axios.create({
  baseURL: baseUrl, 
  headers: {
    'Content-Type': 'application/json'
  },
});

 useAxiosConf.interceptors.request.use(
  (config) => {
    const token = cookies.get('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default useAxiosConf;