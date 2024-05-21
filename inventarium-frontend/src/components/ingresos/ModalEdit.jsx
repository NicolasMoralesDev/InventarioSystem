import { useEffect } from "react";
import ConfirmationModal from "../modal/ConfirmationModal"
import { Form, Input } from "antd"

const ModalEdit = ({form, ingresoEdit, visible, setVisible }) => {

    useEffect(() => {
        form.setFieldsValue({
        id: ingresoEdit.id,
        description: ingresoEdit.description,
        dateIncome: ingresoEdit.dateIncome,
        suppliers: [...ingresoEdit.suppliers],
        products: [...ingresoEdit.products]
    })
    
    }, [ form ])

    const cancelModal = () => {
        setVisible(false)
    }

    const onFinish = (values) => {
        console.log('Success:', values);
    };
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <>
            <ConfirmationModal
                title={ "Editar Registro" }
                open={ visible }
                okText={ "Guardar"}
                cancelText={ "Cancelar" }
                onCancel={ cancelModal }
                onClose={ cancelModal }
                pnOk={ () => form.validateFields().then(() => onFinish(form.getFieldsValue())).catch(() => {}) }
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
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                autoComplete="off"
            >
                <Form.Item
                    label="Observación"
                    name="description"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your username!',
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