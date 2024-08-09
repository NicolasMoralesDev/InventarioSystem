import React, {  useState } from "react";
import { defaultPagination } from "../../../../Hooks/util/DefaultPagination";
import { Button, Space, Table, Tag, Tooltip } from "antd";
import { DeleteFilled, EditOutlined } from "@ant-design/icons"
import { alertPop } from "../../../../Hooks/util/messages/alerts";
import Menu from "../../../menu/Menu";
import "../../estilos/tablaProductos.css"

const TablaProductosEgresos = (props) => {

    const { setVisibleEdit, setProductoEdit,  categorias, dataSourse, onBorrado, onGeneratePdf, loading } = props
    const cateFilter = []
    categorias?.length > 0 ? categorias.map(cate => {cateFilter.push({text: cate.titulo, value: cate.titulo})}) : ""
    const [productosSeleccionados, setProductosSeleccionados] = useState([])

    const isDisabled  = () : Boolean => {
        if (productosSeleccionados.length > 0) {
           return true 
        } else {
            return false
        }
    }

    const onSelectProductos = (productsSelected) => {
        setProductosSeleccionados(productsSelected)
    }

    const onEdit = (producto) => {
        setProductoEdit(producto)
        setVisibleEdit(true)
    }

    const onDelete = () => {
        alertPop("¿Esta seguro que desea borrar los Productos seleccionados?", "question", () => onBorrado(productosSeleccionados))
    }

    const onDownloadPdf = () => {
        onGeneratePdf(productosSeleccionados)
    }

    const columns = [
        {
            title: 'Codigo',
            dataIndex: 'codigo',
            width: "10%",
            key: 'codigo',
            render: (codigo) => <h2 className="text-center">{ codigo }</h2>
        },
        
        {
            title: 'Nombre',
            dataIndex: 'nombre',
            width: "13%",
            key: 'nombre',
            render: (nombre) => <h2 className="text-center">{ nombre }</h2>
        },
        {
            title: 'Descripcion',
            dataIndex: 'descripcion',
            width: "13%",
            key: 'descripcion',
        },
        {
            title: 'Marca',
            dataIndex: 'marca',
            width: "10%",
            key: 'marca',
            render: (marca) => <h2 className="text-center">{ marca }</h2>
        },
        {
            title: 'Categoria',
            dataIndex: 'categoria',
            width: "10%",
            key: 'categoria',
            render: (categoria) => categoria ? <h2 className="text-center">{categoria.titulo} </h2> : <h2 className="text-center">-</h2>,
            filters: cateFilter,
            onFilter: (value, record) => record?.categoria?.titulo === value,
        }
        ,{
            title: 'Cantidad',
            dataIndex: 'cant',
            width: "5%",
            sorter: (a, b) => a.cant - b.cant,
            render: (cant) => <Tag color={ "green" } title={ "Cantidad a egresar" }>{ cant }</Tag>,
            key: 'cant',
        },
        {
            title: 'Precio unitario',
            dataIndex: 'precio',
            width: "15%",
            key: 'precio',
            sorter: (a, b) => a.precio - b.precio,
            render: (precio) => <h2 className="text-center">{`$ ${precio}` }</h2>
        },
        
        {
            title: 'Acciones',
            width: "10%",
            key: 'acciones',
            render: (producto) => <>
                <Button title="Editar Producto" onClick={ () => onEdit(producto) } className="bg-cyan-950 btn-cyan-custom text-white">Editar <EditOutlined /></Button>
            </>
        },
    ];

    return (
        <>
        <Menu/>
        <div className="p-5 pt-0 bg-slate-200" 
            style={{
              marginBottom: "5%"
            }}> 
            <Space className="w-full flex p-3 justify-end" size="middle">
                <Tooltip title="Borrado Multiple">
                    <Button disabled={ isDisabled() ? false : true } className="bg-red-800 text-white" type="primary" onClick={ () => onDelete() }> <DeleteFilled/> Borrado Multiple</Button> 
                </Tooltip>
            </Space>
            <Table
                size="small"
                className="overflow-x-scroll"
                rowKey={ (product) => product.id }
                dataSource={ dataSourse }
                sortDirections={ ["ascend", "descend"] }
                columns={ columns }
                pagination={ defaultPagination(dataSourse, 15) }
                rowSelection={{
                    selectedRowKeys: productosSeleccionados,
                    onChange: onSelectProductos,
                }}
                locale={{
                    emptyText: "No se encontraron Productos",
                }}
                loading={ loading }
            />
        </div>
        </>
    )
}

export default TablaProductosEgresos