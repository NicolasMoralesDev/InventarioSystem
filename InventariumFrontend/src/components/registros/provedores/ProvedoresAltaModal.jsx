import React from "react";
import useForm from "antd/lib/form/hooks/useForm";
import { Card, Col, Form, Input, InputNumber, Row } from "antd";
import ConfirmationModal from "../../modal/ConfirmationModal";
import PhoneInput from "react-phone-number-input/input";

const ProvedoresAltaModal = ({ visible, setVisible, onSend }) => {
  const [form] = useForm();

  const cancelModal = () => {
    setVisible(false);
  };

  const onFinish = (values) => {
    const data = {
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
        title="Cargar nuevo provedor"
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
                   maxLength={ 15 }
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
