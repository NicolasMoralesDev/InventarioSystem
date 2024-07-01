import { useEffect } from "react";
import ConfirmationModal from "../modal/ConfirmationModal"
import { Card, Col, Form, Input, InputNumber, Row, Select } from "antd"
import TextArea from "antd/es/input/TextArea";

  const ProductosModal = ({ form, categorias, productoEdit, visible, setVisible, onSend, edit, altaReg }) => {

    useEffect(() => {
        form.setFieldsValue({
            id: altaReg ? "" : edit ? productoEdit.id : "",
            codigo: edit ? productoEdit.codigo : "",
            nombre: edit ? productoEdit.nombre : "",
            descripcion: edit ? productoEdit.descripcion : "",
            categoria: edit ? productoEdit.categoria?.id : "",
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
            categoria: {id:values.categoria},
            marca: values.marca,
            precio: values.precio,
            cant: values.cant,
            borrado: values.borrado
        }
        onSend(data)
        setVisible(false)
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
                                                transform: (value) => value.trim()
                                            },
                                        ]}
                                    >
                                        <Input showCount maxLength={ 30 }/>
                                    </Form.Item>
                                </Col>
                            </Row>
                    </Row>
                    <Row gutter={ [3, 4] }>
                        <Col span={ 22 }>
                            <Form.Item
                                label="Descripcion:"
                                name="descripcion"
                                rules={[{
                                    transform: (value) => value.trim()
                                }]}
                            >
                                <TextArea showCount maxLength={ 100 }/>
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
                                        transform: (value) => value.trim()
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