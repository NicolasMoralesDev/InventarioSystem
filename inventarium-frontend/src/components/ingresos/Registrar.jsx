import React, { useEffect } from 'react'
import { Button, Card, Col, Input, InputNumber, Row, Select } from 'antd'
import Form from 'antd/es/form/Form'
import TextArea from 'antd/es/input/TextArea'
import useForm  from "antd/lib/form/hooks/useForm"
import './estilos/formIngresos.css'

 const formItemLayout = {
  labelCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 4,
    },
  },
  wrapperCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 20,
    },
  },
 };

 const formItemLayoutWithOutLabel = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 20,
      offset: 4,
    },
 },}

 const ModalRegistrar = ({ ingresoRegistrar, onSend, categorias }) => {

  const [form] = useForm()

  const onFinish = (values) => {
    const data = {
      code: values.code,
      nombre: values.nombre,
      marca: values.marca,
      precio: values.precio,
      cant: values.cant,
      descripcion: values.descripcion,
      categoria: values.categoria
    }
    onSend(data)
    console.log('Success');  

    form.setFieldsValue({
      code: "",
      nombre: "",
      marca: "",
      precio: "",
      cant: "",
      descripcion: "",
      categoria: ""
    })
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <>
    <p>Registro de Ingresos:</p>
      <Card className='w-full bg-slate-200'>
        <Form
          className='w-full'
          form={form}
          name="basic"
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          autoComplete="off"
        >
          <Card className='w-full'>
            <Row>
              <Col span={ 50 }>
                <Form.Item
                  className="w-full"
                  label="Observación del Ingreso"
                  name="descripcion"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese una observacion!',
                    },
                  ]}
                >
                  <TextArea />
                </Form.Item>
              </Col>
            </Row>
          </Card>
          <>
            <Card>
              <Row gutter={[20, 25]} className='gap-5'>
                <Form.Item
                  label="Codigo de Barras"
                  name="code"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese el codigo!',
                    },
                  ]}
                >
                  <InputNumber
                    className='1/4'
                    min={0}
                  />
                </Form.Item>

                <Form.Item
                  label="Nombre"
                  name="nombre"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese el nombre del producto!',
                    },
                  ]}
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  label="Marca"
                  name="marca"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese la marca del producto!',
                    },
                  ]}
                >
                  <Input />
                </Form.Item>

                <Form.Item
                  label="Precio $"
                  name="precio"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese el precio del producto!',
                    },
                  ]}
                >
                  <InputNumber />
                </Form.Item>
                <Form.Item
                  label="Cantidad"
                  name="cant"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese la cantidad!',
                    },
                  ]}
                >
                  <InputNumber />
                </Form.Item>
              </Row>
              <Row>
                <Col span={4}>
                  <Form.Item
                    label="Categoria"
                    name="categoria"
                    rules={[
                      {
                        required: true,
                        message: 'Ingrese la categoria!',
                      },
                    ]}
                  >
                    <Select>
                      {
                        categorias?.map(categoria =>
                          <Select.Option key={categoria.id} value={categoria.id}>{categoria.titulo}</Select.Option>
                        )
                      }
                    </Select>
                  </Form.Item>
                </Col>
              </Row>
            </Card>
          </>
          <Form.Item className="m-3">
            <Button type="primary" htmlType="submit">
              Cargar producto
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </>
  )
}

export default ModalRegistrar