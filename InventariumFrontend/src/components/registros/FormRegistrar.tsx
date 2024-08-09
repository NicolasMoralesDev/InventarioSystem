import React, { useEffect, useState }from 'react';
import { Button, Card, Col, Input, InputNumber, Row, Select, Space } from 'antd'
import Form from 'antd/es/form/Form'
import TextArea from 'antd/es/input/TextArea'
import useForm  from "antd/lib/form/hooks/useForm"
import { BookOutlined, InboxOutlined, ProductFilled, ProductOutlined, UploadOutlined } from "@ant-design/icons";
import { obtenerProductosStorage } from '../../Hooks/util/localStorage/Abm.registros';
import { useGetNombreUsuario } from '../../Hooks/util/localStorage/Auth';
import { obtenerProductoByCodigo } from '../../Hooks/fetch/Productos.hook';
import { usePermission } from '../../Hooks/util/auth.hook';
import { ROLE_DUENIO } from '../../constants/permisos';
import { Producto } from '../../classes/Producto';
import './estilos/formIngresos.css'

 interface FormRegistrar {
    provedores?: any;
    categorias: any;
    onSend: Function;
    onRegister: Function;
    isEgreso?: boolean;
    setVisibleProve?: Function;
    setVisibleProveReg?: Function;
 }

 const FormRegistrar = ({ onSend, provedores, categorias, onRegister, isEgreso, setVisibleProve, setVisibleProveReg }: FormRegistrar) => {

  const [form] = useForm()

  const isAdmin = usePermission(ROLE_DUENIO)

  const [producto, setProducto] =  useState<Producto>()

  const handleChange = async (codigo) => {
    const request = await obtenerProductoByCodigo(codigo)
    setProducto(request?.data)
  }

  useEffect(() => { form.setFieldsValue({
      codigo: producto?.codigo ? producto?.codigo : "" ,
      nombre: producto?.nombre ? producto?.nombre : "",
      marca: producto?.marca ? producto?.marca : "",
      precio: producto?.precio ? producto?.precio : "",
      categoria: producto?.categoria ? producto?.categoria.id : "",
      descripcion: producto?.descripcion ? producto?.descripcion : "",
      cant: ""

  })}, [ producto ])
    
  const nombreUsuario = useGetNombreUsuario()

  const onSendEgreso = (values) => {

    const data = {
      usuario: nombreUsuario,
      codigo: values?.codigo,
      nombre: values?.nombre,
      marca: values?.marca,
      precio: values?.precio,
      cantidad: values?.cantidad,
      observacion: values?.observacion,
      categoria: { id:values?.categoria },
      descripcion: values?.descripcion
   }

     onSend(data)
     console.log('Success');  
  }

  const onSendIngreso = (values) => {

    const data = {
       usuario: nombreUsuario,
       codigo: values?.codigo,
       nombre: values?.nombre,
       marca: values?.marca,
       precio: values?.precio,
       cantidad: values?.cantidad,
       observacion: values?.observacion,
       provedor: values?.provedor,
       categoria: { id:values?.categoria },
       descripcion: values?.descripcion
    }

      onSend(data)
      console.log('Success');  
    
  }

  const onFinish = (values) => { 
    
    isEgreso ? onSendEgreso(values) : onSendIngreso(values) 
    form.setFieldsValue({
      id:"",
      usuario:"",
      codigo: "",
      nombre: "",
      marca: "",
      precio: "",
      cantidad: "",
      categoria: "",
      descripcion: ""
    })
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <>
      <Card className='bg-slate-200'>
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
                  label={ !isEgreso ? "Observación del Ingreso" : "Observación del Egreso" }
                  name="observacion"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese una observacion!',
                      transform: (value) => value.trim()
                    },
                  ]}
                >
                  <TextArea 
                      maxLength={ 200 }
                      showCount
                  />
                </Form.Item>
              </Col>
              { !isEgreso ?
              <>
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
                        provedores?.map(provedor =>
                          <Select.Option key={provedor.id} value={provedor.nombre}>{provedor.nombre}</Select.Option>
                        )
                      }
                  </Select>
                </Form.Item>
              </Col>
              <Col>
                <Space align='start' className='flex flex-wrap justify-center md:flex-row sm:flex-col'>
                  <Button icon={ <BookOutlined /> } type='primary' onClick={ ()=> setVisibleProve(true) } className='btn-cyan-custom bg-blue-950 text-white' >Ver provedores</Button>
                  <Button icon={ <InboxOutlined /> } disabled={ !isAdmin } type='primary' onClick={ ()=> setVisibleProveReg(true) } className='btn-cyan-custom bg-blue-950 text-white' >Registrar provedor</Button>
                </Space>
              </Col>
              </>
               :
               <></>
              }
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
                    minLength={ 4 }
                    onChange={ handleChange }
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
                      transform: (value) => value.trim()
                    },
                  ]}
                >
                  <Input 
                    disabled={ producto?.nombre || isEgreso ? true : false }
                  />
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
                      transform: (value) => value.trim()
                    },
                  ]}
                >
                  <Input 
                    disabled={ producto?.marca || isEgreso ? true : false }
                  />
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
                  <InputNumber 
                    min={ 1 }
                    minLength={ 1 }
                    disabled={ producto?.precio || isEgreso ? true : false }
                  />
                </Form.Item>
                </Col>
                <Col>
                <Form.Item
                  label="Cantidad"
                  name="cantidad"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese la cantidad!',
                    },
                  ]}
                >
                  <InputNumber
                   min={ 1 }
                   max={ isEgreso ? producto?.cantidad : 200 }
                   minLength={ 1 }
                  />
                </Form.Item>
                </Col>
              </Row>
              <Row gutter={ [15,15] }>
              <Col sm={ 5 } span={ 20 }>
                <Form.Item
                  label="Descripcion"
                  name="descripcion"
                  rules={[
                    {
                      required: true,
                      message: 'Ingrese una descripcion!',
                      transform: (value) => value.trim()
                    },
                  ]}
                >
                  <TextArea
                      disabled={ producto?.descripcion || isEgreso ? true : false }
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
                    <Select disabled={ producto?.categoria || isEgreso ? true : false }>
                      {
                        categorias?.map(categoria =>
                          <Select.Option key={ categoria.id } value={ categoria.id }>{ categoria.titulo }</Select.Option>
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
            <Button type="default" className='btn-cyan-custom bg-blue-950 text-white' htmlType="submit" icon={ <ProductOutlined/> }>
              Cargar producto
            </Button>
          </Form.Item>
            { !isEgreso ?
            <Button type="primary" className='bg-blue-950 text-white' disabled={ obtenerProductosStorage("productos") != null ? false : true } onClick={ ()=> onRegister() } icon={ <UploadOutlined/> }>
              Registrar ingreso
            </Button> 
            :
            <Button type="primary" className='bg-blue-950 text-white' disabled={ obtenerProductosStorage("productosEgresos") != null ? false : true } onClick={ ()=> onRegister() } icon={ <ProductFilled/> }>
              Registrar egreso
            </Button> 
            }
          </Space>
        </Form>
      </Card>
    </>
  )
}

export default FormRegistrar