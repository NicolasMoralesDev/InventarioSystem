import { useEffect } from "react";
import ConfirmationModal from "../modal/ConfirmationModal"
import { Form, Input } from "antd"

const ModalEdit = ({ form, ingresoEdit, visible, setVisible, onSend }) => {

    useEffect(() => {
        form.setFieldsValue({
        id: ingresoEdit.id,
        descripcion: ingresoEdit.descripcion,
    })
    
    }, [ form ])

    const cancelModal = () => {
        setVisible(false)
    }

    const onFinish = (values) => {
        const data =  {
            id: values.id,
            descripcion: values.descripcion,
        }
        onSend(data)
        console.log('Success');
    };
    
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <>
            <ConfirmationModal
                title="Editar Registro"
                open={ visible }
                okText="Guardar"
                cancelText="Cancelar"
                onCancel={ cancelModal }
                onClose={ cancelModal }
                onOk={ () => form.validateFields().then(() => onFinish(form.getFieldsValue())).catch(() => {}) }
            > 
            <Form
                form={ form }
                name="basic"
                labelCol={{
                    span: 8,
                }}
                wrapperCol={{
                    span: 16,
                }}
                style={{
                    maxWidth: 600,
                }}
                initialValues={{
                    remember: true,
                }}
                onFinish={ onFinish }
                onFinishFailed={ onFinishFailed }
                autoComplete="off"
            >
                <Form.Item name="id" hidden><Input/></Form.Item>
                <Form.Item
                    label="Observación"
                    name="descripcion"
                    rules={[
                        {
                            required: true,
                            message: 'Ingrese una observacion!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
            </Form>
            </ConfirmationModal>
        </>
    )
}

export default ModalEdit