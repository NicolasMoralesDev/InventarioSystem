import React, { useEffect, useState } from 'react'
import ConfirmationModal from '../../modal/ConfirmationModal'
import TablaProvedores from './TablaProvedores'
import { borradoMultipleProvedores } from '../../../Hooks/fetch/Provedores.hook'
import "./estilos/ModalProvedores.css"
import { successPop } from '../../../Hooks/util/messages/alerts'

const ProvedoresModal = ({ provedores, visible, setVisible, setVisibleEdit, setProvedorEdit, setProvedorEditar }) => {

    const [proveDelete, setProveDelete] = useState(false)  

    useEffect(() => { if(proveDelete) { successPop("Provedores borrados con exito!", "borrado"), setVisible(false), setProveDelete(false) } }, [proveDelete])

    const cancelModal = () => {
        setVisible(false)
    }

    const onDelete = async (Ids) => {
        const request = await borradoMultipleProvedores(Ids)
        request?.data ? setProveDelete(true) : ""
    }

    return (
        <>
            <ConfirmationModal
                className="provedores-modal"
                title="Provedores"
                open={ visible }
                cancelButtonProps={ { style: { display: 'none' } } }
                okText="Cerrar"
                onCancel={ cancelModal }
                onOk={ cancelModal }
            >
               <TablaProvedores 
                dataSourse={ provedores }
                borrarProvedores={ onDelete }
                setProvedorEdit={ setProvedorEdit }
                setVisibleEdit={ setVisibleEdit }
                setProvedorEditar={ setProvedorEditar }
               />
            </ConfirmationModal>
        </>
  )
}

export default ProvedoresModal