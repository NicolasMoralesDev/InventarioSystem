import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

const ProtectedAuth = ({children}) => {
/*     const { loading, isAuthenticated } = useUser(); */
    const navigate = useNavigate();
  
    useEffect(() => {
  
     /*    navigate("/login"); */
      
    }, [navigate]);
  
    return (children);
}

export default ProtectedAuth