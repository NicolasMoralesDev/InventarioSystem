import { Button, Table, Tooltip } from "antd";
import "./estilos/tablaProductos.css"
import { defaultPagination } from "../../Hooks/util/DefaultPagination";
import { useState } from "react";

const TablaProductos = (props) => {

    // eslint-disable-next-line react/prop-types
    const { dataSourse, onBorrado } = props
    const [productosSeleccionados, setProductosSeleccionados] = useState([])

    const onSelectProductos = (productsSelected) => {
        setProductosSeleccionados(productsSelected)
    }

    const onDelete = () => {
        onBorrado(productosSeleccionados)
    }

    const columns = [
        {
            title: 'Codigo',
            dataIndex: 'codigo',
            width: "13%",
            key: 'codigo',
        },
        {
            title: 'Nombre',
            dataIndex: 'name',
            width: "13%",
            key: 'name',
        },
        {
            title: 'Imagen',
            dataIndex: 'img',
            width: "10%",
            key: 'img',
        },
        {
            title: 'Descripcion',
            dataIndex: 'descripcion',
            width: "15%",
            key: 'descripcion',
        },
        {
            title: 'Marca',
            dataIndex: 'marca',
            width: "10%",
            key: 'marca',
        },
        {
            title: 'Precio',
            dataIndex: 'precio',
            width: "10%",
            key: 'precio',
            sorter: (a, b) => a.precio - b.precio,
            render: (precio) => <>{`$ ${precio}`}</>
        },
        {
            title: 'Cantidad',
            dataIndex: 'cant',
            width: "10%",
            sorter: (a, b) => a.cantidad - b.cantidad,
            key: 'cant',
        },
        {
            title: 'Generar Informe',
            dataIndex: '',
            width: "10%",
            key: 'generarInforme',
            render: () => <>
                <Button title="Generar PDF" className="bg-green-600 text-white">Pdf</Button>
                <Button title="Generar EXCEL" className="bg-slate-800 text-white">Excel</Button>
            </>
        },
        {
            title: 'Acciones',
            dataIndex: 'Acciones',
            width: "10%",
            key: 'acciones',
            render: () => <>
                <Button title="Editar Producto" className="bg-green-600 text-white">Editar</Button>
                <Button title="Ver Producto" className="bg-slate-800 text-white">Ver</Button>
            </>
        },
    ];

    return (
        <>
        <div className="w-full flex justify-end">
            <Tooltip title={"Borrado Multiple"} >
                {productosSeleccionados.length > 0 ?
                    <Button onClick={() => onDelete()}>Borrado Multiple</Button> :
                    <Button disabled >Borrado Multiple</Button>
                }
            </Tooltip>
        </div>
            <Table
                size="small"
                className="overflow-x-scroll"
                rowKey={(product) => product.id}
                dataSource={dataSourse}
                columns={columns}
                pagination={defaultPagination(dataSourse, 15)}
                rowSelection={{
                    selectedRowKeys: productosSeleccionados,
                    onChange: onSelectProductos,
                }}
                locale={{
                    emptyText: "No se encontraron Productos"
                }}
            />
        </>
    )
}

export default TablaProductos