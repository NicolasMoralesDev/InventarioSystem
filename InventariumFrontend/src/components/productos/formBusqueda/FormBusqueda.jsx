import React from 'react'
import { Col, Form, InputNumber, Row } from 'antd'
import useForm from 'antd/lib/form/hooks/useForm';

const FormBusqueda = ({ onGetByCode }) => {

    const [form] = useForm()
    const onFinish = (values) => {
        onGetByCode(values.codigo)
        form.setFieldsValue({ codigo:"" })
    };
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

  return (
      <Form
          form={ form }
          name="basic"
          style={{
              maxWidth: "100%",
          }}
          initialValues={{
              remember: true,
          }}
          onFinish={ onFinish }
          onFinishFailed={ onFinishFailed }
          autoComplete="on"
      >
          <Row gutter={ [1, 1] } className='w-full' justify={ 'center' }>
              <Col span={ 30 } className='xl:w-1/3 lg:w-1/3'>
                  <Form.Item
                      label="Busqueda por codigo de barras"
                      name="codigo"
                      className='pl-10 w-full'
                      rules={[
                          {
                              required: true,
                              message:'El codigo es obligatorio!',
                          },
                      ]}
                  >
                      <InputNumber
                      allowClear={ true }
                          placeholder='Ingrese el codigo de barras...'
                          maxLength={ 25 }
                          minLength={ 5 }
                          className='w-10/12'
                      />
                  </Form.Item>
              </Col>
          </Row>
      </Form>
  )
}

export default FormBusqueda