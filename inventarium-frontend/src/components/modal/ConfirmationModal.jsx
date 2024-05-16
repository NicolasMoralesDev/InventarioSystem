import { Modal } from "antd"

const ConfirmationModal = ( props ) => {
    return (
        <Modal
 /*            maskClosable={ !props.loading }
            cancelButtonProps={ {
              disabled: props.loading
            } } */
            { ...props }
        > 
        { props.children }
        </Modal>
    )
  }
  
  export default ConfirmationModal