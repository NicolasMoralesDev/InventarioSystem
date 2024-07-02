import React, { useEffect, useState } from 'react'
import useForm from "antd/lib/form/hooks/useForm"
import ConfirmationModal from '../../modal/ConfirmationModal'
import TablaProvedores from './TablaProvedores'
import { borradoMultipleProvedores } from '../../../Hooks/fetch/Provedores.hook'
import "./estilos/ModalProvedores.css"
import { popUp, successPop } from '../../../Hooks/util/messages/alerts'

const ProvedoresModal = ({ provedores, visible, setVisible }) => {

    const [form] = useForm()
    const [proveDelete, setProveDelete] = useState(false)  

    useEffect(() => {
        form.setFieldsValue({
            nombre: "",
            descripcion: "",
        })
    }, [form])

    useEffect(() => { if(proveDelete) { successPop("Provedores borrados con exito!", "borrado"), setVisible(false), setProveDelete(false) } }, [proveDelete])

    const cancelModal = () => {
        setVisible(false)
    }

    const onDelete = async (Ids) => {
        const request = await borradoMultipleProvedores(Ids)
        request?.data ? setProveDelete(true) : ""
    }

    const onFinish = (values) => {
      const data =  {
            nombre: values.nombre,
            descripcion: values.descripcion,
        }
        onSend(data)
        setVisible(false)
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <>
            <ConfirmationModal
                className="provedores-modal"
                title="Provedores"
                open={ visible }
                okText="Cerrar"
                onCancel={ cancelModal }
                onClose={ cancelModal }
            >
               <TablaProvedores 
                dataSourse={ provedores }
                borrarProvedores={ onDelete }
               />
            </ConfirmationModal>
        </>
  )
}

export default ProvedoresModal