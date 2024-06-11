import { Button, Card, Col, Input, InputNumber, Row, Select, Space } from 'antd'
import Form from 'antd/es/form/Form'
import TextArea from 'antd/es/input/TextArea'
import useForm  from "antd/lib/form/hooks/useForm"
import { UploadOutlined } from "@ant-design/icons";
import './estilos/formIngresos.css'
import { obtenerProductosStorage } from '../../Hooks/util/localStorage/Abm.registros';

 const FormRegistrar = ({ onSend, provedores, categorias, onRegister }) => {

  const [form] = useForm()

  const onFinish = (values) => {

    const data = {
      codigo: values.codigo,
      nombre: values.nombre,
      marca: values.marca,
      precio: values.precio,
      cant: values.cant,
      observacion: values.observacion,
      provedor: values.provedor,
      categoria: {id:values.categoria},
      descripcion: values.descripcion
    }

    onSend(data)
    console.log('Success');  

    form.setFieldsValue({
      codigo: "",
      nombre: "",
      marca: "",
      precio: "",
      cant: "",
      categoria: "",
      descripcion: ""
    })
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <>
    <p>Registro de Ingresos:</p>
      <Card className=' bg-slate-200'>
        <Form
          form={ form }
          name="basic"
          initialValues={{
            remember: true,
          }}
          onFinish={ onFinish }
          onFinishFailed={ onFinishFailed }
          autoComplete="off"
        >
          <Card className="w-full">
            <Row gutter={ [40,30] }>
              <Col span={ 25 }>
                <Form.Item
                  label="Observación del Ingreso"
                  name="observacion"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese una observacion!',
                    },
                  ]}
                >
                  <TextArea 
                      maxLength={ 200 }
                      showCount
                  />
                </Form.Item>
              </Col>
              <Col span={ 24 } sm={ 6 }>
                <Form.Item
                  label="Provedor"
                  name="provedor"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese una observacion!',
                    },
                  ]}
                >
                  <Select> 
                     {
                        provedores.map(provedor =>
                          <Select.Option key={provedor.id} value={provedor.nombre}>{provedor.nombre}</Select.Option>
                        )
                      }
                  </Select>
                </Form.Item>
              </Col>
            </Row>
          </Card>
          <>
            <Card>
              <div className='m-5'>
                <h2>Agregar Productos:</h2>
              </div>
              <Row gutter={ [15,15] }>
                <Col>
                <Form.Item
                  label="Codigo de Barras"
                  name="codigo"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese el codigo!',
                    },
                  ]}
                >
                  <InputNumber
                    className='w-full'
                    min={ 0 }
                  />
                </Form.Item>
                </Col>
                <Col>
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
                </Col>
                <Col>
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
                </Col>
                <Col>
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
                </Col>
                <Col>
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
                </Col>
              </Row>
              <Row gutter={ [15,15] }>
              <Col sm={ 5 } span={20}>
                <Form.Item
                  label="Descripcion"
                  name="descripcion"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese una descripcion!',
                    },
                  ]}
                >
                  <TextArea
                      showCount
                      maxLength={ 200 }
                  />
                </Form.Item>
              </Col>
                <Col sm={ 5 } span={ 15 }>
                  <Form.Item
                    label="Categoria"
                    name="categoria"
                    rules={[
                      {
                        required: false,
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
          <Space>
          <Form.Item className="m-3">
            <Button type="default" htmlType="submit" icon={ <UploadOutlined/> }>
              Cargar producto
            </Button>
          </Form.Item>
            <Button type="default" disabled={ obtenerProductosStorage() != null ? false : true } onClick={ ()=> onRegister() } icon={ <UploadOutlined/> }>
              Registrar ingreso
            </Button>
          </Space>
        </Form>
      </Card>
    </>
  )
}

export default FormRegistrar