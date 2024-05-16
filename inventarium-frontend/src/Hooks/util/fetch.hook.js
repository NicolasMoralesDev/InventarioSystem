// axios configuration

import axios from 'axios';

/* const url = ["http://localhost:8761", "http://localhost:443/"] */

/* 
const baseUrl = import.meta.env.VITE_BACKEND_HOST || url[1];
 */
const useAxiosConf = axios.create({
/*   baseURL: baseUrl, */

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