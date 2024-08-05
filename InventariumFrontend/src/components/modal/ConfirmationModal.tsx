import React from "react"
import { Modal } from "antd"

const ConfirmationModal = ( props ) => {
    return (
        <Modal
            { ...props }
        > 
        { props.children }
        </Modal>
    )
  }
  
  export default ConfirmationModal