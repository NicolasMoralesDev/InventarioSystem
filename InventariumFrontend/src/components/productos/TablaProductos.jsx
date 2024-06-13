import { useState } from "react";
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { Button, Space, Table, Tag, Tooltip } from "antd";
import { DeleteFilled, EditOutlined, FileExcelFilled, FilePdfFilled, ProductFilled } from "@ant-design/icons"
import { alertPop } from "../../Hooks/util/messages/alerts";
import Menu from "../menu/Menu";
import "./estilos/tablaProductos.css"

const TablaProductos = (props) => {

    const { setVisibleAdd, setVisibleEdit, setProductoEdit,  categorias, dataSourse, onBorrado, onGeneratePdf, loading, isList, isExpense } = props
    const cateFilter = []
    categorias?.length > 0 ? categorias.map(cate => {cateFilter.push({text: cate.titulo, value: cate.titulo})}) : ""
    const [productosSeleccionados, setProductosSeleccionados] = useState([])

    const isDisabled = () => {
        if (productosSeleccionados.length > 0) {
           return true 
        } else {
            return false
        }
    }

    const onSelectProductos = (productsSelected) => {
        setProductosSeleccionados(productsSelected)
    }

    const onAdd = () => {
        setVisibleAdd(true)
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
            title: 'Stock',
            dataIndex: 'cant',
            width: "5%",
            sorter: (a, b) => a.cant - b.cant,
            render: (cant) => <Tag color={ cant < 10 ? "volcano-inverse" : cant < 20 ? "yellow-inverse": "green-inverse" } title={ cant < 10 ? "Stock Minimo" : cant < 20 ? "Stock Bajo" : "Stock Normal" }>{ cant }</Tag>,
            key: 'cant',
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
        isList || isExpense ?
        {
            title: 'Categoria',
            dataIndex: 'categoria',
            width: "10%",
            key: 'categoria',
            render: (categoria) => categoria ? <h2 className="text-center">{categoria.titulo} </h2> : <h2 className="text-center">-</h2>,
            filters: cateFilter,
            onFilter: (value, record) => record?.categoria?.titulo === value,
        } : <></> ,
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
        <div className="p-5 pt-0 bg-slate-200" 
        style={{
            marginBottom: "5%",
        }}> 
           <Menu/>
            <Space className="w-full flex p-3 justify-end tabla_botonera" size="middle" >
                { isList ?
                <>
                 <Tooltip title="Cargar Producto">
                    <Button className="bg-blue-950 btn-cyan-custom text-white" onClick={ onAdd }> <ProductFilled/> Cargar Producto</Button>
                 </Tooltip>  
                 <Tooltip title="Generar PDF"> 
                    <Button title="Generar PDF" disabled={ isDisabled() ? false : true } className="bg-red-700 text-white" type="primary" onClick={ ()=> onDownloadPdf() }><FilePdfFilled />PDF</Button>
                 </Tooltip> 
                 <Tooltip title="Generar EXCEL"> 
                    <Button title="Generar EXCEL" disabled={ isDisabled() ? false : true } className="bg-green-700 text-white" type="primary" ><FileExcelFilled />EXCEL</Button>
                 </Tooltip>
                </>
                : 
                <></>
                }
                <Tooltip title="Borrado Multiple">
                    <Button disabled={ isDisabled() ? false : true } className="bg-red-800 text-white" type="primary" onClick={ () => onDelete() }> <DeleteFilled/> Borrado Multiple</Button> 
                </Tooltip>
            </Space>
            <Table
                size="small"
                className="overflow-x-scroll"
                rowKey={ (product) => !isList ? product.codigo : product.id }
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
    )
}

export default TablaProductos