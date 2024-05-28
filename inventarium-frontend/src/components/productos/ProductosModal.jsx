import { useEffect } from "react";
import ConfirmationModal from "../modal/ConfirmationModal"
import { Button, Card, Col, Form, Input, InputNumber, Row, Select } from "antd"
import { MinusCircleOutlined, PlusOutlined } from "@ant-design/icons";
import TextArea from "antd/es/input/TextArea";

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

  const ProductosModal = ({ form, categorias, subCategorias, productoEdit, visible, setVisible, onSend, edit }) => {

    useEffect(() => {
        form.setFieldsValue({
            id: edit ? productoEdit.id : "",
            codigo: edit ? productoEdit.codigo : "",
            nombre: edit ? productoEdit.nombre : "",
            descripcion: edit ? productoEdit.descripcion : "",
            img: edit ? productoEdit.img : "",
            categoria: edit ? productoEdit.categoria.id : "",
            subCategoria: edit ? productoEdit.subCategoria : [],
            marca: edit ? productoEdit.marca : "",
            precio: edit ? productoEdit.precio : "",
            cant: edit ? productoEdit.cant : "",
            borrado: edit ? productoEdit.borrado : ""
        })
    }, [form])

    const cancelModal = () => {
        setVisible(false)
    }

    const onFinish = (values) => {
      const data =  {
            id: values.id,
            codigo: values.codigo,
            nombre: values.nombre,
            descripcion: values.descripcion,
            img: values.img,
            categoria: {id:values.categoria},
            subCategoria: values.subCategoria,
            marca: values.marca,
            precio: values.precio,
            cant: values.cant,
            borrado: values.borrado
        }
        onSend(data)
    };
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <>
            <ConfirmationModal
                title={ edit ? "Editar Producto" : "Agregar Producto" }
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
                    style={{
                        maxWidth: "100%",
                    }}
                    initialValues={{
                        remember: true,
                    }}
                    onFinish={ onFinish }
                    onFinishFailed={ onFinishFailed }
                    autoComplete="off"
                >
                    <Form.Item name="id" hidden><Input/></Form.Item>
                    <Form.Item name="borrado" hidden><Input/></Form.Item>
                    <Form.Item name="img" hidden><Input/></Form.Item>
                    <Card className="w-full">
                    <Row gutter={ [2, 2]} >
                            <Row gutter={ [5, 1] } className="w-full">
                                <Col span={ 11 } >
                                    <Form.Item
                                        label="Codigo:"
                                        name="codigo"
                                        rules={[
                                            {
                                                required: true,
                                                message: 'El codigo es obligatorio!',
                                            },
                                        ]}
                                    >
                                        <InputNumber disabled={ edit ? true : false } />
                                    </Form.Item>
                                </Col>
                                <Col>
                                    <Form.Item
                                        label="Nombre:"
                                        name="nombre"
                                        rules={[
                                            {
                                                required: true,
                                                message: 'El nombre es obligatorio!',
                                            },
                                        ]}
                                    >
                                        <Input maxLength={ 30 }/>
                                    </Form.Item>
                                </Col>
                            </Row>
                    </Row>
                    <Row gutter={ [3, 4] }>
                        <Col span={ 22 }>
                            <Form.Item
                                label="Descripcion:"
                                name="descripcion"
                            >
                                <TextArea maxLength={ 100 }/>
                            </Form.Item>
                        </Col>
                    </Row>
                    <Row gutter={ [2, 2] }>
                            <Row gutter={ [2, 1] } className="w-full">
                                <Col span={ 17 }>
                                    <Form.Item
                                        label="Categoria:"
                                        name="categoria"
                                        rules={[
                                            {
                                                required: true,
                                                message: 'La Categoria es obligatoria!',
                                            },
                                        ]}
                                    >
                                        <Select>
                                            { categorias.length > 0 ?
                                                categorias.map(categoria =>
                                                    <Select.Option key={categoria.id} value={categoria.id}>{categoria.titulo}</Select.Option>
                                                ) : <></>
                                            }
                                        </Select>
                                    </Form.Item>
                                </Col>
                                <Col span={ 22 }>
                                    <Form.List
                                        name="subCategoria"
                                    >
                                        {(fields, { add, remove }, { errors }) => (
                                            <>
                                                {fields.map((field, index) => (
                                                    <Form.Item
                                                        {...(index === 0 ? formItemLayout : formItemLayoutWithOutLabel)}
                                                        required={false}
                                                    >
                                                        <Form.Item
                                                            {...field}
                                                            validateTrigger={['onChange', 'onBlur']}
                                                            rules={[
                                                                {
                                                                    required: true,
                                                                    whitespace: true,
                                                                    message: "Selecione una sub categoria o elimine este campo.",
                                                                },
                                                            ]}
                                                            noStyle
                                                        >
                                                            <Select
                                                                placeholder="sub categorias"
                                                                style={{
                                                                    width: '60%',
                                                                }}
                                                            >
                                                                {  subCategorias.length > 0 ?
                                                                    subCategorias.map(subCate => 
                                                                      <Select.Option key={subCate.id} value={subCate.id}>{subCate.titulo}</Select.Option>
                                                                    ) : <></>
                                                                }
                                                            </Select>
                                                        </Form.Item>
                                                        {fields.length > 1 ? (
                                                            <MinusCircleOutlined
                                                                className="dynamic-delete-button"
                                                                onClick={() => remove(field.name)}
                                                            />
                                                        ) : null}
                                                    </Form.Item>
                                                ))}
                                                <Form.Item>
                                                    <Button
                                                        type="dashed"
                                                        onClick={() => add()}
                                                        style={{
                                                            width: '60%',
                                                        }}
                                                        icon={<PlusOutlined />}
                                                    >
                                                        Agregar sub categorias
                                                    </Button>
                                                    <Form.ErrorList errors={errors} />
                                                </Form.Item>
                                            </>
                                        )}
                                    </Form.List>
                                </Col>
                            </Row>
                    </Row>
                    <Row gutter={ [8, 8] }>
                        <Col>
                            <Form.Item
                                label="Marca:"
                                name="marca"
                                rules={[
                                    {
                                        required: true,
                                        message: 'La marca es obligatoria!',
                                    },
                                ]}
                            >
                                <Input maxLength={ 25 }/>
                            </Form.Item>
                        </Col>
                    </Row>
                    <Row gutter={ [17,2] }>
                        <Col>
                            <Form.Item
                                label="Precio $"
                                name="precio"
                                rules={[
                                    {
                                        required: true,
                                        message: 'El precio es obligatorio!',
                                    },
                                ]}
                            >
                                <InputNumber min={ 1 }/>
                            </Form.Item>
                            </Col>
                        <Col>
                            <Form.Item
                                label="Stock:"
                                name="cant"
                                rules={[
                                    {
                                        required: true,
                                        message: 'El stock es obligatorio!',
                                    },
                                ]}
                            >
                                <InputNumber max={ 50000 } min={ 0 }/>
                              </Form.Item>    
                            </Col>
                    </Row>       
                    </Card>
                </Form>
            </ConfirmationModal>
        </>
    )
}

export default ProductosModal