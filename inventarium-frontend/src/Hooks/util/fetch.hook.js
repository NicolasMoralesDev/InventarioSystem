// axios configuracion

import axios from 'axios';

const url = ["http://localhost:9002", "http://localhost:8080/"]

const baseUrl = import.meta.env.VITE_BACKEND_HOST || url[0];
 
const useAxiosConf = axios.create({
  baseURL: baseUrl, 
  headers: {
    'Content-Type': 'application/json'
  },
});



/* axiosConf.interceptors.request.use(
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
); */

export default useAxiosConf;