import ConfirmationModal from "../modal/ConfirmationModal"

// eslint-disable-next-line react/prop-types
const ModalEdit = ({ visible, setVisible }) => {

    const cancelModal = () => {
        setVisible(false)
    }

    return (
        <>
            <ConfirmationModal
                title={ "Editar Registro" }
                visible={ visible }
                okText={ "Guardar" }
                cancelText={ "Cancelar" }
                onCancel={ cancelModal }
                onClose={ cancelModal }
            >
                <p>titulo</p>
            </ConfirmationModal>
        </>
    )
}

export default ModalEdit