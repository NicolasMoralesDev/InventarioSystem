import { Button, Table, Tag, Tooltip } from "antd";
import { DeleteFilled, FileExcelFilled, FilePdfFilled } from "@ant-design/icons"
import "./estilos/tablaProductos.css"
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { useState } from "react";
import { alertPop } from "../../Hooks/util/messages/alerts";
import Menu from "../menu/Menu";

const TablaProductos = (props) => {

    const {setVisibleEdit, setProductoEdit,  categorias, dataSourse, onBorrado } = props
    
    const cateFilter = []
    categorias.length > 0 ? categorias.map(cate => {cateFilter.push({text: cate.titulo, value: cate.titulo})}) : ""
    const [productosSeleccionados, setProductosSeleccionados] = useState([])

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

    const columns = [
        {
            title: 'Codigo',
            dataIndex: 'codigo',
            width: "10%",
            key: 'codigo',
            render: (codigo) => <h2 className="text-center">{codigo}</h2>
        },
        {
            title: 'Nombre',
            dataIndex: 'nombre',
            width: "13%",
            key: 'nombre',
            render: (nombre) => <h2 className="text-center">{nombre}</h2>
        },
        {
            title: 'Imagen',
            dataIndex: 'img',
            width: "10%",
            key: 'img',
            render: (img) => img ? <img src={img} alt={img}/> : "-"
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
            render: (marca) => <h2 className="text-center">{marca}</h2>
        },
        {
            title: 'Categoria',
            dataIndex: 'categoria',
            width: "10%",
            key: 'categoria',
            render: (categoria) => <h2 className="text-center">{categoria.titulo}</h2>,
            filters: cateFilter,
            onFilter: (value, record) => record.categoria.titulo === value,
        },
        {
            title: 'Precio',
            dataIndex: 'precio',
            width: "15%",
            key: 'precio',
            sorter: (a, b) => a.precio - b.precio,
            render: (precio) => <h2 className="text-center">{`$ ${precio}`}</h2>
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
            title: 'Generar Informe',
            dataIndex: '',
            width: "13%",
            key: 'generarInforme',
            render: () => 
                <div className="p-0 text-center">
                    <Tooltip title="Generar PDF"> <Button title="Generar PDF" className="bg-red-600 btn-rojo-custom text-white xl:w-1/2 sm:w-full "><FilePdfFilled />PDF</Button></Tooltip> 
                    <Tooltip title="Generar EXCEL"> <Button title="Generar EXCEL" className="bg-green-600 btn-verde-custom text-white xl:w-1/2 sm:w-full"><FileExcelFilled />EXCEL</Button></Tooltip>
                </div>
        },
        {
            title: 'Acciones',
            width: "10%",
            key: 'acciones',
            render: (producto) => <>
                <Button title="Editar Producto" onClick={ () => onEdit(producto) } className="bg-slate-800 btn-verde-custom text-white">Editar</Button>
         {/*        <Button title="Ver Producto" className="bg-slate-800  text-white">Ver</Button> */}
            </>
        },
    ];

    return (
        <div className="p-5 pt-0 bg-slate-200" 
        style={{
            marginBottom: "5%",
/*          boxShadow: "-1px -1px 55px -16px ", */
        }}> 
           <Menu/>
            <div className="w-full flex justify-end tabla_botonera p-3">
                <Tooltip title={"Borrado Multiple"} >
                    {productosSeleccionados.length > 0 ?
                        <Button className="bg-red-800 btn-bordo-custom text-white" onClick={() => onDelete()}> <DeleteFilled />Borrado Multiple</Button> :
                        <Button disabled><DeleteFilled/>Borrado Multiple</Button>
                    }
                </Tooltip>
            </div>
            <Table
                size="small"
                className="overflow-x-scroll"
                rowKey={(product) => product.id}
                dataSource={dataSourse}
                sortDirections={["ascend", "descend"]}
                columns={columns}
                pagination={defaultPagination(dataSourse, 15)}
                rowSelection={{
                    selectedRowKeys: productosSeleccionados,
                    onChange: onSelectProductos,
                }}
                locale={{
                    emptyText: "No se encontraron Productos",
                }}
            />
        </div>
    )
}

export default TablaProductos