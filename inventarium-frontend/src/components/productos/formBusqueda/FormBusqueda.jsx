import { Col, Form, InputNumber, Row } from 'antd'
import React, { useEffect } from 'react'

const FormBusqueda = ({form, onGetByCode}) => {

    const onFinish = (values) => {
        onGetByCode(values.codigo)
    };
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

  return (
      <Form
          form={form}
          name="basic"
          style={{
              maxWidth: "100%",
          }}
          initialValues={{
              remember: true,
          }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          autoComplete="on"
      >
          <Row gutter={[1, 1]} className='w-full' justify={'center'}>
              <Col span={70} className='w-1/3'>
                  <Form.Item
                      label="Busqueda por codigo de Barras"
                      name="codigo"
                      rules={[
                          {
                              required: true,
                              message: 'El codigo es obligatorio!',
                          },
                      ]}
                  >
                      <InputNumber
                          placeholder='Ingrese el codigo de barras...'
                          maxLength={25}
                          minLength={5}
                          className='w-10/12'
                      />
                  </Form.Item>
              </Col>
          </Row>
      </Form>
  )
}

export default FormBusqueda