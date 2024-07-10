import React, { useEffect } from "react";
import useForm from "antd/lib/form/hooks/useForm";
import { Card, Col, Form, Input, InputNumber, Row } from "antd";
import ConfirmationModal from "../../modal/ConfirmationModal";

const ProvedoresAltaModal = ({ visible, setVisible, onSend, provedor }) => {
  const [form] = useForm();

  useEffect(() => { form.setFieldsValue({
    id: provedor?.id ? provedor?.id : null,
    nombre: provedor?.nombre ? provedor?.nombre : "" ,
    correo: provedor?.correo ? provedor?.correo : "",
    tel: provedor?.tel ? provedor?.tel : "",
  })}, [ provedor ])

  const cancelModal = () => {
    setVisible(false);
  };

  const onFinish = (values) => {
    const data = {
      id: values.id,
      nombre: values.nombre,
      correo: values.correo,
      tel: values.tel,
    };

    onSend(data);
    setVisible(false)
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <>
      <ConfirmationModal
        title={ provedor?.id ? "Editar provedor" : "Cargar nuevo provedor" }
        open={ visible }
        okText="Guardar"
        cancelText="Cancelar"
        onCancel={ cancelModal }
        onClose={ cancelModal }
        onOk={ () =>
          form
            .validateFields()
            .then(() => onFinish(form.getFieldsValue()))
            .catch(() => {})
        }>
        <Form
          form={ form }
          name="basic"
          onFinish={ onFinish }
          onFinishFailed={ onFinishFailed }
          autoComplete="off">
          
          <Form.Item name="id" hidden><Input/></Form.Item>
          <Card className="w-full">
            <Row>
              <Col span={ 20 }>
                <Form.Item
                  label="Nombre"
                  name="nombre"
                  rules={[
                    {
                      required: true,
                      message: "Ingrese el nombre!",
                      transform: (value) => value.trim()
                    },
                  ]}>
                  <Input placeholder="Nombre del provedor..."/>
                </Form.Item>
              </Col>
            </Row>
            <Row>
              <Col span={ 5 }>
                <Form.Item label="Telefono" name="tel">
                  <InputNumber
                    maxLength={ 10 }
                    min={ 1 }
                    placeholder="Telefono del provedor..."
                  />
                </Form.Item>
              </Col>
              </Row>
              <Row>
              <Col span={ 20 }>
                <Form.Item label="Correo" name="correo" rules={ [{ type: 'email', message:`El correo es invalido`, transform: (value) => value.trim() }] }>
                  <Input
                   maxLength={ 20 }
                   placeholder="Correo del provedor..."
                  />
                </Form.Item>
              </Col>
            </Row>
          </Card>
        </Form>
      </ConfirmationModal>
    </>
  );
};

export default ProvedoresAltaModal;
