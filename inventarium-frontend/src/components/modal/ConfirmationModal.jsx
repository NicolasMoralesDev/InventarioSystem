import { Modal } from "antd"

// eslint-disable-next-line react/prop-types
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